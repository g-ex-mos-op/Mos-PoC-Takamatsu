/*
 * 作成日: 2006/09/12
 *
 */
package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dao.TenpoInfoDao;
import jp.co.isid.mos.bird.bizreport.common.entity.TenpoInfo;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.action.TakuhaiSuiiRefAction;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common.TakuhaiSuiiConstants;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dao.TotalMiseGepoCntDao;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dto.TakuhaiSuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TakuhaiSuiiGepoInfoLogic;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TakuhaiSuiiNipoInfoLogic;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TotalMiseCntGepoLogic;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TotalMiseCntNipoLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 宅配売上推移表、結果画面アクション<p/>
 * 
 * 2007/03/12 T.Kinugawa(ASPAC) 複数ウィンドウ機能対応の不備の対応<br/>
 * 　すでに検索済みのデータを再度検索するときに検索済み条件パラメーターの値が必要。<br/>
 * 現行では、リクエストされたものを使用し検索済みデータとは異なるデータの検索結果を表示していた。<br/>
 * そのため、検索済み条件パラメーターの値を保持する機能を追加し、この値で検索処理を行うよう修正した。<br/>
 * @modifier xkinu 2010/01/18 ADD <br/>
 *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。<br/>
 *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、<br/>
 *        システムエラーが発生することがなくなる為。<br/>
 * @modifier xkinu 2013/05/09 MOSCARD画面追加対応<br/>
 * 
 * @author xwatanabe
 */
public class TakuhaiSuiiRefActionImpl extends CsvOutput2ActionImpl implements TakuhaiSuiiRefAction {

    /** アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = "BBR004A01";
    /** アクションID：対象会社変更処理 */
    public static final String changeCompany_ACTION_ID = "BBR004A02";
    /** アクションID：検索処理 */
    public static final String search_ACTION_ID = "BBR004A03";
    /** アクションID：対象タブ変更処理 */
    public static final String callMiseForm_ACTION_ID = "BBR004A04";
    /** アクションID：売上推移画面切替処理 */
    public static final String callUriageSuii_ACTION_ID = "BBR004A05";
    /** アクションID：MOSCARD推移画面切替処理 */
    public static final String callMoscardSuii_ACTION_ID = "BBR004A06";
    /** アクションID：MOSCARD推移画面切替処理 */
    public static final String downloadCsv_ACTION_ID = "BBR004A07";
    /** アクションID：ネット注文推移画面切替処理 */
    public static final String callNetorderSuii_ACTION_ID = "BBR004A08";
    /** 業績管理共通DAO【店舗情報取得】*/
    private TenpoInfoDao tenpoInfoDao;
    /** 共通DTO【店舗検索】 */
    private MiseSearchDto miseSearchDto;
    /** 共通DTO【プルダウンメニュー】 */
    private PullDownMenuDto pullDownMenuDto;
    /** 推移表共通LOGIC【条件部情報取得】 */
    private ConditionLogic suiiRefConditionLogic;
    /** 推移表共通DTO【条件部情報】 */
    private SuiiRefConditionDto suiiRefConditionDto;
    /** 推移表共通DTO【検索条件】*/
    private SuiiRefParameterDto searchParamDto;
    /** 推移表共通DTO【結果条件】*/
    private SuiiRefParameterDto resultParamDto;
    /** 別ウィンドウフラグ */
    private boolean newWindowFlg = false;
    /** DTO【検索結果】 */
    private TakuhaiSuiiRefResultDto  takuhaiSuiiRefResultDto;     //DTO【検索結果】
    private SuiiRefResultMapDto  takuhaiSuiiRefResultMapDto;     //推移表共通DTO【結果情報Map保持】

    /* LOGIG */
    private TakuhaiSuiiNipoInfoLogic takuhaiSuiiNipoInfoLogic;   //宅配売上推移日次情報取得ロジック
    private TotalMiseCntNipoLogic    totalMiseCntNipoLogic;      //日次店舗数取得ロジック
    private TakuhaiSuiiGepoInfoLogic takuhaiSuiiGepoInfoLogic;   //宅配売上推移月次情報取得ロジック
    private TotalMiseCntGepoLogic    totalMiseCntGepoLogic;      //月次店舗数取得ロジック
    
    private TotalMiseGepoCntDao totalMiseGepoCntDao;

    /* ユーザ情報 */
    private BirdUserInfo birdUserInfo;
    /* 日付情報 */
    private BirdDateInfo birdDateInfo;
    /**
     * 初期化処理<p/>
     * @return 画面遷移情報
     */
    public String initialize() {
        //１．プルダウンメニューから遷移された場合、下記の処理を行います。
        if (getPullDownMenuDto().isClearFlg()) {
	        //1.BIRD共通DTO【メニュープルダウン情報】.クリアフラグへfalseを設定します。
	    	getPullDownMenuDto().setClearFlg(false);
            //2.推移表共通LOGIC【条件部情報取得】.実行し、推移表共通DTO【条件部情報】を設定します。
	    	setSuiiRefConditionDto(getSuiiRefConditionLogic().execute());
            //3.推移表共通DTO【条件部情報】.MAXウィンドウID取得でwindowIdを取得します。
	    	int windowId = getSuiiRefConditionDto().updateWindowId();
            //4.推移表共通DTO【検索条件】.ウィンドウIDへ処理5の戻り値windowIdを設定します。
            searchParamDto.setWindowId(windowId);
            //5.推移表共通DTO【検索条件】.再検索制御フラグへfalseを設定します。
            searchParamDto.setResearchFlg(false);
            //5.推移表共通DTO【検索条件】へデフォルト値を設定します。
	    	getSuiiRefConditionDto().copyDefaultParamerter(searchParamDto);
        }
    	//２．推移表共通DTO【検索条件】.初期アクションフラグがtrueの場合、下記の処理を行います。
        if (searchParamDto.isInitActionFlg()) {
	        //1.推移表共通DTO【結果条件】.再検索(実行・再検索ボタン名称)制御フラグへ
	        //   推移表共通DTO【検索条件】.再検索(実行・再検索ボタン名称)制御フラグ値を設定します。
	        resultParamDto.setResearchFlg(searchParamDto.isResearchFlg());
        	//2.推移表共通DTO【検索条件】へ推移表共通DTO【結果条件】を設定します。
        	setSuiiRefSearchParameterDto(resultParamDto);
	        //3.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。(→処理４の処理を実行させるため）
        	takuhaiSuiiRefResultDto.setExistData(true);
        	if (resultParamDto.isSuiiTypeGepo()){
        		resultParamDto.setTabKbn(TakuhaiSuiiConstants.TAB_AREA_2);//月次
        	}else{
        		resultParamDto.setTabKbn(TakuhaiSuiiConstants.TAB_AREA_0);//日次
        	}
        }
        //３．店舗選択画面から遷移してきた場合。
        else if(getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT){
            //0．MISEを選択後遷移してきた場合。
            if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
                //MISE-SELECT-1．店舗用画面VIEW_IDをリターンします。
            	return TakuhaiSuiiConstants.VIEWID_TAKUHAI_SUII_MISE;
            }
        	//1.Try開始 店舗選択画面からの値などを設定します。
        	try {
	            //MISE-1．BIRD共通DTO【店舗選択画面】.ウィンドウIDを取得します。
	        	int windowId = getMiseSearchDto().getWindowId();
	        	//MISE-2．処理MISE-1のウィンドウIDをキーに推移表共通DTO【条件部情報】からDTO【検索条件情報】を取得します。
	        	//MISE-3．推移表共通DTO【検索条件】へ処理MISE-2のDTO【検索条件情報】を設定します。
	        	setSuiiRefSearchParameterDto(
	        			(SuiiRefParameterDto) getSuiiRefConditionDto().getSearchParameterDto(windowId));
	        	//MISE-4．MISE-1のウィンドウIDをキーに推移表共通DTO【条件部情報】からDTO【結果条件情報】を取得します。
	        	//MISE-5．DTO【結果条件】へ処理２のDTO【結果条件情報】を設定します。
	        	setSuiiRefResultParameterDto(
	        			(SuiiRefParameterDto) getSuiiRefConditionDto().getResultParameterDto(windowId));
	            //MISE-6．処理MISE-1のウィンドウIDをキーに日報共通DTO【条件部情報】からDTO【結果情報】を取得します。
	        	//MISE-7．推移表共通DTO【結果条件】へMISE-6のDTO【結果情報】を設定します。
	        	TakuhaiSuiiRefResultDto resultDtoAtSession = (TakuhaiSuiiRefResultDto)getTakuhaiSuiiRefResultMapDto().getResultDto(windowId);
            	if(resultDtoAtSession ==null) {
            		resultDtoAtSession = new TakuhaiSuiiRefResultDto();
            	}
	        	setTakuhaiSuiiRefResultDto(resultDtoAtSession);
        	}
        	//2.FINALLYで下記の処理を行います。
        	finally {
                //FINAL-1．BIRD共通DTO【店舗選択画面】.遷移区分を初期値に戻す。
                getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
                //FINAL-2．BIRD共通DTO【店舗選択画面】.クリア処理を実行します。
                getMiseSearchDto().clear();
            }
        }
        //４．DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。
        if(takuhaiSuiiRefResultDto.isExistData() 
        		&& takuhaiSuiiRefResultDto.getGepoGamenListSize()==0)
        {
        	//0.推移表共通DTO【検索条件】.ウィンドウIDを取得します。
        	int windowId = searchParamDto.getWindowId();
        	//1.DTO【Map結果情報】から処理0の値をキーに推移表共通DTO【結果条件】を取得します。
        	SuiiRefParameterDto sessionResultParamDto = takuhaiSuiiRefResultMapDto.getParameterDto(windowId);
        	//2.DTO【Map結果情報】から処理0の値をキーにDTO【結果情報】を取得します。
        	TakuhaiSuiiRefResultDto resultDtoAtSession = (TakuhaiSuiiRefResultDto) takuhaiSuiiRefResultMapDto.getResultDto(windowId);
        	//3.推移表共通DTO【結果条件】の条件と処理1の推移表共通DTO【結果条件】の条件が一致した場合、
        	//  下記の処理を行います。
        	if(sessionResultParamDto != null && resultDtoAtSession != null 
        			&& resultParamDto.equalsParams(sessionResultParamDto))
        	{
        		//MATCH-PARAM-1.DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
        		setTakuhaiSuiiRefResultDto(resultDtoAtSession);
        		takuhaiSuiiRefResultDto.setTargetTab(resultParamDto.getTabKbn());
        	}
        	//4.変数.再検索判断フラグがtrueの場合は、セッションにフォーカスタブの検索結果が存在しないと判断し、
        	else {
        		try {
        			//再度検索処理として検索処理を実行します。
		            this.search(resultParamDto);
        		}
        		//5.ApplicationExceptionをキャッチし下記の処理を行います。
        		catch (ApplicationException ex) {
        			//EX-1.DTO【結果情報】.List[[タブ情報]]が空の場合、検索結果無しと判断し、
        			if(takuhaiSuiiRefResultDto.isListResultEmpty()) {
        				//DTO【結果情報】.該当データ存在判断フラグへfalseを設定します。
        				takuhaiSuiiRefResultDto.setExistData(false);
        			}
        			//EX-2.キャッチしたExceptionをスルーします。
        			throw ex;        			
        		}
        	}
        }
    	//５．推移表共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:推移表共通DTO【条件部情報】、推移表共通DTO【結果条件】
        SuiiRefUtil.searchInitialize(getSuiiRefConditionDto(), resultParamDto);
        //６．条件部の対象条件リストを設定するため、推移表共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:推移表共通DTO【条件部情報】、推移表共通DTO【検索条件】
        SuiiRefUtil.searchInitialize(getSuiiRefConditionDto(), searchParamDto);
        //７．nullをリターンします。(現行画面へ遷移)
        return null;
    }

    /**
     * 検索ロジック実行<p/>
     * @param Map ロジック実行用マップ
	 * @modifier xkinu 2010/01/18 ADD 
	 *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。
	 *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、
	 *        システムエラーが発生することがなくなる為。
     */
    private void search(SuiiRefParameterDto parameterDto) {
      	//１．推移表共通UTIL【共通処理】検索事前処理を実行します。
       	//    引数:推移表共通DTO【条件部情報】、DTO【結果条件】
       	SuiiRefUtil.searchInitialize(getSuiiRefConditionDto(), parameterDto);
        //２．CTRL【BIRDユーザー情報】.ユーザータイプ＝”本部”で、
        //    かつCTRL【BIRDログインユーザー情報】.リモート制限がtrueの場合、
        //    下記の確認処理を行います。
        String userId = getBirdUserInfo().getUserID();
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        boolean limitFlg = getBirdUserInfo().isLimit();
        if(UserType.isHonbu(userTypeCd)	&& limitFlg) {
            //リモート制限ユーザログイン時閲覧可能担当支部存在チェック
        	//1.DTO【結果条件】.List[[対象条件]]から、支部のList[[支部]]を取得します。
    		List listTaishoJoken = parameterDto.getListTaishoJoken();
    		List listSibu = null;
        	for(int i=0; i<listTaishoJoken.size(); i++) {
        		UILists taishoJ = (UILists)listTaishoJoken.get(i);
        		if( taishoJ.getKey().equals(TaishoJoken.CODE_SIBU) ) {
        			listSibu = taishoJ.getListData();
        			break;
        		}
        	}
            //2.処理1のList[[対象条件]]が０件の場合下記の処理を行います。
        	if(listSibu ==null || listSibu.isEmpty()) {
    	        //EX-1.DTO【結果情報】.クリア処理を行います。
        		takuhaiSuiiRefResultDto.clear();
                //EX-2.NotExistExceptionを発生させます。
        		throw new NotExistException("担当支部");
        	}
        }
        //３．Try開始 検索処理を行います。
        try {
            //1.DTO【宅配売上推移表用・検索結果】から条件パラメーター値を取得する。
            String companyCd      = parameterDto.getCompanyCd();
            String tenpoShubetu   = parameterDto.getTenpoShubetu();
            String taishoKikan    = parameterDto.getTaishoKikan();
            String kikanSitei     = parameterDto.getKikanSitei();
            String taishoJoken    = parameterDto.getTaishoJoken();
            String hyojiTaisho    = parameterDto.getHyojiTaisho();
            String zenDataShubetu = parameterDto.getZennenDataShubetu();
            String blockCd        = parameterDto.getBlockCd();
            
	        //-----------------------------------------
	        // <1>宅配売上推移月次情報取得ロジックの実行
	        //-----------------------------------------
	        //2.引数用Map作成
	        Map argsMap = new HashMap();
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_COMPANY_CD,      companyCd);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_TENPO_SHUBETU,   tenpoShubetu);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_KIKAN,    taishoKikan);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_KIKAN_SITEI,     kikanSitei);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_JOKEN,    taishoJoken);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_HYOJITAISHO_A,   hyojiTaisho);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_ZENDATA_SHUBETU, zenDataShubetu);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_BLOCK_CD,        blockCd);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_USER_TYPE,       userTypeCd);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_USER_ID,         userId);
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_LIMIT_FLG,       new Boolean(limitFlg));
	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_APPDATE,         getBirdDateInfo().getAppDate());
	        //3.オーナーユーザーの場合
	        if(UserType.isOner(userTypeCd)) {
		        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_ONER_CD, hyojiTaisho);
	        }
	        //4.実行
	        Map  gepoMap    = takuhaiSuiiGepoInfoLogic.execute(argsMap);
	        //5.推移表共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	parameterDto.setResearchFlg(true);
	    	//6.
	        List gepoInfoGamenList = (List)gepoMap.get(TakuhaiSuiiConstants.MAPKEY_GEPO_GAMEN_LIST);
	        if(gepoInfoGamenList == null || gepoInfoGamenList.size()==0){
	        	throw new NoResultException("");
	        }
	
	        //7.宅配対象店舗数(年月(YYYYMM)をキーに店舗すうを保持)
	        Map  tenpoCntMap       = (Map)gepoMap.get(TakuhaiSuiiConstants.MAPKEY_TENPO_CNT_MAP);
	        //結果(月次リスト)をDTOにセット
	        takuhaiSuiiRefResultDto.setGepoGamenList(gepoInfoGamenList);
	
	        //-----------------------------------------
	        // <2>月次店舗数取得ロジックの実行
	        //-----------------------------------------
	        //8.実行
	        int tenpoCntGepo = totalMiseCntGepoLogic.execute(argsMap);
	        
	        //9.結果(対象店舗数)をDTOにセット
	        takuhaiSuiiRefResultDto.setTotalTenpoCountGepo(tenpoCntGepo);
	
	        //-----------------------------------------
	        // <3>宅配売上推移日次情報取得ロジックの実行
	        //-----------------------------------------
	        //10.実行
	        Map  nipoMap    = takuhaiSuiiNipoInfoLogic.execute(argsMap, tenpoCntMap);
	        List nipoInfoGamenList = (List)nipoMap.get(TakuhaiSuiiConstants.MAPKEY_NIPO_GAMEN_LIST);
	        if(nipoInfoGamenList != null && nipoInfoGamenList.size() > 0){
	            //結果(日次リスト)をDTOにセット
	            takuhaiSuiiRefResultDto.setNipoGamenList(nipoInfoGamenList);
	            //-----------------------------------------
	            // <4>日次店舗数取得ロジックの実行
	            //-----------------------------------------
	            //実行
	            int tenpoCntNipo = totalMiseCntNipoLogic.execute(argsMap);
	            //結果(対象店舗数)をDTOにセット
	            takuhaiSuiiRefResultDto.setTotalTenpoCountNipo(tenpoCntNipo);
	        }	
	        //-----------------------
	        // 表示タブ
	        //-----------------------
	        String targetTab    = parameterDto.getTabKbn();
	        if(CommonUtil.isNull(targetTab)){
	            //対象期間が「年度」の場合
	            if (TaishoKikan.CODE_NENDO.equals(taishoKikan)){
	                targetTab = TakuhaiSuiiConstants.TAB_AREA_2;
	            }
	            else {
	            	targetTab = TakuhaiSuiiConstants.TAB_AREA_0;
	            }
	        }
	        //11.
	        String taishoJokenName = TaishoJoken.getName(userTypeCd, taishoJoken);
	        //12.推移表共通DTO【検索条件】.対象条件名称へ推移表共通CODE【対象条件コード定数クラス】名称取得処理で取得した値を設定します。
	        parameterDto.setTaishoJokenName(taishoJokenName);
	        //13.変数.表示対象名称へ""(空)を設定します。
	        String hyojiTaishoName = "";
	        //14.変数.表示対象名称へ下記の処理で名称を設定します。
	        if(UserType.isHonbu(userTypeCd) && TaishoJoken.CODE_MISE.equals(taishoJoken)) {
	        	//NAME-1.本部ユーザーで個店指定の場合、ＤＢから店舗名称を取得し変数.表示対象名称へ設定します。
	        	List listMise = getTenpoInfoDao().getMiseInfoByMiseCd(companyCd, hyojiTaisho);
	        	TenpoInfo eMise = (TenpoInfo)listMise.get(0);
	        	hyojiTaishoName = eMise.getMiseNameKj().trim();
	        }
	        else {
		        //NAME-2.処理NAME-1以外は、推移表共通DTO【検索条件】.List[[対象条件]]から
		        //    推移表共通DTO【検索条件】.対象条件のUILists[対象条件]を取得します。
		        UILists uiTaishoJoken = (UILists)TaishoJoken.getUILists(taishoJoken, parameterDto.getListTaishoJoken());
		        //1-4.処理1-3のUILists[対象条件].データ件数取得処理で戻り値が0以上の場合、下記の処理を行います。
		        if(uiTaishoJoken.getListDataCnt()>0) {
			        //4-1.UILists[対象条件].List[[データ]]を変数.List[CodHyojiTaisho[表示対象]]とします。
			        List listHyojitaisho = uiTaishoJoken.getListData();
			        //4-2.変数.List[CodHyojiTaisho[表示対象]]の件数分下記の処理を行います。
			        for (int ht=0; ht<listHyojitaisho.size(); ht++) {
			        	//for-1.現行インデックスのCodHyojiTaisho[表示対象]を取得します。
			        	CodHyojiTaisho eHyojiTaisho = (CodHyojiTaisho)listHyojitaisho.get(ht);
			        	//for-2.CodHyojiTaisho[表示対象].表示対象コード＝推移表共通DTO【検索条件】.表示対象の場合、下記の処理を行います。
			        	if(eHyojiTaisho.getHyojitaishoCd().equals(parameterDto.getHyojiTaisho())) {
			        		//for-2-1.変数.表示対象名称へCodHyojiTaisho[表示対象].表示対象名称を設定します。
			        		hyojiTaishoName = eHyojiTaisho.getHyojitaishoName();
			        		//for-2-2.for処理終了
			        		break;
			        	}
			        	//for-3.処理for-1へ
			        }//end of for (int ht=0; ht<listHyojitaisho.size(); ht++)
		        }//end of if(uiTaishoJoken.getListDataCnt()>0)
	        }
	        //15．推移表共通DTO【検索条件】.表示対象名称へ変数.表示対象名称を設定します。
	        parameterDto.setHyojiTaishoName(hyojiTaishoName.trim());
	        takuhaiSuiiRefResultDto.setTargetTab(targetTab);
	        //16.推移表共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setSuiiRefResultParameterDto(parameterDto);
	        //17.DTO【Map結果情報】.推移表共通DTO【結果条件】へDTO【検索条件】.ウィンドウIDをキーにDTO【検索条件】を設定します。
	        takuhaiSuiiRefResultMapDto.setParameterDto(parameterDto);
	        //18.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        takuhaiSuiiRefResultMapDto.setResultDto(parameterDto.getWindowId(), takuhaiSuiiRefResultDto);	        
        }
        //４．NoResultExceptionをキャッチし、下記の処理を行います。
        catch (NoResultException noResult) {
	        //EX-1.推移表共通DTO【結果情報】.クリア処理を行います。
        	takuhaiSuiiRefResultDto.clear();
	        //EX-2.推移表共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setSuiiRefResultParameterDto(parameterDto);
	        //EX-3.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        takuhaiSuiiRefResultMapDto.setResultDto(
	        		parameterDto.getWindowId(), takuhaiSuiiRefResultDto);
        	//EX-4.NoResultExceptionを発生させます。
            //該当データがありません。のメッセージを表示します。
        	throw noResult;
        }
        //５．BatchProcessingExceptionをキャッチし、下記の処理を行います。
        catch (BatchProcessingException batchEx) {
	        //EX-1.推移表共通DTO【結果情報】.クリア処理を行います。
        	takuhaiSuiiRefResultDto.clear();
	        //EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        takuhaiSuiiRefResultMapDto.setResultDto(
	        		parameterDto.getWindowId(), takuhaiSuiiRefResultDto);
	    	//EX-3.キャッチしたBatchProcessingExceptionを発生させます。
	        throw batchEx;
        }
    }
    /**
     * 会社プルダウン変更処理<p/>
     * 
     * １．推移表共通DTO【条件部情報】.対象会社初期値設定処理を実行し、推移表共通DTO【検索条件】へデフォルト値を設定します。<br/>
     * ２．nullをリターンします。(現行画面へ遷移)<br/>
     * @return String null(現行画面へ遷移)
     */
    public String changeCompany()  {
    	//１．推移表共通DTO【条件部情報】.対象会社初期値設定処理を実行し、推移表共通DTO【検索条件】へデフォルト値を設定します。
    	getSuiiRefConditionDto().copyDefaultParamerter(searchParamDto.getCompanyCd(), searchParamDto);
        //２．nullをリターンします。(現行画面へ遷移)
        return null;
    }
    
    /**
     * (店舗)検索ボタンの処理実行<p/>
     * @return 画面遷移情報
     */
    public String callMiseForm(){
        //１．BIRD共通DTO【店舗選択画面】.遷移元情報へ初期画面VIEW_IDを設定します。
        getMiseSearchDto().setNavigationCase(TakuhaiSuiiConstants.VIEWID_TAKUHAI_SUII);
        //２．BIRD共通DTO【店舗選択画面】.初期化フラグへtrueを設定します。
        getMiseSearchDto().setInitialFlag(true);
        //３．BIRD共通DTO【店舗選択画面】.遷移区分要否フラグへtrueを設定します。
        getMiseSearchDto().setNeedReturnKind(true);
        //４．BIRD共通DTO【店舗選択画面】.ウィンドウID　へ 推移表共通DTO【検索条件】.ウィンドウIDを設定します。
        getMiseSearchDto().setWindowId(searchParamDto.getWindowId());
        //５．推移表共通DTO【結果条件】.ウィンドウID　へ 推移表共通DTO【検索条件】.ウィンドウIDを設定します。
        resultParamDto.setWindowId(searchParamDto.getWindowId());
        //６．List[[会社]]を作成し推移表共通DTO【検索条件】.会社コードを設定します。
        List listCompany = new ArrayList();
        listCompany.add(searchParamDto.getCompanyCd());
        //７．BIRD共通DTO【店舗選択画面】.List[[会社]]へ処理６のList[[会社]]を設定します。
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //８．推移表共通DTO【条件部情報】.DTO【検索条件情報】へ推移表共通DTO【検索条件】の保管を行う。
        getSuiiRefConditionDto().setSearchParameterDto(searchParamDto);
        //９．推移表共通DTO【条件部情報】.DTO【検索結果情報】へ推移表共通DTO【結果条件】の保管を行う。
        getSuiiRefConditionDto().setResultParameterDto(resultParamDto);        
        //10．店舗選択画面遷移VIEWIDをリターンする。
        return CommonUtil.VIEW_ID_MISESEARCH;
    }
       
    /**
     * 実行ボタンの処理実行<p/>
     * @return 画面遷移情報
	 * @modifier xkinu 2010/01/18 ADD 
	 *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。
	 *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、
	 *        システムエラーが発生することがなくなる為。
     */
    public String search(){
        //１．クラス変数[別ウィンドウフラグ]がtrueの場合、下記の処理を行います。
        if (isNewWindowFlg()) {
            //1.推移表共通DTO【条件部情報】.MAXウィンドウIDカウントアップし、MAXウィンドウIDを取得します。
            int newWindowId = getSuiiRefConditionDto().updateWindowId();
            //2.推移表共通DTO【検索条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            searchParamDto.setWindowId(newWindowId);
            //3.推移表共通DTO【結果条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            resultParamDto.setWindowId(newWindowId);
        }
        //２．推移表共通DTO【条件部情報】.タブ区分へ""(空)を設定し初期化します。
        searchParamDto.setTabKbn("");
        //３．推移表共通DTO【検索条件】を引数に、店別売上情報取得を実行します。
        this.search(searchParamDto);
        String viewId = TakuhaiSuiiConstants.VIEWID_TAKUHAI_SUII;
        //４．対象条件が「個店」の場合
        String taishoJoken    = searchParamDto.getTaishoJoken();
        if (TaishoJoken.CODE_MISE.equals(taishoJoken)){
        	//個店結果画面VIEWIDをリターンします。
            viewId = TakuhaiSuiiConstants.VIEWID_TAKUHAI_SUII_MISE;
        }
        //５．結果画面VIEWIDをリターンします。
        return viewId;
    }


    /**
     * CSVダウンロード<p/>
     */
    public void downloadCsv() {
      	//１．推移表共通UTIL【共通処理】検索事前処理を実行します。
       	//    引数:推移表共通DTO【条件部情報】、DTO【結果条件】
       	SuiiRefUtil.searchInitialize(getSuiiRefConditionDto(), resultParamDto);
		try {
	    	//２．継承クラス.ダウンロード メイン処理を実行します。
	        super.downloadCsv();
	    } catch (IOException e) {
	    	//３．IOExceptionが発生した場合、FtlSystemExceptionを発生させます。
	        throw new FtlSystemException("CSVダウンロード", "", e);
	    }
    }

    /* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.groupsuiiref.action.GroupSuiiRefAction#callMoscardSuii()
	 */
	public String callMoscardSuii() {
		//１．他画面遷移時事前処理
		callScreenInitialize();
        //２．MOSCARD画面VIEW_IDをリターンします。
        return SuiiRefUtil.VIEW_ID_M;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.groupsuiiref.action.GroupSuiiRefAction#callTakuhaiSuii()
	 */
	public String callUriageSuii() {
		//１．他画面遷移時事前処理
		callScreenInitialize();
        //２．売上結果画面VIEW_IDをリターンします。
        return SuiiRefUtil.VIEW_ID_U;
	}
	/** ネット注文推移画面切替処理<p/>
	 * @see jp.co.isid.mos.bird.bizreport.groupsuiiref.action.GroupSuiiRefAction#callNetorderSuii()
	 */
	public String callNetorderSuii() {
		if (TenpoShubetu.CODE_YOSAN.equals(resultParamDto.getTenpoShubetu())
				|| TaishoJoken.CODE_JIGYOU.equals(resultParamDto.getTaishoJoken())) {
			
			throw new GenericMessageException("この検索条件ではネット注文はご利用できません。検索条件を変更してください。");
		}
		//１．推移表共通UTIL【共通処理】他画面遷移時事前処理を実行します。
		SuiiRefUtil.callScreenInitialize(
        		getSuiiRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．ネット注文推移結果画面(店舗別)VIEW_IDをリターンします。
        return SuiiRefUtil.VIEW_ID_N;
	}
    /**
     * 店検索DTO取得処理<p/>
     * @return
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * 店検索DTO設定処理<p/>
     * 
     * @param miseSearchDto
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    /**
     * 日付情報を取得します。<p/>
     * @return 日付情報
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    /**
     * 日付情報をセットします。<p/>
     * @return 日付情報
     */
    public void setBirdDateInfo(BirdDateInfo info) {
        this.birdDateInfo = info;
    }

    /**
     * 宅配売上推移日次情報取得ロジックを取得します<br>
     * @return 宅配売上推移日次情報取得ロジック
     */
    public TakuhaiSuiiNipoInfoLogic getTakuhaiSuiiNipoInfoLogic() {
        return takuhaiSuiiNipoInfoLogic;
    }
    /**
     * 宅配売上推移日次情報取得ロジックを設定します<br>
     * @param 宅配売上推移日次情報取得ロジック
     */
    public void setTakuhaiSuiiNipoInfoLogic(TakuhaiSuiiNipoInfoLogic logic) {
        this.takuhaiSuiiNipoInfoLogic = logic;
    }

    /**
     * 日次店舗数取得ロジックを取得します<br>
     * @return 日次店舗数取得ロジック
     */
    public TotalMiseCntNipoLogic getTotalMiseCntNipoLogic() {
        return totalMiseCntNipoLogic;
    }
    /**
     * 日次店舗数取得ロジックを設定します<br>
     * @param 日次店舗数取得ロジック
     */
    public void setTotalMiseCntNipoLogic(TotalMiseCntNipoLogic logic) {
        this.totalMiseCntNipoLogic = logic;
    }

    /**
     * 宅配売上推移月次情報取得ロジックを取得します<br>
     * @return 宅配売上推移月次情報取得ロジック
     */
    public TakuhaiSuiiGepoInfoLogic getTakuhaiSuiiGepoInfoLogic() {
        return takuhaiSuiiGepoInfoLogic;
    }
    /**
     * 宅配売上推移月次情報取得ロジックを設定します<br>
     * @param 宅配売上推移月次情報取得ロジック
     */
    public void setTakuhaiSuiiGepoInfoLogic(TakuhaiSuiiGepoInfoLogic logic) {
        this.takuhaiSuiiGepoInfoLogic = logic;
    }

    /**
     * 月次店舗数取得ロジックを取得します<br>
     * @return 月次店舗数取得ロジック
     */
    public TotalMiseCntGepoLogic getTotalMiseCntGepoLogic() {
        return totalMiseCntGepoLogic;
    }
    /**
     * 月次店舗数取得ロジックを設定します<br>
     * @param 月次店舗数取得ロジック
     */
    public void setTotalMiseCntGepoLogic(TotalMiseCntGepoLogic logic) {
        this.totalMiseCntGepoLogic = logic;
    }

    /**
     * ユーザ情報を取得します。
     * @return ユーザ情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    /**
     * ユーザ情報をセットします。
     * @return ユーザ情報
     */
    public void setBirdUserInfo(BirdUserInfo userinfo) {
        this.birdUserInfo = userinfo;
    }
	/**
	 * @return totalMiseGepoCntDao を戻します。
	 */
	public TotalMiseGepoCntDao getTotalMiseGepoCntDao() {
		return totalMiseGepoCntDao;
	}

	/**
	 * @param totalMiseGepoCntDao 設定する totalMiseGepoCntDao。
	 */
	public void setTotalMiseGepoCntDao(TotalMiseGepoCntDao totalMiseGepoCntDao) {
		this.totalMiseGepoCntDao = totalMiseGepoCntDao;
	}

	/**
	 * @return クラス変数suiiRefConditionDto を戻します。
	 */
	public SuiiRefConditionDto getSuiiRefConditionDto() {
		return suiiRefConditionDto;
	}

	/**
	 * @param suiiRefConditionDto を クラス変数suiiRefConditionDtoへ設定します。
	 */
	public void setSuiiRefConditionDto(SuiiRefConditionDto suiiRefConditionDto) {
		this.suiiRefConditionDto = suiiRefConditionDto;
	}

	/**
	 * 推移表共通LOGIC【条件部情報取得】 <p/>
	 * @return クラス変数suiiRefConditionLogic を戻します。
	 */
	public ConditionLogic getSuiiRefConditionLogic() {
		return suiiRefConditionLogic;
	}

	/**
	 * 推移表共通LOGIC【条件部情報取得】 <p/>
	 * @param conditionLogic を クラス変数suiiRefConditionLogicへ設定します。
	 */
	public void setSuiiRefConditionLogic(ConditionLogic conditionLogic) {
		this.suiiRefConditionLogic = conditionLogic;
	}

	/**
	 * @return クラス変数pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto を クラス変数pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}
	/**
	 * 推移表共通DTO【検索条件】
	 * @return クラス変数searchParamDto を戻します。
	 */
	public SuiiRefParameterDto getSuiiRefSearchParameterDto() {
		return searchParamDto;
	}

	/**
	 * 推移表共通DTO【検索条件】
	 * @param searchParamDto を クラス変数searchParamDtoへ設定します。
	 */
	public void setSuiiRefSearchParameterDto(
			SuiiRefParameterDto searchParamDto) {
		this.searchParamDto = searchParamDto;
	}

	/**
	 * 推移表共通DTO【結果条件】
	 * @return クラス変数resultParamDto を戻します。
	 */
	public SuiiRefParameterDto getSuiiRefResultParameterDto() {
		return resultParamDto;
	}

	/**
	 * 推移表共通DTO【結果条件】
	 * @param resultParamDto を クラス変数resultParamDtoへ設定します。
	 */
	public void setSuiiRefResultParameterDto(
			SuiiRefParameterDto resultParamDto) {
		this.resultParamDto = resultParamDto;
	}

	/**
	 * @return クラス変数newWindowFlg を戻します。
	 */
	public boolean isNewWindowFlg() {
		return newWindowFlg;
	}

	/**
	 * @param newWindowFlg を クラス変数newWindowFlgへ設定します。
	 */
	public void setNewWindowFlg(boolean newWindowFlg) {
		this.newWindowFlg = newWindowFlg;
	}

	/**
	 * @return クラス変数takuhaiSuiiRefResultDto を戻します。
	 */
	public TakuhaiSuiiRefResultDto getTakuhaiSuiiRefResultDto() {
		return takuhaiSuiiRefResultDto;
	}

	/**
	 * @param takuhaiSuiiRefResultDto を クラス変数takuhaiSuiiRefResultDtoへ設定します。
	 */
	public void setTakuhaiSuiiRefResultDto(
			TakuhaiSuiiRefResultDto takuhaiSuiiRefResultDto) {
		this.takuhaiSuiiRefResultDto = takuhaiSuiiRefResultDto;
	}

	/**
	 * @return クラス変数takuhaiSuiiRefResultMapDto を戻します。
	 */
	public SuiiRefResultMapDto getTakuhaiSuiiRefResultMapDto() {
		return takuhaiSuiiRefResultMapDto;
	}

	/**
	 * @param takuhaiSuiiRefResultMapDto を クラス変数takuhaiSuiiRefResultMapDtoへ設定します。
	 */
	public void setTakuhaiSuiiRefResultMapDto(
			SuiiRefResultMapDto takuhaiSuiiRefResultMapDto) {
		this.takuhaiSuiiRefResultMapDto = takuhaiSuiiRefResultMapDto;
	}

	/**
	 * @return クラス変数tenpoInfoDao を戻します。
	 */
	public TenpoInfoDao getTenpoInfoDao() {
		return tenpoInfoDao;
	}

	/**
	 * @param tenpoInfoDao を クラス変数tenpoInfoDaoへ設定します。
	 */
	public void setTenpoInfoDao(TenpoInfoDao tenpoInfoDao) {
		this.tenpoInfoDao = tenpoInfoDao;
	}
	/**
	 * 他画面遷移時事前処理
	 *
	 */
	private void callScreenInitialize() {
		//１．推移表共通UTIL【共通処理】他画面遷移時事前処理を実行します。
		SuiiRefUtil.callScreenInitialize(
        		getSuiiRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
		if(TakuhaiSuiiConstants.TAB_AREA_2.equals(takuhaiSuiiRefResultDto.getTargetTab())
				|| TakuhaiSuiiConstants.TAB_AREA_3.equals(takuhaiSuiiRefResultDto.getTargetTab()))
		{
			resultParamDto.setFocusTab(SuiiRefUtil.SUII_TYPE_GEPO);
		}
		else if (TaishoKikan.CODE_NENDO.equals(resultParamDto.getTaishoKikan())) {
				resultParamDto.setFocusTab(resultParamDto.getKikanSiteiFrom());
		}
		else {
			resultParamDto.setFocusTab(resultParamDto.getKikanSitei());
		}
	}
}
