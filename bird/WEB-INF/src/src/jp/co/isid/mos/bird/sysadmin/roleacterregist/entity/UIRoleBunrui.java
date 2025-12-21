package jp.co.isid.mos.bird.sysadmin.roleacterregist.entity;

public class UIRoleBunrui {
    
    public static final String TABLE = "BC03RLBR";
    
    public static final String bunruiCd_COLUMN = "BUNRUI_CD";
    
    public static final String bunruiName_COLUMN = "BUNRUI_NAME";
    
    public static final String sortSeq_COLUMN = "SORT_SEQ";
    
    /**
     * 分類コード
     */
    private String bunruiCd;
    
    /**
     * 分類名称
     */
    private String bunruiName;
    
    /**
     * ソート順
     */
    private String sortSeq;
    
    /**
     * 分類コードを取得します。
     * @return 分類コード
     */
    public String getBunruiCd() {
        return bunruiCd;
    }
    /**
     * 分類コードを設定します。
     * @param bunruiCd 分類コード
     */
    public void setBunruiCd(String bunruiCd) {
        this.bunruiCd = bunruiCd;
    }
    
    /**
     * 分類名称を取得します。
     * @return 分類名称
     */
    public String getBunruiName() {
        return bunruiName;
    }
    /**
     * 分類名称を設定します。
     * @param bunruiName 分類名称
     */
    public void setBunruiName(String bunruiName) {
        this.bunruiName = bunruiName;
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
