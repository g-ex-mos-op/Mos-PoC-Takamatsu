package jp.co.isid.mos.bird.entry.mlentry.entity;

import java.sql.Timestamp;

public class UIResultState {
    
    public static final String TABLE = "BT32MLKR";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    public static final String licenseKbn_COLUMN = "LICENSE_KBN";
    public static final String examNo_COLUMN = "EXAM_NO";
    public static final String reentryFlg_COLUMN = "REENTRY_FLG";
    public static final String entryCount_COLUMN = "ENTRY_COUNT";
    public static final String reentryBaseYear_COLUMN = "REENTRY_BASE_YEAR";
    public static final String totalLastYear_COLUMN = "TOTAL_LAST_YEAR";
    public static final String totalLastKai_COLUMN = "TOTAL_LAST_KAI";
    public static final String totalResult_COLUMN = "TOTAL_RESULT";
    public static final String sub1LastYear_COLUMN = "SUB1_LAST_YEAR";
    public static final String sub1LastKai_COLUMN = "SUB1_LAST_KAI";
    public static final String sub1Result_COLUMN = "SUB1_RESULT";
    public static final String sub2LastYear_COLUMN = "SUB2_LAST_YEAR";
    public static final String sub2LastKai_COLUMN = "SUB2_LAST_KAI";
    public static final String sub2Result_COLUMN = "SUB2_RESULT";
    public static final String sub3LastYear_COLUMN = "SUB3_LAST_YEAR";
    public static final String sub3LastKai_COLUMN = "SUB3_LAST_KAI";
    public static final String sub3Result_COLUMN = "SUB3_RESULT";
    public static final String firstUser_COLUMN = "FIRST_USER";
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    public static final String lastUser_COLUMN = "LAST_USER";
    public static final String lastPgm_COLUMN = "LAST_PGM";
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    private String staffId;
    private String licenseKbn;
    private String examNo;
    private String reentryFlg;
    private String entryCount;
    private String reentryBaseYear;
    private String totalLastYear;
    private String totalLastKai;
    private String totalResult;
    private String sub1LastYear;
    private String sub1LastKai;
    private String sub1Result;
    private String sub2LastYear;
    private String sub2LastKai;
    private String sub2Result;
    private String sub3LastYear;
    private String sub3LastKai;
    private String sub3Result;
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

    /**
     * 前回受験フラグ 0:未受験 1:受験
     */
    private String beforeFlg;
    
    /**
     * Nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    

    public String getEntryCount() {
        if(isNull(this.entryCount)) return "";
        return entryCount;
    }
    public void setEntryCount(String entryCount) {
        this.entryCount = entryCount;
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
    public String getReentryBaseYear() {
        if(isNull(this.reentryBaseYear)) return "";
        return reentryBaseYear;
    }
    public void setReentryBaseYear(String reentryBaseYear) {
        this.reentryBaseYear = reentryBaseYear;
    }
    public String getReentryFlg() {
        if(isNull(this.reentryFlg)) return "";
        return reentryFlg;
    }
    public void setReentryFlg(String reentryFlg) {
        this.reentryFlg = reentryFlg;
    }
    public String getStaffId() {
        if(isNull(this.staffId)) return "";
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public String getSub1LastKai() {
        if(isNull(this.sub1LastKai)) return "";
        return sub1LastKai;
    }
    public void setSub1LastKai(String sub1LastKai) {
        this.sub1LastKai = sub1LastKai;
    }
    public String getSub1LastYear() {
        if(isNull(this.sub1LastYear)) return "";
        return sub1LastYear;
    }
    public void setSub1LastYear(String sub1LastYear) {
        this.sub1LastYear = sub1LastYear;
    }
    public String getSub1Result() {
        if(isNull(this.sub1Result)) return "";
        return sub1Result;
    }
    public void setSub1Result(String sub1Result) {
        this.sub1Result = sub1Result;
    }
    public String getSub2LastKai() {
        if(isNull(this.sub2LastKai)) return "";
        return sub2LastKai;
    }
    public void setSub2LastKai(String sub2LastKai) {
        this.sub2LastKai = sub2LastKai;
    }
    public String getSub2LastYear() {
        if(isNull(this.sub2LastYear)) return "";
        return sub2LastYear;
    }
    public void setSub2LastYear(String sub2LastYear) {
        this.sub2LastYear = sub2LastYear;
    }
    public String getSub2Result() {
        if(isNull(this.sub2Result)) return "";
        return sub2Result;
    }
    public void setSub2Result(String sub2Result) {
        this.sub2Result = sub2Result;
    }
    public String getSub3LastKai() {
        if(isNull(this.sub3LastKai)) return "";
        return sub3LastKai;
    }
    public void setSub3LastKai(String sub3LastKai) {
        this.sub3LastKai = sub3LastKai;
    }
    public String getSub3LastYear() {
        if(isNull(this.sub3LastYear)) return "";
        return sub3LastYear;
    }
    public void setSub3LastYear(String sub3LastYear) {
        this.sub3LastYear = sub3LastYear;
    }
    public String getSub3Result() {
        if(isNull(this.sub3Result)) return "";
        return sub3Result;
    }
    public void setSub3Result(String sub3Result) {
        this.sub3Result = sub3Result;
    }
    public String getTotalLastKai() {
        if(isNull(this.totalLastKai)) return "";
        return totalLastKai;
    }
    public void setTotalLastKai(String totalLastKai) {
        this.totalLastKai = totalLastKai;
    }
    public String getTotalLastYear() {
        if(isNull(this.totalLastYear)) return "";
        return totalLastYear;
    }
    public void setTotalLastYear(String totalLastYear) {
        this.totalLastYear = totalLastYear;
    }
    public String getTotalResult() {
        if(isNull(this.totalResult)) return "";
        return totalResult;
    }
    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }
    public String getLicenseKbn() {
        if(isNull(this.licenseKbn)) return "";
        return licenseKbn;
    }
    public void setLicenseKbn(String licenseKbn) {
        this.licenseKbn = licenseKbn;
    }
    public String getBeforeFlg() {
        return beforeFlg;
    }
    public void setBeforeFlg(String beforeFlg) {
        this.beforeFlg = beforeFlg;
    }
}
