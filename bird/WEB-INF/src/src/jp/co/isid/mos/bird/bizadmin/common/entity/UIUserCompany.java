/*
 * 作成日: 2006/02/09
 *
 */
package jp.co.isid.mos.bird.bizadmin.common.entity;

import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * ユーザー所属会社情報
 * 
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public class UIUserCompany extends MstUserCompany {
 
	
	public static final String rCompanyName_COLUMN = "R_COMPANY_NAME";

	public static final String companyCd_COLUMN = "COMPANY_CD";

	public static final String companyName_COLUMN = "COMPANY_NAME";

	/**
     * 企業名(BC05KCOM.COMPANY_NAME or BC02COMP.COMPANY_NAME)
     */
	private String rCompanyName;
	/**
     * 企業コード(BC05KCOM.COMPANY_CD)
     */
	private String companyCd;
	/**
     * 企業名(BC05KCOM.COMPANY_NAME)
     */
	private String companyName;
	

	/**
	 * @return rCompanyName を戻します。
	 */
	public String getRCompanyName() {
		return rCompanyName;
	}
	/**
	 * @param nameを rCompanyName へ設定します。
	 */
	public void setRCompanyName(String name) {
		this.rCompanyName = name;
	}
	/**
	 * チェック判断処理
	 * 
	 * @return true:チェックあり
	 */
	public boolean isChecked() {
		return CommonUtil.isNull(getUserId())==false;
	}
	/**
	 * チェック値設定処理
	 * 
	 * @param check true:チェックあり
	 */
	public void setChecked(boolean check) {
		setUserId(check?"CHECKED":null);
	}
	/**
	 * @return クラス変数companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd を クラス変数companyCdへ設定します。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return クラス変数companyName を戻します。
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName を クラス変数companyNameへ設定します。
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
