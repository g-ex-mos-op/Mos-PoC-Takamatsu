/*
 * 作成日: 2006/08/22
 */
package jp.co.isid.mos.bird.commonform.svsearch.entity;
/**
 * SV検索　SV情報
 * @author xkinu(ASPAC)
 */
public class UISv {

    public static final String TABLE = "KM32SVCD";

    public static final String companyCd_COLUMN = "COMPANY_CD";
    public static final String sibuCd_COLUMN = "SIBU_CD";
    public static final String svCd_COLUMN = "SV_CD";
    public static final String svNameKj_COLUMN = "SV_NAME_KJ";
    public static final String svNameKna_COLUMN = "SV_NAME_KNA";

    /**
     * 会社コード
     */
    private String companyCd;

    /**
     * 支部コード
     */
    private String sibuCd;

    /**
     * SVコード
     */
    private String svCd;

    /**
     * SV名称
     */
    private String svNameKj;

    /**
     * SV名称（カナ）
     */
    private String svNameKna;

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
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
 
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }


    /**
     * SVコードを取得します。
     * @return SVコード
     */
	public String getSvCd() {
		return svCd;
	}

    /**
     * SVコードを設定します。
     * @param svCd SVコード
     */
	public void setSvCd(String svCd) {
		this.svCd = svCd;
	}

    /**
     * SV名称を取得します。
     * @return SV名称
     */
	public String getSvNameKj() {
		return svNameKj;
	}

    /**
     * SV名称を設定します。
     * @param svNameKj SV名称
     */
	public void setSvNameKj(String svNameKj) {
		this.svNameKj = svNameKj;
	}

    /**
     * SV名称（カナ）を取得します。
     * @return SV名称（カナ）
     */
    public String getSvNameKna() {
        return svNameKna;
    }

    /**
     * SV名称（カナ）を設定します。
     * @param svNameKna SV名称（カナ）
     */
    public void setSvNameKna(String svNameKna) {
        this.svNameKna = svNameKna;
    }

    
}
