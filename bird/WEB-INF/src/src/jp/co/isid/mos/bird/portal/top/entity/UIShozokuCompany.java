/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.entity;

/**
 * 作成日:2009/01/07
 * @author xkinu
 *
 */
public class UIShozokuCompany {
    public static final String TABLE = "BM03USCP";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String zokuseiKbn_COLUMN = "ZOKUSEI_KBN";
    
    /**
     * 企業コード
     */
    private String companyCd;
    /**
     * 企業名称
     */
    private String companyName;
    /**
     * 属性区分
     */
    private String zokuseiKbn;
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
	/**
	 * @return zokuseiKbn を戻します。
	 */
	public String getZokuseiKbn() {
		return zokuseiKbn;
	}
	/**
	 * @param zokuseiKbn を クラス変数zokuseiKbnへ設定します。
	 */
	public void setZokuseiKbn(String zokuseiKbn) {
		this.zokuseiKbn = zokuseiKbn;
	}
    
}
