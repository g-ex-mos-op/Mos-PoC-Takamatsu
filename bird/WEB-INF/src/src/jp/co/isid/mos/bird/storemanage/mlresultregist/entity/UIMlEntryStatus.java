package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

import java.sql.Timestamp;

public class UIMlEntryStatus {
    
    public static final String TABLE = "BT23MLEJ";

    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String examNo_COLUMN = "EXAM_NO";
    
    public static final String entryPlaceCd_COLUMN = "ENTRY_PLACE_CD";
    
    public static final String note_COLUMN = "NOTE";
    
    public static final String abilityChk_COLUMN = "ABILITY_CHK";
    
    public static final String examChk_COLUMN = "EXAM_CHK";
    
    public static final String interviewChk_COLUMN = "INTERVIEW_CHK";
    
    public static final String otherChk1_COLUMN = "OTHER_CHK1";
    
    public static final String otherChk2_COLUMN = "OTHER_CHK2";
    
    public static final String empExpYear_COLUMN = "EMP_EXP_YEAR";
    
    public static final String empExpMonth_COLUMN = "EMP_EXP_MONTH";
    
    public static final String paExpYear_COLUMN = "PA_EXP_YEAR";
    
    public static final String paExpMonth_COLUMN = "PA_EXP_MONTH";
    
    public static final String job_COLUMN = "JOB";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * エントリーコード
     */
    private String entryCd;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * スタッフID
     */
    private String staffId;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * 受験番号
     */
    private String examNo;
    
    /**
     * 受験希望地域コード
     */
    private String entryPlaceCd;
    
    /**
     * 備考
     */
    private String note;
    
    /**
     * 能力チェック
     */
    private String abilityChk;
    
    /**
     * 筆記テスト
     */
    private String examChk;
    
    /**
     * 面接
     */
    private String interviewChk;
    
    /**
     * その他1
     */
    private String otherChk1;
    
    /**
     * その他2
     */
    private String otherChk2;
    
    /**
     * 社員暦　年
     */
    private String empExpYear;
    
    /**
     * 社員暦　月
     */
    private String empExpMonth;
    
    /**
     * ＰＡ暦　年
     */
    private String paExpYear;
    
    /**
     * ＰＡ暦　月
     */
    private String paExpMonth;
    
    /**
     * 職位
     */
    private String job;
    
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
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    
    /**
     * スタッフIDを取得します。
     * @return スタッフID
     */
    public String getStaffId() {
        return staffId;
    }
    /**
     * スタッフIDを設定します。
     * @param staffId スタッフID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * 受験番号を取得します。
     * @return 受験番号
     */
    public String getExamNo() {
        return examNo;
    }
    /**
     * 受験番号を設定します。
     * @param examNo 受験番号
     */
    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }
    
    /**
     * 受験希望地域コードを取得します。
     * @return 受験希望地域コード
     */
    public String getEntryPlaceCd() {
        return entryPlaceCd;
    }
    /**
     * 受験希望地域コードを設定します。
     * @param entryPlaceCd 受験希望地域コード
     */
    public void setEntryPlaceCd(String entryPlaceCd) {
        this.entryPlaceCd = entryPlaceCd;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考を設定します。
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 能力チェックを取得します。
     * @return 能力チェック
     */
    public String getAbilityChk() {
        return abilityChk;
    }
    /**
     * 能力チェックを設定します。
     * @param abilityChk 能力チェック
     */
    public void setAbilityChk(String abilityChk) {
        this.abilityChk = abilityChk;
    }
    
    /**
     * 筆記テストを取得します。
     * @return 筆記テスト
     */
    public String getExamChk() {
        return examChk;
    }
    /**
     * 筆記テストを設定します。
     * @param examChk 筆記テスト
     */
    public void setExamChk(String examChk) {
        this.examChk = examChk;
    }
    
    /**
     * 面接を取得します。
     * @return 面接
     */
    public String getInterviewChk() {
        return interviewChk;
    }
    /**
     * 面接を設定します。
     * @param interviewChk 面接
     */
    public void setInterviewChk(String interviewChk) {
        this.interviewChk = interviewChk;
    }
    
    /**
     * その他1を取得します。
     * @return その他1
     */
    public String getOtherChk1() {
        return otherChk1;
    }
    /**
     * その他1を設定します。
     * @param otherChk1 その他1
     */
    public void setOtherChk1(String otherChk1) {
        this.otherChk1 = otherChk1;
    }
    
    /**
     * その他2を取得します。
     * @return その他2
     */
    public String getOtherChk2() {
        return otherChk2;
    }
    /**
     * その他2を設定します。
     * @param otherChk2 その他2
     */
    public void setOtherChk2(String otherChk2) {
        this.otherChk2 = otherChk2;
    }
    
    /**
     * 社員暦　年を取得します。
     * @return 社員暦　年
     */
    public String getEmpExpYear() {
        return empExpYear;
    }
    /**
     * 社員暦　年を設定します。
     * @param empExpYear 社員暦　年
     */
    public void setEmpExpYear(String empExpYear) {
        this.empExpYear = empExpYear;
    }
    
    /**
     * 社員暦　月を取得します。
     * @return 社員暦　月
     */
    public String getEmpExpMonth() {
        return empExpMonth;
    }
    /**
     * 社員暦　月を設定します。
     * @param empExpMonth 社員暦　月
     */
    public void setEmpExpMonth(String empExpMonth) {
        this.empExpMonth = empExpMonth;
    }
    
    /**
     * ＰＡ暦　年を取得します。
     * @return ＰＡ暦　年
     */
    public String getPaExpYear() {
        return paExpYear;
    }
    /**
     * ＰＡ暦　年を設定します。
     * @param paExpYear ＰＡ暦　年
     */
    public void setPaExpYear(String paExpYear) {
        this.paExpYear = paExpYear;
    }
    
    /**
     * ＰＡ暦　月を取得します。
     * @return ＰＡ暦　月
     */
    public String getPaExpMonth() {
        return paExpMonth;
    }
    /**
     * ＰＡ暦　月を設定します。
     * @param paExpMonth ＰＡ暦　月
     */
    public void setPaExpMonth(String paExpMonth) {
        this.paExpMonth = paExpMonth;
    }
    
    /**
     * 職位を取得します。
     * @return 職位
     */
    public String getJob() {
        return job;
    }
    /**
     * 職位を設定します。
     * @param job 職位
     */
    public void setJob(String job) {
        this.job = job;
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
