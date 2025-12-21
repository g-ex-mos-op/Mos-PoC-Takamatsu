package jp.co.isid.mos.bird.entry.nationalentry.entity;

public class UINatiEntrySyokuiInfo {
    
    public static final String TABLE = "BC37JBTP";
    
    public static final String syokuiCd_COLUMN = "SYOKUI_CD";
    
    public static final String syokuiName_COLUMN = "SYOKUI_NAME";
    
    public static final String sortSeq_COLUMN = "SORT_SEQ";
    
    /**
     * 職位コード
     */
    private String syokuiCd;
    
    /**
     * 職位名称
     */
    private String syokuiName;
    
    /**
     * ソート順
     */
    private String sortSeq;
 
    /**
     * 職位コードを取得します。
     * @return 職位コード
     */
    public String getSyokuiCd() {
        return syokuiCd == null ? syokuiCd : syokuiCd.trim();
    }
    /**
     * 職位コードを設定します。
     * @param syokuiCd 職位コード
     */
    public void setSyokuiCd(String syokuiCd) {
        this.syokuiCd = syokuiCd;
    }
    
    /**
     * 職位名称を取得します。
     * @return 職位名称
     */
    public String getSyokuiName() {
        return syokuiName == null ? syokuiName : syokuiName.trim();
    }
    /**
     * 職位名称を設定します。
     * @param syokuiName 職位名称
     */
    public void setSyokuiName(String syokuiName) {
        this.syokuiName = syokuiName;
    }
    
    /**
     * ソート順称を取得します。
     * @return ソート順
     */
    public String getSortSeq() {
        return sortSeq == null ? sortSeq : sortSeq.trim();
    }
    /**
     * ソート順を設定します。
     * @param sortSeq ソート順
     */
    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
    }
}
