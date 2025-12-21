package jp.co.isid.mos.bird.storeinfo.onerref.entity;

import java.math.BigDecimal;

public class MstKyoei {
    
    public static final String TABLE = "KM14KYOY";
    
    public static final String kisuu_COLUMN = "KISUU";
    
    public static final String postName_COLUMN = "POST_NAME";
    
    public static final String kikanFrom_COLUMN = "KIKAN_FROM";
    
    public static final String kikanTo_COLUMN = "KIKAN_TO";
    
    /**
     * 期数
     */
    private BigDecimal kisuu;
    
    /**
     * 役職名
     */
    private String postName;
    
    /**
     * 期間：から
     */
    private String kikanFrom;
    
    /**
     * 期間：まで
     */
    private String kikanTo;
    
    /**
     * 期数を取得します。
     * @return 期数
     */
    public BigDecimal getKisuu() {
        return kisuu;
    }
    /**
     * 期数を設定します。
     * @param kisuu 期数
     */
    public void setKisuu(BigDecimal kisuu) {
        this.kisuu = kisuu;
    }
    
    /**
     * 役職名を取得します。
     * @return 役職名
     */
    public String getPostName() {
        return postName;
    }
    /**
     * 役職名を設定します。
     * @param postName 役職名
     */
    public void setPostName(String postName) {
        this.postName = postName;
    }
    
    /**
     * 期間：からを取得します。
     * @return 期間：から
     */
    public String getKikanFrom() {
        return kikanFrom;
    }
    /**
     * 期間：からを設定します。
     * @param kikanFrom 期間：から
     */
    public void setKikanFrom(String kikanFrom) {
        this.kikanFrom = kikanFrom;
    }
    
    /**
     * 期間：までを取得します。
     * @return 期間：まで
     */
    public String getKikanTo() {
        return kikanTo;
    }
    /**
     * 期間：までを設定します。
     * @param kikanTo 期間：まで
     */
    public void setKikanTo(String kikanTo) {
        this.kikanTo = kikanTo;
    }
    
}
