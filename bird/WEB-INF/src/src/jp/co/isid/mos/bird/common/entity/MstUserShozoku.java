package jp.co.isid.mos.bird.common.entity;

public class MstUserShozoku {
    
    public static final String TABLE = "BM13SHKM";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String shozokuKbn_COLUMN = "SHOZOKU_KBN";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * 所属区分
     */
    private String shozokuKbn;
    
    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザIDを設定します。
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 所属区分を取得します。
     * @return 所属区分
     */
    public String getShozokuKbn() {
        return shozokuKbn;
    }
    /**
     * 所属区分を設定します。
     * @param shozokuKbn 所属区分
     */
    public void setShozokuKbn(String shozokuKbn) {
        this.shozokuKbn = shozokuKbn;
    }
    
}
