/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.action.MosChickenSaleStateViewConditionAction;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dto.MosChickenSaleStateViewDto;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetSaleStateTotalInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetShohinGroupInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetTitleInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.util.MosChichenSaleStateUtil;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * モスチキン販売状況一覧　検索条件画面アクション
 * 
 * @author xlee
 */
public class MosChickenSaleStateViewConditionActionImpl implements MosChickenSaleStateViewConditionAction {
	
	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID = "BBS017A01";
    
	/** アクションID定義:初期化アクション */
    public static final String callOnerForm_ACTION_ID = "BBS017A02";
    
    /** アクションID定義:初期化アクション */
    public static final String execute_ACTION_ID = "BBS017A03";
    
    /** アクションID定義:初期化アクション */
    public static final String onchangeTitle_ACTION_ID = "BBS017A04";
    
    /** アクションID定義:初期化アクション */
    public static final String searchExecute_ACTION_ID = "BBS017A05";
    
    /** アクションID定義:初期化アクション */
    public static final String backMove_ACTION_ID = "BBS017A06";
	
    /** VIEWID定義:オーナー選択画面から戻る画面IDを保持 */
    private static final String VIEWID_RESULT    = "BBS017V02";

    /** VIEWID定義:オーナー選択画面から戻る画面IDを保持 */
    private static final String VIEWID_CONDITION    = "BBS017V01";
    
    /** VIEWID定義:オーナー選択画面 */
    private static final String VIEWID_ONERSEARCH = "BCO006V01";

	/** モスチキン販売状況一覧：session */
	private MosChickenSaleStateViewDto mosChickenSaleStateViewDto;    
	    
    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;
    
    /** オーナーコード取得ロジック */
    private GetShohinGroupInfoLogic getShohinGroupInfoLogic;
    
    /** オーナーコード取得ロジック */
    private GetTitleInfoLogic getTitleInfoLogic;
    
    /** オーナーコード取得ロジック */
    private GetMiseInfoLogic getMiseInfo;

    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /** オーナー情報 */    
    private OwnerSearchDto ownerSearchDto;
    
    /** もしチキン販売状況情報 */
    private GetSaleStateTotalInfoLogic getSaleStateTotalInfoLogic;

    
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
     * 日付情報を取得します。
     * @return 日付情報
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * 日付情報を設定します。
     * @param birdDateInfo 日付情報
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    
    /**
     * モスチキン販売状況一覧DTOを取得します。:session
     * @return  モスチキン販売状況一覧DTO
     */
    public MosChickenSaleStateViewDto getMosChickenSaleStateViewDto() {
        return mosChickenSaleStateViewDto;
    }

    /**
     *  モスチキン販売状況一覧DTOを設定します。
     * @param buyingListViewDto　 モスチキン販売状況一覧DTO
     */
    public void setMosChickenSaleStateViewDto(MosChickenSaleStateViewDto mosChickenSaleStateViewDto) {
        this.mosChickenSaleStateViewDto = mosChickenSaleStateViewDto;
    }
        
    /**
     * メニューDTOを取得します。
     * @return メニューDTO
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    /**
     * メニューDTOを設定します。
     * @param pullDownMenuDto メニューDTO
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
    /**
     * オーナーコード取得ロジックを取得します。
     * @return 　オーナーコード取得ロジック
     */
    public GetTitleInfoLogic getGetTitleInfoLogic() {
        return getTitleInfoLogic;
    }

    /**
     * オーナーコード取得ロジックを設定します。
     * @param getOnerCdLogic　オーナーコード取得ロジック
     */
    public void setGetTitleInfoLogic(GetTitleInfoLogic getTitleInfoLogic) {
        this.getTitleInfoLogic = getTitleInfoLogic;
    }
    
    /**
     * オーナーコード取得ロジックを取得します。
     * @return 　オーナーコード取得ロジック
     */
    public GetShohinGroupInfoLogic getGetShohinGroupInfoLogic() {
        return getShohinGroupInfoLogic;
    }

    /**
     * オーナーコード取得ロジックを設定します。
     * @param getOnerCdLogic　オーナーコード取得ロジック
     */
    public void setGetShohinGroupInfoLogic(GetShohinGroupInfoLogic getShohinGroupInfoLogic) {
        this.getShohinGroupInfoLogic = getShohinGroupInfoLogic;
    }
    
    /**
     * オーナーコード取得ロジックを取得します。
     * @return 　オーナーコード取得ロジック
     */
    public GetMiseInfoLogic getGetMiseInfoLogic() {
        return getMiseInfo;
    }

    /**
     * オーナーコード取得ロジックを設定します。
     * @param getOnerCdLogic　オーナーコード取得ロジック
     */
    public void setGetMiseInfoLogic(GetMiseInfoLogic getMiseInfo) {
        this.getMiseInfo = getMiseInfo;
    }
    
    /**
     * オーナーコード取得ロジックを取得します。
     * @return 　オーナーコード取得ロジック
     */
    public GetSaleStateTotalInfoLogic getGetSaleStateTotalInfoLogic() {
        return getSaleStateTotalInfoLogic;
    }

    /**
     * オーナーコード取得ロジックを設定します。
     * @param getOnerCdLogic　オーナーコード取得ロジック
     */
    public void setGetSaleStateTotalInfoLogic(GetSaleStateTotalInfoLogic getSaleStateTotalInfoLogic) {
        this.getSaleStateTotalInfoLogic = getSaleStateTotalInfoLogic;
    }
    
    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {
   	
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
        
        MosChickenSaleStateViewDto mosChickenSaleStateViewDto = (MosChickenSaleStateViewDto) container.getComponent("mosChickenSaleStateViewDto");
        
        GetTitleInfoLogic getTitleInfoLogic = (GetTitleInfoLogic) container.getComponent("moschickensalestateview.getTitleInfoLogic");
        GetShohinGroupInfoLogic getShohinGroupInfoLogic = (GetShohinGroupInfoLogic) container.getComponent("moschickensalestateview.getShohinGroupInfoLogic");
        GetMiseInfoLogic getMiseInfoLogic = (GetMiseInfoLogic) container.getComponent("moschickensalestateview.getMiseInfo");
        
    	// ユーザタイプ判定
        String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
        
        //アプリ日付
        String appDate = birdDateInfo.getAppDate();
        
        //システム日付
        String sysDate = birdDateInfo.getSysDate();
        
        // 初期処理
        if (pullDownMenuDto.isClearFlg()) {
        	//newWindowIDを設定
        	mosChickenSaleStateViewDto.updateWindowid();
        	//DTOクリア
        	mosChickenSaleStateViewDto.clear();
        	//ユーザータイプをセッションに保持
            mosChickenSaleStateViewDto.setUserTypeCd(userTypeCd);
            mosChickenSaleStateViewDto.setAppDate(appDate);
            mosChickenSaleStateViewDto.setActionKbn("");
        	//本部ユーザーの場合
            if (userTypeCd.equals(MosChichenSaleStateUtil.USER_TYPE_HONBU)) {
            	//検索項目の初期化:
            	mosChickenSaleStateViewDto.setCondOnerCd("");
            } else if (userTypeCd.equals(MosChichenSaleStateUtil.USER_TYPE_ONER) 
            		|| userTypeCd.equals(MosChichenSaleStateUtil.USER_TYPE_TENPO)) {
            
            	mosChickenSaleStateViewDto.setUserId(birdUserInfo.getMstUser().getUser_id());
            	//検索条件表示用データの取得
            	MosChichenSaleStateUtil.setConditionExecute(mosChickenSaleStateViewDto, 
            			getTitleInfoLogic,
            			getShohinGroupInfoLogic,
            			getMiseInfoLogic,
            			sysDate);
            }
        	//メニューフラグfalseにセット
        	pullDownMenuDto.setClearFlg(false);
        } else {
        	if(mosChickenSaleStateViewDto.getActionKbn().equals("BACK")) {
            	getMosChickenSaleStateViewDto().allClear();
            	//ユーザータイプをセッションに保持
                mosChickenSaleStateViewDto.setUserTypeCd(userTypeCd);
                mosChickenSaleStateViewDto.setAppDate(appDate);
                mosChickenSaleStateViewDto.setCondOnerCd("");
                mosChickenSaleStateViewDto.setActionKbn("");
            }
            else if(getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT){
                //【DTO】//オーナー選択.遷移情報を無効に設定する。
            	getOwnerSearchDto().setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
                //DTO【販売状況一覧】ウィンドウIDにDTO【オーナー選択】.ウィンドウIDを設定する。
                mosChickenSaleStateViewDto.setWindowId(getOwnerSearchDto().getWindowId());
				// オーナー選択から戻り
				if(getOwnerSearchDto().isActionFlag()){
		            //オーナーコードの設定
					mosChickenSaleStateViewDto.setCondOnerCd(getOwnerSearchDto().getOnerCd());
					//検索条件の設定
					execute();
					getOwnerSearchDto().setActionFlag(false);
				}
            }
        }
        // 自画面へ遷移
        return null;
    }

    /**
     * オーナー検索フォーム
     * 
     * @return　オーナー検索画面
     */
    public String callOnerForm() {
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(VIEWID_CONDITION);
        getOwnerSearchDto().setInitFlag(true);
        //複数WindowID
        getOwnerSearchDto().setWindowId(getMosChickenSaleStateViewDto().getWindowId());
        //オーナー選択.遷移情報を有効に設定。
        getOwnerSearchDto().setNeedReturnKind(true);
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(getMosChickenSaleStateViewDto().getCondCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        //オーナーコードを保持
        getMosChickenSaleStateViewDto().setCondOnerCd(getMosChickenSaleStateViewDto().getCondOnerCd());
        
        return VIEWID_ONERSEARCH;
    }
    
    //オーナー検索
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}
	
    //オーナー検索
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}
	
    /**
     * オーナーコードに対する検索条件の取得処理
     * 
     * @return 画面遷移情報
     */
	public String execute() {
    	//　オーナーコードチェック
    	validate();
    	getMosChickenSaleStateViewDto().setActionKbn("");
    	String sysDate = getBirdDateInfo().getSysDate();
    	//検索条件リストを取得する
    	MosChichenSaleStateUtil.setConditionExecute(getMosChickenSaleStateViewDto(),
    			getGetTitleInfoLogic(),
    			getGetShohinGroupInfoLogic(),
    			getGetMiseInfoLogic(),
    			sysDate);
		return null;
	}
	
    /**
     * タイトル切替処理
     * 
     * @return　画面遷移情報
     */
    public String onchangeTitle() {
    	//検索の処理を実行する
    	MosChichenSaleStateUtil.setChangeTitleExecute(getMosChickenSaleStateViewDto());

        return null;
    }
    
    /**
     * 検索処理
     * 
     * @return　画面遷移情報
     */    
	public String searchExecute() {
		
        //DAOを実行する為のパラメータ
        String sysDate = getBirdDateInfo().getSysDate();
        
        //ロジックの実行
        List searchResultList = getGetSaleStateTotalInfoLogic().execute(getMosChickenSaleStateViewDto(), sysDate);
        
        MosChichenSaleStateUtil.setResult(getMosChickenSaleStateViewDto(), searchResultList, sysDate);
		
		return VIEWID_RESULT;
	}
	
    /**
     * オーナーコード入力画面へ戻る処理
     * 
     * @return　画面遷移情報
     */	
	public String backMove() {
		getMosChickenSaleStateViewDto().setActionKbn("BACK");
		return null;
	}
	
    /**
     * 入力チェック
     */
    private void validate() {
        // オーナーコード必須チェック
    	String onerCd = getMosChickenSaleStateViewDto().getCondOnerCd();
    	
    	//オーナーコード未入力
		if (onerCd == null || onerCd.equals("")) {
			throw new NoInputException("オーナーコード");
		}
        // 半角数字
		CodeVerifier codeVerifier = new CodeVerifier();
		if (onerCd != null && !onerCd.equals("")
				&& !codeVerifier.validate(onerCd)) {
			throw new InvalidInputException("オーナーコード", "onerCd", "");
		}
    }

}
