/**
 * 
 */
package jp.co.isid.mos.bird.sysadmin.help.entity;

/**
 * ヘルプ変更履歴エンティティ
 * 
 * IEキャッシュ対応用
 * 
 * 作成日:2012/03/28
 * @author xkinu
 *
 */
public class HelpVersion {
	public static final String TABLE = "BR83VERS";
	public static final String TYPE_CD = "01";
    
    public static final String version_COLUMN       = "VERSION";
	private String version;

	/**
	 * @return クラス変数version を戻します。
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version を クラス変数versionへ設定します。
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
