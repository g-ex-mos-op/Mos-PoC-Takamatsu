package jp.co.isid.mos.bird.entry.mlentry.entity;

import java.sql.Timestamp;

public class UIEntry {
    
    public static final String TABLE = "BT23MLEJ";
    

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
    public static final String pa_expYear_COLUMN = "PA_EXP_YEAR";
    public static final String pa_expMonth_COLUMN = "PA_EXP_MONTH";
    public static final String job_COLUMN = "JOB";
    public static final String firstUser_COLUMN = "FIRST_USER";
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    public static final String lastUser_COLUMN = "LAST_USER";
    public static final String lastPgm_COLUMN = "LAST_PGM";
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    
    private String entryCd;
    private String entryYear;
    private String entryKai;
    private String staffId;
    private String companyCd;
    private String onerCd;
    private String examNo;
    private String entryPlaceCd;
    private String note;
    private String abilityChk;
    private String examChk;
    private String interviewChk;
    private String otherChk1;
    private String otherChk2;
    private String empExpYear;
    private String empExpMonth;
    private String paExpYear;
    private String paExpMonth;
    private String job;
    private String firstUser;
    private String firstPgm;
    private Timestamp firstTmsp;
    private String lastUser;
    private String lastPgm;
    private Timestamp lastTmsp;

	/**
	 * 新規行フラグ true:新規
	 */
	private boolean insertFlg = false;
    
    public boolean isInsertFlg() {
        return insertFlg;
    }
    public void setInsertFlg(boolean insertFlg) {
        this.insertFlg = insertFlg;
    }


    public String getAbilityChk() {
        return abilityChk;
    }
    public void setAbilityChk(String abilityChk) {
        this.abilityChk = abilityChk;
    }
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getEmpExpMonth() {
        return empExpMonth;
    }
    public void setEmpExpMonth(String empExpMonth) {
        this.empExpMonth = empExpMonth;
    }
    public String getEmpExpYear() {
        return empExpYear;
    }
    public void setEmpExpYear(String empExpYear) {
        this.empExpYear = empExpYear;
    }
    public String getEntryCd() {
        return entryCd;
    }
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    public String getEntryKai() {
        return entryKai;
    }
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    public String getEntryPlaceCd() {
        return entryPlaceCd;
    }
    public void setEntryPlaceCd(String entryPlaceCd) {
        this.entryPlaceCd = entryPlaceCd;
    }
    public String getEntryYear() {
        return entryYear;
    }
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    public String getExamChk() {
        return examChk;
    }
    public void setExamChk(String examChk) {
        this.examChk = examChk;
    }
    public String getExamNo() {
        return examNo;
    }
    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }
    public String getFirstPgm() {
        return firstPgm;
    }
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    public String getFirstUser() {
        return firstUser;
    }
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    public String getInterviewChk() {
        return interviewChk;
    }
    public void setInterviewChk(String interviewChk) {
        this.interviewChk = interviewChk;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
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
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getOnerCd() {
        return onerCd;
    }
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    public String getOtherChk1() {
        return otherChk1;
    }
    public void setOtherChk1(String otherChk1) {
        this.otherChk1 = otherChk1;
    }
    public String getOtherChk2() {
        return otherChk2;
    }
    public void setOtherChk2(String otherChk2) {
        this.otherChk2 = otherChk2;
    }
    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public String getPaExpMonth() {
        return paExpMonth;
    }
    public void setPaExpMonth(String paExpMonth) {
        this.paExpMonth = paExpMonth;
    }
    public String getPaExpYear() {
        return paExpYear;
    }
    public void setPaExpYear(String paExpYear) {
        this.paExpYear = paExpYear;
    }
}
