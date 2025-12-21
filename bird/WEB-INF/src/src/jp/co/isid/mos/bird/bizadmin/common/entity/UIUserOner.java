/*
 * 作成日: 2006/02/09
 *
 */
package jp.co.isid.mos.bird.bizadmin.common.entity;

import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * @author 慮
 *
 */
public class UIUserOner extends MstUserOner {
	
	public static final String companyName_COLUMN = "COMPANY_NAME";
	
	public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
	
	public static final String checkStatus_COLUMN = "CHECK_STATUS";

	/**
	 * 管理会社企業名称
	 */
	private String companyName;
	
	/**
	 * オーナーコード名
	 */
	private String onerNameKj;

	/**
	 * オーナーコードを設定対象にするか否かの判断値
	 * 
	 * デフォルトではユーザIDが
	 * 設定されている場合は、チェック有りになります。
	 */
	private String checkStatus;

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
	/**
	 * @return companyName を戻します。
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 
	 * @param name
	 */
	public void setCompanyName(String name) {
		this.companyName = name;
	}
	/**
	 * @return クラス変数checkStatus を戻します。
	 */
	public String getCheckStatus() {
		return checkStatus;
	}
	/**
	 * @param checkStatus を クラス変数checkStatusへ設定します。
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * チェック判断処理
	 * 
	 * @return true:チェックあり
	 */
	public boolean isChecked() {
		return CommonUtil.isNull(getCheckStatus())==false;
	}
	/**
	 * チェック値設定処理
	 * 
	 * @param check true:チェックあり
	 */
	public void setChecked(boolean check) {
		setCheckStatus(check?"CHECKED":null);
	}
}
