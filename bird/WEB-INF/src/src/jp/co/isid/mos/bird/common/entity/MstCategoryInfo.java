package jp.co.isid.mos.bird.common.entity;

public class MstCategoryInfo {
    
    public static final String TABLE = "BM02IFCT";
    
    public static final String infoShu_COLUMN = "INFO_SHU";
    
    public static final String cateId_COLUMN = "CATE_ID";
    
    public static final String cateName_COLUMN = "CATE_NAME";
    
    public static final String sortKey_COLUMN = "SORT_KEY";
    
    /**
     * 情報種別
     */
    private String infoShu;
    
    /**
     * カテゴリID
     */
    private String cateId;
    
    /**
     * カテゴリ名
     */
    private String cateName;
    
    /**
     * ソートキー
     */
    private String sortKey;
    
    /**
     * 情報種別を取得します。
     * @return 情報種別
     */
    public String getInfoShu() {
        return infoShu;
    }
    /**
     * 情報種別を設定します。
     * @param infoShu 情報種別
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }
    
    /**
     * カテゴリIDを取得します。
     * @return カテゴリID
     */
    public String getCateId() {
        return cateId;
    }
    /**
     * カテゴリIDを設定します。
     * @param cateId カテゴリID
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
    
    /**
     * カテゴリ名を取得します。
     * @return カテゴリ名
     */
    public String getCateName() {
        return cateName;
    }
    /**
     * カテゴリ名を設定します。
     * @param cateName カテゴリ名
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    public String getSortKey() {
        return sortKey;
    }
    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }
}
