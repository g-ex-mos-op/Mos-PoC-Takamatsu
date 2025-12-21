/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity;

/**
 * 売掛先名称(UIUrikakesakiName)
 * 
 * 作成日:2009/06/26
 * @author xkinu
 *
 */
public class UIUrikakesakiName {
    public static final String TABLE = "TM11ONER";
    
    public static final String onerCd_COLUMN = "ONER_CD";

    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";

    /**
     * 売掛先コード
     */
    private String onerCd;
    
    /**
     * 売掛先名称
     */
    private String onerNameKj;

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
	 * @return onerNameCd を戻します。
	 */
	public String getOnerNameKj() {
		return onerNameKj;
	}

	/**
	 * @param name を クラス変数onerNameCdへ設定します。
	 */
	public void setOnerNameKj(String name) {
		this.onerNameKj = name;
	}
}
