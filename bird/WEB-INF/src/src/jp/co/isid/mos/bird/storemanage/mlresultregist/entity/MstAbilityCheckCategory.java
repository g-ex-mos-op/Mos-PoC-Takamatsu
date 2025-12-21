package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

public class MstAbilityCheckCategory {
    
    public static final String TABLE = "BM28ABCA";
    
    public static final String categoryCd_COLUMN = "CATEGORY_CD";
    
    public static final String categoryName_COLUMN = "CATEGORY_NAME";
    
    public static final String categoryNameRyk_COLUMN = "CATEGORY_NAME_RYK";
    
    public static final String passNum_COLUMN = "PASS_NUM";
    
    /**
     * カテゴリーコード
     */
    private String categoryCd;
    
    /**
     * カテゴリー名称
     */
    private String categoryName;
    
    /**
     * カテゴリー略称
     */
    private String categoryNameRyk;
    
    /**
     * 合格条件
     */
    private int passNum;

    /**
     * カテゴリー結果
     */
    private String categoryResult;

    /**
     * カテゴリーコードを取得します。
     * @return カテゴリーコード
     */
    public String getCategoryCd() {
        return categoryCd;
    }
    /**
     * カテゴリーコードを設定します。
     * @param categoryCd カテゴリーコード
     */
    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }
    
    /**
     * カテゴリー名称を取得します。
     * @return カテゴリー名称
     */
    public String getCategoryName() {
        return categoryName;
    }
    /**
     * カテゴリー名称を設定します。
     * @param categoryName カテゴリー名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    /**
     * カテゴリー略称を取得します。
     * @return カテゴリー略称
     */
    public String getCategoryNameRyk() {
        return categoryNameRyk;
    }
    /**
     * カテゴリー略称を設定します。
     * @param categoryNameRyk カテゴリー略称
     */
    public void setCategoryNameRyk(String categoryNameRyk) {
        this.categoryNameRyk = categoryNameRyk;
    }
    
    /**
     * 合格条件を取得します。
     * @return 合格条件
     */
    public int getPassNum() {
        return passNum;
    }
    /**
     * 合格条件を設定します。
     * @param passNum 合格条件
     */
    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    /**
     * カテゴリー結果を取得します。
     * @return カテゴリー結果
     */
    public String getCategoryResult() {
        return categoryResult;
    }
    /**
     * カテゴリー結果を設定します。
     * @param categoryResult カテゴリー結果
     */
    public void setCategoryResult(String categoryResult) {
        this.categoryResult = categoryResult;
    }
}
