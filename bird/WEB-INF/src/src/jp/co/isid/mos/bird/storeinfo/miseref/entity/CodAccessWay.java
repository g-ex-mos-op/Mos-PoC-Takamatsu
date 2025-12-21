package jp.co.isid.mos.bird.storeinfo.miseref.entity;

import java.sql.Timestamp;

public class CodAccessWay {
    
    public static final String TABLE = "KC01ACWY";
    
    public static final String accessWay_COLUMN = "ACCESS_WAY";
    
    public static final String accessWayName_COLUMN = "ACCESS_WAY_NAME";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 交通手段
     */
    private String accessWay;
    
    /**
     * 交通手段名称
     */
    private String accessWayName;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 交通手段を取得します。
     * @return 交通手段
     */
    public String getAccessWay() {
        return accessWay;
    }
    /**
     * 交通手段を設定します。
     * @param accessWay 交通手段
     */
    public void setAccessWay(String accessWay) {
        this.accessWay = accessWay;
    }
    
    /**
     * 交通手段名称を取得します。
     * @return 交通手段名称
     */
    public String getAccessWayName() {
        return accessWayName;
    }
    /**
     * 交通手段名称を設定します。
     * @param accessWayName 交通手段名称
     */
    public void setAccessWayName(String accessWayName) {
        this.accessWayName = accessWayName;
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
