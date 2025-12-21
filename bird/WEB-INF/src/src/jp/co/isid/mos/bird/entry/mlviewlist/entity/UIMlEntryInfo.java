package jp.co.isid.mos.bird.entry.mlviewlist.entity;

import java.sql.Timestamp;

public class UIMlEntryInfo {

    public static final String TABLE = "BT23MLEJ";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";

    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String staffId_COLUMN = "STAFF_ID";

    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String examNo_COLUMN = "EXAM_NO";
    
    public static final String entryPlaceName_COLUMN = "ENTRY_PLACE_NAME";
    
    public static final String staffNameKj_COLUMN = "STAFF_NAME_KJ";

    public static final String abilityChk_COLUMN = "ABILITY_CHK";
    
    public static final String examChk_COLUMN = "EXAM_CHK";
    
    public static final String interviewChk_COLUMN = "INTERVIEW_CHK";
    
    public static final String courseStatus_COLUMN = "COURSE_STATUS";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    private static final String COUSE_STATUS_NOTCOMPLETION = "0";
    private static final String COUSE_STATUS_COMPLETION = "1";
    private static final String COUSE_STATUS_SCHEDULE = "2";
    private static final String COUSE_STATUS_NAME_NOTCOMPLETION = "未修了";
    private static final String COUSE_STATUS_NAME_COMPLETION = "修了";
    private static final String COUSE_STATUS_NAME_SCHEDULE = "予定";

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
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * スタッフID
     */
    private String staffId;
    
    /**
     * スタッフ名称(受験者氏名)
     */
    private String staffNameKj;
    
    /**
     * 受験番号
     */
    private String examNo;
    
    /**
     * 受験希望地
     */
    private String entryPlaceName;
    
    /**
     * 能力
     */
    private String abilityChk;
    
    /**
     * 筆記
     */
    private String examChk;
    
    /**
     * 面接
     */
    private String interviewChk;

    /**
     * 研修状況
     */
    private String courseStatus;
    
    /**
     * オーナー契約フラグ
     */
    private String keiyakuFlg;
    
    /**
     * 能力フラグ
     */
    private boolean abilityFlg;
    
    /**
     * 筆記フラグ
     */
    private boolean examFlg;
    
    /**
     * 面接フラグ
     */
    private boolean interviewFlg;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正タイムスタンプ
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
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 研修生IDを取得します。
     * @return 研修生ID
     */
    public String getStaffId() {
        return staffId;
    }
    /**
     * 研修生IDを設定します。
     * @param staffId 研修生ID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    /**
     * 研修生名称を取得します。
     * @return 研修生名称
     */
    public String getStaffNameKj() {
        return staffNameKj;
    }
    /**
     * 研修生名称を設定します。
     * @param staffNameKj 研修生名称
     */
    public void setStaffNameKj(String staffNameKj) {
        this.staffNameKj = staffNameKj;
    }

    /**
     * オーナー契約フラグを取得します。
     * @return オーナー契約フラグ
     */
    public String getKeiyakuFlg() {
        return keiyakuFlg;
    }
    /**
     * オーナー契約フラグを設定します。
     * @param keiyakuFlg オーナー契約フラグ
     */
    public void setKeiyakuFlg(String keiyakuFlg) {
        this.keiyakuFlg = keiyakuFlg;
    }
    
    
    /**
     * 研修状況を取得します。
     * @return 研修状況
     */
    public String getCourseStatus() {
        return courseStatus;
    }
    /**
     * 研修状況を設定します。
     * @param courseStatus 研修状況
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
    
    /**
     * 受験希望地を取得します。
     * @return 受験希望地
     */
    public String getEntryPlaceName() {
        return entryPlaceName;
    }
    /**
     * 受験希望地を設定します。
     * @param entryPlaceName 受験希望地
     */
    public void setEntryPlaceName(String entryPlaceName) {
        this.entryPlaceName = entryPlaceName;
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
     * 能力チェックフラグを取得します。
     * @return 能力チェックフラグ
     */
    public String getAbilityChk() {
        return abilityChk;
    }
    /**
     * 能力チェックフラグを設定します。
     * @param abilityChk 能力チェックフラグ
     */
    public void setAbilityChk(String abilityChk) {
        this.abilityChk = abilityChk;
    }
    
    /**
     * 筆記チェックフラグを取得します。
     * @return 筆記チェックフラグ
     */
    public String getExamChk() {
        return examChk;
    }
    /**
     * 筆記チェックフラグを設定します。
     * @param examChk 筆記チェックフラグ
     */
    public void setExamChk(String examChk) {
        this.examChk = examChk;
    }

    /**
     * 面接チェックフラグを取得します。
     * @return 面接チェックフラグ
     */
    public String getInterviewChk() {
        return interviewChk;
    }
    /**
     * 面接チェックフラグを設定します。
     * @param interviewChk 面接チェックフラグ
     */
    public void setInterviewChk(String interviewChk) {
        this.interviewChk = interviewChk;
    }
    
    
    public boolean isAbilityFlg() {
        return abilityFlg;
    }
    public void setAbilityFlg(boolean abilityFlg) {
        this.abilityFlg = abilityFlg;
    }
    public boolean isExamFlg() {
        return examFlg;
    }
    public void setExamFlg(boolean examFlg) {
        this.examFlg = examFlg;
    }
    public boolean isInterviewFlg() {
        return interviewFlg;
    }
    public void setInterviewFlg(boolean interviewFlg) {
        this.interviewFlg = interviewFlg;
    }
    public String getLastPgm() {
        return lastPgm;
    }
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    public String getLastUser() {
        return lastUser;
    }
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public String getCourseStatusName() {
        String courseStatusName = "";
        if (getCourseStatus() != null) {
            if (getCourseStatus().equals(COUSE_STATUS_NOTCOMPLETION)) {
                courseStatusName = COUSE_STATUS_NAME_NOTCOMPLETION;
            } else if (getCourseStatus().equals(COUSE_STATUS_COMPLETION)) {
                courseStatusName = COUSE_STATUS_NAME_COMPLETION;
            } else if (getCourseStatus().equals(COUSE_STATUS_SCHEDULE)) {
                courseStatusName = COUSE_STATUS_NAME_SCHEDULE;
            }
        }
        return courseStatusName;
    }
}
