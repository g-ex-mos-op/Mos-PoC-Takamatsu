package jp.co.isid.mos.bird.framework.entity;


/**
 * セッションエラー時遷移先情報エンティティクラス
 * @author xsong
 *
 */
public class SelectViewID {

	public static final String TABLE = "BR58RETV";
    
    public static final String returnViewId_COLUMN       = "RETURN_VIEW_ID";
    
    /**
     * 遷移先ViewID
     */
    private String returnViewId;
	
    /**
     * 遷移先ViewIDを返します。
     * @return 遷移先ViewID
     */
    public String getReturnViewId() {
		return returnViewId;
	}
    
    /**
     * 遷移先ViewIDをセットします。
     * @param returnViewId 遷移先ViewID
     */
	public void setReturnViewId(String returnViewId) {
		this.returnViewId = returnViewId;
	}
    
}
