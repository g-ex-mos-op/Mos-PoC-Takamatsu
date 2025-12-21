package jp.co.isid.mos.bird.config.scheduleregist.entity;

import java.sql.Timestamp;

public class CtlSchedule {
    
    public static final String TABLE = "BR70SCDL";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String taishoCd_COLUMN = "TAISHO_CD";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String scdlDate_COLUMN = "SCDL_DATE";
    
    public static final String scdlId_COLUMN = "SCDL_ID";
    
    public static final String title_COLUMN = "TITLE";
    
    public static final String titleDb_COLUMN = "TITLE_DB";
    
    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String seq_COLUMN = "SEQ";
    
    public static final String firstUserName_COLUMN = "FIRST_USER_NAME";
    
    /**
     * 対象コード
     */
    private String taishoCd;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * スケジュール日付
     */
    private String scdlDate;
    
    /**
     * スケジュールID
     */
    private String scdlId;
    
    /**
     * スケジュールタイトル
     */
    private String title;
    
    /**
     * スケジュールタイトル更新前値
     */
    private String titleDb;
    
    /**
     * 削除フラグ
     */
    private String sakujoFlg;
    
    /**
     * 登録ユーザーID
     */
    private String firstUser;
    
    /**
     * 登録ユーザー名称
     */
    private String firstUserName;
    
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
     * SEQ
     */
    private String seq;
    
    /**
     * 更新ステータス
     */
    private String status;
    
    /**
     * 更新対象フラグ
     */
    private boolean registFlg;
    
    /**
     * 対象コードを取得します。
     * @return 対象コード
     */
    public String getTaishoCd() {
        return taishoCd;
    }
    /**
     * 対象コードを設定します。
     * @param taishoCd 対象コード
     */
    public void setTaishoCd(String taishoCd) {
        this.taishoCd = taishoCd;
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
     * スケジュール日付を取得します。
     * @return スケジュール日付
     */
    public String getScdlDate() {
        return scdlDate;
    }
    /**
     * スケジュール日付を設定します。
     * @param scdlDate スケジュール日付
     */
    public void setScdlDate(String scdlDate) {
        this.scdlDate = scdlDate;
    }
    
    /**
     * スケジュールIDを取得します。
     * @return スケジュールID
     */
    public String getScdlId() {
        return scdlId;
    }
    /**
     * スケジュールIDを設定します。
     * @param scdlId スケジュールID
     */
    public void setScdlId(String scdlId) {
        this.scdlId = scdlId;
    }
    
    /**
     * スケジュールタイトルを取得します。
     * @return スケジュールタイトル
     */
    public String getTitle() {
        return title;
    }
    /**
     * スケジュールタイトルを設定します。
     * @param title スケジュールタイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * 削除フラグを取得します。
     * @return 削除フラグ
     */
    public String getSakujoFlg() {
        return sakujoFlg;
    }
    /**
     * 削除フラグを設定します。
     * @param sakujoFlg 削除フラグ
     */
    public void setSakujoFlg(String sakujoFlg) {
        this.sakujoFlg = sakujoFlg;
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
    public String getSeq() {
        return seq;
    }
    public void setSeq(String seq) {
        this.seq = seq;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isRegistFlg() {
        return registFlg;
    }
    public void setRegistFlg(boolean registFlg) {
        this.registFlg = registFlg;
    }
    public String getFirstUserName() {
        return firstUserName;
    }
    public void setFirstUserName(String firstUserName) {
        this.firstUserName = firstUserName;
    }
    public String getTitleDb() {
        return titleDb;
    }
    public void setTitleDb(String titleDb) {
        this.titleDb = titleDb;
    }
    
}
