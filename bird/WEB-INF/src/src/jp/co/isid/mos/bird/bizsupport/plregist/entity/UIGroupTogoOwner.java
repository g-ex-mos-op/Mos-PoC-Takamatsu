/*
 * 作成日: 2006/3/29
 */
package jp.co.isid.mos.bird.bizsupport.plregist.entity;
/**
 * オーナ検索　オーナ情報
 * @author itamoto
 */
public class UIGroupTogoOwner {

    public static final String TABLE = "BM11ONRM";

    public static final String onerCd_COLUMN = "ONER_CD";
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    public static final String onerNameKna_COLUMN = "ONER_NAME_KNA";
    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final int uIGroupTogoMise_RELNO = 0;
    public static final String uIGroupTogoMise_RELKEYS = "KAISYA_CD, ONER_CD";

    /**
     * オーナコード
     */
    private String onerCd;

    /**
     * 会社名称
     */
    private String onerNameKj;

    /**
     * 会社名称（カナ）
     */
    private String onerNameKna;

    /**
     * 会社コード
     */
    private String companyCd;

    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
		return companyCd;
	}
 
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

    /**
     * オーナコードを取得します。
     * @return オーナコード
     */
	public String getOnerCd() {
		return onerCd;
	}

    /**
     * オーナコードを設定します。
     * @param onerCd オーナコード
     */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}

    /**
     * オーナ名称を取得します。
     * @return オーナ名称
     */
	public String getOnerNameKj() {
		return onerNameKj;
	}

    /**
     * オーナ名称を設定します。
     * @param onerNameKj オーナ名称
     */
	public void setOnerNameKj(String onerNameKj) {
		this.onerNameKj = onerNameKj;
	}

    /**
     * オーナ名称（カナ）を取得します。
     * @return オーナ名称（カナ）
     */
    public String getOnerNameKna() {
        return onerNameKna;
    }

    /**
     * オーナ名称（カナ）を設定します。
     * @param onerNameKna オーナ名称（カナ）
     */
    public void setOnerNameKna(String onerNameKna) {
        this.onerNameKna = onerNameKna;
    }
}
