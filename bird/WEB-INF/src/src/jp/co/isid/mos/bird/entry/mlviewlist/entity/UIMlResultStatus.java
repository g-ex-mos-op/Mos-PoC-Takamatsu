package jp.co.isid.mos.bird.entry.mlviewlist.entity;

import java.sql.Timestamp;

public class UIMlResultStatus {

    public static final String TABLE = "BT32MLKR";
    
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String totalLastYear_COLUMN = "TOTAL_LAST_YEAR";
    
    public static final String totalLastKai_COLUMN = "TOTAL_LAST_KAI";
    
    public static final String examNo_COLUMN = "EXAM_NO";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    

    
    /**
     * スタッフID
     */
    private String staffId;
    
    /**
     * 受験年度
     */
    private String totalLastYear;
    
    /**
     * 受験回
     */
    private String totalLastKai;
    
    /**
     * 受験番号
     */
    private String examNo;
    
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

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getTotalLastKai() {
        return totalLastKai;
    }

    public void setTotalLastKai(String totalLastKai) {
        this.totalLastKai = totalLastKai;
    }

    public String getTotalLastYear() {
        return totalLastYear;
    }

    public void setTotalLastYear(String totalLastYear) {
        this.totalLastYear = totalLastYear;
    }
    
}
