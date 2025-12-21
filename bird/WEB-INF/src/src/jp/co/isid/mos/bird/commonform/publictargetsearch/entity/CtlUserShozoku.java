package jp.co.isid.mos.bird.commonform.publictargetsearch.entity;

public class CtlUserShozoku {
    
    public static final String TABLE = "BM11SHKM";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String shozokuKbn_COLUMN = "SHOZOKU_KBN";
    
    /**
     * ユーザＩＤ
     */
    private String userId;
    
    /**
     * 所属区分
     */
    private String shozokuKbn;
    
    /**
     * ユーザＩＤを取得します。
     * @return ユーザＩＤ
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザＩＤを設定します。
     * @param userId ユーザＩＤ
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
