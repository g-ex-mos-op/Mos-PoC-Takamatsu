package jp.co.isid.mos.bird.entry.mlentry.entity;

import java.sql.Timestamp;

public class UIEntryInfo{
    
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
    public static final String paExpYear_COLUMN = "PA_EXP_YEAR";
    public static final String paExpMonth_COLUMN = "PA_EXP_MONTH";
    public static final String job_COLUMN = "JOB";
    public static final String firstUser_COLUMN = "FIRST_USER";
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    public static final String lastUser_COLUMN = "LAST_USER";
    public static final String lastPgm_COLUMN = "LAST_PGM";
    public static final String lastTmsp_COLUMN = "LAST_TMSP";


    
    public String entryCd;
    public String entryYear;
    public String entryKai;
    public String staffId;
    public String companyCd;
    public String onerCd;
    public String examNo;
    public String entryPlaceCd;
    public String note;
    public String abilityChk;
    public String examChk;
    public String interviewChk;
    public String otherChk1;
    public String otherChk2;
    public String empExpYear;
    public String empExpMonth;
    public String paExpYear;
    public String paExpMonth;
    public String job;
    public String firstUser;
    public String firstPgm;
    public Timestamp firstTmsp;
    public String lastUser;
    public String lastPgm;
    public Timestamp lastTmsp;
    
    
    
    /**
     * エントリー状況フラグ
     * 3:エントリー済＋有効履歴あり
     * 2:エントリー済＋有効履歴なし ※履歴テーブルにレコードは存在するが、空レコードのデータ(今回分のデータ)を含む
     * 1:エントリー未＋有効履歴あり
     * 0:エントリー未＋有効履歴なし
     */
    private String stateFlg;
    
    
    /**
     * 新規フラグ true:新規
     */
    private boolean insertFlg;
    
    /**
     * 能力フラグ
     */
    private boolean abilityFlg = true;
    
    /**
     * 筆記フラグ
     */
    private boolean examFlg = true;
    
    /**
     * 面接フラグ
     */
    private boolean interviewFlg = true;
    
    
    
    /**
     * Nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    
    
    public String getStateFlg() {
        return stateFlg;
    }
    public void setStateFlg(String stateFlg) {
        this.stateFlg = stateFlg;
    }
    
    
    
    public String getAbilityChk() {
        if(isNull(this.abilityChk)) return "";
        return abilityChk;
    }
    public void setAbilityChk(String abilityChk) {
        this.abilityChk = abilityChk;
    }
    public String getCompanyCd() {
        if(isNull(this.companyCd)) return "";
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getEmpExpMonth() {
        if(isNull(this.empExpMonth)) return "";
        return empExpMonth;
    }
    public void setEmpExpMonth(String empExpMonth) {
        this.empExpMonth = empExpMonth;
    }
    public String getEmpExpYear() {
        if(isNull(this.empExpYear)) return "";
        return empExpYear;
    }
    public void setEmpExpYear(String empExpYear) {
        this.empExpYear = empExpYear;
    }
    public String getEntryCd() {
        if(isNull(this.entryCd)) return "";
        return entryCd;
    }
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    public String getEntryKai() {
        if(isNull(this.entryKai)) return "";
        return entryKai;
    }
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    public String getEntryPlaceCd() {
        if(isNull(this.entryPlaceCd)) return "";
        return entryPlaceCd;
    }
    public void setEntryPlaceCd(String entryPlaceCd) {
        this.entryPlaceCd = entryPlaceCd;
    }
    public String getEntryYear() {
        if(isNull(this.entryYear)) return "";
        return entryYear;
    }
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    public String getExamChk() {
        if(isNull(this.examChk)) return "";
        return examChk;
    }
    public void setExamChk(String examChk) {
        this.examChk = examChk;
    }
    public String getExamNo() {
        if(isNull(this.examNo)) return "";
        return examNo;
    }
    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }
    public String getFirstPgm() {
        if(isNull(this.firstPgm)) return "";
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
        if(isNull(this.firstUser)) return "";
        return firstUser;
    }
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    public String getInterviewChk() {
        if(isNull(this.interviewChk)) return "";
        return interviewChk;
    }
    public void setInterviewChk(String interviewChk) {
        this.interviewChk = interviewChk;
    }
    public String getJob() {
        if(isNull(this.job)) return "";
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getLastPgm() {
        if(isNull(this.lastPgm)) return "";
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
        if(isNull(this.lastUser)) return "";
        return lastUser;
    }
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    public String getNote() {
        if(isNull(this.note)) return "";
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getOnerCd() {
        if(isNull(this.onerCd)) return "";
        return onerCd;
    }
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    public String getOtherChk1() {
        if(isNull(this.otherChk1)) return "";
        return otherChk1;
    }
    public void setOtherChk1(String otherChk1) {
        this.otherChk1 = otherChk1;
    }
    public String getOtherChk2() {
        if(isNull(this.otherChk2)) return "";
        return otherChk2;
    }
    public void setOtherChk2(String otherChk2) {
        this.otherChk2 = otherChk2;
    }
    public String getPaExpMonth() {
        if(isNull(this.paExpMonth)) return "";
        return paExpMonth;
    }
    public void setPaExpMonth(String paExpMonth) {
        this.paExpMonth = paExpMonth;
    }
    public String getPaExpYear() {
        if(isNull(this.paExpYear)) return "";
        return paExpYear;
    }
    public void setPaExpYear(String paExpYear) {
        this.paExpYear = paExpYear;
    }
    public String getStaffId() {
        if(isNull(this.staffId)) return "";
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public boolean isInsertFlg() {
        return insertFlg;
    }
    public void setInsertFlg(boolean insertFlg) {
        this.insertFlg = insertFlg;
    }

}
