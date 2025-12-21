/*
 * 作成日: 2006/04/10
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.logic.GetSibuTorikomiLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.storemanage.common.util.StoreManageUtil;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.MlCompletionregistAction;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.MlCompletionregistCondAction;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.AllClearLogic;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl.ConditionLogicImpl;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl.EditLogicImpl;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl.SibuListLogicImpl;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.util.MlCompletionregistUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * マスタライセンス研修修了登録
 * 条件画面アクション
 * 
 * @author xkinu
 */
public class MlCompletionregistCondActionImpl 
    implements MlCompletionregistCondAction {
	
    /* アクションID:初期処理 BSM006A01 */
    public static String initialize_ACTIONID    = ACTIONID+"1";
    /* アクションID:会社プルダウン変更時処理 BSM006A02 */
    public static String changeCompany_ACTIONID = ACTIONID+"2";
    /* アクションID:実行 BSM006A03 */
    public static String execute_ACTIONID       = ACTIONID+"3";
    /* アクションID:全クリア BSM006A04 */
    public static String allClear_ACTIONID      = ACTIONID+"4";
    
	/** 共通ロジック【ユーザの汎用画面ロール制御情報取得】*/
    private GetGamenRoleLogic gamenRoleLogic;
    
    /*【ロジック】条件画面出力データ検索ロジック */
    private ConditionLogicImpl conditionLogic;
    /*【ロジック】条件画面会社プルダウン変更時出力支部データ検索ロジック */
    private SibuListLogicImpl sibuListLogic;
    /*【ロジック】編集データ検索ロジック*/
    private EditLogicImpl editLogic;
    /*【ロジック】支部取込コードによる支部情報取得ロジック*/
    private GetSibuTorikomiLogic sibuTorikomiLogic;
    /* ロジック【ライセンス保持者管理更新研修情報全クリア】*/
    private AllClearLogic mlCompletionregistAllClearLogic;

    /*【DTO】*/
    private MlCompletionregistDto completionregistDto;
    
    /**
     * 初期処理
     * @return null
     */
    public String initialize() {

        if (getPullDownMenuDto().isClearFlg()) {
            //Dto初期化処理
    		getMlCompletionregistDto().initClear();
            //Dtoへユーザータイプ設定
            getMlCompletionregistDto().setUsertypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            //DtoへユーザーID設定
            getMlCompletionregistDto().setUserId(getBirdUserInfo().getUserID());
	    	//Dtoへシステム日付設定
	    	getMlCompletionregistDto().setToday(getBirdDateInfo().getSysDate());
            //Dtoへ制限区分設定
            getMlCompletionregistDto().setLimit(getBirdUserInfo().isLimit());


	    	//----------------------------------------
            // ロジック【条件画面出力データ検索】
            //----------------------------------------
            Map logigMap = getConditionLogic().execute(getMlCompletionregistDto());

            // 研修選択タイトルデータ取得
            getMlCompletionregistDto().setMapEntryTitle((Map)logigMap.get("mapEntryTitle"));
            // 会社データ取得
            getMlCompletionregistDto().setCompanyList((List)logigMap.get("listCompany"));
            // 支部データ取得
            getMlCompletionregistDto().setSibuList((List)logigMap.get("listSibu"));
            
	        //エントリーコード初期値設定
	        getMlCompletionregistDto().setInitEntryCd();
            
            //画面遷移情報設定
            getMlCompletionregistDto().setScenechangedKbn(MlCompletionregistDto.SCENECHANGE_KBN_SELF);
            
            //全クリア可能ユーザ判断処理
            boolean mhaUser = StoreManageUtil.isMhaUser(getGamenRoleLogic()
            		, getBirdUserInfo().getUserID()
            		, MlCompletionregistUtil.SCREEN_ID
            		, MlCompletionregistUtil.BUNRUI_ALL_CLEAR);
            getMlCompletionregistDto().setAllClear(mhaUser);
            
            getPullDownMenuDto().setClearFlg(false);
    	}

    	// 編集画面 一括設定 入力値初期化
    	completionregistDto.setCourseListIndex(null);
    	completionregistDto.setParamCourseStatus("1");
    	completionregistDto.setSetUpYm(null);

    	return null;
    }

    
    /**
     * 会社プルダウン変更処理
     */
    public String changeCompany() {

        //// ロジック【条件画面出力データ検索】.会社プルダウン変更処理
        //Map logigMap = getSibuListLogic().execute(getMlCompletionregistDto());    	
        //// 支部データ取得
        //getMlCompletionregistDto().setSibuList((List)logigMap.get("listSibu"));

        //-------------------------------------------
        // 支部リストの再取得(支部取込コード)
        //-------------------------------------------
        //DTOより情報を取得
        String companyCd = getMlCompletionregistDto().getCompanyCd();
        String userId    = getMlCompletionregistDto().getUserId();
        boolean limit   = getMlCompletionregistDto().isLimit();
 
        //リスト取得
        List sibuTorikomiList = sibuTorikomiLogic.execute(companyCd, userId, limit);
//        if (sibuTorikomiList == null || sibuTorikomiList.size() == 0) {
//            throw new NotExistException("支部情報");
//        }
        
        //DTOにセット
        getMlCompletionregistDto().setSibuList(sibuTorikomiList);
        
        
        // 条件画面-->条件画面の遷移区分を立てる
        getMlCompletionregistDto().setScenechangedKbn(
        		MlCompletionregistDto.SCENECHANGE_KBN_SELF);
        return null;
    }

    /**
     * 実行ボタン押下処理
     * @return  VIEWID_EDIT
     */
    public String execute() {

        //入力チェック
        validate(getMlCompletionregistDto());
       
        //条件項目データ設定処理
    	getMlCompletionregistDto().setEntryData();

        // ロジック【編集画面出力データ検索】
        Map logigMap = getEditLogic().execute(getMlCompletionregistDto());
        if(getMlCompletionregistDto().isRenewalEntry()) {
            // コースデータ取得&設定
            getMlCompletionregistDto().setCourseList((List)logigMap.get("listCourse"));
        }

        // 編集用データ取得&設定
        getMlCompletionregistDto().setRegistDataList((List)logigMap.get("listStaff"));

        // 条件画面-->編集画面の遷移区分を立てる
        getMlCompletionregistDto().setScenechangedKbn(
        		MlCompletionregistDto.SCENECHANGE_KBN_COND_TO_EDIT);

        return MlCompletionregistAction.VIEWID_EDIT;
    }

    
    /**
     * 入力チェック
     */
    private void validate(MlCompletionregistDto dto) {

        //入力値を取得
        String sibuCd = dto.getSibuCd();            //支部(取込)コード
        boolean optionFlg = dto.getOptionFlg();    //オプション

        
        //「エントリー者のみ表示」がOFFの時、支部選択必須チェック
        if(!optionFlg && (sibuCd == null || sibuCd.length() ==0)){
            String msg = "エントリー者のみをチェックしていない場合、支部選択";
            throw new NotNullException(msg, "sibuCd", 0);
        }
    }
	/**
	 * 全クリア
	 * 
	 * @see jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.MlCompletionregistCondAction#allClear()
	 */
	public String allClear() {
		//１．研修修了情報全クリア可能ユーザ判断フラグがfalseの場合
		//    E0402「アクセス権限がありません」のExceptionを発生させます。
        if(!getMlCompletionregistDto().isAllClear()){
            throw new CannotAccessException();
        }
        //２．ロジック【ライセンス保持者管理更新研修情報全クリア】を実行します。
        getMlCompletionregistAllClearLogic().execute(getMlCompletionregistDto());
        //３．Nullをリターン。
        return null;
	}

    /**
     * 戻るボタン押下処理
     * @return null
     */
    public String back() {
        return null;
    }
    

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }

    /**
     * メニューDTO取得処理
     * @return PullDownMenuDto メニューDTOを戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    
    /**
     * 条件画面出力データ検索ロジック取得処理
     * @return companyJohoLogic を戻します。
     */
    public ConditionLogicImpl getConditionLogic() {
        return conditionLogic;
    }
    /**
     * 条件画面出力データ検索ロジック設定処理
     * @param conditionLogic を設定。
     */
    public void setConditionLogic(ConditionLogicImpl conditionLogic) {
        this.conditionLogic = conditionLogic;
    }
    /**
     * 条件画面会社プルダウン変更時出力支部データ検索ロジック取得処理
     * @return companyJohoLogic を戻します。
     */
    public SibuListLogicImpl getSibuListLogic() {
        return sibuListLogic;
    }
    /**
     * 条件画面会社プルダウン変更時出力支部データ検索ロジック設定処理
     * @param conditionLogic を設定。
     */
    public void setSibuListLogic(SibuListLogicImpl sibuListLogic) {
        this.sibuListLogic = sibuListLogic;
    }

    /**
     * 条件画面出力データ検索ロジック取得処理
     * @return editLogic を戻します。
     */
    public EditLogicImpl getEditLogic() {
        return editLogic;
    }
    /**
     * 条件画面出力データ検索ロジック設定処理
     * @param editLogic を設定。
     */
    public void setEditLogic(EditLogicImpl logic) {
        this.editLogic = logic;
    }
    /**
     * @return mlCompletionregistDto を戻します。
     */
    public MlCompletionregistDto getMlCompletionregistDto() {
        return this.completionregistDto;
    }
    /**
     * @param mlCompletionregistDto mlCompletionregistDto を設定。
     */
    public void setMlCompletionregistDto(MlCompletionregistDto completionregistDto) {
        this.completionregistDto = completionregistDto;
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * @return sibuTorikomiLogic を戻します。
     */
    public GetSibuTorikomiLogic getSibuTorikomiLogic() {
        return sibuTorikomiLogic;
    }
    /**
     * @param sibuTorikomiLogic 設定する sibuTorikomiLogic。
     */
    public void setSibuTorikomiLogic(GetSibuTorikomiLogic sibuTorikomiLogic) {
        this.sibuTorikomiLogic = sibuTorikomiLogic;
    }


	/**
	 * 共通ロジック【ユーザの汎用画面ロール制御情報取得】取得処理
	 * 
	 * @return gamenRoleLogic を戻します。
	 */
	public GetGamenRoleLogic getGamenRoleLogic() {
		return gamenRoleLogic;
	}


	/**
	 * 共通ロジック【ユーザの汎用画面ロール制御情報取得】設定処理
	 * 
	 * @param gamenRoleLogic 設定する gamenRoleLogic。
	 */
	public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
		this.gamenRoleLogic = gamenRoleLogic;
	}


	/**
	 * ロジック【ライセンス保持者管理更新研修情報全クリア】取得処理
	 * 
	 * @return mlCompletionregistAllClearLogic を戻します。
	 */
	public AllClearLogic getMlCompletionregistAllClearLogic() {
		return mlCompletionregistAllClearLogic;
	}


	/**
	 * ロジック【ライセンス保持者管理更新研修情報全クリア】設定処理
	 * 
	 * @param mlCompletionregistAllClearLogic 設定する logic。
	 */
	public void setMlCompletionregistAllClearLogic(
			AllClearLogic logic) {
		this.mlCompletionregistAllClearLogic = logic;
	}


}