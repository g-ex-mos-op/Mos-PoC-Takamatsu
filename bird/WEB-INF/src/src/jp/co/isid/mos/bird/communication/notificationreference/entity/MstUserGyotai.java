/*
 * 作成日: 2006/02/10
 *
 */
package jp.co.isid.mos.bird.communication.notificationreference.entity;

public class MstUserGyotai {
    
    public static final String TABLE = "BM05USGT";
    
    public static final String userId_COLUMN = "USER_ID";
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    public static final String gyotaiKbnName_COLUMN = "GYOTAI_KBN_NAME";
    
    
    /** ユーザID */
    private String userId;
    /** 業態区分 */
    private String gyotaiKbn;
    /** 業態区分名称 */
    private String gyotaiKbnName;
    
    
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
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    
    /**
     * 業態区分名称を取得します。
     * @return 業態区分名称
     */
    public String getGyotaiKbnName() {
        return gyotaiKbnName;
    }
    /**
     * 業態区分名称を設定します。
     * @param gyotaiKbnName 業態区分名称
     */
    public void setGyotaiKbnName(String gyotaiKbnName) {
        this.gyotaiKbnName = gyotaiKbnName;
    }
}
