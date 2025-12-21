/*
 * 作成日: 2006/04/10
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.plinfoview.action.PlInfoViewResultAction;
import jp.co.isid.mos.bird.bizsupport.plinfoview.dto.PlInfoViewDto;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetPLInfoLogic;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetSibuCategoryLogic;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetUserOnerLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * P/L照会画面アクション
 * @author xnkusama
 */
public class PlInfoViewResultActionImpl implements PlInfoViewResultAction, CommonAction  {

    /* アクションID */
    public static final String initialize_ACTION_ID = "BBS006A11";
    public static final String research_ACTION_ID     = "BBS006A12";
    public static final String callMiseForm_ACTION_ID = "BBS006A13";
    public static final String callOnerForm_ACTION_ID = "BBS006A14";
    public static final String dispGenkaInfo_ID     = "BBS006A15";
    public static final String back_ACTION_ID     = "BBS006A16";
    public static final String downloadCsv_ACTION_ID     = "BBS006A17";
    /* ビューID */
    private static final String VIEWID_CONDITION    = "BBS006V01";
    private static final String VIEWID_RESULT       = "BBS006V02";
    private static final String VIEWID_GENKAINFO    = "BBS006V03";
    private static final String VIEWID_RESULT_RC    = "BBS006V04";
    private static final String VIEWID_MISESEARCH   = "BCO008V01";//店選択
    private static final String VIEWID_ONERSEARCH   = "BCO006V01";//オーナー選択検索
    /* ユーザータイプ */
    private static final String USER_TYPE_HONBU = "01";
    private static final String USER_TYPE_ONER  = "02";
    private static final String USER_TYPE_MISE  = "03";
    /* LOGIC */
    private GetSibuCategoryLogic getSibuCategoryLogic;
    private GetUserOnerLogic getUserOnerLogic;
    private GetPLInfoLogic getPlInfoLogic;
    /* DTO */
    private PlInfoViewDto plInfoViewDto;
    private PlInfoViewDto plInfoViewCommonDto;
    private MiseSearchDto miseSearchDto;
    private OwnerSearchDto ownerSearchDto;
    /* ACTION */
    private CsvOutputAction plInfoViewCsvOutputAction;
    /* 別ウィンドウチェック状態 */
    private boolean newWindowFlg;
    
    /* 条件画面 対象年月表示月数 */
    private static final int NENGETU_DISPLAY_MONTH = 25;
    /**
     * 再検索
     * @return
     */
    public String research() {
        // 別ウィンドウ判定
        if (isNewWindowFlg()) {
            // ウィンドウID生成
            getPlInfoViewCommonDto().updateWindowid();
            // 別ウィンドウチェック状態OFF
            setNewWindowFlg(false);
        }
        
        // 検索結果をクリア
        getPlInfoViewDto().setEntityTougetu(null);
        getPlInfoViewDto().setEntityZennen(null);
        getPlInfoViewDto().setListEntityTogetuPlusZennen(null);
        
        String sysDt = getBirdDateInfo().getSysDate();
        String appDt = getBirdDateInfo().getAppDate();
        
        // 共通情報コピー
        getPlInfoViewDto().setUserTypeCd(getPlInfoViewCommonDto().getUserTypeCd());
        getPlInfoViewDto().setLimitKbn(getPlInfoViewCommonDto().isLimitKbn());

        // 検索条件を保存
        getPlInfoViewCommonDto().setCondTargetYM(getPlInfoViewDto().getCondTargetYM());
        getPlInfoViewCommonDto().setCondTaishoTenpo(getPlInfoViewDto().getCondTaishoTenpo());
        getPlInfoViewCommonDto().setCondTaishoTenpoOner(getPlInfoViewDto().getCondTaishoTenpoOner());
        getPlInfoViewCommonDto().setCondCompanyCd(getPlInfoViewDto().getCondCompanyCd());
        getPlInfoViewCommonDto().setCondJigyouCd(getPlInfoViewDto().getCondJigyouCd());
        getPlInfoViewCommonDto().setCondSlareaCd(getPlInfoViewDto().getCondSlareaCd());
        getPlInfoViewCommonDto().setCondSibuCd(getPlInfoViewDto().getCondSibuCd());
        getPlInfoViewCommonDto().setCondBlockCd(getPlInfoViewDto().getCondBlockCd());
        getPlInfoViewCommonDto().setCondMiseCd(getPlInfoViewDto().getCondMiseCd());
        getPlInfoViewCommonDto().setCondMiseCdOner(getPlInfoViewDto().getCondMiseCdOner());
        getPlInfoViewCommonDto().setCondMiseName(getPlInfoViewDto().getCondMiseName());
        getPlInfoViewCommonDto().setCondOnerCd(getPlInfoViewDto().getCondOnerCd());
        getPlInfoViewCommonDto().setCondOnerName(getPlInfoViewDto().getCondOnerName());
        getPlInfoViewCommonDto().setCondTenpoShu(getPlInfoViewDto().getCondTenpoShu());
        getPlInfoViewCommonDto().setCondSearchType(getPlInfoViewDto().getCondSearchType());
        getPlInfoViewCommonDto().setCsvMode(getPlInfoViewDto().getCsvMode());
// add start xkhata 2007-02-07
        getPlInfoViewDto().setUriageFlg(getPlInfoViewCommonDto().getUriageFlg());
// add end
        //---2006/05/22 add 最終検索時の対象店舗を保持（店、オーナー検索時の対処）
        getPlInfoViewDto().setCondLastSearchTaishoTenpo(getPlInfoViewDto().getCondTaishoTenpo());
        getPlInfoViewCommonDto().setCondLastSearchTaishoTenpo(getPlInfoViewDto().getCondLastSearchTaishoTenpo());
        
        // 検索ロジック実行
        getGetPlInfoLogic().execute(getPlInfoViewDto(), sysDt, appDt, getBirdUserInfo().getUserID());
        
        // 検索ロジックにて設定されるFC/RCモードを保存
        getPlInfoViewCommonDto().setResultFcRC(getPlInfoViewDto().isResultFcRc());

        if (getPlInfoViewDto().isResultFcRc()) {
            return VIEWID_RESULT_RC;
        }
        else {
            return VIEWID_RESULT;
        }
    }

    /**
     * 初期アクション
     * @return
     */
    public String initialize() {

        if (getPlInfoViewCommonDto().isCallFormFlag()) {
            // 再検索
            searchPlInfo();
            // 検索条件復帰
            loadSearchCondition();
            getPlInfoViewCommonDto().setCallFormFlag(false);
        }
        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg()) {
            getPlInfoViewDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }
        // オーナ選択結果取得
        if (getOwnerSearchDto().isActionFlag()) {
            getPlInfoViewDto().setCondOnerCd(getOwnerSearchDto().getOnerCd());
            ownerSearchDto.setActionFlag(false);
        }
        return null;
    }

    /**
     * 店検索フォーム
     * @return
     */
    public String callMiseForm() {
        if (getPlInfoViewCommonDto().isResultFcRc()) {
            getMiseSearchDto().setNavigationCase(VIEWID_RESULT_RC);
        }
        else {
            getMiseSearchDto().setNavigationCase(VIEWID_RESULT);
        }
        getMiseSearchDto().setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getBirdUserInfo().getMstUser().getRCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        // 検索条件退避
        saveSearchCondition();
        // 共通フォーム呼出フラグON
        getPlInfoViewCommonDto().setCallFormFlag(true);
        return VIEWID_MISESEARCH;
    }

    /**
     * オーナー検索フォーム
     * @return
     */
    public String callOnerForm() {
        getOwnerSearchDto().clear();
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        if (getPlInfoViewCommonDto().isResultFcRc()) {
            getOwnerSearchDto().setNavigationCase(VIEWID_RESULT_RC);
        }
        else {
            getOwnerSearchDto().setNavigationCase(VIEWID_RESULT);
        }
        getOwnerSearchDto().setInitFlag(true);
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(getBirdUserInfo().getMstUser().getRCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        // 検索条件退避
        saveSearchCondition();
        // 共通フォーム呼出フラグON
        getPlInfoViewCommonDto().setCallFormFlag(true);
        return VIEWID_ONERSEARCH;
    }

    /**
     * 原価情報表示
     * @return
     */
    public String dispGenkaInfo() {
        searchPlInfo();
        return VIEWID_GENKAINFO;
    }
    
    /**
     * 戻るボタン（原価情報画面-->FC結果画面）
     * @return
     */
    public String back() {
        searchPlInfo();
    	return VIEWID_RESULT;
    }

    /**
     * CSVダウンロード
     * @return
     */
    public String downloadCsv() {
        // CSVモード設定
        getPlInfoViewCommonDto().setCsvMode(getPlInfoViewDto().getCsvMode());
        
// add start xkhata 2007-02-07
        getPlInfoViewDto().setUriageFlg(getPlInfoViewCommonDto().getUriageFlg());
// add end
        // CSVダウンロード
        try {
            getPlInfoViewCsvOutputAction().downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        return null;
    }

    private void searchPlInfo() {
        String sysDt = getBirdDateInfo().getSysDate();
        String appDt = getBirdDateInfo().getAppDate();

        // 共通情報コピー
        getPlInfoViewDto().setUserTypeCd(getPlInfoViewCommonDto().getUserTypeCd());
        getPlInfoViewDto().setLimitKbn(getPlInfoViewCommonDto().isLimitKbn());

        // 検索条件コピー
        getPlInfoViewDto().setCondTargetYM(getPlInfoViewCommonDto().getCondTargetYM());
        getPlInfoViewDto().setCondTaishoTenpo(getPlInfoViewCommonDto().getCondTaishoTenpo());
        getPlInfoViewDto().setCondTaishoTenpoOner(getPlInfoViewCommonDto().getCondTaishoTenpoOner());
        getPlInfoViewDto().setCondCompanyCd(getPlInfoViewCommonDto().getCondCompanyCd());
        getPlInfoViewDto().setCondJigyouCd(getPlInfoViewCommonDto().getCondJigyouCd());
        getPlInfoViewDto().setCondSlareaCd(getPlInfoViewCommonDto().getCondSlareaCd());
        getPlInfoViewDto().setCondSibuCd(getPlInfoViewCommonDto().getCondSibuCd());
        getPlInfoViewDto().setCondBlockCd(getPlInfoViewCommonDto().getCondBlockCd());
        getPlInfoViewDto().setCondMiseCd(getPlInfoViewCommonDto().getCondMiseCd());
        getPlInfoViewDto().setCondMiseCdOner(getPlInfoViewCommonDto().getCondMiseCdOner());
        getPlInfoViewDto().setCondMiseName(getPlInfoViewCommonDto().getCondMiseName());
        getPlInfoViewDto().setCondOnerCd(getPlInfoViewCommonDto().getCondOnerCd());
        getPlInfoViewDto().setCondOnerName(getPlInfoViewCommonDto().getCondOnerName());
        getPlInfoViewDto().setCondTenpoShu(getPlInfoViewCommonDto().getCondTenpoShu());
        getPlInfoViewDto().setCondSearchType(getPlInfoViewCommonDto().getCondSearchType());
        getPlInfoViewDto().setCondLastSearchTaishoTenpo(getPlInfoViewCommonDto().getCondTaishoTenpo());

//// add start xkhata 2007/02/08
        getPlInfoViewDto().setUriageFlg(getPlInfoViewCommonDto().getUriageFlg());
//// add end
        // 検索ロジック実行
        getGetPlInfoLogic().execute(getPlInfoViewDto(), sysDt, appDt, getBirdUserInfo().getUserID());
    }

    private void saveSearchCondition() {
        // 検索条件退避
        getPlInfoViewCommonDto().setTempTargetYM(getPlInfoViewDto().getCondTargetYM());
        getPlInfoViewCommonDto().setTempTaishoTenpo(getPlInfoViewDto().getCondTaishoTenpo());
        getPlInfoViewCommonDto().setTempTaishoTenpoOner(getPlInfoViewDto().getCondTaishoTenpoOner());
        getPlInfoViewCommonDto().setTempJigyouCd(getPlInfoViewDto().getCondJigyouCd());
        getPlInfoViewCommonDto().setTempSlareaCd(getPlInfoViewDto().getCondSlareaCd());
        getPlInfoViewCommonDto().setTempSibuCd(getPlInfoViewDto().getCondSibuCd());
        getPlInfoViewCommonDto().setTempBlockCd(getPlInfoViewDto().getCondBlockCd());
        getPlInfoViewCommonDto().setTempMiseCd(getPlInfoViewDto().getCondMiseCd());
        getPlInfoViewCommonDto().setTempMiseCdOner(getPlInfoViewDto().getCondMiseCdOner());
        getPlInfoViewCommonDto().setTempOnerCd(getPlInfoViewDto().getCondOnerCd());
        getPlInfoViewCommonDto().setTempTenpoShu(getPlInfoViewDto().getCondTenpoShu());
        getPlInfoViewCommonDto().setTempSearchType(getPlInfoViewDto().getCondSearchType());
    }

    private void loadSearchCondition() {
        // 退避検索条件コピー
        getPlInfoViewDto().setCondTargetYM(getPlInfoViewCommonDto().getTempTargetYM());
        getPlInfoViewDto().setCondTaishoTenpo(getPlInfoViewCommonDto().getTempTaishoTenpo());
        getPlInfoViewDto().setCondTaishoTenpoOner(getPlInfoViewCommonDto().getTempTaishoTenpoOner());
        getPlInfoViewDto().setCondJigyouCd(getPlInfoViewCommonDto().getTempJigyouCd());
        getPlInfoViewDto().setCondSlareaCd(getPlInfoViewCommonDto().getTempSlareaCd());
        getPlInfoViewDto().setCondSibuCd(getPlInfoViewCommonDto().getTempSibuCd());
        getPlInfoViewDto().setCondBlockCd(getPlInfoViewCommonDto().getTempBlockCd());
        getPlInfoViewDto().setCondMiseCd(getPlInfoViewCommonDto().getTempMiseCd());
        getPlInfoViewDto().setCondMiseCdOner(getPlInfoViewCommonDto().getTempMiseCdOner());
        getPlInfoViewDto().setCondOnerCd(getPlInfoViewCommonDto().getTempOnerCd());
        getPlInfoViewDto().setCondTenpoShu(getPlInfoViewCommonDto().getTempTenpoShu());
        getPlInfoViewDto().setCondSearchType(getPlInfoViewCommonDto().getTempSearchType());
        getPlInfoViewDto().setCondLastSearchTaishoTenpo(getPlInfoViewCommonDto().getCondLastSearchTaishoTenpo());

        // 退避領域クリア
        getPlInfoViewCommonDto().setTempTargetYM(null);
        getPlInfoViewCommonDto().setTempTaishoTenpo(null);
        getPlInfoViewCommonDto().setTempTaishoTenpoOner(null);
        getPlInfoViewCommonDto().setTempJigyouCd(null);
        getPlInfoViewCommonDto().setTempSlareaCd(null);
        getPlInfoViewCommonDto().setTempSibuCd(null);
        getPlInfoViewCommonDto().setTempBlockCd(null);
        getPlInfoViewCommonDto().setTempMiseCd(null);
        getPlInfoViewCommonDto().setTempMiseCdOner(null);
        getPlInfoViewCommonDto().setTempOnerCd(null);
        getPlInfoViewCommonDto().setTempTenpoShu(null);
        getPlInfoViewCommonDto().setTempSearchType(null);
    }

    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
	private PullDownMenuDto getPullDownMenuDto() {
		return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
	}
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
	public PlInfoViewDto getPlInfoViewDto() {
		return plInfoViewDto;
	}
	public void setPlInfoViewDto(PlInfoViewDto plInfoViewDto) {
		this.plInfoViewDto = plInfoViewDto;
	}
    public PlInfoViewDto getPlInfoViewCommonDto() {
        return plInfoViewCommonDto;
    }
    public void setPlInfoViewCommonDto(
            PlInfoViewDto plInfoViewCommonDto) {
        this.plInfoViewCommonDto = plInfoViewCommonDto;
    }
	public MiseSearchDto getMiseSearchDto() {
		return miseSearchDto;
	}
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}
	public GetPLInfoLogic getGetPlInfoLogic() {
		return getPlInfoLogic;
	}
	public void setGetPlInfoLogic(GetPLInfoLogic getPlInfoLogic) {
		this.getPlInfoLogic = getPlInfoLogic;
	}
    public CsvOutputAction getPlInfoViewCsvOutputAction() {
        return plInfoViewCsvOutputAction;
    }
    public void setPlInfoViewCsvOutputAction(
            CsvOutputAction plInfoViewCsvOutputAction) {
        this.plInfoViewCsvOutputAction = plInfoViewCsvOutputAction;
    }
    public boolean isNewWindowFlg() {
        return newWindowFlg;
    }
    public void setNewWindowFlg(boolean newWindowFlg) {
        this.newWindowFlg = newWindowFlg;
    }

}
