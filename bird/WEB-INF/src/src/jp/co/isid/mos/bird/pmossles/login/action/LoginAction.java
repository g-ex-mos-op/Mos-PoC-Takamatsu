/**
 * 
 */
package jp.co.isid.mos.bird.pmossles.login.action;

/**
 * ログインアクションインターフェース
 * 
 * 作成日:2010/07/22
 * @author xkinu
 *
 */
public interface LoginAction  {
	public static final String SCREEN_ID = "PPO000";
    /** セッションＫｅｙ：初期リクエストURL */
    public static final String PK_FIRSTURL = "firstRequestURL";
    /** セッションＫｅｙ：店舗識別コード */
    public static final String PK_LOGPRM = "sikibetuCd";
    /** セッションＫｅｙ：遷移先VIEWID */
    public static final String PK_VIEWID = "viewId";
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
