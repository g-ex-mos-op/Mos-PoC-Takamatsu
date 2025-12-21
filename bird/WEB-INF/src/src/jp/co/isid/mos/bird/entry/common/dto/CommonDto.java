package jp.co.isid.mos.bird.entry.common.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 共通DTO Abstractクラス
 * 
 * 保持機能
 * １．複数Window対応
 * 
 * @author xkinu
 *
 */
public abstract class CommonDto {
    /**システム日付 */
    private String sysDate = "";
    /**アプリ日付 */
    private String appDate = "";
    /** ユーザーID */
    private String userId ="";
    /** ユーザーID */
    private String userTypeCd ="";
    /* 対象会社コード  */
    private String targetCompanyCd="00";
    /* セッションKey */
    private String nowSessionKey;
    /* セッションKey */
    private Map sessionKey = new HashMap();
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
     * セッションKeyの設定
     * @return sessionKey を戻します。
     */
    public String getSessionKey() {
        return (String)sessionKey.get(new Integer(getWindowId()));
    }
    /**
     * セッションKeyの設定
     * @param sessionKey sessionKey を設定。
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey.put(new Integer(getWindowId()), sessionKey);
    }
    /**
     * @return nowSessionKey を戻します。
     */
    public String getNowSessionKey() {
        return nowSessionKey;
    }
    /**
     * @param nowSessionKey 設定する nowSessionKey。
     */
    public void setNowSessionKey(String nowSessionKey) {
        this.nowSessionKey = nowSessionKey;
    }
    /**
     * @return targetCompanyCd を戻します。
     */
    public String getTargetCompanyCd() {
        return targetCompanyCd;
    }
    /**
     * @param targetCompanyCd 設定する targetCompanyCd。
     */
    public void setTargetCompanyCd(String targetCompanyCd) {
        this.targetCompanyCd = targetCompanyCd;
    }
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

    /**
     * @return appDate を戻します。
     */
    public String getAppDate() {
        return appDate;
    }

    /**
     * @param appDate 設定する appDate。
     */
    public void setAppDate(String appDate) {
        this.appDate = appDate;
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

}
