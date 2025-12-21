/*
 * 作成日: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.misesearch.entity;

/**
 * @author xyuchida
 *
 */
public class UIGroupTogoOner {

	// テーブルアノテーション
    public static final String TABLE = "BM11ONER";

    // カラムアノテーション
    public static final String companyCd_COLUMN = "COMPANY_CD";
    public static final String onerCd_COLUMN = "ONER_CD";
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";

    /**
     * 会社コード
     */
    private String companyCd;

    /**
     * オーナーコード
     */
    private String onerCd;

    /**
     * オーナー名称
     */
    private String onerNameKj;

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
     * オーナーコードを取得します。
     * @return オーナーコード
     */
	public String getOnerCd() {
		return onerCd;
	}

    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}

	/**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
	public String getOnerNameKj() {
		return onerNameKj;
	}

    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
	public void setOnerNameKj(String onerNameKj) {
		this.onerNameKj = onerNameKj;
	}
}
