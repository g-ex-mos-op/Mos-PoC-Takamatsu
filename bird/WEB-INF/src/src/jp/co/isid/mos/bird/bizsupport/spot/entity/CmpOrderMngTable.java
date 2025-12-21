package jp.co.isid.mos.bird.bizsupport.spot.entity;


/**
 * 対象キャンペーン情報エンティティ
 * @author xsong
 *
 */
public class CmpOrderMngTable {
    
    public static final String TABLE = "BM53CPJK";
    
    public static final String cmpNo_COLUMN = "CMP_NO";
    
    public static final String cmpName_COLUMN = "CMP_NAME";
    
    /**
     * キャンペーンNO
     */
    private String cmpNo;
    
    /**
     * キャンペーン名称
     */
    private String cmpName;
    
    /**
     * キャンペーンNOを取得します。
     * @return キャンペーンNO
     */
    public String getCmpNo() {
        return cmpNo;
    }
    
    /**
     * キャンペーンNOを設定します。
     * @param cmpNo キャンペーンNO
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
