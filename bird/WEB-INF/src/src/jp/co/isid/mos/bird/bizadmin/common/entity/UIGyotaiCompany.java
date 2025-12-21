/*
 * 作成日: 2006/02/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.common.entity;

/**
 * @author 慮
 *
 */

//業態運営会社
public class UIGyotaiCompany {
	
	public static final String TABLE = "BM04GTCP";
	
	public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
	
	public static final String companyCd_COLUMN = "COMPANY_CD";
	
	/**
	 *  業態区分
	 */
	private String gyotaiKbn;
	
	/**
	 *  管理会社企業コード
	 */
	private String companyCd;
	
	/**
	 * @return 管理会社企業コード を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param 管理会社企業コード companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * @return 業態区分 を戻します。
	 */
	public String getGyotaiKbn() {
		return gyotaiKbn;
	}
	/**
	 * @param 業態区分 を設定。
	 */
	public void setGyotaiKbn(String gyotaiKbn) {
		this.gyotaiKbn = gyotaiKbn;
	}
}
