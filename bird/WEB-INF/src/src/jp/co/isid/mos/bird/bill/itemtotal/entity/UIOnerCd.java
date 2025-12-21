package jp.co.isid.mos.bird.bill.itemtotal.entity;

public class UIOnerCd {
    
    public static final String TABLE = "BM06UONR";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
}
