package jp.co.isid.mos.bird.entry.longserviceoffer.entity;

public class UIOfferMise {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String miseFirst_COLUMN = "MISE_FIRST";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * １号店コード
     */
    private String miseFirst;
    
    /**
     * 支部名
     */
    private String sibuName;
    
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
     * １号店コードを取得します。
     * @return １号店コード
     */
    public String getMiseFirst() {
        return miseFirst;
    }
    /**
     * １号店コードを設定します。
     * @param miseFirst １号店コード
     */
    public void setMiseFirst(String miseFirst) {
        this.miseFirst = miseFirst;
    }
    
    /**
     * 支部名を取得します。
     * @return 支部名
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名を設定します。
     * @param sibuName 支部名
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
}
