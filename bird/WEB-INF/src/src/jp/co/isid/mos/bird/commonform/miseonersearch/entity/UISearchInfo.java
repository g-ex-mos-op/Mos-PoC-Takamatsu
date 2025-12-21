/**
 * 
 */
package jp.co.isid.mos.bird.commonform.miseonersearch.entity;

import jp.co.isid.mos.bird.commonform.miseonersearch.util.MiseOnerSearchUtil;

/**
 * 店・オーナー検索情報
 * 
 * 作成日:2008/11/17
 * @author xkinu
 *
 */
public class UISearchInfo  {
    public static final String bunruiKbn_COLUMN = "BUNRUI_KBN";
	
    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String companyName_COLUMN = "COMPANY_NAME";

    public static final String miseCd_COLUMN = "MISE_CD";

    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";

    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    /**
     * 分類
     */
    private String bunruiKbn;
    
    /**
     * 会社コード
     */
    private String companyCd;
    /**
     * 会社コード
     */
    private String companyName;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;

	/**
	 * @return miseCd を戻します。
	 */
	public String getMbunruiNameKj() {
		return miseCd;
	}

	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}

	/**
	 * @param miseCd を クラス変数miseCdへ設定します。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}

	/**
	 * @return miseNameKj を戻します。
	 */
	public String getMiseNameKj() {
		return miseNameKj;
	}

	/**
	 * @param miseNameKj を クラス変数miseNameKjへ設定します。
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}

	/**
	 * @return onerCd を戻します。
	 */
	public String getOnerCd() {
		return onerCd;
	}

	/**
	 * @param onerCd を クラス変数onerCdへ設定します。
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
	/**
	 * @return bunruiKbn を戻します。
	 */
	public String getBunruiKbn() {
		return bunruiKbn;
	}

	/**
	 * @param bunruiKbn を クラス変数bunruiKbnへ設定します。
	 */
	public void setBunruiKbn(String bunruiKbn) {
		this.bunruiKbn = bunruiKbn;
	}

	/**
	 * @return bunruiNameKj を戻します。
	 */
	public String getBunruiName() {
		return MiseOnerSearchUtil.getBunruiName(getBunruiKbn());
	}

	/**
	 * @return companyCd を戻します。
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
	 * @return onerNameKj を戻します。
	 */
	public String getOnerNameKj() {
		return onerNameKj;
	}

	/**
	 * @param onerNameKj を クラス変数onerNameKjへ設定します。
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
	 * @param companyName を クラス変数companyNameへ設定します。
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
