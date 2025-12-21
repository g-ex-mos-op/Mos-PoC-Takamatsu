package jp.co.isid.mos.bird.bizreport.posreportregist.entity;

/**
 * 店舗別リアルタイム集信期間マスタ エンティティクラス
 * 
 * 作成日:2010/11/10
 * @author xkinu
 *
 */
public class MstRealTimeSchedule {
    
    public static final String TABLE = "BR82RTSM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String shuDtSta_COLUMN = "SHU_DT_STA";
    
    public static final String shuDtEnd_COLUMN = "SHU_DT_END";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 集信開始日
     */
    private String shuDtSta;
    
    /**
     * 集信終了日
     */
    private String shuDtEnd;
    
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
    private java.sql.Timestamp firstTmsp;
    
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
    private java.sql.Timestamp lastTmsp;
    
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
     * 集信開始日を取得します。
     * @return 集信開始日
     */
    public String getShuDtSta() {
        return shuDtSta;
    }
    /**
     * 集信開始日を設定します。
     * @param shuDtSta 集信開始日
     */
    public void setShuDtSta(String shuDtSta) {
        this.shuDtSta = shuDtSta;
    }
    
    /**
     * 集信終了日を取得します。
     * @return 集信終了日
     */
    public String getShuDtEnd() {
        return shuDtEnd;
    }
    /**
     * 集信終了日を設定します。
     * @param shuDtEnd 集信終了日
     */
    public void setShuDtEnd(String shuDtEnd) {
        this.shuDtEnd = shuDtEnd;
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
    public java.sql.Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(java.sql.Timestamp firstTmsp) {
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
    public java.sql.Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正タイムスタンプを設定します。
     * @param lastTmsp 修正タイムスタンプ
     */
    public void setLastTmsp(java.sql.Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
