/**
 * 
 */
package jp.co.isid.mos.bird.common.code;

/**
 * 設定区分定数クラス
 * 
 * 作成日:2009/12/17
 * @author xkinu
 *
 */
public class SetteiKbn {
    /* 設定区分：システム管理者 */
	public static final String KBN_SYSTEM_ADMIN = "01";
    /* 設定区分：ユーザー管理者 */
	public static final String KBN_USER_ADMIN = "02";
	
    /**
     * 外部からインスタンス化できない
     */
	private SetteiKbn () {
		super();
	}
	/**
	 * システム管理者か否か判断処理
	 * 
	 * @param kbn
	 * @return
	 */
	public static boolean isSystemAdminUser(String kbn) {
	    /* システム管理者設定区分 */
		return KBN_SYSTEM_ADMIN.equals(kbn);

	}
	/**
	 * ユーザー管理者か否か判断処理
	 * 
	 * @param kbn
	 * @return
	 */
	public static boolean isUserAdminUser(String kbn) {
	    /* ユーザー管理者設定区分 */
		return KBN_USER_ADMIN.equals(kbn);

	}

}
