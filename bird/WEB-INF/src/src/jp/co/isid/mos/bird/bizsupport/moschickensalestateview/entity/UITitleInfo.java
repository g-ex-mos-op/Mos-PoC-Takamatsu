package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity;

public class UITitleInfo {
    
    public static final String TABLE = "BM39CPRD";
    
    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String title_COLUMN = "TITLE";
    
    public static final String targetFrom_COLUMN = "TARGET_FROM";
    
    public static final String targetTo_COLUMN = "TARGET_TO";
    
    public static final String defaultFrom_COLUMN = "DEFAULT_FROM";
    
    public static final String defaultTo_COLUMN = "DEFAULT_TO";
    
    /**
     * 管理番号
     */
    private String ckanriNo;
    
    /**
     * タイトル
     */
    private String title;
    
    /**
     * 対象期間FROM
     */
    private String targetFrom;
    
    /**
     * 対象期間TO
     */
    private String targetTo;
    
    /**
     * デフォルト表示期間FROM
     */
    private String defaultFrom;
    
    /**
     * デフォルト表示期間TO
     */
    private String defaultTo;
    
    /**
     * 管理番号を取得します。
     * @return 管理番号
     */
    public String getCkanriNo() {
        return ckanriNo;
    }
    /**
     * 管理番号を設定します。
     * @param ckanriNo 管理番号
     */
    public void setCkanriNo(String ckanriNo) {
        this.ckanriNo = ckanriNo;
    }
    
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }
    /**
     * タイトルを設定します。
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * 対象期間FROMを取得します。
     * @return 対象期間FROM
     */
    public String getTargetFrom() {
        return targetFrom;
    }
    /**
     * 対象期間FROMを設定します。
     * @param targetFrom 対象期間FROM
     */
    public void setTargetFrom(String targetFrom) {
        this.targetFrom = targetFrom;
    }
    
    /**
     * 対象期間TOを取得します。
     * @return 対象期間TO
     */
    public String getTargetTo() {
        return targetTo;
    }
    /**
     * 対象期間TOを設定します。
     * @param targetTo 対象期間TO
     */
    public void setTargetTo(String targetTo) {
        this.targetTo = targetTo;
    }
    
    /**
     * デフォルト表示期間FROMを取得します。
     * @return デフォルト表示期間FROM
     */
    public String getDefaultFrom() {
        return defaultFrom;
    }
    /**
     * デフォルト表示期間FROMを設定します。
     * @param defaultFrom デフォルト表示期間FROM
     */
    public void setDefaultFrom(String defaultFrom) {
        this.defaultFrom = defaultFrom;
    }
    
    /**
     * デフォルト表示期間TOを取得します。
     * @return デフォルト表示期間TO
     */
    public String getDefaultTo() {
        return defaultTo;
    }
    /**
     * デフォルト表示期間TOを設定します。
     * @param defaultTo デフォルト表示期間TO
     */
    public void setDefaultTo(String defaultTo) {
        this.defaultTo = defaultTo;
    }
    
}
