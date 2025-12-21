package jp.co.isid.mos.bird.bizadmin.svtantousibu.entity;

import java.sql.Timestamp;

public class MstTantouSibu {
    
    public static final String TABLE = "BM51SVSB";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String svCd_COLUMN = "SV_CD";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * ユーザID
     */
    private String svCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 登録ユーザ
     */
    private String firstUser;
    
    /**
     * 登録PGM
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 更新者
     */
    private String lastUser;
    
    /**
     * 最終変更PGM
     */
    private String lastPgm;
    
    /**
     * 更新TMSP
     */
    private Timestamp lastTmsp;
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getSvCd() {
        return svCd;
    }
    /**
     * ユーザIDを設定します。
     * @param svCd ユーザID
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
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
     * 登録ユーザを取得します。
     * @return 登録ユーザ
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザを設定します。
     * @param firstUser 登録ユーザ
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録PGMを取得します。
     * @return 登録PGM
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録PGMを設定します。
     * @param firstPgm 登録PGM
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
     * 更新者を取得します。
     * @return 更新者
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新者を設定します。
     * @param lastUser 更新者
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 最終変更PGMを取得します。
     * @return 最終変更PGM
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 最終変更PGMを設定します。
     * @param lastPgm 最終変更PGM
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新TMSPを取得します。
     * @return 更新TMSP
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新TMSPを設定します。
     * @param lastTmsp 更新TMSP
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
