package jp.co.isid.mos.bird.bizreport.zendougeturegist.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZenDougetuRegistDto {

    /**
     * ユーザーID
     */
    private String userId;
    /**
     * ユーザータイプコード
     */
    private String usertypeCd;
    /**
     * 会社コード
     */
    private String companyCd;
    /**
     * システム日付
     */
    private String sysDate;
    /**
     * システム日付
     */
    private String applyDate;
    /**
     * 過去判定
     */
    private String pastYearFlg;
    /**
     * 対象年月
     */
    private String taishoNendo;
    
    /*検索結果[前年同月情報]**/
    private HashMap listZenDougetu = new HashMap();
    /*検索結果[ユーザー所属会社]**/
    private List listUserRCompany;
    /*検索結果[対象期間]**/
    private List listTaishouNendo;
    

    /**
     * userIdを取得
     * @return userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * userIdを設定
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * usertypeCdを取得
     * @return usertypeCd
     */
    public String getUsertypeCd() {
        return usertypeCd;
    }
    /**
     * usertypeCdを設定
     * @param usertypeCd
     */
    public void setUsertypeCd(String usertypeCd) {
        this.usertypeCd = usertypeCd;
    }
    /**
     * companyCdを設定
     * @param companyCd
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * companyCdを取得
     * @return companyCd
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * sysDateを取得
     * @return sysDate
     */
    public String getSysDate() {
        return sysDate;
    }
    
    /**
     * sysDateを設定
     * @param sysDate
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
    /**
     * applyDateを取得
     * @return applyDate
     */
    public String getApplyDate() {
        return applyDate;
    }
    /**
     * applyDateを設定
     * @param applyDate
     */
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
    /**
     * pastYearFlgを取得
     * @return pastYearFlg
     */
    public String getPastYearFlg() {
        return pastYearFlg;
    }
    /**
     * pastYearFlgを設定
     * @param pastYearFlg
     */
    public void setPastYearFlg(String pastYearFlg) {
        this.pastYearFlg = pastYearFlg;
    }
    /**
     * listZenDougetuを取得
     * @return listZenDougetu
     */
    public List getListZenDougetu() {
        return (List)listZenDougetu.get(new Integer(getWindowId()));
    }
    /**
     * listZenDougetuを設定
     * @param listZenDougetu
     */
    public void setListZenDougetu(List listZenDougetu) {
        this.listZenDougetu.put(new Integer(getWindowId()), listZenDougetu);
    }
    /**
     * listUserRCompanyを取得
     * @return listUserRCompany
     */
    public List getListUserRCompany() {
        return listUserRCompany;
    }
    /**
     * listUserRCompanyを設定
     * @param listUserRCompany
     */
    public void setListUserRCompany(List listUserRCompany) {
        this.listUserRCompany = listUserRCompany;
    }
    /**
     * listTaishouNendoを取得
     * @return listTaishouNendo
     */
    public List getListTaishouNendo() {
        return listTaishouNendo;
    }
    /**
     * listTaishouNendoを設定
     * @param listTaishouNendo
     */
    public void setListTaishouNendo(List listTaishouNendo) {
        this.listTaishouNendo = listTaishouNendo;
    }
    /**
     * taishoNendoを取得
     * @return taishoNendo
     */
    public String getTaishoNendo() {
        return taishoNendo;
    }
    /**
     * taishoNendo
     * @param taishoNendoを設定
     */
    public void setTaishoNendo(String taishoNendo) {
        this.taishoNendo = taishoNendo;
    }

    /***************セッションキー作成*********************/

    /* セッションKey */
    private String viewSessionKey;
    /* セッションKey */
    private Map sessionKeyMap = new HashMap();
    /**
     * ウィンドウID
     */
    private int windowId = 0;
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;


    ////////////////
    //　複数Window対応
    // セッションキー作成
    ////////////////
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
/***************セッションキー作成*********************/
    /**
     * クリア処理
     */
    public void clear() {
        setApplyDate(null);
        setCompanyCd(null);
        setListTaishouNendo(null);
        setListUserRCompany(null);
        setListZenDougetu(null);
        setPastYearFlg(null);
        setSysDate(null);
        setTaishoNendo(null);
        setUsertypeCd(null);
    }

}
