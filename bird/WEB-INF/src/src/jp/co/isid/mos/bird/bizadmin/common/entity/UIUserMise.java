/*
 * 作成日: 2006/02/09
 *
 */
package jp.co.isid.mos.bird.bizadmin.common.entity;


/**
 * ユーザー対応店舗(BM07UTEN)エンティティ
 * 
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public class UIUserMise extends MstUserMise {

	public static final String companyName_COLUMN = "COMPANY_NAME";
	
	public static final String miseNameKj_COLUMN = "MISE_NAME_KJ"; 
	
	/**
	 * 管理会社企業名称
	 */
	private String companyName;
	
	/**
	 * 店コード名
	 */
	private String miseNameKj;
	
	/**
	 * @return miseNameKj を戻します。
	 */
	public String getMiseNameKj() {
		return miseNameKj;
	}
	/**
	 * @param miseNameKj miseNameKj を設定。
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
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
