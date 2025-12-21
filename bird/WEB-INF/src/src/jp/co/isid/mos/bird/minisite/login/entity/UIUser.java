package jp.co.isid.mos.bird.minisite.login.entity;

/**
 * ユーザーエンティティ
 *
 * 作成日:2017/08/05
 * @author zcj
 *
 */
public class UIUser {

    public static final String TABLE = "br01user";

    public static final String userId_COLUMN = "USER_ID";

    public static final String userPswd_COLUMN = "USER_PSWD";

    /**
     * ユーザーＩＤ
     */
    private String userId;

    /**
     * ユーザーパスワード
     */
    private byte[] userPswd;

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
