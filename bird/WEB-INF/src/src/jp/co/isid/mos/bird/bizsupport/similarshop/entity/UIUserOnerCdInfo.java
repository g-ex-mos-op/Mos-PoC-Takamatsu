package jp.co.isid.mos.bird.bizsupport.similarshop.entity;

public class UIUserOnerCdInfo {
    
    public static final String TABLE = "BM06UONR";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String onerCd_COLUMN = "ONER_CD";
    
    /**
     * ユーザーID
     */
    private String userId;
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
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
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
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
