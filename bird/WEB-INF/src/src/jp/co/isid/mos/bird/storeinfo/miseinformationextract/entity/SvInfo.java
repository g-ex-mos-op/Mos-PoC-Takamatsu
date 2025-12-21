package jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity;

/**
 * SV情報
 */
public class SvInfo {
	public static final String TABLE = "BR01USER";
    public static final String svCd_COLUMN = "SV_CD";
    public static final String svNameKj_COLUMN = "SV_NAME_KJ";
    public static final String svNameKna_COLUMN = "SV_NAME_KNA";

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
