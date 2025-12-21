package jp.co.isid.mos.bird.bizsupport.common.entity;

import java.sql.Timestamp;

public class CtlYosanControlDate {
    
    public static final String TABLE = "BR62YCDT";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String nendo_COLUMN = "NENDO";
    
    public static final String shoriKbn_COLUMN = "SHORI_KBN";
    
    public static final String stateFlg_COLUMN = "STATE_FLG";
    
    public static final String shoriDt_COLUMN = "SHORI_DT";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 年度
     */
    private String nendo;
    
    /**
     * 処理区分
     */
    private String shoriKbn;
    
    /**
     * ステータスフラグ
     */
    private String stateFlg;
    
    /**
     * 日付パラメータ
     */
    private String shoriDt;
    
    /**
     * 修正ユーザ
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時タイムスタンプ
     */
    private Timestamp lastTmsp;
    
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
     * 年度を取得します。
     * @return 年度
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * 年度を設定します。
     * @param nendo 年度
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }
    
    /**
     * 処理区分を取得します。
     * @return 処理区分
     */
    public String getShoriKbn() {
        return shoriKbn;
    }
    /**
     * 処理区分を設定します。
     * @param shoriKbn 処理区分
     */
    public void setShoriKbn(String shoriKbn) {
        this.shoriKbn = shoriKbn;
    }
    
    /**
     * ステータスフラグを取得します。
     * @return ステータスフラグ
     */
    public String getStateFlg() {
        return stateFlg;
    }
    /**
     * ステータスフラグを設定します。
     * @param stateFlg ステータスフラグ
     */
    public void setStateFlg(String stateFlg) {
        this.stateFlg = stateFlg;
    }
    
    /**
     * 日付パラメータを取得します。
     * @return 日付パラメータ
     */
    public String getShoriDt() {
        return shoriDt;
    }
    /**
     * 日付パラメータを設定します。
     * @param shoriDt 日付パラメータ
     */
    public void setShoriDt(String shoriDt) {
        this.shoriDt = shoriDt;
    }
    
    /**
     * 修正ユーザを取得します。
     * @return 修正ユーザ
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザを設定します。
     * @param lastUser 修正ユーザ
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時タイムスタンプを取得します。
     * @return 修正時タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時タイムスタンプを設定します。
     * @param lastTmsp 修正時タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
