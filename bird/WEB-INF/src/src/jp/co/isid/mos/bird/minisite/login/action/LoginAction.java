/**
 *
 */
package jp.co.isid.mos.bird.minisite.login.action;

/**
 * ログインアクションインターフェース
 *
 * 作成日:2017/08/05
 * @author zcj
 *
 */
public interface LoginAction  {
	public static final String SCREEN_ID = "MNI000";
    /** セッションＫｅｙ：初期リクエストURL */
    public static final String PK_FIRSTURL = "firstRequestURL";
    /** セッションＫｅｙ：キー値 */
    public static final String PK_KEY = "key";

	/**
	 * 初期化処理
	 * @return
	 */
	public String initialize();

	/**
	 * ログイン処理
	 * @return
	 */
    public String login();

}
