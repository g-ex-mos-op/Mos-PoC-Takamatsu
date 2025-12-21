/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.action.impl;

import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanoffer.action.ProjectPlanOfferIninAction;
import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.CheckOfferInputParamLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferMiseInfoLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferRegistLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.util.ProjectPlanOfferUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * モスチキン販売状況一覧　検索条件画面アクション
 * 
 * @author xlee
 */
public class ProjectPlanOfferIninActionImpl implements ProjectPlanOfferIninAction {
	
	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID =  "BEN011A04";
    
    /** アクションID定義:初期化アクション */
    public static final String registOffer_ACTION_ID = "BEN011A05";
    
    /** アクションID定義:初期化アクション */
    public static final String backMove_ACTION_ID = "BEN011A06";
	
    /** VIEWID定義:編集画面へ戻る画面IDを保持 */
    private static final String VIEWID_EDIT    = "BEN011V01";
    
    /** VIEWID定義:確認画面へ遷移する画面IDを保持 */
    private static final String VIEWID_CONFIRM = "BEN011V03";
    
    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;
    
	/** 事業方針説明会申込：session */
	private ProjectPlanOfferDto projectPlanOfferDto;
	
    /** 事業方針説明会申込　入力データチェックロジック */
    private CheckOfferInputParamLogic checkOfferInputParamLogic;
    
    /** 事業方針説明会申込 店情報取得ロジック*/
    private GetOfferMiseInfoLogic getOfferMiseInfoLogic;
    
    /** 事業方針説明会申込 登録処理ロジック*/
    private GetOfferRegistLogic getOfferRegistLogic;

    /**
     * ユーザ関連情報を取得します。
     * @return ユーザ関連情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ユーザ関連情報を設定します。
     * @param birdUserInfo ユーザ関連情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * 事業方針説明会申込DTOを取得します。:session
     * @return  事業方針説明会申込DTO
     */
    public ProjectPlanOfferDto getProjectPlanOfferDto() {
        return projectPlanOfferDto;
    }

    /**
     *  事業方針説明会申込DTOを設定します。
     * @param buyingListViewDto　 事業方針説明会申込DTO
     */
    public void setProjectPlanOfferDto(ProjectPlanOfferDto projectPlanOfferDto) {
        this.projectPlanOfferDto = projectPlanOfferDto;
    }
    
    /**
     * 入力情報チェック処理ロジックを取得します。
     * @return 　入力情報チェック処理ロジック
     */
    public CheckOfferInputParamLogic getCheckOfferInputParamLogic() {
        return checkOfferInputParamLogic;
    }

    /**
     * 入力情報チェック処理ロジックを設定します。
     * @param checkOfferInputParamLogic　入力情報チェック処理ロジック
     */
    public void setCheckOfferInputParamLogic(CheckOfferInputParamLogic checkOfferInputParamLogic) {
        this.checkOfferInputParamLogic = checkOfferInputParamLogic;
    }
    
    /**
     *店情報取得ロジックを取得します。
     * @return 　店情報取得
     */
    public GetOfferMiseInfoLogic getGetOfferMiseInfoLogic() {
        return getOfferMiseInfoLogic;
    }

    /**
     * 店情報取得ロジックを設定します。
     * @param getOfferMiseInfoLogic　店情報取得
     */
    public void setGetOfferMiseInfoLogic(GetOfferMiseInfoLogic getOfferMiseInfoLogic) {
        this.getOfferMiseInfoLogic = getOfferMiseInfoLogic;
    }
    /**
     * 登録処理ロジックを取得します。
     * @return 　登録処理
     */
    public GetOfferRegistLogic getOfferRegistLogic() {
        return getOfferRegistLogic;
    }

    /**
     * 登録処理ロジックを設定します。
     * @param getOfferRegistLogic　登録処理
     */
    public void setGetOfferRegistLogic(GetOfferRegistLogic getOfferRegistLogic) {
        this.getOfferRegistLogic = getOfferRegistLogic;
    }
    
    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        //事業方針説明会申込のDTOの取得
        ProjectPlanOfferDto projectPlanOfferDto = (ProjectPlanOfferDto) container.getComponent("projectPlanOfferDto");
        
        //複数ウィンドウセッションキー生成
        MakeSessionKey make = new MakeSessionKey();
        String sessionKey = make._makeSessionKey();
        
        projectPlanOfferDto.setViewSessionKey( sessionKey );
        projectPlanOfferDto.setSessioniKey( sessionKey );
        
        //店の一号店が選択されるように
    	projectPlanOfferDto = ProjectPlanOfferUtil.choiceMise(
    			projectPlanOfferDto, getCheckOfferInputParamLogic());
    	
    	//登録内容があれば、画面へその情報を表示
        // 自画面へ遷移
        return null;
    }
	
    /**
     * 登録・終了処理
     * 
     * @return　画面遷移情報
     */
    public String registOffer() {
		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        
        if (!make.isValidSessionKey( getProjectPlanOfferDto().getViewSessionKey(),getProjectPlanOfferDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
    	Map paramChkMap = ProjectPlanOfferUtil.setEntityRegInfoMap(
    			getProjectPlanOfferDto(), 
    			getCheckOfferInputParamLogic());
    	////2007.1.15　登録・終了が行われる画面モードを設定
    	getProjectPlanOfferDto().setValChkMode(ProjectPlanOfferDto.VALCHK_MODE_ININ);
    	getCheckOfferInputParamLogic().validater(getProjectPlanOfferDto(), paramChkMap);
        
    	//DBへ格納するデータを設定
    	Map paramMap = ProjectPlanOfferUtil.setRegistInfo(
    			getProjectPlanOfferDto(),
    			paramChkMap, 
    			getCheckOfferInputParamLogic());
    	
		paramMap.put(ProjectPlanOfferDto.MAP_KEY_USERID, getBirdUserInfo().getUserID());
		//8.確認用の情報
		getProjectPlanOfferDto().setPrmInfo(paramMap);
    	getOfferRegistLogic().execute(paramMap);
    	
        return VIEWID_CONFIRM;
    }
    
    /**
     * 編集画面へ戻る処理
     */
	public String backMove() {
		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        
        if (!make.isValidSessionKey( getProjectPlanOfferDto().getViewSessionKey(),getProjectPlanOfferDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
		//戻るタブが選択されるように
		getProjectPlanOfferDto().setPrmTabNo(getProjectPlanOfferDto().getPrmTabNo());
		return VIEWID_EDIT;
	}
}
