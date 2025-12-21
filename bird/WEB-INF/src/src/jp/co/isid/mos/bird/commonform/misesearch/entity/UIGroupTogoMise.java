package jp.co.isid.mos.bird.commonform.misesearch.entity;


public class UIGroupTogoMise {

	// テーブルアノテーション
    public static final String TABLE = "BM01TENM";

    // カラムアノテーション
    public static final String companyCd_COLUMN = "COMPANY_CD";
    public static final String miseCd_COLUMN = "MISE_CD";
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    public static final String miseNameKna_COLUMN = "MISE_NAME_KNA";
    public static final String closeKj_COLUMN = "CLOSE_KJ";

    // N:1マッピング
    public static final int uiGroupTogoOner_RELNO = 0;
    public static final String uiGroupTogoOner_RELKEYS = "COMPANY_CD, ONER_CD";

    /**
     * 会社コード
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
     * グループ統合オーナマスタ
     */
    private UIGroupTogoOner uiGroupTogoOner;

    /**
     * クローズ日名称
     */
    private String closeKj;
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
     * クローズ名称を設定します。
     * @param closeKj クローズ名称
     */
    public void setCloseKj(String closeKj) {
        this.closeKj = closeKj;
    }
    /**
     * クローズ名称を取得します。
     * @return closeKj クローズ名称
     */
    public String getCloseKj() {
        return this.closeKj;
    }
    /**
     * グループ統合オーナマスタを取得します。
     * @return グループ統合オーナマスタ
     */
	public UIGroupTogoOner getUIGroupTogoOner() {
		return uiGroupTogoOner;
	}

	/**
	 * グループ統合オーナマスタを設定します。
	 * @param uiGroupTogoOner グループ統合オーナマスタ
	 */
	public void setUIGroupTogoOner(UIGroupTogoOner uiGroupTogoOner) {
		this.uiGroupTogoOner = uiGroupTogoOner;
	}
}
