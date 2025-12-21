package jp.co.isid.mos.bird.bizreport.posreportregist.entity;

import java.sql.Timestamp;

public class UIRealtimeStInfo {
    
    public static final String TABLE = "BR57RTST";
    
    public static final String haisSijiDt_COLUMN = "HAIS_SIJI_DT";
    
    public static final String haisSijiSeq_COLUMN = "HAIS_SIJI_SEQ";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String haisRsltSt_COLUMN = "HAIS_RSLT_ST";
    
    public static final String haisDt_COLUMN = "HAIS_DT";
    
    public static final String haisTime_COLUMN = "HAIS_TIME";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 配信指示日
     */
    private String haisSijiDt;
    
    /**
     * 配信指示SEQ
     */
    private String haisSijiSeq;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 配信結果ステータス
     */
    private String haisRsltSt;
    
    /**
     * 配信日
     */
    private String haisDt;
    
    /**
     * 配信時間
     */
    private String haisTime;
    
    /**
     * 登録ユーザＩＤ
     */
    private String firstUser;
    
    /**
     * 登録プログラムＩＤ
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザＩＤ
     */
    private String lastUser;
    
    /**
     * 修正プログラムＩＤ
     */
    private String lastPgm;
    
    /**
     * 修正タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 配信指示日を取得します。
     * @return 配信指示日
     */
    public String getHaisSijiDt() {
        return haisSijiDt;
    }
    /**
     * 配信指示日を設定します。
     * @param haisSijiDt 配信指示日
     */
    public void setHaisSijiDt(String haisSijiDt) {
        this.haisSijiDt = haisSijiDt;
    }
    
    /**
     * 配信指示SEQを取得します。
     * @return 配信指示SEQ
     */
    public String getHaisSijiSeq() {
        return haisSijiSeq;
    }
    /**
     * 配信指示SEQを設定します。
     * @param haisSijiSeq 配信指示SEQ
     */
    public void setHaisSijiSeq(String haisSijiSeq) {
        this.haisSijiSeq = haisSijiSeq;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 配信結果ステータスを取得します。
     * @return 配信結果ステータス
     */
    public String getHaisRsltSt() {
        return haisRsltSt;
    }
    /**
     * 配信結果ステータスを設定します。
     * @param haisRsltSt 配信結果ステータス
     */
    public void setHaisRsltSt(String haisRsltSt) {
        this.haisRsltSt = haisRsltSt;
    }
    
    /**
     * 配信日を取得します。
     * @return 配信日
     */
    public String getHaisDt() {
        return haisDt;
    }
    /**
     * 配信日を設定します。
     * @param haisDt 配信日
     */
    public void setHaisDt(String haisDt) {
        this.haisDt = haisDt;
    }
    
    /**
     * 配信時間を取得します。
     * @return 配信時間
     */
    public String getHaisTime() {
        return haisTime;
    }
    /**
     * 配信時間を設定します。
     * @param haisTime 配信時間
     */
    public void setHaisTime(String haisTime) {
        this.haisTime = haisTime;
    }
    
    /**
     * 登録ユーザＩＤを取得します。
     * @return 登録ユーザＩＤ
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザＩＤを設定します。
     * @param firstUser 登録ユーザＩＤ
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムＩＤを取得します。
     * @return 登録プログラムＩＤ
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムＩＤを設定します。
     * @param firstPgm 登録プログラムＩＤ
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
     * 修正ユーザＩＤを取得します。
     * @return 修正ユーザＩＤ
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザＩＤを設定します。
     * @param lastUser 修正ユーザＩＤ
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムＩＤを取得します。
     * @return 修正プログラムＩＤ
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムＩＤを設定します。
     * @param lastPgm 修正プログラムＩＤ
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正タイムスタンプを取得します。
     * @return 修正タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正タイムスタンプを設定します。
     * @param lastTmsp 修正タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
