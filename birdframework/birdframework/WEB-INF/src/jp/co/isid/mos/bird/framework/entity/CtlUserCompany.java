package jp.co.isid.mos.bird.framework.entity;

public class CtlUserCompany {
    
    public static final String TABLE = "BM03USCP";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String rCompanyCd_COLUMN = "R_COMPANY_CD";

    public static final String zokuseiKbn_COLUMN = "ZOKUSEI_KBN";

    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * 企業コード
     */
    private String rCompanyCd;

    /**
     * 属性区分
     */
    private String zokuseiKbn;

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
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getRCompanyCd() {
        return rCompanyCd;
    }
    /**
     * 企業コードを設定します。
     * @param rCompanyCd 企業コード
     */
    public void setRCompanyCd(String rCompanyCd) {
        this.rCompanyCd = rCompanyCd;
    }
    
    /**
     * 属性区分を取得します。
     * @return zokuseiKbn 属性区分
     */
    public String getZokuseiKbn() {
        return zokuseiKbn;
    }
    /**
     * 属性区分を設定します。
     * @param zokuseiKbn 属性区分
     */
    public void setZokuseiKbn(String zokuseiKbn) {
        this.zokuseiKbn = zokuseiKbn;
    }
}
