package jp.co.isid.mos.bird.portal.login.action;

/**
 * ログイン画面 アクションインターフェイス
 * @author xnkusama
 */
public interface LoginAction {
	public String login();
    public String redirect();
    public String loginInit();
	/**
	 * アクション【外部リンク呼出】
	 * ポップアップブロック対応 ADD 2013/04/22
	 * @return 呼出し画面VIEW_ID
	 */
	public String callSite();
}
