/*
 * 作成日: 2006/02/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.common.entity;

/**
 * @author 慮
 *
 */
public class UITogoOwner {
	
	public static final String TABLE = "BM11ONER";
	
	public static final String companyCd_COLUMN = "COMPANY_CD";
	
	public static final String onerCd_COLUMN = "ONER_CD";
	
	public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
	
	
	private String companyCd;
		
	private String onerCd;
	
	private String onerNameKj;

	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return onerCd を戻します。
	 */
	public String getOnerCd() {
		return onerCd;
	}
	/**
	 * @param onerCd onerCd を設定。
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
	/**
	 * @return onerNameKj を戻します。
	 */
	public String getOnerNameKj() {
		return onerNameKj;
	}
	/**
	 * @param onerNameKj onerNameKj を設定。
	 */
	public void setOnerNameKj(String onerNameKj) {
		this.onerNameKj = onerNameKj;
	}
}
