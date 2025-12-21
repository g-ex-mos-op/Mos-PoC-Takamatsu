/**
 * 
 */
package jp.co.isid.mos.bird.framework.entity;

/**
 * 作成日:2008/11/19
 * @author xkinu
 *
 */
public class CtlLeaveOutMenu {
    public static final String TABLE = "BR77MNJG";
    
    public static final String viewId_COLUMN = "VIEW_ID";
    
    /**
     * 初期表示画面ID
     */
    private String viewId;

	/**
	 * @return viewId を戻します。
	 */
	public String getViewId() {
		return viewId;
	}

	/**
	 * @param viewId を クラス変数viewIdへ設定します。
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

}
