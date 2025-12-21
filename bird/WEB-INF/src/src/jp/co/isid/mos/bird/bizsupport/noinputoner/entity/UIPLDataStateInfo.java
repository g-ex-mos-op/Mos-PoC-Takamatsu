package jp.co.isid.mos.bird.bizsupport.noinputoner.entity;

public class UIPLDataStateInfo {
    
    public static final String TABLE = "BT17PLDT";
    
    public static final String plType_COLUMN = "PL_TYPE";
    
    public static final String plYm_COLUMN = "PL_YM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String errFlg_COLUMN = "ERR_FLG";
    
    /**
     * PLの種類
     */
    private String plType;
    
    /**
     * 年月
     */
    private String plYm;
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * エラーフラグ
     */
    private String errFlg;
    
    /**
     * PLの種類を取得します。
     * @return PLの種類
     */
    public String getPlType() {
        return plType;
    }
    /**
     * PLの種類を設定します。
     * @param plType PLの種類
     */
    public void setPlType(String plType) {
        this.plType = plType;
    }
    
    /**
     * 年月を取得します。
     * @return 年月
     */
    public String getPlYm() {
        return plYm;
    }
    /**
     * 年月を設定します。
     * @param plYm 年月
     */
    public void setPlYm(String plYm) {
        this.plYm = plYm;
    }
    
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
    
	public String getErrFlg() {
		return errFlg;
	}
	public void setErrFlg(String errFlg) {
		this.errFlg = errFlg;
	}
}
