package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 販売予約管理数DTO
 * 
 * @author inazawa
 *
 */
public class MosChickenRefConfListCondDto {
    /**
     * ウィンドウID
     */
    private int windowId = 0;
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;


    /**
     * オーナーコード
     */
    private Map onerCd = new HashMap();
    /**
     * 本部ユーザー以外の初期処理を行うフラグ
     * 1.本部ユーザー以外初期画面
     * 0.本部ユーザー用初期画面
     */

    private HashMap initShoriFlg = new HashMap();
    /**
     * ユーザーID
     */
    private String userId;
    /**
     * ユーザータイプ
     */
    private String userTypeCd;
    /**
     * システム日付
     */
    private String sysDate;

    /*保有店舗数*/
    private int miseCnt;
    /*タイトル数*/
    private int titleCnt;
    /*日付数*/
    private int cnpDays;
    /* 条件項目：[[キャンペーン情報リスト]] */
    private Map listCamp = new HashMap();
    /* 条件項目：[[オーナー保有店舗リスト]] */
    private Map listOnerMise = new HashMap();
    /* 条件項目：[[日付リスト]] */
    private Map listDate = new HashMap();
    /* 検索項目 対象時間：FROM*/
    private Map listReserveFrom = new HashMap();
    /* 検索項目 対象時間：TO*/
    private Map listReserveTo = new HashMap();

    /**
     * クリア処理
     *
     */
    public void clear() {
        setOnerCd(null);
        setInitShoriFlg(null);        
        setListCamp(null);
        setListDate(null);
        setListOnerMise(null);
        setListReserveFrom(null);
        setListReserveTo(null);
        setListCamp(null);
    }

    /**
     * onerCdを戻す 
     * @return onerCd
     */
    public String getOnerCd() {
        return (String)onerCd.get(new Integer(getWindowId()));
    }
    /**
     * onerCdを設定
     * @param onerCd
     */
    public void setOnerCd(String onerCd) {
        this.onerCd.put(new Integer(getWindowId()),onerCd);
    }
    /**
     * 店数を取得します。
     * @return miseCnt
     */
    public int getMiseCnt() {
        return miseCnt;
    }
    /**
     * miseCntを設定
     * @param miseCnt
     */
    public void setMiseCnt(int miseCnt) {
        this.miseCnt = miseCnt;
    }
    /**
     * titleCntを取得します。
     * @return miseCnt
     */
    public int getTitleCnt() {
        return titleCnt;
    }
    /**
     * titleCntを設定
     * @param titleCnt
     */
    public void setTitleCnt(int titleCnt) {
        this.titleCnt = titleCnt;
    }
    /**
     * cnpDaysを取得
     * @return cnpDays
     */
    public int getCnpDays() {
        return cnpDays;
    }
    /**
     * cnpDaysを設定
     * @param cnpDays
     */
    public void setCnpDays(int cnpDays) {
        this.cnpDays = cnpDays;
    }
    /**
     * initShoriFlgを戻す
     * @return initShoriFlg
     */
    public String getInitShoriFlg() {
        return (String) initShoriFlg.get(new Integer(getWindowId()));
    }
    /**
     * initShoriFlgを設定
     * @param initShoriFlg
     */
    public void setInitShoriFlg(String initShoriFlg) {
        this.initShoriFlg.put(new Integer(getWindowId()), initShoriFlg);
    }
    /**
     * @return listCamp を戻します。
     */
    public List getListCamp() {
        return (List)listCamp.get(new Integer(getWindowId()));
    }

    /**
     * @param listCamp 設定する listCamp。
     */
    public void setListCamp(List listCamp) {
        this.listCamp.put(new Integer(getWindowId()), listCamp);
    }
    /**
     * listDateを戻す
     * @return listDate
     */
    public List getListDate() {
        return (List)listDate.get(new Integer(getWindowId()));
    }
    /**
     * listDateを設定
     * @param listDate
     */
    public void setListDate(List listDate) {
        this.listDate.put(new Integer(getWindowId()), listDate);
    }    /**
     * ReserveFromを戻す
     * @return ReserveFrom
     */
    public List getListReserveFrom() {
        return (List)listReserveFrom.get(new Integer(getWindowId()));
    }
    /**
     * ReserveToを戻す
     * @return ReserveTo
     */
    public List getListReserveTo() {
        return (List)listReserveTo.get(new Integer(getWindowId()));
    }
    /**
     * ReserveToを設定
     * @param ReserveTo
     */
    public void setListReserveTo(List listReserveTo) {
        this.listReserveTo.put(new Integer(getWindowId()), listReserveTo);
    }
    /**
     * ReserveFromを設定
     * @param ReserveFrom
     */
    public void setListReserveFrom(List listReserveFrom) {
        this.listReserveFrom.put(new Integer(getWindowId()), listReserveFrom);
    }
    /**
     * @return listOnerMise を戻します。
     */
    public List getListOnerMise() {
        return (List)listOnerMise.get(new Integer(getWindowId()));
    }
    /**
     * @param listOnerMise 設定する listOnerMise。
     */
    public void setListOnerMise(List listOnerMise) {
        this.listOnerMise.put(new Integer(getWindowId()), listOnerMise);
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

    //-----
    //複数Window対応
    //-----
    /**
     * ウィンドウIDを取得します。
     * @return　ウィンドウID
     */
    public int getWindowId() {
        return windowId;
    }
    
    /**
     * ウィンドウIDを設定します。
     * @param windowId　ウィンドウID
     */
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    /**
     * 最大ウィンドウIDを取得します。
     * @return 最大ウィンドウID
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }
    
    /**
     * 最大ウィンドウIDを設定します。
     * @param maxWindowId　最大ウィンドウID
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    
    // ウィンドウID生成
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    
    /**
     * ウィンドウIDを更新します。
     */
    public void updateWindowid() {
        setWindowId(createWindowId());
    }

}
