package jp.co.isid.mos.bird.bill.itemtotal.entity;

import java.math.BigDecimal;

public class UIUriageDakaInfo {
    
    public static final String TABLE = "BT64ZGEP";
    
    public static final String uriageDogetu_COLUMN = "URIAGE_DOGETU";
    
    /**
     * 前年同月対象当年売上高(当月売上)
     */
    private BigDecimal uriageDogetu;
    
    /**
     * 前年同月対象当年売上高(当月売上)を取得します。
     * @return 前年同月対象当年売上高(当月売上)
     */
    public BigDecimal getUriageDogetu() {
        return uriageDogetu;
    }
    /**
     * 前年同月対象当年売上高(当月売上)を設定します。
     * @param uriageDogetu 前年同月対象当年売上高(当月売上)
     */
    public void setUriageDogetu(BigDecimal uriageDogetu) {
        this.uriageDogetu = uriageDogetu;
    }
    
}
