package jp.co.isid.mos.bird.bizsupport.plregist.entity;

public class MSTUserMiseInfo {
    
    public static final String TABLE = "BM06UONR";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";

    public static final String openDt_COLUMN = "OPEN_DT";

    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String closeFlg_COLUMN = "CLOSE_FLG";

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
     * オープン日付
     */
    private String openDt;

    /**
     * クローズ日付
     */
    private String closeDt;

    /**
     * クローズフラグ
     */
    private String closeFlg;
    
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
        
        if(this.closeFlg == null) {
            this.label = getMiseNameKj();
        }
        else {
            if(this.closeFlg.equals("1")) { 
                label = getMiseNameKj() + " (close)";
            } else { 
                label = getMiseNameKj();
            }
        }
        return label;
	}
	/**
	 * 画面表示ラベルの設定
	 * @param label label を設定。
	 */
	public void setLabel(String label) {
        this.label = label;
	}

	/**
	 * クローズ日付
	 * @return closeDt を戻します。
	 */
	public String getCloseDt() {
		return closeDt;
	}
	/**
	 * クローズ日付
	 * @param closeDt closeDt を設定。
	 */
	public void setCloseDt(String closeDt) {
		this.closeDt = closeDt;
	}
	/**
	 * オープン日付の設定
	 * @return openDt を戻します。
	 */
	public String getOpenDt() {
		return openDt;
	}
	/**
	 * オープン日付の設定
	 * @param openDt openDt を設定。
	 */
	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}
    
    /**
     * クローズフラグの設定
     * @return closeFlg を戻します。
     */
    public String getCloseFlg() {
        return closeFlg;
    }
    /**
     * クローズフラグの設定
     * @param closeFlg closeFlg を設定。
     */
    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg;
    }
    
}
