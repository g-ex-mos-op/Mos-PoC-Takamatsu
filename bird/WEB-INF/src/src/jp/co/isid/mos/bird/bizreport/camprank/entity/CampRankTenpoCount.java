package jp.co.isid.mos.bird.bizreport.camprank.entity;

import java.math.BigDecimal;

public class CampRankTenpoCount {
    
    public static final String TABLE = "BM65CPMS";
    
    public static final String campId_COLUMN = "CAMP_ID";
    
    public static final String tenpoCount_COLUMN = "TENPO_COUNT";
    
    /**
     * キャンペーン識別番号
     */
    private String campId;
    
    /**
     * 対象店舗数
     */
    private BigDecimal tenpoCount;
    
    /**
     * キャンペーン識別番号を取得します。
     * @return キャンペーン識別番号
     */
    public String getCampId() {
        return campId;
    }
    /**
     * キャンペーン識別番号を設定します。
     * @param campId キャンペーン識別番号
     */
    public void setCampId(String campId) {
        this.campId = campId;
    }
    
    /**
     * 対象店舗数を取得します。
     * @return 対象店舗数
     */
    public BigDecimal getTenpoCount() {
        return tenpoCount;
    }
    /**
     * 対象店舗数を設定します。
     * @param tenpoCount 対象店舗数
     */
    public void setTenpoCount(BigDecimal tenpoCount) {
        this.tenpoCount = tenpoCount;
    }
    
}
