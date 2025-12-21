package jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity;

import java.sql.Timestamp;

public class TrnMlResult {
    
    public static final String TABLE = "BT32MLKR";

    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String totalLastYear_COLUMN = "TOTAL_LAST_YEAR";
    
    public static final String totalLastKai_COLUMN = "TOTAL_LAST_KAI";  
    
    public static final String totalResult_COLUMN = "TOTAL_RESULT";
    
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
     * 総合結果
     */
    private String totalResult;
    
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
     * 受験年度を取得します。
     * @return 受験年度
     */
    public String getTotalLastYear() {
        return totalLastYear;
    }
    /**
     * 受験年度を設定します。
     * @param totalLastYear 受験年度
     */
    public void setTotalLastYear(String totalLastYear) {
        this.totalLastYear = totalLastYear;
    }
    
    /**
     * 受験回を取得します。
     * @return 受験回
     */
    public String getTotalLastKai() {
        return totalLastKai;
    }
    /**
     * 受験回を設定します。
     * @param totalLastKai 受験回
     */
    public void setTotalLastKai(String totalLastKai) {
        this.totalLastKai = totalLastKai;
    }
    
    /**
     * 総合結果を取得します。
     * @return 総合結果
     */
    public String getTotalResult() {
        return totalResult;
    }
    /**
     * 総合結果を設定します。
     * @param totalResult 総合結果
     */
    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
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
