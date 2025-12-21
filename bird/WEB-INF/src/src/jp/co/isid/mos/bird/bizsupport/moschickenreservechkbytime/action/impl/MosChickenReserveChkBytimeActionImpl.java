/*
 * 作成日: 2006/10/12
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.action.MosChickenReserveChkBytimeAction;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dto.MosChickenReserveChkBytimeDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.GetTitleInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.util.MosChickenReserveChkBytimeUtil;
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
 * 時間帯別予約状況確認表　検索条件画面アクション
 * 
 * @author xlee
 */
public class MosChickenReserveChkBytimeActionImpl implements MosChickenReserveChkBytimeAction {
	
	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID = "BBS019A01";
    
	/** アクションID定義:オーナー検索アクション */
    public static final String callOnerForm_ACTION_ID = "BBS019A02";
    
    /** アクションID定義:実行アクション */
    public static final String execute_ACTION_ID = "BBS019A03";
    
    /** アクションID定義:タイトル切替アクション */
    public static final String onchangeTitle_ACTION_ID = "BBS019A04";
    
    /** アクションID定義:戻るアクション */
    public static final String backMove_ACTION_ID = "BBS019A05";
    
    /** VIEWID定義:オーナー選択画面 */
    private static final String VIEWID_CONDITION = "BBS019V01";
	
    /** VIEWID定義:オーナー選択画面 */
    private static final String VIEWID_ONERSEARCH = "BCO006V01";

	/** モスチキン販売状況一覧：session */
	private MosChickenReserveChkBytimeDto mosChickenReserveChkBytimeDto;    
	    
    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;
    
    /** オーナーコード取得ロジック */
    private GetTitleInfoLogic getMosChickenByTimeTitleInfoLogic;
    
    /** オーナーコード取得ロジック */
    private GetMiseInfoLogic getMosChickenByTimeMiseInfoLogic;

    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /** オーナー情報 */    
    private OwnerSearchDto ownerSearchDto;
   
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
     * 時間帯別予約状況確認表DTOを取得します。:session
     * @return  時間帯別予約状況確認表DTO
     */
    public MosChickenReserveChkBytimeDto getMosChickenReserveChkBytimeDto() {
        return mosChickenReserveChkBytimeDto;
    }

    /**
     *  時間帯別予約状況確認表DTOを設定します。
     * @param buyingListViewDto　 時間帯別予約状況確認表DTO
     */
    public void setMosChickenReserveChkBytimeDto(MosChickenReserveChkBytimeDto mosChickenReserveChkBytimeDto) {
        this.mosChickenReserveChkBytimeDto = mosChickenReserveChkBytimeDto;
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
     * タイトル情報取得ロジックを取得します。
     * @return 　タイトル情報取得ロジック
     */
    public GetTitleInfoLogic getGetTitleInfoLogic() {
        return getMosChickenByTimeTitleInfoLogic;
    }

    /**
     * タイトル情報取得ロジックを設定します。
     * @param getOnerCdLogic　タイトル情報取得ロジック
     */
    public void setGetTitleInfoLogic(GetTitleInfoLogic getMosChickenByTimeTitleInfoLogic) {
        this.getMosChickenByTimeTitleInfoLogic = getMosChickenByTimeTitleInfoLogic;
    }
   
    /**
     * 店情報取得ロジックを取得します。
     * @return 　店情報取得ロジック
     */
    public GetMiseInfoLogic getGetMiseInfoLogic() {
        return getMosChickenByTimeMiseInfoLogic;
    }

    /**
     * 店情報取得ロジックを設定します。
     * @param getOnerCdLogic　店情報取得ロジック
     */
    public void setGetMiseInfoLogic(GetMiseInfoLogic getMosChickenByTimeMiseInfoLogic) {
        this.getMosChickenByTimeMiseInfoLogic = getMosChickenByTimeMiseInfoLogic;
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
        
        MosChickenReserveChkBytimeDto mosChickenReserveChkBytimeDto = (MosChickenReserveChkBytimeDto) container.getComponent("mosChickenReserveChkBytimeDto");
       
        GetTitleInfoLogic getMosChickenByTimeTitleInfoLogic = (GetTitleInfoLogic) container.getComponent("moschickenreservechkbytime.getMosChickenByTimeTitleInfoLogic");
        GetMiseInfoLogic getMosChickenByTimeMiseInfoLogic = (GetMiseInfoLogic) container.getComponent("moschickenreservechkbytime.getMosChickenByTimeMiseInfoLogic");
        
    	// ユーザタイプ判定
        String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
        
        //システム日付
        String sysDate = birdDateInfo.getSysDate();
        
        // 初期処理
        if (pullDownMenuDto.isClearFlg()) {
        	//newWindowIDを設定
        	mosChickenReserveChkBytimeDto.updateWindowid();
        	
        	//メニューから遷移された場合、DTO初期化する
        	mosChickenReserveChkBytimeDto.clear();
            
        	//ユーザータイプ/システム日付/アクション区分をセッションに保持
        	mosChickenReserveChkBytimeDto.setUserTypeCd(userTypeCd);
        	mosChickenReserveChkBytimeDto.setSysDate(sysDate);
        	mosChickenReserveChkBytimeDto.setActionKbn("");
            
        	//本部ユーザーの場合
            if (userTypeCd.equals(MosChickenReserveChkBytimeUtil.USER_TYPE_HONBU)) {
            	//検索項目の初期化:
            	mosChickenReserveChkBytimeDto.setCondOnerCd("");
            } else if (userTypeCd.equals(MosChickenReserveChkBytimeUtil.USER_TYPE_ONER) 
            		|| userTypeCd.equals(MosChickenReserveChkBytimeUtil.USER_TYPE_TENPO)) {
            	//ログインユーザIDをセット
            	mosChickenReserveChkBytimeDto.setUserId(birdUserInfo.getMstUser().getUser_id());
            	//検索条件表示用データの取得
            	MosChickenReserveChkBytimeUtil.setConditionExecute(mosChickenReserveChkBytimeDto, 
            			getMosChickenByTimeTitleInfoLogic,
            			getMosChickenByTimeMiseInfoLogic);
            }
        	//メニューフラグfalseにセット
        	pullDownMenuDto.setClearFlg(false);
        } else {
            if(mosChickenReserveChkBytimeDto.getActionKbn().equals("BACK")) {
            	getMosChickenReserveChkBytimeDto().allClear();
            	//ユーザータイプをセッションに保持
            	mosChickenReserveChkBytimeDto.setUserTypeCd(userTypeCd);
            	mosChickenReserveChkBytimeDto.setSysDate(sysDate);
            	mosChickenReserveChkBytimeDto.setCondOnerCd("");
            	mosChickenReserveChkBytimeDto.setActionKbn("");
            } else if(getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT){
	            //【DTO】//オーナー選択.遷移情報を無効に設定する。
	        	getOwnerSearchDto().setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
	            //DTO【販売状況一覧】ウィンドウIDにDTO【オーナー選択】.ウィンドウIDを設定する。
	        	mosChickenReserveChkBytimeDto.setWindowId(getOwnerSearchDto().getWindowId());
				// オーナー選択から戻り
				if(getOwnerSearchDto().isActionFlag()){
		            //オーナーコードの設定
					mosChickenReserveChkBytimeDto.setCondOnerCd(getOwnerSearchDto().getOnerCd());
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
        getOwnerSearchDto().setWindowId(getMosChickenReserveChkBytimeDto().getWindowId());
        //オーナー選択.遷移情報を有効に設定。
        getOwnerSearchDto().setNeedReturnKind(true);
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(getMosChickenReserveChkBytimeDto().getCondCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        //タイトルがない場合のエラー値が設定されているとクリア
        if(getMosChickenReserveChkBytimeDto().getErrFlg()) {
        	getMosChickenReserveChkBytimeDto().setErrFlg(false);
        }
        //オーナーコードを保持
        getMosChickenReserveChkBytimeDto().setCondOnerCd(getMosChickenReserveChkBytimeDto().getCondOnerCd());
        
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
        //タイトルがない場合のエラー値が設定されているとクリア
        if(getMosChickenReserveChkBytimeDto().getErrFlg()) {
        	getMosChickenReserveChkBytimeDto().setErrFlg(false);
        }		
    	//　オーナーコードチェック
    	validate();
    	getMosChickenReserveChkBytimeDto().setActionKbn("");
    	//検索条件リストを取得する
    	MosChickenReserveChkBytimeUtil.setConditionExecute(getMosChickenReserveChkBytimeDto(),
    			getGetTitleInfoLogic(),
    			getGetMiseInfoLogic());
		return null;
	}
	
    /**
     * タイトル切替処理
     * 
     * @return　画面遷移情報
     */
    public String onchangeTitle() {
    	//検索の処理を実行する
    	MosChickenReserveChkBytimeUtil.setChangeTitleExecute(getMosChickenReserveChkBytimeDto(), getGetMiseInfoLogic(), getBirdDateInfo().getSysDate());

        return null;
    }
    
    /**
     * オーナーコード入力画面へ戻る処理
     * 
     * @return　画面遷移情報
     */	
	public String backMove() {
		getMosChickenReserveChkBytimeDto().setActionKbn("BACK");
		return null;
	}
	
    /**
     * 入力チェック
     */
    private void validate() {
        // オーナーコード必須チェック
    	String onerCd = getMosChickenReserveChkBytimeDto().getCondOnerCd();
    	
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
