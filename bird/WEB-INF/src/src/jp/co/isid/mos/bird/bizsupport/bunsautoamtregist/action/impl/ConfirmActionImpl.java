/*
 * 作成日: 2006/12/22
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.action.ConfirmAction;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.UserType;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dto.BunsAutoAmtRegistDto;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dto.BunsAutoAmtRegistReqDto;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.RegistLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * バンズ自動設定数量変更
 * 確認画面アクション
 * 
 * @author xkinu
 *   
 */
public class ConfirmActionImpl implements ConfirmAction {
    
    /** アクションID：初期処理 */
    public static final String initialize_ACTION_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"A21";
    /** アクションID：戻る処理 */
    public static final String back_ACTION_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"A22";
    /** アクションID：登録・終了処理 */
    public static final String regist_ACTION_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"A23";
    /** アクションID：終了処理 */
    public static final String end_ACTION_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"A24";
    /** DTO【自画面Session用】*/
    private BunsAutoAmtRegistDto bunsAutoAmtRegistDto;
    /** DTO【自画面リクエスト用】*/
    private BunsAutoAmtRegistReqDto bunsAutoAmtRegistReqDto;
    /**
     * ロジック【データ検索】
     */
    private SearchLogic bunsAutoAmtRegistSearchLogic;
    /**
     * ロジック【データ登録】
     */
    private RegistLogic bunsAutoAmtRegistRegistLogic;
    /**
     * ロジック【条件項目の取得】
     */
    private ConditionLogic bunsAutoAmtRegistConditionLogic;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /* 共通DTO【ユーザーロール情報検索用】 */
    private GamenRoleDto gamenRoleDto;
    /* セッションキー作成クラス生成 */
    MakeSessionKey mkSession = new MakeSessionKey();

    /**
     *  初期化処理
     * １．現ウィンドウID の保管データから【DTO】検索対象条件項目へ値の設定を行う。
     */
    public String initialize() {
    	//１．変数【遷移先VIEW_DI】 ＝ null　を生成する
        String viewId = null;
        //２．ユーザータイプ取得
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        //３．オーナー又は店舗ユーザーの場合、プルダウンメニューから選択して、戻りVIEW_IDにVIEW_ID【確認・照会画面】を設定する
        if ((UserType.ONER.equals(userTypeCd) || UserType.TENPO.equals(userTypeCd))
				&& BunsAutoAmtRegistUtil.VIEW_ID_CONDITION
						.equals(getBunsAutoAmtRegistReqDto().getBackViewId())) {
			getBunsAutoAmtRegistReqDto().setBackViewId(BunsAutoAmtRegistUtil.VIEW_ID_CONFIRM);
		}

        //４．オーナーまたは店舗ユーザーの場合、
        //・プルダウンメニューから選択して、初期化処理します
        //・編集画面から遷移されると、初期化処理しません
        if((UserType.ONER.equals(userTypeCd) || UserType.TENPO.equals(userTypeCd)) &&
        		BunsAutoAmtRegistUtil.VIEW_ID_CONFIRM.equals(getBunsAutoAmtRegistReqDto().getBackViewId())) {
        	//４－１．初期化条件データ処理
        	init();
        	//４－２．DTO【自画面Session用】.ユーザーロール情報.変更許可フラグ＝trueの場合、VIEW_ID【編集画面】をリターンする
        	if (getBunsAutoAmtRegistDto().isRegist()) {
        		getBunsAutoAmtRegistReqDto().setBackViewId(BunsAutoAmtRegistUtil.VIEW_ID_EDIT);
				viewId = BunsAutoAmtRegistUtil.VIEW_ID_EDIT;
				return viewId;
			}
        }
        //５．DTO【自画面Session用】.ユーザーロール情報.変更許可フラグ＝falseの場合、
        if(!getBunsAutoAmtRegistDto().isRegist()) {
            if(getBunsAutoAmtRegistDto().isOnerUser()) {
                //５－１．オーナーユーザーの場合、データを再検索実行する
                //DTO【自画面Session用】.対象条件へ初期値として’オーナー’を設定する。
                getBunsAutoAmtRegistReqDto().setTargetTaishoJoken(ConditionTaishoJoken.VALUE_ONER);
                //DTO【自画面Session用】.対象条件へ初期値としてユーザーオーナーコードを設定する。
                getBunsAutoAmtRegistReqDto().setTargetOnerCd(((UIUserOner)(getBirdUserInfo().getUserOner().get(0))).getOnerCd());
                search();
            } else if(getBunsAutoAmtRegistDto().isTenpoUser()) {
                //５－２．店舗ユーザーの場合、データを再検索実行する
                //DTO【自画面Session用】.対象条件へ初期値として’店舗’を設定する。
                getBunsAutoAmtRegistReqDto().setTargetTaishoJoken(ConditionTaishoJoken.VALUE_MISE);
                //DTO【自画面Session用】.条件店舗コードへ初期値としてユーザー店舗コードを設定する。
                getBunsAutoAmtRegistReqDto().setTargetMiseCd(((UIUserMise)(getBirdUserInfo().getUserMise().get(0))).getMiseCd());
                search();
            }
        }
        //６．現ウィンドウID の保管データから【DTO】検索対象条件項目へ値の設定を行う。
        getBunsAutoAmtRegistDto().copyData(getBunsAutoAmtRegistReqDto());
        //７．変数【遷移先VIEW_DI】をリターンする。
        return viewId;
    }
    /**
     * 画面の｢戻る｣ボタンを押した場合
     * 
     * １．編集画面VIEWIDをリターンする。
     * 
     * 
     */
    public String back(){
        //１． sessionKey有効チェックを行う。
        if (!isValidSessionKey()) {
            //１－１． 有効でない場合は操作エラー画面に遷移
            return BunsAutoAmtRegistUtil.operationErr_VIEW_ID;
        }
        getBunsAutoAmtRegistReqDto().setBackViewId(BunsAutoAmtRegistUtil.VIEW_ID_CONFIRM);
        //２．自画面VIEWIDをリターンする。
        return BunsAutoAmtRegistUtil.VIEW_ID_EDIT;
    }
    /**
     * 画面の｢登録・終了｣ボタンを押した場合
     * 
     * １．編集画面VIEWIDをリターンする。
     * 
     * 
     */
    public String regist(){
        //１． sessionKey有効チェックを行う。
        if (!isValidSessionKey()) {
            //１－１． 有効でない場合は操作エラー画面に遷移
            return BunsAutoAmtRegistUtil.operationErr_VIEW_ID;
        }
        //２．ロジック【データ登録】を実行する。
        List listRegData = getBunsAutoAmtRegistDto().getListRegData();
        Map params = new HashMap();
        params.put(RegistLogic.PK_USERINFO, getBirdUserInfo());
        params.put(RegistLogic.PK_DATEINFO, getBirdDateInfo());
        params.put(RegistLogic.PK_LIST_REGDATA, listRegData);
        getBunsAutoAmtRegistRegistLogic().execute(params);
        getBunsAutoAmtRegistReqDto().setRegistedFlg("comp");
        //ユーザータイプ取得
        String userType = getBirdUserInfo().getMstUser().getUserTypeCd();
        if ("02".equals(userType) || "03".equals(userType)) {
        	getBunsAutoAmtRegistReqDto().setBackViewId(BunsAutoAmtRegistUtil.VIEW_ID_CONFIRM);
        }
        //３．編集画面VIEWIDをリターンする。
        return BunsAutoAmtRegistUtil.VIEW_ID_EDIT;
    }
    /**
     * 画面の｢終了｣ボタンを押した場合
     * 
     * １．初期画面VIEWIDをリターンする。
     * 
     * 
     */
    public String end(){
        //変数WindowID設定
        int windowId = getBunsAutoAmtRegistReqDto().getWindowId();
        getBunsAutoAmtRegistDto().setJokenTaishoJoken(windowId, ConditionTaishoJoken.VALUE_ONER);
        getBunsAutoAmtRegistDto().setJokenOnerCd(windowId, "");
        getBunsAutoAmtRegistDto().searchDataClear(windowId);
        //３．共通各種イベント申込状況確認画面VIEWIDをリターンする。
        return BunsAutoAmtRegistUtil.VIEW_ID_CONDITION;
    }
    /**
     * 実行ボタン処理
     * 
     */
    private String search() {
        //変数WindowID設定
        int windowId = getBunsAutoAmtRegistReqDto().getWindowId();
        try{
             //１．現ウィンドウID の検索対象条件項目値の保管を行う。
            getBunsAutoAmtRegistDto().holdJokenParam(getBunsAutoAmtRegistReqDto());
            //２．ロジック【検索対象報取得】を実行し、[[バンズ自動設定数量データ]]を取得する。            
            //２－１．ロジック【検索対象報取得】用パラメーターを生成する。
            Map params = new HashMap();
            params.put(SearchLogic.PK_USERINFO, getBirdUserInfo());
            params.put(SearchLogic.PK_SYSDATE, getBirdDateInfo().getSysDate());
            params.put(SearchLogic.PK_COMPANY_CD, getBunsAutoAmtRegistDto().getTargetCompanyCd());
            params.put(SearchLogic.PK_TAISHOJOKEN, getBunsAutoAmtRegistReqDto().getTargetTaishoJoken());
            params.put(SearchLogic.PK_ONER_CD, getBunsAutoAmtRegistReqDto().getTargetOnerCd());
            params.put(SearchLogic.PK_MISE_CD, getBunsAutoAmtRegistReqDto().getTargetMiseCd());
            params.put(SearchLogic.PK_SV_CD, getBunsAutoAmtRegistReqDto().getTargetSvCd());
            //２－２．ロジック【検索対象報取得】を実行する。
            Map rparams = getBunsAutoAmtRegistSearchLogic().execute(params);
            //２－１．処理２.結果戻り値から[[バンズ自動設定数量データ]]を取得する。
            List listSearchData = (List)rparams.get(SearchLogic.RK_LIST_SEARCHDATA);
            //２－２．処理２－１の[[バンズ自動設定数量データ]]の取得結果が該当データなしの場合は、下記の処理を行う。
            if (listSearchData == null || listSearchData.size() == 0) {
                throw new NoResultException();
            }
            //２－７．DTO【自画面Session用】.検索結果リストへ処理２－５[[バンズ自動設定数量データ]]の値を設定する。    
            getBunsAutoAmtRegistDto().setListSearchData(windowId, listSearchData);
            //２－８．DTO【自画面Session用】.検索結果の店舗一覧リストへ処理２－５[[検索結果の店舗一覧]]の値を設定する。    
            getBunsAutoAmtRegistDto().setListSearchDataMiseList(windowId, (List)rparams.get(SearchLogic.RK_LIST_SEARCHDATA_MISELIST));
        }
        catch (NoResultException noRltEx){
            //Ⅰ．前回検索結果データをクリアするため、DTO【自画面Session用】検索結果クリア処理を実行する。
            getBunsAutoAmtRegistDto().searchDataClear(windowId);
            //Ⅱ．Exception　MSG【E0102】を発生させる。
            throw noRltEx;
        }
        finally{
            //３．DTO【リクエスト用】へ対象データを設定する。
            getBunsAutoAmtRegistDto().copyData(getBunsAutoAmtRegistReqDto());
        }
        return null;
    }
    
    /**
	 * オーナーユーザーと店舗ユーザーの場合、初期データ設定処理
	 */
	private void init() {
		// 変数WindowID設定
		int windowId = getBunsAutoAmtRegistReqDto().getWindowId();
		// DTO【自画面Request用】.遷移元画面VIEW_IDへ初期画面VIEWIDを設定する。
		getBunsAutoAmtRegistReqDto().setBackViewId(
				BunsAutoAmtRegistUtil.VIEW_ID_CONFIRM);
		// １．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
		if (getPullDownMenuDto().isClearFlg()) {
			// １－１．DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
			getPullDownMenuDto().setClearFlg(false);
			// １－２．DTO【自画面Session用】MaxウィンドウIDを更新する。
			windowId = getBunsAutoAmtRegistDto().createWindowId();
			getBunsAutoAmtRegistReqDto().setWindowId(windowId);
			// 初期データ設定処理を行います。
			settingInitdata(windowId);
		}

	}
    
    /**
	 * 初期データ設定処理
	 * 
	 * @param windowId
	 */
    private void settingInitdata(int windowId) {
        //１－３．複数ウィンドウ制御用セッションKey生成
        String key = mkSession._makeSessionKey();
        //１－４．処理１－３で生成した複数ウィンドウ制御用セッションKeyをDTO【自画面Session用】へ設定する。
        getBunsAutoAmtRegistDto().setNowSessionKey(key);
        getBunsAutoAmtRegistDto().setSessionKey(windowId, key);
        getBunsAutoAmtRegistDto().setBirdDateInfo(getBirdDateInfo());
        getBunsAutoAmtRegistDto().setBirdUserInfo(getBirdUserInfo());

        //１－５．ロジック【条件項目の取得】を実行する。
        //パラメーター    BIRDユーザー情報
        //                システム日付
        //                汎用画面ロール情報
        Map params = new HashMap();
        params.put(ConditionLogic.PK_USERINFO, getBirdUserInfo());
        params.put(ConditionLogic.PK_SYSDATE, getBirdDateInfo().getSysDate());
        params.put(ConditionLogic.PK_DTO_GAMENROE, getGamenRoleDto());
        Map logigMap = getBunsAutoAmtRegistConditionLogic().execute(params);
                    
        //１－６．処理１－５の戻り値から、ダウンロード許可フラグを取得し、DTO【自画面Session用】へ設定する。
        getBunsAutoAmtRegistDto().setFlgDownload((String)logigMap.get(ConditionLogic.RK_FLG_DOWNLOAD));
        //１－７．処理１－５の戻り値から、ユーザー別照会制御用判断処理許可フラグを取得する。
        getBunsAutoAmtRegistDto().setFlgRegist((String)logigMap.get(ConditionLogic.RK_FLG_REGIST));
    }
    
    /**
     * Seaser2Containaer取得処理
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
    /**
     * BIRDユーザー情報取得処理
     * 
     * @return
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRD日付情報取得処理
     * 
     * @return
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * @return bunsAutoAmtRegistReqDto を戻します。
     */
    public BunsAutoAmtRegistReqDto getBunsAutoAmtRegistReqDto() {
        return bunsAutoAmtRegistReqDto;
    }
    /**
     * @param bunsAutoAmtRegistReqDto 設定する bunsAutoAmtRegistReqDto。
     */
    public void setBunsAutoAmtRegistReqDto(
            BunsAutoAmtRegistReqDto reqDto) {
        this.bunsAutoAmtRegistReqDto = reqDto;
    }
    /**
     * @return bunsAutoAmtRegistRegistLogic を戻します。
     */
    public RegistLogic getBunsAutoAmtRegistRegistLogic() {
        return bunsAutoAmtRegistRegistLogic;
    }
    /**
     * @param registLogic 設定する bunsAutoAmtRegistRegistLogic。
     */
    public void setBunsAutoAmtRegistRegistLogic(
            RegistLogic registLogic) {
        this.bunsAutoAmtRegistRegistLogic = registLogic;
    }
    /**
     * @return bunsAutoAmtRegistDto を戻します。
     */
    public BunsAutoAmtRegistDto getBunsAutoAmtRegistDto() {
        return bunsAutoAmtRegistDto;
    }
    /**
     * @param sessionDto 設定する bunsAutoAmtRegistDto。
     */
    public void setBunsAutoAmtRegistDto(
            BunsAutoAmtRegistDto sessionDto) {
        this.bunsAutoAmtRegistDto = sessionDto;
    }
    /**
     * 操作エラー判断処理
     * 
     * 現行のウィンドウが無効の場合は false
     *                   有効の場合は true
     * @return
     */
    private boolean isValidSessionKey(){
        return mkSession.isValidSessionKey( 
                getBunsAutoAmtRegistDto().getNowSessionKey()
                  ,  getBunsAutoAmtRegistDto().getSessionKey(getBunsAutoAmtRegistReqDto().getWindowId()) );
    }
    /**
     * @return bunsAutoAmtRegistSearchLogic を戻します。
     */
    public SearchLogic getBunsAutoAmtRegistSearchLogic() {
        return bunsAutoAmtRegistSearchLogic;
    }
    /**
     * @param bunsAutoAmtRegistSearchLogic 設定する bunsAutoAmtRegistSearchLogic。
     */
    public void setBunsAutoAmtRegistSearchLogic(
            SearchLogic bunsAutoAmtRegistSearchLogic) {
        this.bunsAutoAmtRegistSearchLogic = bunsAutoAmtRegistSearchLogic;
    }
    /**
     * @return bunsAutoAmtRegistConditionLogic を戻します。
     */
    public ConditionLogic getBunsAutoAmtRegistConditionLogic() {
        return bunsAutoAmtRegistConditionLogic;
    }
    /**
     * @param conditionLogic 設定する bunsAutoAmtRegistConditionLogic。
     */
    public void setBunsAutoAmtRegistConditionLogic(
            ConditionLogic conditionLogic) {
        this.bunsAutoAmtRegistConditionLogic = conditionLogic;
    }
    /**
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * @param pullDownMenuDto 設定する pullDownMenuDto。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    /**
     * @return gamenRoleDto を戻します。
     */
    public GamenRoleDto getGamenRoleDto() {
        return gamenRoleDto;
    }
    /**
     * @param gamenRoleDto 設定する gamenRoleDto。
     */
    public void setGamenRoleDto(GamenRoleDto gamenRoleDto) {
        this.gamenRoleDto = gamenRoleDto;
    }
}