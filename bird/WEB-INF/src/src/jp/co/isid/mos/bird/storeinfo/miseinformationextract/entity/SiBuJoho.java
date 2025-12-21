package jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity;

public class SiBuJoho {
    public static final String TABLE = "BM10GSIB";
    public static final String sibuCd_COLUMN = "SIBU_CD";
    public static final String sibuName_COLUMN = "SIBU_NAME";
    /**
     * 支部コード
     */
    private String sibuCd;

    /**
     * 支部名称
     */
    private String sibuName;

    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
	public String getSibuCd() {
        return convString(sibuCd);
	}

    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
	public void setSibuCd(String sibuCd) {
		this.sibuCd = sibuCd;
	}

    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
	public String getSibuName() {
        return convString(sibuName);
	}

    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
	public void setSibuName(String sibuName) {
		this.sibuName = sibuName;
	}

    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
}
