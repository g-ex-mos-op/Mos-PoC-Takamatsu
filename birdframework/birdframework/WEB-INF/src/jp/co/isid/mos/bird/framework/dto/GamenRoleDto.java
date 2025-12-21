package jp.co.isid.mos.bird.framework.dto;


/**
 * 画面ロールDTO
 * @author xnlee
 */
public class GamenRoleDto {

    /* ユーザーID */
    private String _userId;
    /* 画面ID */
    private String _gamenId;
    /* 分類コード */
    private String _bunruiCd;
    
	/**
	 * @return _bunruiCd を戻します。
	 */
	public String getBunruiCd() {
		return _bunruiCd;
	}
	/**
	 * @param bunruiCd _bunruiCd を設定。
	 */
	public void setBunruiCd(String bunruiCd) {
		_bunruiCd = bunruiCd;
	}
	/**
	 * @return _gamenId を戻します。
	 */
	public String getGamenId() {
		return _gamenId;
	}
	/**
	 * @param gamenId _gamenId を設定。
	 */
	public void setGamenId(String gamenId) {
		_gamenId = gamenId;
	}
	/**
	 * @return _userId を戻します。
	 */
	public String getUserId() {
		return _userId;
	}
	/**
	 * @param userId _userId を設定。
	 */
	public void setUserId(String userId) {
		_userId = userId;
	}
}
