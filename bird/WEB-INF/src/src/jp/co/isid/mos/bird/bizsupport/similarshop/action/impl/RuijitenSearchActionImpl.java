package jp.co.isid.mos.bird.bizsupport.similarshop.action.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.bizsupport.similarshop.action.RuijitenSearchAction;
import jp.co.isid.mos.bird.bizsupport.similarshop.dto.RuijitenReferenceDto;
import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIOnerTenpoInfo;
import jp.co.isid.mos.bird.bizsupport.similarshop.logic.GetOnerTenpoInfoLogic;
import jp.co.isid.mos.bird.bizsupport.similarshop.logic.GetPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.similarshop.logic.GetTenpoInfoLogic;
import jp.co.isid.mos.bird.bizsupport.similarshop.logic.GetUserOnerCdLogic;
import jp.co.isid.mos.bird.bizsupport.similarshop.logic.impl.GetRuijitenGraphLogicImpl;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.impl.GraphOutputActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class RuijitenSearchActionImpl implements CommonAction, RuijitenSearchAction {

    /* アクションID */
    public static String initialize_ACTION_ID        = "BBS008A01";
    public static String execute_ACTION_ID           = "BBS008A02";
    public static String callMiseInfo_ACTION_ID      = "BBS008A03";
    public static String callMiseInfoResult_ACTION_ID = "BBS008A04";
    
    /* ビューID */
    private static final String VIEWID_CONDITION    = "BBS008V01"; //条件画面
    private static final String VIEWID_RESULT       = "BBS008V02"; //結果画面
    private static final String VIEWID_MISESEARCH   = "BCO008V01";//店検索
    
    /* ユーザータイプ */
    // ユーザータイプ：０１ 本部
    private static final String USER_TYPE_HONBU = "01";
    // ユーザータイプ：０２ オーナー
    private static final String USER_TYPE_ONER = "02";
    // ユーザータイプ：０３ 店舗
    private static final String USER_TYPE_MISE = "03";

    /* ACTION */
    // グラフ作成Action //
    private GraphOutputActionImpl graphOutputActionImpl;
    
    /* LOGIC */
    // オーナー保有店舗の検索Logic //
    private GetOnerTenpoInfoLogic getOnerTenpoInfoLogic;
    // PL情報の取得Logic //
    private GetPLDataLogic getPLDataLogic;
    // 対象店舗情報の取得Logic //
    private GetTenpoInfoLogic getTenpoInfoLogic;
    // ユーザーオーナーコード情報の取得Logic //
    private GetUserOnerCdLogic getUserOnerCdLogic;
    // グラフ作成Logic //
    private GetRuijitenGraphLogicImpl getRuijitenGraphLogicImpl;
    
    
    /* DTO */
    // 類似店照会用Dto //
    private RuijitenReferenceDto ruijitenReferenceDto;
    // 類似店照会用CommonDto //
    private RuijitenReferenceDto ruijitenCommonDto;
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;
    /* miseSearchDto */
    private MiseSearchDto miseSearchDto;
    

    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /* 別ウィンドウ表示フラグ */
    private boolean newWindowFlg;



    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

	////////////////////
	/*     ACTION     */
	////////////////////
	/**
	 * グラフ作成Actionの設定
	 * @return graphOutputActionImpl を戻します。
	 */
	public GraphOutputActionImpl getGraphOutputActionImpl() {
		return graphOutputActionImpl;
	}
	/**
	 * グラフ作成Actionの設定
	 * @param graphOutputActionImpl を設定。
	 */
	public void setGraphOutputActionImpl(GraphOutputActionImpl graphOutputActionImpl) {
		this.graphOutputActionImpl = graphOutputActionImpl;
	}
	
	/////////////////
	/*     DTO     */
	/////////////////
	/**
	 * 類似店検索用dtoの設定
	 * @return ruijitenReferenceDto を戻します。
	 */
	public RuijitenReferenceDto getRuijitenReferenceDto() {
		return ruijitenReferenceDto;
	}
	/**
	 * 類似店検索用dtoの設定
	 * @param ruijitenReferenceDto を設定。
	 */
	public void setRuijitenReferenceDto(RuijitenReferenceDto ruijitenReferenceDto) {
		this.ruijitenReferenceDto = ruijitenReferenceDto;
	}
    
    /**
     * 類似店検索CommonDtoの設定
     * @return ruijitenCommonDto を戻します。
     */
    public RuijitenReferenceDto getRuijitenCommonDto() {
        return ruijitenCommonDto;
    }
    /**
     * 類似店検索用CommonDtoの設定
     * @param ruijitenCommonDto を設定。
     */
    public void setRuijitenCommonDto(RuijitenReferenceDto ruijitenCommonDto) {
        this.ruijitenCommonDto = ruijitenCommonDto;
    }
    
	/**
     * 初期処理判定Dtoの設定
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * 初期処理判定Dtoの設定
     * @param pullDownMenuDto pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
    /**
     * @return miseSearchDto を戻します。
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * @param miseSearchDto miseSearchDto を設定。
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

	
	///////////////////
	/*     LOGIC     */
	///////////////////
	/**
	 * オーナー保有店舗情報の取得Logicの設定
	 * @return getOnerTenpoInfoLogic を戻します。
	 */
	public GetOnerTenpoInfoLogic getOnerTenpoInfoLogic() {
		return getOnerTenpoInfoLogic;
	}
	/**
	 * オーナー保有店舗情報の取得Logicの設定
	 * @param getOnerTenpoInfoLogic を設定。
	 */
	public void setGetOnerTenpoInfoLogic(GetOnerTenpoInfoLogic getOnerTenpoInfoLogic) {
		this.getOnerTenpoInfoLogic = getOnerTenpoInfoLogic;
	}
	
	/**
	 * PL情報の取得Logicの設定
	 * @return getPLDataLogic を戻します。
	 */
	public GetPLDataLogic getPLDataLogic() {
		return getPLDataLogic;
	}
	/**
	 * PL情報の取得Logicの設定
	 * @param getPLDataLogic を設定。
	 */
	public void setGetPLDataLogic(GetPLDataLogic getPLDataLogic) {
		this.getPLDataLogic = getPLDataLogic;
	}
	
	/**
	 * 対象店舗情報の取得Logicの設定
	 * @return getTenpoInfoLogic を戻します。
	 */
	public GetTenpoInfoLogic getTenpoInfoLogic() {
		return getTenpoInfoLogic;
	}
	/**
	 * 対象店舗情報の取得Logicの設定
	 * @param getTenpoInfoLogic を設定。
	 */
	public void setGetTenpoInfoLogic(GetTenpoInfoLogic getTenpoInfoLogic) {
		this.getTenpoInfoLogic = getTenpoInfoLogic;
	}
	
	/**
	 * ユーザーオーナーコード情報の取得Logicの設定
	 * @return getUserOnerCdLogic を戻します。
	 */
	public GetUserOnerCdLogic getUserOnerCdLogic() {
		return getUserOnerCdLogic;
	}
	/**
	 * ユーザーオーナーコード情報の取得Logicの設定
	 * @param getUserOnerCdLogic を設定。
	 */
	public void setGetUserOnerCdLogic(GetUserOnerCdLogic getUserOnerCdLogic) {
		this.getUserOnerCdLogic = getUserOnerCdLogic;
	}
	
	/**
	 * グラフ作成Logicの設定
	 * @return getRuijitenGraphLogicImpl を戻します。
	 */
	public GetRuijitenGraphLogicImpl getGetRuijitenGraphLogicImpl() {
		return getRuijitenGraphLogicImpl;
	}
	/**
	 * グラフ作成Logicの設定
	 * @param getRuijitenGraphLogicImpl を設定。
	 */
	public void setGetRuijitenGraphLogicImpl(GetRuijitenGraphLogicImpl getRuijitenGraphLogicImpl) {
		this.getRuijitenGraphLogicImpl = getRuijitenGraphLogicImpl;
	}

    
    /**
     * 選択処理
     * @return 
     */
    public String callMiseForm() {
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(VIEWID_CONDITION);
        miseSearchDto.setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add("00");
        miseSearchDto.setRCompanyCdList(listCompany);
        // 検索条件退避
        saveSearchCondition();
        // 共通フォーム呼出フラグON
        getRuijitenCommonDto().setCallFormFlag(true);
        return VIEWID_MISESEARCH;
    }
    
    
    /**
     * 選択処理(結果画面から)
     * @return 
     */
    public String callMiseFormResult() {
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(VIEWID_RESULT);
        miseSearchDto.setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add("00");
        miseSearchDto.setRCompanyCdList(listCompany);
        // 検索条件退避
        saveSearchCondition();
        // 共通フォーム呼出フラグON
        getRuijitenCommonDto().setCallFormFlag(true);
        return VIEWID_MISESEARCH;
    }
    
    
        
	/**
     * 類似店検索 初期表示処理
     * @return 画面遷移情報
     */
    public String initialize() {
    	
    	if (pullDownMenuDto.isClearFlg()) {

            // 初期化
            ruijitenReferenceDto = getRuijitenReferenceDto();
            getUserOnerCdLogic = getUserOnerCdLogic();
            // 初期処理
            ruijitenReferenceDto.setMiseCd("");//店コード
            ruijitenReferenceDto.setArea("");//エリア
            ruijitenReferenceDto.setTenpoKeitai("");//店舗形態
            ruijitenReferenceDto.setRitti("");//立地
            ruijitenReferenceDto.setUriageFrom("");//売上差FROM
            ruijitenReferenceDto.setUriageTo("");//売上差TO
            ruijitenReferenceDto.setOpenDtFrom("");//オープン日FROM
            ruijitenReferenceDto.setOpenDtTo("");//オープン日TO
            //ruijitenReferenceDto.setBirdUserInfo(null);
            ruijitenReferenceDto.setGraphUrl("");
            ruijitenReferenceDto.setUserTypeCd("");
            ruijitenReferenceDto.setMisePLData(null);
            ruijitenReferenceDto.setMiseRuijiPLData(null);
            ruijitenCommonDto.setMstOnerHoyuMiseList(null);
            ruijitenReferenceDto.setOpenDt("");
            ruijitenReferenceDto.setTenpoData(null);
            ruijitenReferenceDto.setUriagedaka("");
            ruijitenReferenceDto.setUserOnerCdData(null);
            ruijitenReferenceDto.setUserId("");
            ruijitenReferenceDto.setLimitFlg(false);
            getPullDownMenuDto().setClearFlg(false);
            getMiseSearchDto().setInitialFlag(false);
            
            // ウィンドウID初期値設定
            getRuijitenCommonDto().updateWindowid();
            
            // MstUser取得
            MstUser mstUser = getBirdUserInfo().getMstUser();
            // ユーザータイプ判別
            String userType = mstUser.getUserTypeCd();
            // システム日付
            String sysdate = getBirdDateInfo().getSysDate();
            // SV制限区分
            boolean limitFlg = getBirdUserInfo().isLimit();
            
            //ユーザーがMOSユーザーかオーナーユーザーか判定
            if (USER_TYPE_ONER.equals(userType)) {
                //オーナーユーザーの場合
                //ユーザーID取得
                String userId = mstUser.getUser_id();
                //オーナーコードの取得
                ruijitenReferenceDto.setUserOnerCdData(getUserOnerCdLogic.execute(userId, "00"));
                //オーナー保有店舗分の売上高とオープン日を取得
                ruijitenCommonDto.setMstOnerHoyuMiseList(getOnerTenpoInfoLogic.execute(sysdate, ruijitenReferenceDto.getUserOnerCdData().getOnerCd(), sysdate.substring(0, 6)));
            }else{
                //MOSユーザーの場合
                //何もしない
            }
            
            // ユーザー判定区分へセット
            ruijitenReferenceDto.setUserTypeCd(userType);
            // ユーザーIDをセット
            ruijitenReferenceDto.setUserId(mstUser.getUser_id());
            // SV制限区分をセット
            ruijitenReferenceDto.setLimitFlg(limitFlg);
            
            // ユーザー判定区分をCommonDtoへセット
            ruijitenCommonDto.setUserTypeCd(userType);
            // ユーザーIDをCommonDtoへセット
            ruijitenCommonDto.setUserId(mstUser.getUser_id());
            // SV制限区分をセット
            ruijitenCommonDto.setLimitFlg(limitFlg);
            
        }
        
        if (getRuijitenCommonDto().isCallFormFlag()) {
            // PLデータクリア
            ruijitenReferenceDto.setMisePLData(null);
            ruijitenReferenceDto.setMiseRuijiPLData(null);
            // 再検索
            searchPlInfo();
            // 検索条件復帰
            loadSearchCondition();
            getRuijitenCommonDto().setCallFormFlag(false);
        }
            
        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg()) {
            ruijitenReferenceDto.setMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }
        
        return null;
    }
    
    
    /**
     * 類似店検索 初期表示処理
     * @return 画面遷移情報
     */
    public String initializeBAK() {
        
        if (pullDownMenuDto.isClearFlg()) {

            S2Container container = SingletonS2ContainerFactory.getContainer();
            HttpServletRequest request = (HttpServletRequest) container
                    .getComponent("request");
            BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
                    .getAttribute("birdUserInfo");

            // 初期化
            ruijitenReferenceDto = getRuijitenReferenceDto();
            getUserOnerCdLogic = getUserOnerCdLogic();
            
            // 初期処理
            if(ruijitenReferenceDto.getClearFlg() || ruijitenReferenceDto.getMisePLData() != null){
                ruijitenReferenceDto.setMiseCd("");//店コード
                ruijitenReferenceDto.setArea("");//エリア
                ruijitenReferenceDto.setTenpoKeitai("");//店舗形態
                ruijitenReferenceDto.setRitti("");//立地
                ruijitenReferenceDto.setUriageFrom("");//売上差FROM
                ruijitenReferenceDto.setUriageTo("");//売上差TO
                ruijitenReferenceDto.setOpenDtFrom("");//オープン日FROM
                ruijitenReferenceDto.setOpenDtTo("");//オープン日TO
                //ruijitenReferenceDto.setBirdUserInfo(null);
                ruijitenReferenceDto.setGraphUrl("");
                ruijitenReferenceDto.setUserTypeCd("");
                ruijitenReferenceDto.setMisePLData(null);
                ruijitenReferenceDto.setMiseRuijiPLData(null);
                ruijitenReferenceDto.setMstOnerHoyuMiseList(null);
                ruijitenReferenceDto.setOpenDt("");
                ruijitenReferenceDto.setTenpoData(null);
                ruijitenReferenceDto.setUriagedaka("");
                ruijitenReferenceDto.setUserOnerCdData(null);
            }

            ruijitenReferenceDto.setClearFlg(true);
    
            // MstUser取得
            MstUser mstUser = birdUserInfo.getMstUser();
            // ユーザータイプ判別
            String userType = mstUser.getUserTypeCd();
            // システム日付
            String sysdate = getBirdDateInfo().getSysDate();
            
            //ユーザーがMOSユーザーかオーナーユーザーか判定
            if (USER_TYPE_ONER.equals(userType)) {
            //if(true){
                //オーナーユーザーの場合
                //ユーザーID取得
                String userId = mstUser.getUser_id();
                //オーナーコードの取得
                ruijitenReferenceDto.setUserOnerCdData(getUserOnerCdLogic.execute(userId, "00"));
                //オーナー保有店舗分の売上高とオープン日を取得
                ruijitenReferenceDto.setMstOnerHoyuMiseList(getOnerTenpoInfoLogic.execute(sysdate, ruijitenReferenceDto.getUserOnerCdData().getOnerCd(), sysdate.substring(0, 6)));
            }else{
                //MOSユーザーの場合
                //何もしない
            }

            //ユーザー判定区分へセット
            ruijitenReferenceDto.setUserTypeCd(userType);
            //ruijitenReferenceDto.setUserTypeCd("02");
            
            // 店検索戻り値のセット
            if (getMiseSearchDto().isActionFlg()) {
                ruijitenReferenceDto.setMiseCd(getMiseSearchDto().getMiseCd());
            }
            
        }
        
        // BIRD内画面から遷移された場合の処理
        if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
            S2Container container = SingletonS2ContainerFactory.getContainer();
            HttpServletRequest request = (HttpServletRequest) container
                    .getComponent("request");
            BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
                    .getAttribute("birdUserInfo");

        }

        return null;
    }

    
    /**
     * 類似店検索 実行処理
     * @return 画面遷移情報
     */
    public String execute() {

        //初期化
        ruijitenReferenceDto.setMisePLData(null);
        ruijitenReferenceDto.setMiseRuijiPLData(null);
        getMiseSearchDto().setInitialFlag(false);
        
        //ウィンドウIDをセット
        //ruijitenReferenceDto.setWindowId(getRuijitenCommonDto().getWindowId());
        
        // 別ウィンドウ判定
        if (isNewWindowFlg()) {
            // ウィンドウID生成
            getRuijitenCommonDto().updateWindowid();
            // 別ウィンドウチェック状態OFF
            setNewWindowFlg(false);
        }
        
    	// 入力チェック
    	validate();
    	
    	// 引数セット
    	String miseCd  = ruijitenReferenceDto.getMiseCd();//店コード
    	String sysdate = getBirdDateInfo().getSysDate();//システム日付
     	String area    = ruijitenReferenceDto.getArea();//エリア
    	String tenpoKeitai = ruijitenReferenceDto.getTenpoKeitai();//店舗形態
    	String ritti   = ruijitenReferenceDto.getRitti();//立地
    	BigDecimal uriageMin = new BigDecimal(0);//売上差FROM
    	BigDecimal uriageMax = new BigDecimal(0);//売上差TO
    	String openDtMin = ruijitenReferenceDto.getOpenDtFrom();//オープン日FROM
    	String openDtMax = ruijitenReferenceDto.getOpenDtTo();//オープン日TO
    	String uriageSitei = "";
        String userId = getRuijitenCommonDto().getUserId();//ユーザーID
        //boolean limitFlg = ruijitenReferenceDto.getLimitFlg();//SV制限
        boolean limitFlg = getRuijitenCommonDto().getLimitFlg();
        String userTypeCd = getRuijitenCommonDto().getUserTypeCd();//ログインユーザー区分

    	
        // 検索条件を保存
        getRuijitenCommonDto().setMiseCd(getRuijitenReferenceDto().getMiseCd());
        getRuijitenCommonDto().setArea(getRuijitenReferenceDto().getArea());
        getRuijitenCommonDto().setTenpoKeitai(getRuijitenReferenceDto().getTenpoKeitai());
        getRuijitenCommonDto().setRitti(getRuijitenReferenceDto().getRitti());
        getRuijitenCommonDto().setUriageFrom(getRuijitenReferenceDto().getUriageFrom());
        getRuijitenCommonDto().setUriageTo(getRuijitenReferenceDto().getUriageTo());
        getRuijitenCommonDto().setOpenDtFrom(getRuijitenReferenceDto().getOpenDtFrom());
        getRuijitenCommonDto().setOpenDtTo(getRuijitenReferenceDto().getOpenDtTo());

        
        
    	//対象店舗情報の取得
        UIOnerTenpoInfo tenpoData = getTenpoInfoLogic.execute(sysdate, miseCd, sysdate.substring(0,6), userId, userTypeCd, limitFlg);
        if (tenpoData == null) {
            ruijitenReferenceDto.setClearFlg(false);
            // PLデータクリア
            ruijitenReferenceDto.setMisePLData(null);
            ruijitenReferenceDto.setMiseRuijiPLData(null);
            if(!limitFlg){
                //SV制限なしの場合
                throw new NotExistException("対象店舗", "miseCd", "");
            }else{
                //SV制限ありの場合
                throw new NotExistException("照会可能な対象店舗", "miseCd", "");
            }
        }
        ruijitenReferenceDto.setTenpoData(tenpoData);
        
        //類似店PLデータの取得条件
    	//エリア
    	if(area != null && !area.equals("")){
    		area = ruijitenReferenceDto.getTenpoData().getAreaDai();
    	}
    	//店舗形態
    	if(tenpoKeitai != null && !tenpoKeitai.equals("")){
    		tenpoKeitai = ruijitenReferenceDto.getTenpoData().getMiseKeitai();
    	}
    	//立地
    	if(ritti != null && !ritti.equals("")){
    		ritti = ruijitenReferenceDto.getTenpoData().getLocateKbn();
    	}
    	//売上指定
    	if(ruijitenReferenceDto.getUriageFrom() != null){
    		uriageMin = new BigDecimal(Integer.valueOf(ruijitenReferenceDto.getTenpoData().getUriagedaka()).intValue() - Integer.valueOf(ruijitenReferenceDto.getUriageFrom()).intValue());
    		uriageMax = new BigDecimal(Integer.valueOf(ruijitenReferenceDto.getTenpoData().getUriagedaka()).intValue() + Integer.valueOf(ruijitenReferenceDto.getUriageTo()).intValue());
    		uriageSitei = "ON";
    	}
    	//オープン日
    	if(openDtMin != null){
    		try{
    			openDtMin = DateManager.getPrevYear(ruijitenReferenceDto.getTenpoData().getOpenDt().substring(0,4), Integer.valueOf(openDtMin).intValue()) + "0101";
    			openDtMax = DateManager.getNextYear(ruijitenReferenceDto.getTenpoData().getOpenDt().substring(0,4), Integer.valueOf(openDtMax).intValue()) + "1231";
    		}
        	catch (Exception ex) {
                ruijitenReferenceDto.setClearFlg(false);
        		throw new FtlSystemException("類似店検索");
        	}
    	}
    	

   		// PL情報検索
   		getPLDataLogic.execute(ruijitenReferenceDto, miseCd, sysdate, area, tenpoKeitai, ritti, uriageSitei, uriageMin, uriageMax, openDtMin, openDtMax, userId, userTypeCd, limitFlg);
        if(ruijitenReferenceDto.getMisePLData() == null){
            ruijitenReferenceDto.setClearFlg(false);
            // PLデータクリア
            ruijitenReferenceDto.setMisePLData(null);
            ruijitenReferenceDto.setMiseRuijiPLData(null);
            throw new NotExistException("対象店舗のPL情報", "miseCd", "");
        }

   		// グラフ作成Action
   		graphOutputActionImpl = new GraphOutputActionImpl();
   		getRuijitenGraphLogicImpl = new GetRuijitenGraphLogicImpl();
   		graphOutputActionImpl.setGraphOutputLogic(getRuijitenGraphLogicImpl);
   		graphOutputActionImpl.setGraphOutputDto(ruijitenReferenceDto);
   		graphOutputActionImpl.viewGraph();

       
		return VIEWID_RESULT;
	}
    

    /**
     * 入力チェック
     */
    private void validate() {
    	
        ruijitenReferenceDto.setClearFlg(true);
        
        // 店コード必須チェック
        
    	String miseCd = ruijitenReferenceDto.getMiseCd();
        if (miseCd == null || miseCd.equals("")) {
            ruijitenReferenceDto.setClearFlg(false);
            throw new NotNullException("店コード", "miseCd", "");
        }
        // 半角数字
		CodeVerifier codeVerifier = new CodeVerifier();
		if (miseCd != null && !miseCd.equals("")
				&& !codeVerifier.validate(miseCd)) {
            ruijitenReferenceDto.setClearFlg(false);
			throw new InvalidInputException("店コード", "miseCd", "");
		}
	    // 店コード桁数
	    if (miseCd != null && miseCd.getBytes().length > 5) {
            ruijitenReferenceDto.setClearFlg(false);
	        throw new InvalidInputException("店コード", "miseCd", "");
	    }
	    // 店コード前ゼロ編集
	    if(miseCd.getBytes().length < 5){
	    	int addZeroSize = 5 - miseCd.getBytes().length;
	    	for(int i=0;i<addZeroSize;i++){
	    		miseCd = "0" + miseCd;
	    	}
	    	ruijitenReferenceDto.setMiseCd(miseCd);
	    }
	    
        
	    // 必須条件チェック
	    if(ruijitenReferenceDto.getTenpoKeitai() == null &&
	    	ruijitenReferenceDto.getRitti()      == null &&
			ruijitenReferenceDto.getUriageFrom() == null &&
			ruijitenReferenceDto.getUriageTo()   == null &&
			ruijitenReferenceDto.getOpenDtFrom() == null &&
			ruijitenReferenceDto.getOpenDtTo()   == null   ){
            ruijitenReferenceDto.setClearFlg(false);
	    	throw new NotNullException("店舗形態、立地、売上差、オープン年差のいずれか", "tenpoKeitai", "");
	    }
    	
    	// 売上差指定チェック
    	String uriageFrom = ruijitenReferenceDto.getUriageFrom();
    	String uriageTo = ruijitenReferenceDto.getUriageTo();
    	if(uriageFrom == null){
    		uriageFrom = "";
    	}
    	if(uriageTo == null){
    		uriageTo = "";
    	}
    	uriageFrom = uriageFrom.trim();
    	uriageTo = uriageTo.trim();
    	
    	if(uriageFrom.equals("") && !uriageTo.equals("")){
            ruijitenReferenceDto.setClearFlg(false);
    		throw new NotNullException("売上差FROM", "uriageFrom", "");
    	}
    	if(!uriageFrom.equals("") && uriageTo.equals("")){
            ruijitenReferenceDto.setClearFlg(false);
    		throw new NotNullException("売上差TO", "uriageTo", "");
    	}
    	
    	//オープン日差指定チェック
    	String openDtFrom = ruijitenReferenceDto.getOpenDtFrom();
    	String openDtTo = ruijitenReferenceDto.getOpenDtTo();
    	if(openDtFrom == null){
    		openDtFrom = "";
    	}
    	if(openDtTo == null){
    		openDtTo = "";
    	}
    	openDtFrom = openDtFrom.trim();
    	openDtTo = openDtTo.trim();
    	
    	if(openDtFrom.equals("") && !openDtTo.equals("")){
            ruijitenReferenceDto.setClearFlg(false);
    		throw new NotNullException("オープン日差FROM", "openDtFrom", "");
    	}
    	if(!openDtFrom.equals("") && openDtTo.equals("")){
            ruijitenReferenceDto.setClearFlg(false);
    		throw new NotNullException("オープン日差TO", "openDtTo", "");
    	}
    	
    }



	/**
	 * システム日付より年度を取得
	 */
	private String getNendo() {
		String sysDate = getBirdDateInfo().getSysDate();
		if (sysDate.substring(4, 6).compareTo("04") < 0) {
			String nendo =  sysDate.substring(0, 4);
			return String.valueOf(Integer.valueOf(nendo).intValue() - 1);
		}
		else {
			return sysDate.substring(0, 4);
		}
	}

	/**
	 * お約束ロジック
	 */
	private HttpServletRequest getHttpServletRequest() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		return (HttpServletRequest) container.getComponent("request");
	}

	/**
	 * お約束ロジック
	 */
	private CommonCodeDto getCommonCodeDto() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
	}

	/**
	 * お約束ロジック
	 */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    
    /**
     * 別ウィンドウ表示判定フラグを取得
     * @return newWindowFlg を戻します。
     */
    public boolean isNewWindowFlg() {
        return newWindowFlg;
    }
    /**
     * 別ウィンドウ表示判定フラグ設定
     * @return newWindowFlg を戻します。
     */
    public void setNewWindowFlg(boolean newWindowFlg) {
        this.newWindowFlg = newWindowFlg;
    }
    
    /**
     * 店選択ボタンを押下したタイミングの条件項目を退避
     */
    private void saveSearchCondition() {
        // 検索条件退避
        getRuijitenCommonDto().setTempMiseCd(getRuijitenReferenceDto().getMiseCd());
        getRuijitenCommonDto().setTempArea(getRuijitenReferenceDto().getArea());
        getRuijitenCommonDto().setTempTenpoKeitai(getRuijitenReferenceDto().getTenpoKeitai());
        getRuijitenCommonDto().setTempRitti(getRuijitenReferenceDto().getRitti());
        getRuijitenCommonDto().setTempUriageFrom(getRuijitenReferenceDto().getUriageFrom());
        getRuijitenCommonDto().setTempUriageTo(getRuijitenReferenceDto().getUriageTo());
        getRuijitenCommonDto().setTempOpenDtFrom(getRuijitenReferenceDto().getOpenDtFrom());
        getRuijitenCommonDto().setTempOpenDtTo(getRuijitenReferenceDto().getOpenDtTo());
    }
    
    
    /**
     * 再検索処理
     */
    private void searchPlInfo() {

        // 検索条件コピー
        getRuijitenReferenceDto().setMiseCd(getRuijitenCommonDto().getMiseCd());
        getRuijitenReferenceDto().setArea(getRuijitenCommonDto().getArea());
        getRuijitenReferenceDto().setTenpoKeitai(getRuijitenCommonDto().getTenpoKeitai());
        getRuijitenReferenceDto().setRitti(getRuijitenCommonDto().getRitti());
        getRuijitenReferenceDto().setUriageFrom(getRuijitenCommonDto().getUriageFrom());
        getRuijitenReferenceDto().setUriageTo(getRuijitenCommonDto().getUriageTo());
        getRuijitenReferenceDto().setOpenDtFrom(getRuijitenCommonDto().getOpenDtFrom());
        getRuijitenReferenceDto().setOpenDtTo(getRuijitenCommonDto().getOpenDtTo());
        getRuijitenReferenceDto().setUserId(getRuijitenCommonDto().getUserId());
        getRuijitenReferenceDto().setUserTypeCd(getRuijitenCommonDto().getUserTypeCd());
        getRuijitenReferenceDto().setLimitFlg(getRuijitenCommonDto().getLimitFlg());
        
        
        String sysdate = getBirdDateInfo().getSysDate();//システム日付
        String miseCd = getRuijitenReferenceDto().getMiseCd();
        String area = getRuijitenReferenceDto().getArea();
        String tenpoKeitai = getRuijitenReferenceDto().getTenpoKeitai();
        String ritti = getRuijitenReferenceDto().getRitti();
        String openDtMin = getRuijitenReferenceDto().getOpenDtFrom();
        String openDtMax = getRuijitenReferenceDto().getOpenDtTo();
        String userId = getRuijitenReferenceDto().getUserId();
        String userTypeCd = getRuijitenReferenceDto().getUserTypeCd();
        boolean limitFlg = getRuijitenReferenceDto().getLimitFlg();
        BigDecimal uriageMin = new BigDecimal(0);//売上差FROM
        BigDecimal uriageMax = new BigDecimal(0);//売上差TO
        String uriageSitei = "";

        
        // 対象店舗情報の取得
        UIOnerTenpoInfo tenpoData = getTenpoInfoLogic.execute(sysdate, miseCd, sysdate.substring(0,6), userId, userTypeCd, limitFlg);
        if (tenpoData == null) {
            
            ruijitenReferenceDto.setClearFlg(false);
      
        }else{
            
            ruijitenReferenceDto.setTenpoData(tenpoData);
        
            //類似店PLデータの取得条件
            //エリア
            if(area != null && !area.equals("")){
                area = ruijitenReferenceDto.getTenpoData().getAreaDai();
            }
            //店舗形態
            if(tenpoKeitai != null && !tenpoKeitai.equals("")){
                tenpoKeitai = ruijitenReferenceDto.getTenpoData().getMiseKeitai();
            }
            //立地
            if(ritti != null && !ritti.equals("")){
                ritti = ruijitenReferenceDto.getTenpoData().getLocateKbn();
            }
            //売上指定
            if(ruijitenReferenceDto.getUriageFrom() != null){
                uriageMin = new BigDecimal(Integer.valueOf(ruijitenReferenceDto.getTenpoData().getUriagedaka()).intValue() - Integer.valueOf(ruijitenReferenceDto.getUriageFrom()).intValue());
                uriageMax = new BigDecimal(Integer.valueOf(ruijitenReferenceDto.getTenpoData().getUriagedaka()).intValue() + Integer.valueOf(ruijitenReferenceDto.getUriageTo()).intValue());
                uriageSitei = "ON";
            }
            //オープン日
            if(openDtMin != null){
                try{
                    openDtMin = DateManager.getPrevYear(ruijitenReferenceDto.getTenpoData().getOpenDt().substring(0,4), Integer.valueOf(openDtMin).intValue()) + "0101";
                    openDtMax = DateManager.getNextYear(ruijitenReferenceDto.getTenpoData().getOpenDt().substring(0,4), Integer.valueOf(openDtMax).intValue()) + "1231";
                }
                catch (Exception ex) {
                    ruijitenReferenceDto.setClearFlg(false);
                    // 検索条件復帰
                    loadSearchCondition();
                    getRuijitenCommonDto().setCallFormFlag(false);
                    // 店検索戻り値のセット
                    if (getMiseSearchDto().isActionFlg()) {
                        ruijitenReferenceDto.setMiseCd(getMiseSearchDto().getMiseCd());
                        getMiseSearchDto().setActionFlg(false);
                    }
                    throw new FtlSystemException("類似店検索");
                }
            }
        

            // PL情報検索
            getPLDataLogic.execute(ruijitenReferenceDto, miseCd, sysdate, area, tenpoKeitai, ritti, uriageSitei, uriageMin, uriageMax, openDtMin, openDtMax, userId, userTypeCd, limitFlg);
            if(ruijitenReferenceDto.getMisePLData() == null){
            
                ruijitenReferenceDto.setClearFlg(false);
            
            }else{
        
               // グラフ作成Action
                graphOutputActionImpl = new GraphOutputActionImpl();
                getRuijitenGraphLogicImpl = new GetRuijitenGraphLogicImpl();
                graphOutputActionImpl.setGraphOutputLogic(getRuijitenGraphLogicImpl);
                graphOutputActionImpl.setGraphOutputDto(ruijitenReferenceDto);
                graphOutputActionImpl.viewGraph();
            }
        }
        
    }
    
    private void loadSearchCondition() {
        // 退避検索条件コピー
        getRuijitenReferenceDto().setMiseCd(getRuijitenCommonDto().getTempMiseCd());
        getRuijitenReferenceDto().setArea(getRuijitenCommonDto().getTempArea());
        getRuijitenReferenceDto().setTenpoKeitai(getRuijitenCommonDto().getTempTenpoKeitai());
        getRuijitenReferenceDto().setRitti(getRuijitenCommonDto().getTempRitti());
        getRuijitenReferenceDto().setUriageFrom(getRuijitenCommonDto().getTempUriageFrom());
        getRuijitenReferenceDto().setUriageTo(getRuijitenCommonDto().getTempUriageTo());
        getRuijitenReferenceDto().setOpenDtFrom(getRuijitenCommonDto().getTempOpenDtFrom());
        getRuijitenReferenceDto().setOpenDtTo(getRuijitenCommonDto().getTempOpenDtTo());
        
       
        // 退避領域クリア
        getRuijitenCommonDto().setTempMiseCd(null);
        getRuijitenCommonDto().setTempArea(null);
        getRuijitenCommonDto().setTempTenpoKeitai(null);
        getRuijitenCommonDto().setTempRitti(null);
        getRuijitenCommonDto().setTempUriageFrom(null);
        getRuijitenCommonDto().setTempUriageTo(null);
        getRuijitenCommonDto().setTempOpenDtFrom(null);
        getRuijitenCommonDto().setTempOpenDtTo(null);

    }
    
    
}