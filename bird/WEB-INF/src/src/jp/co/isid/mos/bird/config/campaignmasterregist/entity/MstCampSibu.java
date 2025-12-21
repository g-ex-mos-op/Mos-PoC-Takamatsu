package jp.co.isid.mos.bird.config.campaignmasterregist.entity;

import java.sql.Timestamp;

public class MstCampSibu {
    
    public static final String TABLE = "BM63CSSB";
    
    public static final String campId_COLUMN = "CAMP_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String areaDaiFlg_COLUMN = "AREA_DAI_FLG";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    /**
     * キャンペーン識別番号
     */
    private String campId;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * エリア大フラグ
     */
    private String areaDaiFlg;
    
    /**
     * 登録ユーザーID
     */
    private String firstUser;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 更新ユーザーID
     */
    private String lastUser;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * キャンペーン識別番号を取得します。
     * @return キャンペーン識別番号
     */
    public String getCampId() {
        return campId;
    }
    /**
     * キャンペーン識別番号を設定します。
     * @param campId キャンペーン識別番号
     */
    public void setCampId(String campId) {
        this.campId = campId;
    }
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * エリア大フラグを取得します。
     * @return エリア大フラグ
     */
    public String getAreaDaiFlg() {
        return areaDaiFlg;
    }
    /**
     * エリア大フラグを設定します。
     * @param areaDaiFlg エリア大フラグ
     */
    public void setAreaDaiFlg(String areaDaiFlg) {
        this.areaDaiFlg = areaDaiFlg;
    }
    
    /**
     * 登録ユーザーIDを取得します。
     * @return 登録ユーザーID
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーIDを設定します。
     * @param firstUser 登録ユーザーID
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
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
     * 更新ユーザーIDを取得します。
     * @return 更新ユーザーID
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザーIDを設定します。
     * @param lastUser 更新ユーザーID
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
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
    
}
