package jp.co.isid.mos.bird.bizreport.moscardniporef.entity;

public class MstSegmentInfo {
    
    public static final String TABLE = "BM08SGTP";
    
    public static final String segmentType_COLUMN = "SEGMENT_TYPE";
    
    public static final String segmentName_COLUMN = "SEGMENT_NAME";
    
    /**
     * セグメントタイプ
     */
    private String segmentType;
    
    /**
     * セグメント名称
     */
    private String segmentName;
    
    
    /**
     * セグメントタイプを取得します。
     * @return セグメントタイプ
     */
    public String getSegmentType() {
        return segmentType;
    }
    /**
     * セグメントタイプを設定します。
     * @param segmentType セグメントタイプ
     */
    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
    }
    
    /**
     * セグメント名称を取得します。
     * @return
     */
    public String getSegmentName() {
        return segmentName;
    }
    
    /**
     * セグメント名称を設定します。
     * @param segmentName
     */
    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }
    
}
