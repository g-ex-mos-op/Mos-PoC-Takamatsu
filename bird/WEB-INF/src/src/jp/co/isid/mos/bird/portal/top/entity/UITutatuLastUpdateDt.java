package jp.co.isid.mos.bird.portal.top.entity;

/**
 * 通達更新日エンティティ
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public class UITutatuLastUpdateDt {
    
    public static final String TABLE = "BT02NTCM";
    
    public static final String upDate_COLUMN = "UP_DATE";
    /**
     * 更新日
     */
    private String upDate;
	/**
	 * @return upDate を戻します。
	 */
	public String getUpDate() {
		return upDate;
	}
	/**
	 * @param upDate を クラス変数upDateへ設定します。
	 */
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}
}