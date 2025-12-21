package jp.co.isid.mos.bird.bizsupport.campcheckamount.entity;

public class UICampaignInfo {
    
    public static final String TABLE = "BM53CPJK";
    
    public static final String cmpNo_COLUMN = "CMP_NO";
    
    public static final String cmpName_COLUMN = "CMP_NAME";
    
    /**
     * キャンペーンＮＯ
     */
    private String cmpNo;
    
    /**
     * キャンペーン名称
     */
    private String cmpName;
    
    /**
     * キャンペーンＮＯを取得します。
     * @return キャンペーンＮＯ
     */
    public String getCmpNo() {
        return cmpNo;
    }
    /**
     * キャンペーンＮＯを設定します。
     * @param cmpNo キャンペーンＮＯ
     */
    public void setCmpNo(String cmpNo) {
        this.cmpNo = cmpNo;
    }
    
    /**
     * キャンペーン名称を取得します。
     * @return キャンペーン名称
     */
    public String getCmpName() {
        return cmpName;
    }
    /**
     * キャンペーン名称を設定します。
     * @param cmpName キャンペーン名称
     */
    public void setCmpName(String cmpName) {
        this.cmpName = cmpName;
    }
    
}
