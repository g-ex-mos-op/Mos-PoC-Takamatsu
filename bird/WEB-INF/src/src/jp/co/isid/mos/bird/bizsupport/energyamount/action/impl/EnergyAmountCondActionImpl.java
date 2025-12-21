package jp.co.isid.mos.bird.bizsupport.energyamount.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.energyamount.action.EnergyAmountAction;
import jp.co.isid.mos.bird.bizsupport.energyamount.common.EnergyAmountConst;
import jp.co.isid.mos.bird.bizsupport.energyamount.dto.EnergyAmountRequestDto;
import jp.co.isid.mos.bird.bizsupport.energyamount.dto.EnergyAmountSessionDto;
import jp.co.isid.mos.bird.bizsupport.energyamount.dto.SuiiRequestDto;
import jp.co.isid.mos.bird.bizsupport.energyamount.dto.SuiiSessionDto;
import jp.co.isid.mos.bird.bizsupport.energyamount.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

/**
 * エネルギー使用量ダウンロード アクション
 * @author xnkusama
 * 
 * 更新日:2010/05/14 xkinu エネルギーデータ推移表追加対応
 */
public class EnergyAmountCondActionImpl implements EnergyAmountAction {
    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = "BBS032A01";
    public static final String downloadCsv_ACTION_ID = "BBS032A02";
    public static final String callMiseForm_ACTION_ID = "BBS032A03";

   private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    private PullDownMenuDto pullDownMenuDto;
    /*【DTO】店検索DTO */
    private MiseSearchDto miseSearchDto;
    /* ACTION */
    private CsvOutputAction energyamountCsvAction;
    /* LOGIC */
    private ConditionInfoLogic energyamountConditionInfoLogic;
    /* DTO */
    private EnergyAmountRequestDto energyamountRequestDto;
    private EnergyAmountSessionDto energyamountSessionDto;
    
    /** 推移表ダウンロードセッション情報保持DTO */
    private SuiiSessionDto energyamountSuiiSesDto;
    /** 推移表ダウンロードリクエスト情報保持DTO */
    private SuiiRequestDto energyamountSuiiReqDto;
    
    public String initialize() {

        //メニューからの遷移
        if (getPullDownMenuDto().isClearFlg()) {
            //ユーザータイプコード
            getEnergyamountSessionDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            
            //条件画面情報の設定
            setCondInfo();
            
            //DTO初期情報設定
            initDto();
            
            getPullDownMenuDto().setClearFlg(false);
        }
        //２．店舗選択画面から遷移したきた場合。
        else if(MiseSearchDto.RETURNKIND_INIT != getMiseSearchDto().getReturnKind()) {
            try {
                //1．DTO【自画面Request用】．ウィンドウIDへDTO【店舗選択】.ウィンドウIDを設定します。
                setEnergyamountRequestDto((EnergyAmountRequestDto)getEnergyamountSessionDto().getHoldRequestDto());
                setEnergyamountSuiiReqDto((SuiiRequestDto)getEnergyamountSuiiSesDto().getHoldRequestDto());
                
                //2．店舗を選択後遷移してきた場合。
                if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getMiseSearchDto().getMiseCd();
                    //2-1．DTO【自画面Request】.表示対象へ共通DTO【店舗選択】.店舗コードを設定します。
                    getEnergyamountSuiiReqDto().setTaishoCd(selectedCd);
                    getEnergyamountSuiiReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                }
            }
            //4．finally処理で下記の処理を行います。
            finally {
                //4-2．DTO【店舗選択】.遷移区分を初期値に戻します。
                getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
                //4-3．DTO【店舗選択】.クリア処理を実行します。
                getMiseSearchDto().clear();
            }
        }
        return null;
    }

    /**
     * CSVダウンロードアクション
     * @return
     */
    public String downloadCsv() {
        try {
            getEnergyamountRequestDto().setUserId(getBirdUserInfo().getUserID());
            getEnergyamountRequestDto().setUserTypeCd(getEnergyamountSessionDto().getUserTypeCd());
            getEnergyamountRequestDto().setSysDate(getBirdDateInfo().getSysDate());
            getEnergyamountRequestDto().setLimitFlg(getBirdUserInfo().isLimit());
            
            getEnergyamountCsvAction().downloadCsv();
        }
        catch (ApplicationException aex) {
            throw aex;
        }
        catch (Exception ex) {
            throw new FtlSystemException("ダウンロード", null, ex);
        }
        
        return null;
    }

    /**
     * 条件画面情報の設定
     * 
     * 更新日:2010/05/14 xkinu エネルギーデータ推移表追加対応
     */
    private void setCondInfo() {
    	String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        //条件画面情報の設定
        Map mapCond = getEnergyamountConditionInfoLogic().execute(getEnergyamountRequestDto().getCompanyCd(), getBirdDateInfo().getSysDate(), getBirdUserInfo());
        if(UserType.isHonbu(userTypeCd)) {
        	getEnergyamountSessionDto().setListTaishoJoken((List) mapCond.get(ConditionInfoLogic.MAP_KEY_SIBU));
        }
        getEnergyamountSessionDto().setListNoInputTaishoNengetu((List) mapCond.get(ConditionInfoLogic.MAP_KEY_NOINPUT_NENGETU));
        getEnergyamountSessionDto().setListTaishoNengetu((List) mapCond.get(ConditionInfoLogic.MAP_KEY_ENERGY_NENGETU));
        getEnergyamountSessionDto().setListTaishoNendo((List) mapCond.get(ConditionInfoLogic.MAP_KEY_ENERGY_NENDO));
        getEnergyamountSessionDto().setListTaishoKikan((List) mapCond.get(ConditionInfoLogic.MAP_KEY_TAISHO_KIKAN));
        getEnergyamountSessionDto().setListNoInputTaishoKikan((List) mapCond.get(ConditionInfoLogic.MAP_KEY_NOINPUT_TAISHO_KIKAN));
        getEnergyamountSessionDto().setListMeterKbn((List) mapCond.get(ConditionInfoLogic.MAP_KEY_METER_KBN));
        getEnergyamountSessionDto().setListNoInputMeterKbn((List) mapCond.get(ConditionInfoLogic.MAP_KEY_NOINPUT_METER_KBN));
        //エネルギーデータ推移表用 条件項目情報設定処理
        getEnergyamountSuiiSesDto().setListNendo((List) mapCond.get(ConditionInfoLogic.MAP_KEY_SUII_NENDO));
        if(UserType.isHonbu(userTypeCd)) {
	        getEnergyamountSuiiSesDto().setListTaishoJoken((List) mapCond.get(ConditionInfoLogic.MAP_KEY_SUII_TAISHO_JOKEN));
	        getEnergyamountSuiiSesDto().setListSibuCd((List) mapCond.get(ConditionInfoLogic.MAP_KEY_SUII_SIBU));
        }

        mapCond.clear();
    }
    
    /**
     * DTO初期情報設定
     */
    private void initDto() {
        //対象データ初期値設定 初期値：対象データ
        getEnergyamountRequestDto().setTaishoData(EnergyAmountConst.TAISHO_DATA_LIST);
        //対象データ初期値設定 初期値：エネルギー使用量一覧用対象期間
        getEnergyamountRequestDto().setTaishoKikan(EnergyAmountConst.TAISHO_KIKAN_NENGETU);
        //対象データ初期値設定 初期値：未入力店舗一覧用対象期間
        getEnergyamountRequestDto().setTaishoKikanNoinput(EnergyAmountConst.TAISHO_KIKAN_NENGETU);
        //対象データ初期値設定 初期値：推移表用対象期間
        getEnergyamountSuiiReqDto().setTaishoKikan(EnergyAmountConst.TAISHO_KIKAN_NENDO);
        //対象データ初期値設定 初期値：推移表用対象条件
        getEnergyamountSuiiReqDto().setTaishoJoken(TaishoJoken.CODE_SIBU);
    }
    /**
     * 店検索フォーム呼び出し処理
     * @return 店検索フォームViewID
     */
    public String callMiseForm() {
        //１．共通DTO【店舗選択】遷移元情報へ初期画面VIEWIDを設定します。
        getMiseSearchDto().setNavigationCase("BBS032V01");
        //２．共通DTO【店舗選択】初期化フラグへtrueを設定します。
        getMiseSearchDto().setInitialFlag(true);
        //３．共通DTO【店舗選択】遷移区分要否フラグへtrueを設定します。
        getMiseSearchDto().setNeedReturnKind(true);
        //４．共通DTO【店舗選択】会社コードリストへ対象会社コードを保持したListを設定します。
        List listCompany = new ArrayList();
        listCompany.add(getEnergyamountSuiiReqDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //５．現ウィンドウID の検索対象条件項目値の保管を行います。
        getEnergyamountSessionDto().setHoldRequestDto(getEnergyamountRequestDto());
        getEnergyamountSuiiSesDto().setHoldRequestDto(getEnergyamountSuiiReqDto());
        //６．選択画面遷移VIEWIDをリターンします。
        return CommonUtil.VIEW_ID_MISESEARCH;
    }
    
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public ConditionInfoLogic getEnergyamountConditionInfoLogic() {
        return energyamountConditionInfoLogic;
    }

    public void setEnergyamountConditionInfoLogic(
            ConditionInfoLogic energyamountConditionInfoLogic) {
        this.energyamountConditionInfoLogic = energyamountConditionInfoLogic;
    }

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public EnergyAmountRequestDto getEnergyamountRequestDto() {
        return energyamountRequestDto;
    }

    public void setEnergyamountRequestDto(
            EnergyAmountRequestDto energyamountRequestDto) {
        this.energyamountRequestDto = energyamountRequestDto;
    }

    public EnergyAmountSessionDto getEnergyamountSessionDto() {
        return energyamountSessionDto;
    }

    public void setEnergyamountSessionDto(
            EnergyAmountSessionDto energyamountSessionDto) {
        this.energyamountSessionDto = energyamountSessionDto;
    }

    public CsvOutputAction getEnergyamountCsvAction() {
        return energyamountCsvAction;
    }

    public void setEnergyamountCsvAction(CsvOutputAction energyamountCsvAction) {
        this.energyamountCsvAction = energyamountCsvAction;
    }

	/**
	 * @return クラス変数energyamountSuiiReqDto を戻します。
	 */
	public SuiiRequestDto getEnergyamountSuiiReqDto() {
		return energyamountSuiiReqDto;
	}

	/**
	 * @param energyamountSuiiReqDto を クラス変数energyamountSuiiReqDtoへ設定します。
	 */
	public void setEnergyamountSuiiReqDto(SuiiRequestDto energyamountSuiiReqDto) {
		this.energyamountSuiiReqDto = energyamountSuiiReqDto;
	}

	/**
	 * @return クラス変数energyamountSuiiSesDto を戻します。
	 */
	public SuiiSessionDto getEnergyamountSuiiSesDto() {
		return energyamountSuiiSesDto;
	}

	/**
	 * @param energyamountSuiiSesDto を クラス変数energyamountSuiiSesDtoへ設定します。
	 */
	public void setEnergyamountSuiiSesDto(SuiiSessionDto energyamountSuiiSesDto) {
		this.energyamountSuiiSesDto = energyamountSuiiSesDto;
	}

	/**
	 * @return クラス変数miseSearchDto を戻します。
	 */
	public MiseSearchDto getMiseSearchDto() {
		return miseSearchDto;
	}

	/**
	 * @param miseSearchDto を クラス変数miseSearchDtoへ設定します。
	 */
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}

}
