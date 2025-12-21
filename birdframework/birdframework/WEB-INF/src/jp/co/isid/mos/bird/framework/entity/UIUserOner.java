package jp.co.isid.mos.bird.framework.entity;

public class UIUserOner {
    
    public static final String TABLE = "BM06UONR";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userNameKana_COLUMN = "USER_NAME_KANA";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * ユーザ名称
     */
    private String userNameKj;
    
    /**
     * ユーザ名称(カナ)
     */
    private String userNameKana;
    
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
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
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
    
    /**
     * ユーザ名称を取得します。
     * @return ユーザ名称
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * ユーザ名称を設定します。
     * @param userNameKj ユーザ名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * ユーザ名称(カナ)を取得します。
     * @return ユーザ名称(カナ)
     */
    public String getUserNameKana() {
        return userNameKana;
    }
    /**
     * ユーザ名称(カナ)を設定します。
     * @param userNameKana ユーザ名称(カナ)
     */
    public void setUserNameKana(String userNameKana) {
        this.userNameKana = userNameKana;
    }
    
}
