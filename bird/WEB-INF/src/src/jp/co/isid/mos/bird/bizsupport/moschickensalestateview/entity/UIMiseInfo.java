package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity;

public class UIMiseInfo {

    public static final String TABLE = "BM01TENM";

    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String miseCd_COLUMN = "MISE_CD";

    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";

    public static final String closeDate_COLUMN = "CLOSE_DT";

    /**
     * 管理会社企業コード
     */
    private String companyCd;

    /**
     * 店コード
     */
    private String miseCd;

    /**
     * 店名称
     */
    private String miseNameKj;

    /**
     * 締切期日
     */
    private String closeDate;

    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    /**
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }

    /**
     * 締切期日を取得します。
     * @return 締切期日
     */
	public String getCloseDate() {
		return closeDate;
	}
	/**
     * 締切期日を設定します。
     * @param miseNameKj 締切期日
     */
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
}
