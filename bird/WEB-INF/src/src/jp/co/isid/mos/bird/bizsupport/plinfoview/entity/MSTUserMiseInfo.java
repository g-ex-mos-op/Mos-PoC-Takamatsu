package jp.co.isid.mos.bird.bizsupport.plinfoview.entity;

public class MSTUserMiseInfo {
    
    public static final String TABLE = "BM06UONR";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";

    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;

    /**
     * 画面表示ラベル
     */
    private String label;

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
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
	/**
	 * 画面表示ラベルの設定
	 * @return label を戻します。
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * 画面表示ラベルの設定
	 * @param label label を設定。
	 */
	public void setLabel(String label) {
		this.label = label;
	}
}
