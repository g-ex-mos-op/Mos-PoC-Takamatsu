package jp.co.isid.mos.bird.entry.hanyoapplication.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TrnEntryResult {
    
    public static final String TABLE = "BT30ENKJ";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String courseStatus_COLUMN = "COURSE_STATUS";
    
    public static final String compleCourseDt_COLUMN = "COMPLE_COURSE_DT";
    
    public static final String complePoint_COLUMN = "COMPLE_POINT";
    
    public static final String compleCourseCd_COLUMN = "COMPLE_COURSE_CD";
    
    public static final String compleCourseName_COLUMN = "COMPLE_COURSE_NAME";
    
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
     * スタッフID
     */
    private String staffId;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * 修了（予定）コース状況
     */
    private String courseStatus = "";
    
    /**
     * 修了（予定）コース修了年月
     */
    private String compleCourseDt = "";
    
    /**
     * 点数
     */
    private BigDecimal complePoint = new BigDecimal("0");
    
    /**
     * 修了（予定）コースコード
     */
    private String compleCourseCd = "";
    
    /**
     * 修了（予定）コース名称
     */
    private String compleCourseName = "";
    
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
     * 修了（予定）コース状況を取得します。
     * @return 修了（予定）コース状況
     */
    public String getCourseStatus() {
        return courseStatus;
    }
    /**
     * 修了（予定）コース状況を設定します。
     * @param courseStatus 修了（予定）コース状況
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
    
    /**
     * 修了（予定）コース修了年月を取得します。
     * @return 修了（予定）コース修了年月
     */
    public String getCompleCourseDt() {
        return compleCourseDt;
    }
    /**
     * 修了（予定）コース修了年月を設定します。
     * @param compleCourseDt 修了（予定）コース修了年月
     */
    public void setCompleCourseDt(String compleCourseDt) {
        this.compleCourseDt = compleCourseDt;
    }
    
    /**
     * 点数を取得します。
     * @return 点数
     */
    public BigDecimal getComplePoint() {
        return complePoint;
    }
    /**
     * 点数を設定します。
     * @param complePoint 点数
     */
    public void setComplePoint(BigDecimal complePoint) {
        this.complePoint = complePoint;
    }
    
    /**
     * 修了（予定）コースコードを取得します。
     * @return 修了（予定）コースコード
     */
    public String getCompleCourseCd() {
        return compleCourseCd;
    }
    /**
     * 修了（予定）コースコードを設定します。
     * @param compleCourseCd 修了（予定）コースコード
     */
    public void setCompleCourseCd(String compleCourseCd) {
        this.compleCourseCd = compleCourseCd;
    }
    
    /**
     * 修了（予定）コース名称を取得します。
     * @return 修了（予定）コース名称
     */
    public String getCompleCourseName() {
        return compleCourseName;
    }
    /**
     * 修了（予定）コース名称を設定します。
     * @param compleCourseName 修了（予定）コース名称
     */
    public void setCompleCourseName(String compleCourseName) {
        this.compleCourseName = compleCourseName;
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
