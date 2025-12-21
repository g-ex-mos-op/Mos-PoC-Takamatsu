package jp.co.isid.mos.bird.bizreport.common.entity;

public class CodSegmentInfo {
    
    public static final String TABLE = "BM08SGTP";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String segmentType_COLUMN = "SEGMENT_TYPE";
    
    public static final String segmentName_COLUMN = "SEGMENT_NAME";
    
    public static final String description_COLUMN = "DESCRIPTION";
    
    public static final String sortSeq_COLUMN = "SORT_SEQ";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * セグメントタイプ
     */
    private String segmentType;
    
    /**
     * セグメント名称
     */
    private String segmentName;
    
    /**
     * 説明
     */
    private String description;
    
    /**
     * ソート順
     */
    private String sortSeq;
    
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
     * @return セグメント名称
     */
    public String getSegmentName() {
        return segmentName;
    }
    /**
     * セグメント名称を設定します。
     * @param segmentName セグメント名称
     */
    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }
    
    /**
     * 説明を取得します。
     * @return 説明
     */
    public String getDescription() {
        return description;
    }
    /**
     * 説明を設定します。
     * @param description 説明
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * ソート順を取得します。
     * @return ソート順
     */
    public String getSortSeq() {
        return sortSeq;
    }
    /**
     * ソート順を設定します。
     * @param sortSeq ソート順
     */
    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
    }
    
}
