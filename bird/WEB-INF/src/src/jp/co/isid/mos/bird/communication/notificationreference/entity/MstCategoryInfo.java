package jp.co.isid.mos.bird.communication.notificationreference.entity;

public class MstCategoryInfo {
    
    public static final String TABLE = "BM02IFCT";
    
    public static final String cateId_COLUMN = "CATE_ID";
    public static final String cateName_COLUMN = "CATE_NAME";
    public static final String dataCnt_COLUMN = "CNT";
    
    /** カテゴリID */
    private String cateId;
    /** カテゴリ名 */
    private String cateName;
    /** カテゴリデータ件数 */
    private int dataCnt;

    
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
    
    /**
     * カテゴリデータ件数を取得します。
     * @return カテゴリデータ件数
     */
    public int getDataCnt() {
        return dataCnt;
    }
    /**
     * カテゴリデータ件数を設定します。
     * @param dataCnt カテゴリデータ件数
     */
    public void setDataCnt(int dataCnt) {
        this.dataCnt = dataCnt;
    }

}
