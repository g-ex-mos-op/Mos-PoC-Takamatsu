/**
 *
 */
package jp.co.isid.mos.bird.bizreport.moscardsuiiref.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.UITabData;
import jp.co.isid.mos.bird.bizreport.common.suiiref.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.moscardsuiiref.action.MoscardSuiiRefAction;
import jp.co.isid.mos.bird.bizreport.moscardsuiiref.logic.SearchLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * MOSCARD推移表画面<br/>
 * ACTION【照会画面アクション】インターフェース<p/>
 * 作成日:2013/05/02<br/>
 * @author xkinu
 *
 */
public class MoscardSuiiRefActionImpl extends CsvOutput2ActionImpl implements MoscardSuiiRefAction {

    /** アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = "BBR016A01";
    /** アクションID：会社切替処理 */
    public static final String changeCompany_ACTION_ID = "BBR016A02";
    /** アクションID：検索処理 */
    public static final String search_ACTION_ID = "BBR016A03";
    /** アクションID：タブ切替 */
    public static final String changeTab_ACTION_ID = "BBR016A04";
    /** アクションID：宅配推移画面切替処理 */
    public static final String callTakuhaiSuii_ACTION_ID = "BBR016A05";
    /** アクションID：売上推移画面切替処理 */
    public static final String callUriageSuii_ACTION_ID = "BBR016A06";
    /** アクションID：店舗検索 */
    public static final String callMiseForm_ACTION_ID = "BBR016A07";
    /** アクションID：CSVダウンロード */
    public static final String downloadCsv_ACTION_ID = "BBR016A08";
    /** アクションID：ネット注文売上推移画面切替処理 */
    public static final String callNetorderSuii_ACTION_ID = "BBR016A09";

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
    /** DTO【結果情報】*/
    private SuiiRefResultDto suiiRefResultDto;
    /** DTO【結果情報MAP保持】*/
    private SuiiRefResultMapDto suiiRefResultMapDto;
    /** LOGIC【検索結果取得】*/
    private SearchLogic moscardSuiiRefSearchLogic;
    /** 別ウィンドウフラグ */
    private boolean newWindowFlg = false;

    /**
     * 初期処理<p/>
     * １．プルダウンメニューから遷移された場合、下記の処理を行います。
     * 	1.BIRD共通DTO【メニュープルダウン情報】.クリアフラグへfalseを設定します。
     * 	2.推移表共通LOGIC【条件部情報取得】.実行し、推移表共通DTO【条件部情報】を設定します。
     * 	3.推移表共通DTO【条件部情報】.MAXウィンドウID取得でwindowIdを取得します。
     * 	4.推移表共通DTO【検索条件】.ウィンドウIDへ処理5の戻り値windowIdを設定します。
     * 	5.推移表共通DTO【検索条件】.再検索制御フラグへfalseを設定します。
     * 	6.推移表共通DTO【検索条件】へデフォルト値を設定します。
     *
     * ２．推移表共通DTO【検索条件】.初期アクションフラグがtrueの場合、下記の処理を行います。
     * 	1.推移表共通DTO【結果条件】.再検索(実行・再検索ボタン名称)制御フラグへ
     * 		推移表共通DTO【検索条件】.再検索(実行・再検索ボタン名称)制御フラグ値を設定します。
     * 	2.推移表共通DTO【検索条件】へ推移表共通DTO【結果条件】を設定します。
     * 	3.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。(→処理５の処理を実行させるため）
     *
     * ３．店舗選択画面から遷移してきた場合。
     * 	1.Try開始 店舗選択画面からの値などを設定します。
     * 		MISE-1．BIRD共通DTO【店舗選択画面】.ウィンドウIDを取得します。
     * 		MISE-2．処理MISE-1のウィンドウIDをキーに推移表共通DTO【条件部情報】からDTO【検索条件情報】を取得します。				     *
     * 		MISE-3．推移表共通DTO【検索条件】へ処理MISE-2のDTO【検索条件情報】を設定します。
     * 		MISE-4．MISE-1のウィンドウIDをキーに推移表共通DTO【条件部情報】からDTO【結果条件情報】を取得します。
     * 		MISE-5．DTO【結果条件】へ処理２のDTO【結果条件情報】を設定します。
     * 		MISE-6．MISEを選択後遷移してきた場合。
     * 			MISE-SELECT-1．推移表共通DTO【検索条件】.MISEコードへBIRD共通DTO【店舗選択画面】.MISEコードを設定します。			     *
     * 			MISE-SELECT-2．アクション【検索】を実行します。
     * 	2.FINALLYで下記の処理を行います。
     * 		FINAL-1．BIRD共通DTO【店舗選択画面】.遷移区分を初期値に戻す。
     * 		FINAL-2．BIRD共通DTO【店舗選択画面】.クリア処理を実行します。
     *
     * ５．DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。						     *
     * 	0.推移表共通DTO【検索条件】.ウィンドウIDを取得します。
     * 	1.DTO【Map結果情報】から処理0の値をキーに推移表共通DTO【結果条件】を取得します。
     * 	2.DTO【Map結果情報】から処理0の値をキーにDTO【結果情報】を取得します。
     * 	3.変数.再検索判断フラグを生成し、初期値はtrueとます。
     * 	4.表示対象の推移表共通DTO【結果条件】の条件と処理1の推移表共通DTO【結果条件】の条件が一致した場合、
     * 		下記の処理を行います。
     * 		MATCH-PARAM-1.変数.再検索判断フラグへfalseを設定します。
     * 		MATCH-PARAM-2.DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
     * 		MATCH-PARAM-3.DTO【結果情報】.フォーカスタブへ推移表共通DTO【結果条件】.フォーカスタブを設定します。
     * 		MATCH-PARAM-4.DTO【結果情報】.[フォーカスタブ].List[[検索結果]]がない場合、変数.再検索判断フラグへtrueを設定します。	     *
     * 	5.変数.再検索判断フラグがtrueの場合は、セッションにフォーカスタブの検索結果が存在しないと判断し、
     * 		再度検索処理として検索結果取得処理を実行します。
     * 	6.ApplicationExceptionをキャッチし下記の処理を行います。
     * 		EX-1.DTO【結果情報】.List[[タブ情報]]が空の場合、検索結果無しと判断し、
     * 			DTO【結果情報】.該当データ存在判断フラグへfalseを設定します。
     * 		EX-2.キャッチしたExceptionをスルーします。
     *
     * ６．推移表共通UTIL【共通処理】検索事前処理を実行します。
     * 	引数:推移表共通DTO【条件部情報】、推移表共通DTO【結果条件】
     *
     * ７．条件部の対象条件リストを設定するため、推移表共通UTIL【共通処理】検索事前処理を実行します。
     * 	引数:推移表共通DTO【条件部情報】、推移表共通DTO【検索条件】
     *
     * ８．タブエリア表示メッセージ取得処理を実行し、戻り値を推移表共通DTO【結果情報】注訳文言へ設定します。						     *
     *
     * ９．nullをリターンします。(現行画面へ遷移)
     * @return null
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
            //6.推移表共通DTO【検索条件】へデフォルト値を設定します。
	    	getSuiiRefConditionDto().copyDefaultParamerter(searchParamDto);
        }
    	//２．推移表共通DTO【検索条件】.初期アクションフラグがtrueの場合、下記の処理を行います。
        if (searchParamDto.isInitActionFlg()) {
	        //1.推移表共通DTO【結果条件】.再検索(実行・再検索ボタン名称)制御フラグへ
	        //   推移表共通DTO【検索条件】.再検索(実行・再検索ボタン名称)制御フラグ値を設定します。
	        resultParamDto.setResearchFlg(searchParamDto.isResearchFlg());
        	//2.推移表共通DTO【検索条件】へ推移表共通DTO【結果条件】を設定します。
        	setSuiiRefSearchParameterDto(resultParamDto);
	        //3.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。(→処理５の処理を実行させるため）
        	suiiRefResultDto.setExistData(true);
        }
        //３．店舗選択画面から遷移してきた場合。
        else if(getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT){
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
	            //MISE-6．MISEを選択後遷移してきた場合。
	            if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
	                //MISE-SELECT-1．推移表共通DTO【検索条件】.MISEコードへBIRD共通DTO【店舗選択画面】.MISEコードを設定します。
	                searchParamDto.setHyojiTaisho(getMiseSearchDto().getMiseCd());
	            	//MISE-SELECT-2．アクション【検索】を実行します。
	                this.search();
	            }
	            //MISE-7．戻る選択後遷移してきた場合。
	            else {
		        	//CANCEL-1．処理MISE-1のウィンドウIDをキーに日報共通DTO【条件部情報】からDTO【結果情報】を取得します。
		        	//CANCEL-2．推移表共通DTO【結果条件】へCANCEL-1のDTO【結果情報】を設定します。
	            	SuiiRefResultDto resultDtoAtSession = (SuiiRefResultDto)getMoscardSuiiRefResultMapDto().getResultDto(windowId);
	            	if(resultDtoAtSession ==null) {
	            		resultDtoAtSession = new SuiiRefResultDto();
	            	}
		        	setMoscardSuiiRefResultDto(resultDtoAtSession);
	            }
        	}
        	//2.FINALLYで下記の処理を行います。
        	finally {
                //FINAL-1．BIRD共通DTO【店舗選択画面】.遷移区分を初期値に戻す。
                getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
                //FINAL-2．BIRD共通DTO【店舗選択画面】.クリア処理を実行します。
                getMiseSearchDto().clear();
            }
        }
        //４．個店ポータル画面から遷移された場合
        else if(getCommonCodeDto().getUseCommonDto()){
            //KOTEN-1.推移表共通LOGIC【条件部情報取得】.実行し、推移表共通DTO【条件部情報】を設定します。
	    	setSuiiRefConditionDto(getSuiiRefConditionLogic().execute());
            //KOTEN-2.推移表共通DTO【条件部情報】.MAXウィンドウID取得でwindowIdを取得します。
	    	int windowId = getSuiiRefConditionDto().updateWindowId();
            //KOTEN-3.推移表共通DTO【検索条件】.ウィンドウIDへ処理5の戻り値windowIdを設定します。
            searchParamDto.setWindowId(windowId);
            //KOTEN-4.推移表共通DTO【検索条件】へデフォルト値を設定します。
	    	getSuiiRefConditionDto().copyDefaultParamerter(searchParamDto);
            //KOTEN-5.推移表共通DTO【検索条件】.会社コードへBIRD共通DTO【個店ポータル】.会社コードを設定します。
            searchParamDto.setCompanyCd(getCommonCodeDto().getCompanyCd());
            //KOTEN-6.推移表共通DTO【検索条件】.表示対象へBIRD共通DTO【個店ポータル】.MISEコードを設定します。
            searchParamDto.setHyojiTaisho(getCommonCodeDto().getMiseCd());
            //KOTEN-7.推移表共通DTO【検索条件】.対象条件へ推移表共通CODE【対象条件コード定数クラス】.個店を設定します。
            searchParamDto.setTaishoJoken(TaishoJoken.CODE_MISE);
        	//KOTEN-8．アクション【検索】を実行します。
            this.search();
        }
        //５．DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。
        if(suiiRefResultDto != null
        		&& (suiiRefResultDto.isExistData()
        		&& (suiiRefResultDto.isListResultEmpty()
        				|| suiiRefResultDto.getListFocusTabResult() == null
        				|| suiiRefResultDto.getListFocusTabResult().isEmpty())) )
        {
        	//0.推移表共通DTO【検索条件】.ウィンドウIDを取得します。
        	int windowId = searchParamDto.getWindowId();
        	//1.DTO【Map結果情報】から処理0の値をキーに推移表共通DTO【結果条件】を取得します。
        	SuiiRefParameterDto sessionResultParamDto = suiiRefResultMapDto.getParameterDto(windowId);
        	//2.DTO【Map結果情報】から処理0の値をキーにDTO【結果情報】を取得します。
        	SuiiRefResultDto resultDtoAtSession = (SuiiRefResultDto) suiiRefResultMapDto.getResultDto(windowId);
        	//3.変数.再検索判断フラグを生成し、初期値はtrueとます。
        	boolean mustResearch = true;
        	//4.表示対象の推移表共通DTO【結果条件】の条件と処理1の推移表共通DTO【結果条件】の条件が一致した場合、
        	//  下記の処理を行います。
        	if(sessionResultParamDto != null && resultDtoAtSession != null
        			&& resultParamDto.equalsParams(sessionResultParamDto))
        	{
        		//MATCH-PARAM-1.変数.再検索判断フラグへfalseを設定します。
    			mustResearch = false;
        		//MATCH-PARAM-2.DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
        		setMoscardSuiiRefResultDto(resultDtoAtSession);
        		//MATCH-PARAM-3.DTO【結果情報】.フォーカスタブへ推移表共通DTO【結果条件】.フォーカスタブを設定します。
        		suiiRefResultDto.setFocusTab(resultParamDto.getFocusTab());
        		suiiRefResultDto.setListFocusTabResult(suiiRefResultMapDto.getListResult(windowId, resultParamDto.getFocusTab()));
        		UITabData tabData = suiiRefResultDto.getFocusUITabData();
        		//MATCH-PARAM-4.DTO【結果情報】.[フォーカスタブ].List[[検索結果]]がない場合、変数.再検索判断フラグへtrueを設定します。
        		if(tabData == null || (tabData.getMiseCntAll() > 0
        				&& (suiiRefResultDto.getListFocusTabResult() == null
        					|| suiiRefResultDto.getListFocusTabResult().isEmpty())))
        		{
        			mustResearch = true;
        		}
        	}
        	//5.変数.再検索判断フラグがtrueの場合は、セッションにフォーカスタブの検索結果が存在しないと判断し、
        	if(mustResearch) {
        		try {
        			//再度検索処理として売上情報取得処理を実行します。
		            this.search(resultParamDto, suiiRefResultDto);
        		}
        		//6.ApplicationExceptionをキャッチし下記の処理を行います。
        		catch (ApplicationException ex) {
        			//EX-1.DTO【結果情報】.List[[タブ情報]]が空の場合、検索結果無しと判断し、
        			if(suiiRefResultDto.isListResultEmpty()) {
        				//DTO【結果情報】.該当データ存在判断フラグへfalseを設定します。
        				suiiRefResultDto.setExistData(false);
        			}
        			//EX-2.キャッチしたExceptionをスルーします。
        			throw ex;
        		}
        	}
        }
    	//６．推移表共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:推移表共通DTO【条件部情報】、推移表共通DTO【結果条件】
        SuiiRefUtil.searchInitialize(getSuiiRefConditionDto(), resultParamDto);
        //７．条件部の対象条件リストを設定するため、推移表共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:推移表共通DTO【条件部情報】、推移表共通DTO【検索条件】
        SuiiRefUtil.searchInitialize(getSuiiRefConditionDto(), searchParamDto);
		//８．タブエリア表示メッセージ取得処理を実行し、戻り値を推移表共通DTO【結果情報】注訳文言へ設定します。
		suiiRefResultDto.setTabMsg(SuiiRefUtil.getTabMsg(getBirdDateInfo().getSysDate(), resultParamDto));
    	//９．nullをリターンします。(現行画面へ遷移)
        return null;
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
     * 店検索フォーム呼び出し処理<p/>
     * @return 店検索フォームViewID
     */
    public String callMiseForm() {
        //１．BIRD共通DTO【店舗選択画面】.遷移元情報へMOSCARD売上推移画面VIEW_IDを設定します。
        getMiseSearchDto().setNavigationCase(SuiiRefUtil.VIEW_ID_M);
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
     * 日次タブデータ検索処理<p/>
     *
     * 画面上の日次タブ又は、月次のリンクを押下された場合に呼び出されるメソッドです。<br/>
     * 押下した対象の年月のみの検索を実行します。<br/>
     * <br/>
     * 1.ロジック【推移表データ検索ロジック】日次タブ推移データ検索処理 実行<br/>
     * 2.検索結果件数が１件以上の場合下記の処理を実行<br/>
     *   DTO【グループ業績売上推移Dto】.[画面表示推移表日次情報12ヶ月分リスト]へ、<br/>
     *   パラメータ【日次データListIndex】のIndex位置に[日次タブ推移データ]を設定。<br/>
     *   他の固定の日次データ以外は空にする。<br/>
     * <br/>
     * @return null(現行画面へ遷移)
     */
    public String changeTab() {
        //１．nullをリターンします。(現行画面へ遷移)
        return null;
    }
    /**
     * アクション【検索】<p/>
     * @return  MoscardSuiiRefUtil.VIEW_ID_CONFIRM
     */
    public String search()  {
        //１．クラス変数[別ウィンドウフラグ]がtrueの場合、下記の処理を行います。
        if (isNewWindowFlg()) {
            //1.推移表共通DTO【条件部情報】.MAXウィンドウIDカウントアップし、MAXウィンドウIDを取得します。
            int newWindowId = getSuiiRefConditionDto().updateWindowId();
            //2.推移表共通DTO【検索条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            searchParamDto.setWindowId(newWindowId);
            //3.推移表共通DTO【結果条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            resultParamDto.setWindowId(newWindowId);
        }
        //２．同じウィンドウＩＤで過去の検索結果が存在する場合は、削除します。
        suiiRefResultMapDto.deleteResult(searchParamDto.getWindowId());
        //３．推移表共通DTO【検索条件】を引数に、検索結果取得処理を実行します。
        this.search(searchParamDto, null);
        //４．nullをリターンします。(現行画面へ遷移)
        return null;
    }
    /**
     * 検索処理<p/>
     *
     * @throws Exception
     *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う(仕様番号:SP010101)。
     */
    private void search(SuiiRefParameterDto parameterDto, SuiiRefResultDto resultDto) {
      	//１．推移表共通UTIL【共通処理】検索事前処理を実行します。
       	//    引数:推移表共通DTO【条件部情報】、DTO【結果条件】
       	SuiiRefUtil.searchInitialize(getSuiiRefConditionDto(), parameterDto);
        //２．CTRL【BIRDユーザー情報】.ユーザータイプ＝”本部”で、
        //    かつCTRL【BIRDログインユーザー情報】.リモート制限がtrueの場合、
        //    下記の確認処理を行います。(仕様番号:SP010101)
        boolean limitFlg = getBirdUserInfo().isLimit();
        if(UserType.isHonbu(getBirdUserInfo().getMstUser().getUserTypeCd())
        		&& limitFlg)
        {
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
    	        suiiRefResultDto.clear();
                //EX-2.NotExistExceptionを発生させます。
        		throw new NotExistException("担当支部");
        	}
        }
        //３．Try開始 検索処理を行います。
        try {
	        //1.LOGIC【検索結果取得】を実行し、戻り値List[[検索結果]]とします。
        	boolean isCsv = false;
        	suiiRefResultDto =  getMoscardSuiiRefSearchLogic().execute(isCsv, parameterDto, resultDto);

	        //2.推移表共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setSuiiRefResultParameterDto(parameterDto);
	        //3.DTO【Map結果情報】.推移表共通DTO【結果条件】へDTO【検索条件】.ウィンドウIDをキーにDTO【検索条件】を設定します。
	        suiiRefResultMapDto.setParameterDto(parameterDto);
	        //4.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        suiiRefResultMapDto.setResultDto(parameterDto.getWindowId(), suiiRefResultDto);
        }
        //４．NoResultExceptionをキャッチし、下記の処理を行います。
        catch (NoResultException noResult) {
	        //EX-1.推移表共通DTO【結果情報】.クリア処理を行います。
	        suiiRefResultDto.clear();
	        //EX-2.推移表共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setSuiiRefResultParameterDto(parameterDto);
	        //EX-3.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        suiiRefResultMapDto.setResultDto(
	        		parameterDto.getWindowId(), suiiRefResultDto);
        	//EX-4.NoResultExceptionを発生させます。
            //該当データがありません。のメッセージを表示します。
        	throw noResult;
        }
        //５．BatchProcessingExceptionをキャッチし、下記の処理を行います。
        catch (BatchProcessingException batchEx) {
	        //EX-1.推移表共通DTO【結果情報】.クリア処理を行います。
	        suiiRefResultDto.clear();
	        //EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        suiiRefResultMapDto.setResultDto(
	        		parameterDto.getWindowId(), suiiRefResultDto);
	    	//EX-3.キャッチしたBatchProcessingExceptionを発生させます。
	        throw batchEx;
        }
        //６．処理終了
    }

    /**
     * 売上推移画面切替処理<p/>
	 * @see jp.co.isid.mos.bird.bizreport.groupsuiiref.action.MoscardSuiiRefAction#callMoscardSuii()
	 */
	public String callUriageSuii() {
		//１．推移表共通UTIL【共通処理】他画面遷移時事前処理を実行します。
		SuiiRefUtil.callScreenInitialize(
        		getSuiiRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．売上推移画面VIEW_IDをリターンします。
        return SuiiRefUtil.VIEW_ID_U;
	}
	/**
     * 宅配売上推移画面切替処理<p/>
	 * @see jp.co.isid.mos.bird.bizreport.groupsuiiref.action.MoscardSuiiRefAction#callTakuhaiSuii()
	 */
	public String callTakuhaiSuii() {
		//１．推移表共通UTIL【共通処理】他画面遷移時事前処理を実行します。
		SuiiRefUtil.callScreenInitialize(
        		getSuiiRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //３．.DTO【結果条件】.対象条件＝”個店(店舗）”の場合は、宅配店舗用画面VIEW_IDをリターンします。
		if(TaishoJoken.CODE_MISE.equals(resultParamDto.getTaishoJoken())) {
			return SuiiRefUtil.VIEW_ID_T_MISE;
		}
		//４．.DTO【結果条件】.対象条件＝”個店(店舗）”以外の場合は、宅配画面VIEW_IDをリターンします。
		return SuiiRefUtil.VIEW_ID_T;
	}
    /** ネット注文推移画面切替処理<p/>
	 * @see jp.co.isid.mos.bird.bizreport.groupsuiiref.action.MoscardSuiiRefAction#callNetorderSuii()
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
	 * アクション【ダウンロード】<p/>
	 * @see jp.co.isid.mos.bird.framework.action.CsvOutputAction#downloadCsv()
	 */
	public void downloadCsv() {
      	//１．推移表共通UTIL【共通処理】検索事前処理を実行します。
       	//    引数:推移表共通DTO【条件部情報】、DTO【結果条件】
       	SuiiRefUtil.searchInitialize(getSuiiRefConditionDto(), resultParamDto);
		try {
	    	//１．継承クラス.ダウンロード メイン処理を実行します。
	        super.downloadCsv();
	    } catch (IOException e) {
	    	//２．IOExceptionが発生した場合、FtlSystemExceptionを発生させます。
	        throw new FtlSystemException("CSVダウンロード", "", e);
	    }

	}
	/**
     *
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * CTRL【BIRDユーザー情報】取得処理<p/>
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * CTRL【BIRD日付情報】取得処理<p/>
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
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
     * 個店ポータルからの遷移時保持DTO取得処理<p/>
     * @return
     */
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) getS2Container().getComponent(CommonCodeDto.class);
    }
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
	/**
	 * 推移表共通DTO【条件部情報】<p/>
	 * @return クラス変数suiiRefConditionDto を戻します。
	 */
	public SuiiRefConditionDto getSuiiRefConditionDto() {
		return suiiRefConditionDto;
	}

	/**
	 * 推移表共通DTO【条件部情報】<p/>
	 * @param conditionDto を クラス変数suiiRefConditionDtoへ設定します。
	 */
	public void setSuiiRefConditionDto(SuiiRefConditionDto conditionDto) {
		this.suiiRefConditionDto = conditionDto;
	}

	/**
	 * 推移表共通DTO【検索条件】<p/>
	 * @return クラス変数searchParamDto を戻します。
	 */
	public SuiiRefParameterDto getSuiiRefSearchParameterDto() {
		return searchParamDto;
	}

	/**
	 * 推移表共通DTO【検索条件】<p/>
	 * @param searchParamDto を クラス変数searchParamDtoへ設定します。
	 */
	public void setSuiiRefSearchParameterDto(
			SuiiRefParameterDto searchParamDto) {
		this.searchParamDto = searchParamDto;
	}

	/**
	 * 推移表共通DTO【結果条件】<p/>
	 * @return クラス変数resultParamDto を戻します。
	 */
	public SuiiRefParameterDto getSuiiRefResultParameterDto() {
		return resultParamDto;
	}

	/**
	 * 推移表共通DTO【結果条件】<p/>
	 * @param resultParamDto を クラス変数resultParamDtoへ設定します。
	 */
	public void setSuiiRefResultParameterDto(
			SuiiRefParameterDto resultParamDto) {
		this.resultParamDto = resultParamDto;
	}

	/**
	 * 推移表共通DTO【結果情報】<p/>
	 * @return クラス変数suiiRefResultDto を戻します。
	 */
	public SuiiRefResultDto getMoscardSuiiRefResultDto() {
		return suiiRefResultDto;
	}

	/**
	 * 推移表共通DTO【結果情報】<p/>
	 * @param resultDto を クラス変数suiiRefResultDtoへ設定します。
	 */
	public void setMoscardSuiiRefResultDto(SuiiRefResultDto resultDto) {
		this.suiiRefResultDto = resultDto;
	}

	/**
	 * @return クラス変数suiiRefResultMapDto を戻します。
	 */
	public SuiiRefResultMapDto getMoscardSuiiRefResultMapDto() {
		return suiiRefResultMapDto;
	}

	/**
	 * @param suiiRefResultMapDto を クラス変数suiiRefResultMapDtoへ設定します。
	 */
	public void setMoscardSuiiRefResultMapDto(SuiiRefResultMapDto suiiRefResultMapDto) {
		this.suiiRefResultMapDto = suiiRefResultMapDto;
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
	 * @return クラス変数suiiRefConditionLogic を戻します。
	 */
	public ConditionLogic getSuiiRefConditionLogic() {
		return suiiRefConditionLogic;
	}

	/**
	 * @param conditionLogic を クラス変数suiiRefConditionLogicへ設定します。
	 */
	public void setSuiiRefConditionLogic(ConditionLogic conditionLogic) {
		this.suiiRefConditionLogic = conditionLogic;
	}

	/**
	 * LOGIC【検索結果取得】<p/>
	 * @return クラス変数moscardSuiiRefSearchLogic を戻します。
	 */
	public SearchLogic getMoscardSuiiRefSearchLogic() {
		return moscardSuiiRefSearchLogic;
	}

	/**
	 * LOGIC【検索結果取得】<p/>
	 * @param searchLogic を クラス変数moscardSuiiRefSearchLogicへ設定します。
	 */
	public void setMoscardSuiiRefSearchLogic(SearchLogic searchLogic) {
		this.moscardSuiiRefSearchLogic = searchLogic;
	}
	/**
	 * MOS CARD用会社プルダウンリスト情報取得<p/>
	 * @return
	 */
	public List getListMoscardSuiiRefCompany() {
		List listMoscardSuiiRefCompany = new ArrayList(0);
		for(int i=0; i<getSuiiRefConditionDto().getListCompany().size(); i++) {
			CodCompany codCompany = (CodCompany)getSuiiRefConditionDto().getListCompany().get(i);
			if(CommonUtil.COMPANY_CD_MOS.equals(codCompany.getCompanyCd()) ) {
				listMoscardSuiiRefCompany.add(codCompany);
				break;
			}
		}
		return listMoscardSuiiRefCompany;
	}
}
