package jp.co.isid.mos.bird.pmossles.login.entity;

/**
 * 店舗対象ユーザーエンティティ
 * 
 * 作成日:2010/12/08
 * @author xkinu
 *
 */
public class UIMiseUser {
    
    public static final String TABLE = "br01user";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userNameKana_COLUMN = "USER_NAME_KANA";
    
    public static final String userPswd_COLUMN = "USER_PSWD";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * ユーザーＩＤ
     */
    private String userId;
    
    /**
     * ユーザー名称（漢字）
     */
    private String userNameKj;
    
    /**
     * ユーザー名称（カナ）
     */
    private String userNameKana;
    
    /**
     * ユーザーパスワード
     */
    private byte[] userPswd;
    
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
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * ユーザーＩＤを取得します。
     * @return ユーザーＩＤ
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザーＩＤを設定します。
     * @param userId ユーザーＩＤ
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * ユーザー名称（漢字）を取得します。
     * @return ユーザー名称（漢字）
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * ユーザー名称（漢字）を設定します。
     * @param userNameKj ユーザー名称（漢字）
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * ユーザー名称（カナ）を取得します。
     * @return ユーザー名称（カナ）
     */
    public String getUserNameKana() {
        return userNameKana;
    }
    /**
     * ユーザー名称（カナ）を設定します。
     * @param userNameKana ユーザー名称（カナ）
     */
    public void setUserNameKana(String userNameKana) {
        this.userNameKana = userNameKana;
    }
    
    /**
     * ユーザーパスワードを取得します。
     * @return ユーザー所属会社
     */
    public byte[] getUserPswd() {
        return userPswd;
    }
    /**
     * ユーザーパスワードを設定します。
     * @param userPswd ユーザー所属会社
     */
    public void setUserPswd(byte[] userPswd) {
        this.userPswd = userPswd;
    }
    
}
