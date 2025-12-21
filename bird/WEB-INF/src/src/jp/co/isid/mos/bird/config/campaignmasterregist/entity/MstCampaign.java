package jp.co.isid.mos.bird.config.campaignmasterregist.entity;

import java.sql.Timestamp;

public class MstCampaign {
    
    public static final String TABLE = "BM60CPDT";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String campId_COLUMN = "CAMP_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String campTitle_COLUMN = "CAMP_TITLE";
    
    public static final String campFrom_COLUMN = "CAMP_FROM";
    
    public static final String campTo_COLUMN = "CAMP_TO";
    
    public static final String dispFrom_COLUMN = "DISP_FROM";
    
    public static final String dispTo_COLUMN = "DISP_TO";
    
    public static final String yobiFrom_COLUMN = "YOBI_FROM";
    
    public static final String yobiTo_COLUMN = "YOBI_TO";
    
    public static final String targetKbn_COLUMN = "TARGET_KBN";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String campFromEditBef_COLUMN = "CAMP_FROM_EDIT_BEF";
    
    /**
     * キャンペーン識別番号
     */
    private String campId;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * タイトル
     */
    private String campTitle;
    
    /**
     * 対象期間開始日
     */
    private String campFrom;
    
    /**
     * 対象期間終了日
     */
    private String campTo;
    
    /**
     * 表示期間開始日
     */
    private String dispFrom;
    
    /**
     * 表示期間終了日
     */
    private String dispTo;
    
    /**
     * 予備期間開始日
     */
    private String yobiFrom;
    
    /**
     * 予備期間終了日
     */
    private String yobiTo;
    
    /**
     * キャンペーン対象区分
     */
    private String targetKbn;
    
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
     * 登録モード
     */
    private String registMode;
    
    /**
     * キャンペーン対象期間開始日 編集前
     */
    private String campFromEditBef;
    
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
     * タイトルを取得します。
     * @return タイトル
     */
    public String getCampTitle() {
        return campTitle;
    }
    /**
     * タイトルを設定します。
     * @param campTitle タイトル
     */
    public void setCampTitle(String campTitle) {
        this.campTitle = campTitle;
    }
    
    /**
     * 対象期間開始日を取得します。
     * @return 対象期間開始日
     */
    public String getCampFrom() {
        return campFrom;
    }
    /**
     * 対象期間開始日を設定します。
     * @param campFrom 対象期間開始日
     */
    public void setCampFrom(String campFrom) {
        this.campFrom = campFrom;
    }
    
    /**
     * 対象期間終了日を取得します。
     * @return 対象期間終了日
     */
    public String getCampTo() {
        return campTo;
    }
    /**
     * 対象期間終了日を設定します。
     * @param campTo 対象期間終了日
     */
    public void setCampTo(String campTo) {
        this.campTo = campTo;
    }
    
    /**
     * 表示期間開始日を取得します。
     * @return 表示期間開始日
     */
    public String getDispFrom() {
        return dispFrom;
    }
    /**
     * 表示期間開始日を設定します。
     * @param dispFrom 表示期間開始日
     */
    public void setDispFrom(String dispFrom) {
        this.dispFrom = dispFrom;
    }
    
    /**
     * 表示期間終了日を取得します。
     * @return 表示期間終了日
     */
    public String getDispTo() {
        return dispTo;
    }
    /**
     * 表示期間終了日を設定します。
     * @param dispTo 表示期間終了日
     */
    public void setDispTo(String dispTo) {
        this.dispTo = dispTo;
    }
    
    /**
     * 予備期間開始日を取得します。
     * @return 予備期間開始日
     */
    public String getYobiFrom() {
        return yobiFrom;
    }
    /**
     * 予備期間開始日を設定します。
     * @param yobiFrom 予備期間開始日
     */
    public void setYobiFrom(String yobiFrom) {
        this.yobiFrom = yobiFrom;
    }
    
    /**
     * 予備期間終了日を取得します。
     * @return 予備期間終了日
     */
    public String getYobiTo() {
        return yobiTo;
    }
    /**
     * 予備期間終了日を設定します。
     * @param yobiTo 予備期間終了日
     */
    public void setYobiTo(String yobiTo) {
        this.yobiTo = yobiTo;
    }
    
    /**
     * キャンペーン対象区分を取得します。
     * @return キャンペーン対象区分
     */
    public String getTargetKbn() {
        return targetKbn;
    }
    /**
     * キャンペーン対象区分を設定します。
     * @param targetKbn キャンペーン対象区分
     */
    public void setTargetKbn(String targetKbn) {
        this.targetKbn = targetKbn;
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
    
    /**
     * 登録モードを取得します。
     * @return 登録モード
     */
    public String getRegistMode() {
        return registMode;
    }
    /**
     * 登録モードを設定します。
     * @param registMode 登録モード
     */
    public void setRegistMode(String registMode) {
        this.registMode = registMode;
    }
    public String getCampFromEditBef() {
        return campFromEditBef;
    }
    public void setCampFromEditBef(String campFromEditBef) {
        this.campFromEditBef = campFromEditBef;
    }
    
}
