/*
 * 作成日: 2006/09/12
 *
 */
package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.action.impl;

import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.action.TakuhaiSuiiRefMiseAction;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common.TakuhaiSuiiConstants;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dto.TakuhaiSuiiRefResultDto;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;

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
public class TakuhaiSuiiRefMiseActionImpl extends TakuhaiSuiiRefActionImpl implements CommonAction , TakuhaiSuiiRefMiseAction {
    /** アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = "BBR004A11";
    /** アクションID：対象会社変更処理 */
    public static final String changeCompany_ACTION_ID = "BBR004A12";
    /** アクションID：検索処理 */
    public static final String search_ACTION_ID = "BBR004A13";
    /** アクションID：対象タブ変更処理 */
    public static final String callMiseForm_ACTION_ID = "BBR004A14";
    /** アクションID：売上推移画面切替処理 */
    public static final String callUriageSuii_ACTION_ID = "BBR004A15";
    /** アクションID：MOSCARD推移画面切替処理 */
    public static final String callMoscardSuii_ACTION_ID = "BBR004A16";
    /** アクションID：MOSCARD推移画面切替処理 */
    public static final String downloadCsv_ACTION_ID = "BBR004A17";
    /** アクションID：ネット注文推移画面切替処理 */
    public static final String callNetorderSuii_ACTION_ID = "BBR004A18";

    /**
     * 初期化処理<p/>
     * 
     * @return 画面遷移情報
     */
    public String initialize() {
        //１．店舗選択画面から遷移してきた場合。
        if(getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT){
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
	                getSuiiRefSearchParameterDto().setHyojiTaisho(getMiseSearchDto().getMiseCd());
	            	//MISE-SELECT-2．アクション【検索】を実行します。
	                this.search();
	            }
	            //MISE-7．戻る選択後遷移してきた場合。
	            else {
		        	//CANCEL-1．処理MISE-1のウィンドウIDをキーに日報共通DTO【条件部情報】からDTO【結果情報】を取得します。
		        	//CANCEL-2．推移表共通DTO【結果条件】へCANCEL-1のDTO【結果情報】を設定します。
		        	TakuhaiSuiiRefResultDto resultDtoAtSession = (TakuhaiSuiiRefResultDto)getTakuhaiSuiiRefResultMapDto().getResultDto(windowId);
	            	if(resultDtoAtSession ==null) {
	            		resultDtoAtSession = new TakuhaiSuiiRefResultDto();
	            	}
		        	setTakuhaiSuiiRefResultDto(resultDtoAtSession);
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
        //７．nullをリターンします。(現行画面へ遷移)
        return super.initialize();

    }
    /**
     * 店舗検索<p/>
     * ０．super.店舗検索を実行します。<br/>
     * １．BIRD共通DTO【店舗選択画面】.遷移元情報へ個店結果画面VIEW_IDを設定します。<br/>
     * ２．店舗選択画面遷移VIEWID(BCO008V01)をリターンする。<br/>
     * @return 画面遷移情報
     */
    public String callMiseForm(){
    	//０．super.店舗検索を実行します。
    	super.callMiseForm();
        //１．BIRD共通DTO【店舗選択画面】.遷移元情報へ個店結果画面VIEW_IDを設定します。
        getMiseSearchDto().setNavigationCase(TakuhaiSuiiConstants.VIEWID_TAKUHAI_SUII_MISE);
        //２．店舗選択画面遷移VIEWIDをリターンする。
        return CommonUtil.VIEW_ID_MISESEARCH;
    }
}
