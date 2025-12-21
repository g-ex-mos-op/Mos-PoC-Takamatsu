package jp.co.isid.mos.bird.bizreport.moscardniporef.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.moscardniporef.action.MoscardNipoRefConditionAction;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ACTION【MOSCARD初期画面】クラス
 * 
 * @author   xyamauchi
 */
public class MoscardNipoRefConditionActionImpl extends CsvOutput2ActionImpl implements MoscardNipoRefConditionAction {
    /** アクション【初期化処理】ID */
    public static final String initialize_ACTION_ID   = "BBR015A01";
    /** アクション【実行】ID */
    public static final String search_ACTION_ID   = "BBR015A02";
    /** アクション【SV検索ボタン】ID */
    public static final String callSvForm_ACTION_ID   = "BBR015A03";
    /** アクション【営業月報抽出CSVダウンロード】ID */
    public static final String downloadCsvGepo_ACTION_ID   = "BBR015A04";

    /** BIRD共通DTO【SV選択画面】 */    
    private SvSearchDto newSvSearchDto;

    /** 日報共通LOGIC【条件部情報取得】 */
    private ConditionLogic conditionLogic;
    /** 日報共通DTO【条件部情報】 */
    private NipoRefConditionDto nipoRefConditionDto;
    /** 日報共通DTO【検索条件】*/
    private NipoRefConditionParameterDto searchParamDto;
    /** 日報共通DTO【結果条件】*/
    private NipoRefConditionParameterDto resultParamDto;

	/**
     * アクション【初期化処理】
     */
    public String initialize() {

        //１．プルダウンメニューから遷移された場合、下記の処理を行います。
        if (getPullDownMenuDto().isClearFlg()) {
            //1.BIRD共通DTO【メニュープルダウン情報】.クリアフラグへfalseを設定します。
        	getPullDownMenuDto().setClearFlg(false);
            //2.日報共通LOGIC【条件部情報取得】.実行を実行します。
            Map map = getConditionLogic().execute(getBirdUserInfo().getMstUser().getUserTypeCd(), getBirdUserInfo().getUserID(), getBirdDateInfo().getAppDate());
            //3.日報共通DTO【条件部情報】.CTRL【BIRD日付情報】へコンテナから取得したCTRL【BIRD日付情報】を設定します。
            getNipoRefConditionDto().setBirdDataInfo(getBirdDateInfo());
            //4.日報共通DTO【条件部情報】.CTRL【BIRDログインユーザー情報】へコンテナから取得したCTRL【BIRDログインユーザー情報】を設定します。
            getNipoRefConditionDto().setBirdUserInfo(getBirdUserInfo());
            //5.日報共通DTO【条件部情報】.条件情報設定を実行し、戻り値windowIdを取得します。
            int windowId = getNipoRefConditionDto().initialize(map);
            //6.日報共通DTO【検索条件】.ウィンドウIDへ処理5の戻り値windowIdを設定します。
            searchParamDto.setWindowId(windowId);
            //7.日報共通DTO【検索条件】へデフォルト値を設定します。
            getNipoRefConditionDto().copyDefaultParamerter(searchParamDto);
        }
        /*２．SV選択画面から遷移してきた場合。2008/12/09追加 **************************/
        else if(getNewSvSearchDto().getReturnKind() != SvSearchDto.RETURNKIND_INIT){
        	//1.Try開始 SV選択画面からの値などを設定します。
        	try {
	            //SV-1．BIRD共通DTO【SV選択画面】.ウィンドウIDを取得します。
	        	int windowId = getNewSvSearchDto().getWindowId();
	        	//SV-2．処理SV-1のウィンドウIDをキーに日報共通DTO【条件部情報】からDTO【検索条件情報】を取得します。
	        	//SV-3．日報共通DTO【検索条件】へ処理SV-2のDTO【検索条件情報】を設定します。
	        	setNipoRefSearchParameterDto(
	        			(NipoRefConditionParameterDto) getNipoRefConditionDto().getSearchParameterDto(windowId));
	        	//SV-4．SV-1のウィンドウIDをキーに日報共通DTO【条件部情報】からDTO【結果条件情報】を取得します。
	        	//SV-5．DTO【結果条件】へ処理２のDTO【結果条件情報】を設定します。
	        	setNipoRefResultParameterDto(
	        			(NipoRefConditionParameterDto) getNipoRefConditionDto().getResultParameterDto(windowId));
	            //SV-6．SVを選択後遷移してきた場合。
	            if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
	                //SV-SELECT-1．日報共通DTO【検索条件】.SVコードへBIRD共通DTO【SV選択画面】.SVコードを設定します。
	                searchParamDto.setSvCd(getNewSvSearchDto().getSvCd());
	            	//SV-SELECT-2．日報共通DTO【結果条件】へ日報共通DTO【条件条件】を設定します。
	            	resultParamDto = searchParamDto;
	            	//SV-SELECT-3．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
	                NipoRefUtil.callScreenInitialize(
	                		getNipoRefConditionDto(), searchParamDto, resultParamDto, false);
	                //SV-SELECT-4.店舗別画面VIEW_IDをリターンします。(MOSCARD店舗別画面へ遷移)
	                return NipoRefConstants.VIEW_ID_M_MISE;
	            }
        	}
        	//2.FINALLYで下記の処理を行います。
        	finally {
                //FINAL-1．BIRD共通DTO【SV選択画面】.遷移区分を初期値に戻す。
                getNewSvSearchDto().setReturnKind(SvSearchDto.RETURNKIND_INIT);
                //FINAL-2．BIRD共通DTO【SV選択画面】.クリア処理を実行します。
                getNewSvSearchDto().clear();
            }
        }

        //３．nullをリターンします。(現行画面へ遷移)
        return null;
    }
    
    /**
     * アクション【実行】処理
     */
    public String search() {
    	//１．日報共通DTO【結果条件】へ日報共通DTO【条件条件】を設定します。
    	resultParamDto = searchParamDto;
    	//２．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, false);
        //３．日報共通DTO【条件部情報】.ユーザータイプコード＝"本部"の場合、
        if (UserType.isHonbu(getNipoRefConditionDto().getUserTypeCd())) {
            //1．日報共通DTO【条件条件】.集計区分で『SV指定(担当店一覧)』が選択された場合は、下記の処理を行います。
            
            if(searchParamDto.isSvFlg()){    
            	//SV-1．店一覧画面へ遷移する。
                searchParamDto.setTaishoTenpoCd(TaishoTenpo.ALL);
                return NipoRefConstants.VIEW_ID_M_MISE;
            }
	    	//2.支部別結果画面VIEW_IDをリターンします。
	        return NipoRefConstants.VIEW_ID_M_SIBU;
    	}
    	//４．日報共通DTO【条件部情報】.ユーザータイプコード＝"オーナー"の場合、店舗別結果画面(オーナー用)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_M_ONER_MISE;
   }
    /**
     * アクション【営業月報抽出CSVダウンロード】
     */
    public void downloadCsvGepo() {
    	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), searchParamDto);
    	//２．Try開始　ダウンロード処理を行います。
        try {
        	//1.継承クラス.ダウンロード メイン処理を実行します。
            super.downloadCsv();
        }
        //３．NoResultExceptionをキャッチし、下記の処理を行います。
	    catch (NoResultException noResult) {
	        //EX-0.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	searchParamDto.setResearchFlg(true);
	        //EX-1.NoResultExceptionを発生させます。
            //該当データがありません。のメッセージを表示します。
	    	throw noResult;
        }
	    //４．IOExceptionをキャッチし、下記の処理を行います。
	    catch (IOException e) {
        	//EX1.FtlSystemExceptionを発生させます。
            throw new FtlSystemException(NipoRefConstants.MSG_CSV_DOWNLOAD, "", e);
        }
    }
   
    /**
     * アクション【SV検索ボタン】処理
     * 
     * @return SV検索フォームViewID
     */
    public String callSvForm()  {
        //１．BIRD共通DTO【SV選択画面】.遷移元情報へ初期画面VIEW_IDを設定します。
        getNewSvSearchDto().setNavigationCase(NipoRefConstants.VIEW_ID_M_CONDITION);
        //２．BIRD共通DTO【SV選択画面】.初期化フラグへtrueを設定します。
        getNewSvSearchDto().setInitFlag(true);
        //３．BIRD共通DTO【SV選択画面】.ウィンドウID　へ 日報共通DTO【検索条件】.ウィンドウIDを設定します。
        getNewSvSearchDto().setWindowId(searchParamDto.getWindowId());
        //４．List[[会社]]を作成し日報共通DTO【検索条件】.会社コードを設定します。
        List listCompany = new ArrayList();
        listCompany.add(searchParamDto.getCompanyCd());
        //５．BIRD共通DTO【SV選択画面】.List[[会社]]へ処理４のList[[会社]]を設定します。
        getNewSvSearchDto().setRCompanyCdList(listCompany);
        //６．日報共通DTO【条件部情報】.DTO【検索条件情報】へ日報共通DTO【検索条件】の保管を行う。
        getNipoRefConditionDto().setSearchParameterDto(searchParamDto);
        //７．日報共通DTO【条件部情報】.DTO【検索結果情報】へ日報共通DTO【結果条件】の保管を行う。
        getNipoRefConditionDto().setResultParameterDto(resultParamDto);        
        //８．SV選択画面遷移VIEWIDをリターンする。
        return NipoRefConstants.VIEW_ID_SVSEARCH;
    }
    /**
     * コンテナ取得処理
     * @return
     */
    private S2Container getS2Container() {
    	return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * CTRL【BIRD日付情報】取得処理
     * @return
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * CTRL【BIRDログインユーザー情報】取得処理
     * @return
     */
    private BirdUserInfo getBirdUserInfo() {
    	return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRD共通DTO【メニュープルダウン情報】取得処理
     * @return
     */
    private PullDownMenuDto getPullDownMenuDto() {
    	return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    /**
     * 日報共通DTO【条件部情報】を取得する
     * @return NipoRefConditionDto 日報共通DTO【条件部情報】
     */
    public NipoRefConditionDto getNipoRefConditionDto() {
        return nipoRefConditionDto;
    }
    
    /**
     * 日報共通DTO【条件部情報】を設定する
     * @param nipoRefConditionDto 日報共通DTO【条件部情報】
     */
    public void setNipoRefConditionDto(NipoRefConditionDto nipoRefConditionDto) {
        this.nipoRefConditionDto = nipoRefConditionDto;
    }

    /**
     * 日報共通LOGIC【条件部情報取得】を取得する
     * @return 日報共通LOGIC【条件部情報取得】
     */
    public ConditionLogic getConditionLogic() {
        return conditionLogic;
    }

    /**
     * 日報共通LOGIC【条件部情報取得】を設定する
     * @param conditionLogic 日報共通LOGIC【条件部情報取得】
     */
    public void setConditionLogic(ConditionLogic conditionLogic) {
        this.conditionLogic = conditionLogic;
    }

    public SvSearchDto getNewSvSearchDto() {
        return newSvSearchDto;
    }

    public void setNewSvSearchDto(SvSearchDto newSvSearchDto) {
        this.newSvSearchDto = newSvSearchDto;
    }

	/**
	 * 日報共通DTO【検索条件】
	 * 
	 * @return クラス変数resultParamDto を戻します。
	 */
	public NipoRefConditionParameterDto getNipoRefSearchParameterDto() {
		return searchParamDto;
	}

	/**
	 * 日報共通DTO【検索条件】
	 * 
	 * @param parameterDto を クラス変数searchParamDtoへ設定します。
	 */
	public void setNipoRefSearchParameterDto(
			NipoRefConditionParameterDto parameterDto) {
		this.searchParamDto =parameterDto;
	}

	/**
	 * 日報共通DTO【結果条件】
	 * @return クラス変数resultParamDto を戻します。
	 */
	public NipoRefConditionParameterDto getNipoRefResultParameterDto() {
		return resultParamDto;
	}

	/**
	 * 日報共通DTO【結果条件】
	 * @param parameterDto を クラス変数resultParamDtoへ設定します。
	 */
	public void setNipoRefResultParameterDto(
			NipoRefConditionParameterDto parameterDto) {
		this.resultParamDto = parameterDto;
	}
    
    // MOSCARD用会社リスト
    public List getMoscardCompanyList() {
        List moscardList = new ArrayList();
        try {
             // 営業日報用会社リストから会社モスフードサービス(00)を抜き出して MOSCARD用会社リストを作成
             for (int i = 0; i <getNipoRefConditionDto().getCompanyList().size(); i++) {
                 CodCompany company = (CodCompany)getNipoRefConditionDto().getCompanyList().get(i);
                 if (company.getCompanyCd().equals(CommonUtil.COMPANY_CD_MOS)) {
                      moscardList.add(company);
                      break;
                 }
          
             }    
        } catch (NullPointerException e) {
            // TODO: handle exception
            
        }
        return moscardList;
        
    }
}