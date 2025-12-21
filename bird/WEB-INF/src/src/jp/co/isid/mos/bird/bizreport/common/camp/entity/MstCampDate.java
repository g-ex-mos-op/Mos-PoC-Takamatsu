package jp.co.isid.mos.bird.bizreport.common.camp.entity;

import java.sql.Timestamp;

public class MstCampDate {
    
    public static final String TABLE = "BM60CPDT";
    
    public static final String campId_COLUMN = "CAMP_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String campTitle_COLUMN = "CAMP_TITLE";
    
    public static final String campFrom_COLUMN = "CAMP_FROM";
    
    public static final String campTo_COLUMN = "CAMP_TO";
    
    public static final String dispFrom_COLUMN = "DISP_FROM";
    
    public static final String dispTo_COLUMN = "DISP_TO";
    
    public static final String yobiFrom_COLUMN = "YOBI_FROM";
    
    public static final String yobiTo_COLUMN = "YOBI_TO";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * キャンペーン識別番号
     */
    private String campId;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * キャンペーンタイトル
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
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
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
     * キャンペーンタイトルを取得します。
     * @return キャンペーンタイトル
     */
    public String getCampTitle() {
        return campTitle;
    }
    /**
     * キャンペーンタイトルを設定します。
     * @param campTitle キャンペーンタイトル
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
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
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
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
