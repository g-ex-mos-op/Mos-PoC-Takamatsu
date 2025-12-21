package jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity;


public class MstStoreInfo {
    
    public static final String TABLE = "";
    
    
    public static final String miseCd_COLUMN = "MISE_CD";
    public static final String miseName_COLUMN = "MISE_NAME";
    
    
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    
    /**
     * 店舗名称
     */
    private String miseName;

    
    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    /**
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseName() {
        return miseName;
    }
    /**
     * 店舗名称を設定します。
     * @param miseName 店舗名称
     */
    public void setMiseName(String miseName) {
        this.miseName = miseName;
    }

    
}
