package jp.co.isid.mos.bird.framework.entity;

public class CtlOnlineUsedTbl {
    
    public static final String TABLE = "BR13OLTL";
    
    public static final String onlId_COLUMN = "ONL_ID";
    
    public static final String tblId_COLUMN = "TBL_ID";
    
    public static final String state_COLUMN = "STATE";
    
    /**
     * オンラインID
     */
    private String onlId;
    
    /**
     * テーブルID
     */
    private String tblId;
    
    /**
     * 状態
     */
    private String state;
    
    /**
     * オンラインIDを取得します。
     * @return オンラインID
     */
    public String getOnlId() {
        return onlId;
    }
    /**
     * オンラインIDを設定します。
     * @param onlId オンラインID
     */
    public void setOnlId(String onlId) {
        this.onlId = onlId;
    }
    
    /**
     * テーブルIDを取得します。
     * @return テーブルID
     */
    public String getTblId() {
        return tblId;
    }
    /**
     * テーブルIDを設定します。
     * @param tblId テーブルID
     */
    public void setTblId(String tblId) {
        this.tblId = tblId;
    }
    
    /**
     * 状態を取得します。
     * @return 状態
     */
    public String getState() {
        return state;
    }
    /**
     * 状態を設定します。
     * @param state 状態
     */
    public void setState(String state) {
        this.state = state;
    }
    
}
