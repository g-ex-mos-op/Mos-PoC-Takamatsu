package jp.co.isid.mos.bird.bill.detailbilldownload.entity;

public class UIUserOner {
    
    public static final String TABLE = "BM06UONR";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * ユーザーID
     */
    private String userId;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * ユーザーIDを取得します。
     * @return ユーザーID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザーIDを設定します。
     * @param userId ユーザーID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
}
