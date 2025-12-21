package jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto;

import java.util.HashMap;
import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * モスチキン予約入力DTO
 * 
 * @author inazawa
 *
 */
public class MosChickenRevInfoDto {
    /**
     * ユーザーID
     */
    private String userId ="";
    /**
     * ユーザータイプ
     */
    private String userTypeCd ="";
    /**
     * システム日付
     */
    private String sysDate = "";
    /**
     * 管理会社
     */
    private String companyCd = "";
    /**
     * 本部ユーザー以外の初期処理を行うフラグ
     * 1.本部ユーザー以外初期画面
     * 0.本部ユーザー用初期画面
     */
    //private String initShoriFlg = "";
    /**
     * お渡し時間（時）
     */
    private String reservHour = "";
    /**
     * お渡し時間（分）
     */
    private String reservMin  = "";
    /**
     * 備考
     */
    private String bikou  = "";
    /**
     * プレミアムお渡し済フラグ
     */
    private String premiumFlg = "";
    /**
     * メモ
     */
    private String memo = "";
    /**
     * 代金済みフラグ
     * 0:未払い
     * 1:代済み
     */
    private String paymentFlg = "";
    /**
     * 商品追加用検索テキスト
     */
    private String addMenuText = "";
    /**
     * 詳細予約index番号
     */
    private int revIndex;
    /**
     * 店名称
     */
    private String miseNmKj = "";
    /**
     * キャンセルフラグ
     * 0:未1:済2:新規
     */
    private String cancelFlg = "";
    /**
     * 登録時のMAXSEQNO
     */
    private int maxSeqNo;
    /**
     * 新規登録フラグ
     * 0:新規1:修正
     */
    private String newFlg;
    /**
     * 編集するシーケンスNO
     */
    private int seqNo;
    /**
     * お渡し時間をHHMMで取得
     */
    private String reserveYYMM;
    /* 条件項目：[[キャンペーン情報リスト]] */
    //private List listCamp;
    /* 条件項目：[[日付リスト]] */
    //private List listDate;
    /* 検索項目 お渡し時間：時*/
    private List reservHh;
    /* 検索項目 お渡し時間：分*/
    private List reservMm;
    /* 検索条件：管理番号 */
    private String ckanriNo;
    /* 検索結果：[[追加商品]] */
    private List listAddMenu;
    /*マスタ登録メニュー用リスト*/
    private List listMstMenu;
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /*保有店舗数*/
    private int miseCnt;
    /**
     * オーナーコード保持
     */ 
    private String onerCd;
    
    /**
     * タイトルコード保持
     */
    private String title;
    /**
     * 店コード保持
     */
    private String miseCd;
    
    /**
     * お渡し日保持
     */
    private String reservDate;
    
    /**
     * お渡し日保持
     */
    private String paramReservDate;
    
    /**
     * 予約情報保持
     */
    private List listMosChiCkenInfo;
    
    /**
     * アクション区分
     */
    private String actionKbn = null;
    
//    /**
//     * 予約情報保持
//     */
//    private List listMosChiCkenDetailInfo;
    
// add start xkhata 20061027 セッションキーによる複数ウィンドウチェック(後勝ち) 

    /**
     * 予約情報保持
     */
    private HashMap listMosChiCkenDatailInfo = new HashMap();

    private HashMap listCampMap = new HashMap();

    private HashMap listDateMap = new HashMap();
    
    private HashMap listOnerMiseMap = new HashMap();
    
    private HashMap initShoriFlgMap = new HashMap();
    
    /* 画面用セッションKey */
    private String viewSessionKey;
    //private String viewSessionKeyMap = new HashMap();

    /* セッションKey */
    //private String sessionKey;
    private HashMap sessionKeyMap = new HashMap();

    /* ウィンドウ管理 */
    // ウィンドウID
    private int windowId = 0;

    // 最大ウィンドウID
    private int maxWindowId = 0;
    
    private String onerDefCd;
    
// add end
    

    /**
     * @return listCamp を戻します。
     */
    public List getListCamp() {
    	return (List) listCampMap.get(new Integer(getWindowId()));
    	
    }

    /**
     * @param listCamp 設定する listCamp。
     */
    public void setListCamp(List listCamp) {
    	this.listCampMap.put(new Integer(getWindowId()), listCamp);
    }
    
    /**
     * タイトルのサイズを設定します。
     * @param  タイトルのサイズ
     */   
	public int getListCampSize(){
		if(getListCamp() == null){
			return 0;
		}else{
			return getListCamp().size();
		}
	}

    /**
     * @return listAddMenu を戻します。
     */
    public List getListAddMenu() {
        return listAddMenu;
    }

    /**
     * @param listAddMenu 設定する listAddMenu。
     */
    public void setListAddMenu(List listAddMenu) {
        this.listAddMenu=listAddMenu;
    }

    /**
     * @return listMosChiCkenInfo を戻します。
     */
    public List getListMosChiCkenInfo() {
        return   listMosChiCkenInfo;
    }

    /**
     * @param listMosChiCkenInfo 設定する listMosChiCkenInfo。
     */
    public void setListMosChiCkenInfo(List listMosChiCkenInfo) {
        this.listMosChiCkenInfo= listMosChiCkenInfo;
    }

    public List getListMosChiCkenDatailInfo() {
        return (List)listMosChiCkenDatailInfo.get(new Integer(getWindowId() ) ) ;
    }
    public void setListMosChiCkenDatailInfo(List listMosChiCkenDetailInfo )  {
        this.listMosChiCkenDatailInfo.put( new Integer( getWindowId() ) ,listMosChiCkenDetailInfo );
    }
    
//    /**
//     * @return listMosChiCkenDetailInfo を戻します。
//     */
//    public List getListMosChiCkenDetailInfo() {
//        return   listMosChiCkenDetailInfo;
//    }
//
//    /**
//     * @param listMosChiCkenDetailInfo 設定する listMosChiCkenDetailInfo。
//     */
//    public void setListMosChiCkenDetailInfo(List listMosChiCkenDetailInfo) {
//        this.listMosChiCkenDetailInfo= listMosChiCkenDetailInfo;
//    }

    /**
     * @return ckanriNo を戻します。
     */
    public String getCkanriNo() {
        return ckanriNo;
    }

    /**
     * @param ckanriNo 設定する ckanriNo。
     */
    public void setCkanriNo(String ckanriNo) {
        this.ckanriNo=ckanriNo;
    }
    /**
     * クリア処理
     *
     */
    public void clear() {
        setCkanriNo(null);
        setInitShoriFlg(null);
        setListAddMenu(null);
        setListCamp(null);
        setListDate(null);
//        setListMosChiCkenDetailInfo(null);
        setListMosChiCkenInfo(null);
        setListOnerMise(null);
        setMiseCd(null);
//        setOnerCd(null);
        setOnerDefCd(null );
        setRCompanyCd(null);
        setReservDate(null);
        setParamReservDate(null);
        setSysDate(null);
        setUserId(null);
        setUserTypeCd(null);
        setSeqNo(-1);
        setMaxSeqNo(-1);
        setViewSessionKey(null);
        setSessioniKey(null);
    }
    
    
    /**
     * クリア処理
     *
     */
    public void subClear() {
        setCkanriNo(null);
        //setInitShoriFlg(null);
        setListAddMenu(null);
//        setListMosChiCkenDetailInfo(null);
        setListMosChiCkenInfo(null);
        setMiseCd(null);
//        setOnerCd(null);
        setOnerDefCd(null );
        setRCompanyCd(null);
        setReservDate(null);
        setParamReservDate(null);
        setSysDate(null);
        setUserId(null);
        setUserTypeCd(null);
        setSeqNo(-1);
        setMaxSeqNo(-1);
        //setViewSessionKey(null);
        //setSessioniKey(null);
    }
    

    /**
     * @return menuCd を戻します。
     */
    public String getMiseCd() {
        return   miseCd;
    }

    /**
     * @param menuCd 設定する menuCd。
     */
    public void setMiseCd(String miseCd) {
        this.miseCd= miseCd;
    }


    /**
     * @return sysDate を戻します。
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * @param sysDate 設定する sysDate。
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }


    /**
     * @return userId を戻します。
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId 設定する userId。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return userTypeCd を戻します。
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }

    /**
     * @param userTypeCd 設定する userTypeCd。
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    /**
     * @return listOnerMise を戻します。
     */
    public List getListOnerMise() {
    	return (List) listOnerMiseMap.get(new Integer(getWindowId()));
        
    }
    /**
     * @param listOnerMise 設定する listOnerMise。
     */
    public void setListOnerMise(List listOnerMise) {
        this.listOnerMiseMap.put(new Integer(getWindowId()), listOnerMise);
    }
    /**
     * companyCdを戻す
     * @return
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * companyCdを設定
     * @param companyCd
     */
    public void setRCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * initShoriFlgを戻す
     * @return initShoriFlg
     */
    public String getInitShoriFlg() {
        //return   initShoriFlg;
    	return (String) initShoriFlgMap.get(new Integer(getWindowId()));
    }
    /**
     * initShoriFlgを設定
     * @param initShoriFlg
     */
    public void setInitShoriFlg(String initShoriFlg) {
        //this.initShoriFlg= initShoriFlg;
    	this.initShoriFlgMap.put(new Integer(getWindowId()), initShoriFlg);
    }
    /**
     * onerCdを戻す 
     * @return onerCd
     */
    public String getOnerCd() {
        return   onerCd;
    }
    /**
     * onerCdを設定
     * @param onerCd
     */
    public void setOnerCd(String onerCd) {
        this.onerCd= onerCd;
    }
    /**
     * listDateを戻す
     * @return listDate
     */
    public List getListDate() {
    	return (List) listDateMap.get(new Integer(getWindowId()));
    }
    /**
     * listDateを設定
     * @param listDate
     */
    public void setListDate(List listDate) {
    	this.listDateMap.put(new Integer(getWindowId()), listDate);
    }
    /**
     * reservDateを渡す
     * @return reservDate
     */
    public String getReservDate() {
        return   reservDate;
    }
    /**
     * reservDateを設定
     * @param reservDate
     */
    public void setReservDate(String reservDate) {
        this.reservDate= reservDate;
    }
    
    /**
     * reservDateを渡す
     * @return reservDate
     */
    public String getParamReservDate() {
        return   paramReservDate;
    }
    /**
     * reservDateを設定
     * @param reservDate
     */
    public void setParamReservDate(String paramReservDate) {
        this.paramReservDate= paramReservDate;
    }

    /**
     * titleを戻す
     * @return title
     */
    public String getTitle() {
        return   title;
    }
    /**
     * titleを設定
     * @param title
     */
    public void setTitle(String title) {
        this.title= title;
    }
    /**
     * miseNmKjを戻す
     * @return miseNmKj
     */
    public String getMiseNmKj() {
        return miseNmKj;
    }
    /**
     * miseNmKを設定
     * @param miseNmKj
     */
    public void setMiseNmKj(String miseNmKj) {
        this.miseNmKj=miseNmKj;
    }
    /**
     * reservHourを戻す
     * @return reservHour
     */
    public String getReservHour() {
        return reservHour;
    }
    /**
     * reservHourを設定
     * @param reservHour
     */
    public void setReservHour(String reservHour) {
        this.reservHour=reservHour;
    }
    /**
     * reservMinを戻す
     * @return reservMin
     */
    public String getReservMin() {
        return reservMin;
    }
    /**
     * reservMinを設定
     * @param reservMin
     */
    public void setReservMin(String reservMin) {
        this.reservMin=reservMin;
    }
    /**
     * bikouを戻す
     * @return bikou
     */
    public String getBikou() {
        return bikou;
    }
    /**
     * bikouを返す
     * @param bikou
     */
    public void setBikou(String bikou) {
        this.bikou=bikou;
    }
    /**
     * memoを戻す
     * @return memo
     */
    public String getMemo() {
        return memo;
    }
    /**
     * memoを返す
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo=memo;
    }
    /**
     * paymentFlgを戻す
     * @return paymentFlg
     */
    public String getPaymentFlg() {
        return paymentFlg;
    }
    /**
     * paymentFlgを返す
     * @param paymentFlg
     */
    public void setPaymentFlg(String paymentFlg) {
        this.paymentFlg=paymentFlg;
    }
    /**
     * addMenuTextを返す
     * @return addMenuText
     */
    public String getAddMenuText() {
        return addMenuText;
    }
    /**
     * addMenuTextを設定
     * @param addMenuText
     */
    public void setAddMenuText(String addMenuText) {
        this.addMenuText=addMenuText;
    }
    /**
     * revIndexを返す
     * @return revIndex
     */
    public int getRevIndex() {
        return revIndex;
    }
    /**
     * revIndexを設定
     * @param revIndex
     */
    public void setRevIndex(int revIndex) {
        this.revIndex = revIndex;
    }
    /**
     * cancelFlgを戻す
     * @return cancelFlg
     */
    public String getCancelFlg() {
        return cancelFlg;
    }
    /**
     * cancelFlgを設定
     * @param cancelFlg
     */
    public void setCancelFlg(String cancelFlg) {
        this.cancelFlg=cancelFlg;
    }
    /**
     * listHhを戻す
     * @return listHh
     */
    public List getReservHh() {
        return reservHh;
    }
    /**
     * listMmを戻す
     * @return listMm
     */
    public List getReservMm() {
        return reservMm;
    }
    /**
     * reservHhを設定
     * @param reservHh
     */
    public void setReservHh(List reservHh) {
        this.reservHh = reservHh;
    }
    /**
     * reservMmを設定
     * @param reservMm
     */
    public void setReservMm(List reservMm) {
        this.reservMm = reservMm;
    }
    /**
     * newFlgを返す
     * @return newFlg
     */
    public String getNewFlg() {
        return newFlg;
    }
    /**
     * newFlgを設定
     * @param newFlg
     */
    public void setNewFlg(String newFlg) {
        this.newFlg=newFlg;
    }
    /**
     * maxSeqNoを戻す
     * @return maxSeqNo
     */
    public int getMaxSeqNo() {
        return maxSeqNo;
    }
    /**
     * maxSeqNoを設定
     * @param maxSeqNo
     */
    public void setMaxSeqNo(int maxSeqNo) {
        this.maxSeqNo= maxSeqNo;
    }

    /**
     * BIRDログイン情報の設定
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    /**
     * BIRDログイン情報の設定
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    /**
     * 新規作成時のクリア処理
     *
     */
    public void newRegClear() {
        setReservHour(null);
        setReservMin(null);
        setBikou(null);
        setMemo(null);
        //2007/10/18 代済フラグ、プレミアムお渡し済フラグの初期値を「済」(=1)に変更
        setPaymentFlg("");
        setPremiumFlg("");
        setListMstMenu(null);
        setSeqNo(-1);
        setMaxSeqNo(-1);
    }
    /**
     * seqNoを返す
     * @return seqNo
     */
    public int getSeqNo() {
        return seqNo;
    }
    /**
     * seqNoを設定
     * @param seqNo
     */
    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }
    /**
     * listMstMenuを取得
     * @return listMstMenu
     */
    public List getListMstMenu() {
        return listMstMenu;
    }
    /**
     * listMstMenuを設定
     * @param listMstMenu
     */
    public void setListMstMenu(List listMstMenu) {
        this.listMstMenu=listMstMenu;
    }
    /**
     * お渡し時間をHHMMで取得
     * @param 
     */
    public String getReserveYYMM() {
        return reserveYYMM;
    }
    /**
     * お渡し時間をHHMMで取得
     * @param 
     */
    public void setReserveYYMM(String reserveYYMM) {
        this.reserveYYMM=reserveYYMM;
    }
    /**
     * 店数を取得します。
     * @return miseCnt
     */
    public int getMiseCnt() {
        if(getListOnerMise() != null){
            miseCnt = getListOnerMise().size();
        }else{
            miseCnt = 1;
        }
        return miseCnt;
    }

// add start xkhata 20061027 セッションキーによる複数ウィンドウチェック(後勝ち)
    // セッションキー作成 /////////////////////////////////////////////

    /**
     * viewSessionKey取得
     */
    public String getViewSessionKey() {
        return this.viewSessionKey;
    }
    
    /**
     * viewSessionKey取得
     * @param viewSessionKey
     */
    public void setViewSessionKey( String viewSessionKey ) {
        this.viewSessionKey = viewSessionKey;
    }

    /**
     * sessionKey取得
     */
    public String getSessionKey() {
    	return (String) sessionKeyMap.get(new Integer(getWindowId()));
    }
    
    /**
     * sessionKey取得
     * @param sessionKey
     */
    public void setSessioniKey( String sessionKey ) {
    	this.sessionKeyMap.put(new Integer(getWindowId()), sessionKey);
    }

    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    public int getMaxWindowId() {
        return maxWindowId;
    }
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    
    // ウィンドウID生成
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    public void updateWindowid() {
        setWindowId(createWindowId());
    }
    
    public String getOnerDefCd() {
        return this.onerDefCd;
    }
    
    public void setOnerDefCd( String onerDefCd ) {
        this.onerDefCd = onerDefCd;
    }
    
    public String getActionKbn() {
        return this.actionKbn;
    }
    
    public void setActionKbn( String actionKbn ) {
        this.actionKbn = actionKbn;
    }

    public String getPremiumFlg() {
        return premiumFlg;
    }

    public void setPremiumFlg(String premiumFlg) {
        this.premiumFlg = premiumFlg;
    }
    
//  add end
}
