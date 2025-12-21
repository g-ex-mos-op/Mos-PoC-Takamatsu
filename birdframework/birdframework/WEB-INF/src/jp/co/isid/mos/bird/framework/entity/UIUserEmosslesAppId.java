/**
 * 
 */
package jp.co.isid.mos.bird.framework.entity;

/**
 * 所属区分サイト区分
 * 
 * e-mosslesでのサイト区分(APP_ID)情報を保持します。
 * 作成日:2008/11/10
 * @author xkinu
 *
 */
public class UIUserEmosslesAppId {
	public static final String TABLE = "BR73SKSK";
    
    public static final String appId_COLUMN       = "APP_ID";

    private String appId = "";

	/**
	 * @return appId を戻します。
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId を クラス変数appIdへ設定します。
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
    
}
