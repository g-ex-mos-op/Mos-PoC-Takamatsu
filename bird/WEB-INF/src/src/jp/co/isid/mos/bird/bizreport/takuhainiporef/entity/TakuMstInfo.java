package jp.co.isid.mos.bird.bizreport.takuhainiporef.entity;

/**
 * 宅配種別情報エンティティクラス
 * 
 * @author xjung
 */
public class TakuMstInfo {
    /** テーブル名称 */
    public static final String TABLE = "BM47TDMS";
    /** カラム名称：宅配コード */
    public static final String takuCd_COLUMN = "TAKU_CD";
    /** カラム名称：宅配名 */
    public static final String takuName_COLUMN = "TAKU_NAME";
    /** カラム名称：宅配明細コード */
    public static final String takuDetailCd_COLUMN = "TAKU_DETAIL_CD";
    /** カラム名称：店舗数 */
    public static final String takuDetailName_COLUMN = "TAKU_DETAIL_NAME";
    
    /**
     * 宅配コード
     */
    private String takuCd;
    
    /**
     * 宅配名称
     */
    private String takuName;
    
    /**
     * 宅配明細コード
     */
    private String takuDetailCd;
    
    /**
     * 宅配明細名称
     */
    private String takuDetailName;
    
    /**
     * 宅配コードを取得します。
     * @return 宅配コード
     */
    public String getTakuCd() {
        return takuCd;
    }
    /**
     * 宅配コードを設定します。
     * @param takuCd 宅配コード
     */
    public void setTakuCd(String takuCd) {
        this.takuCd = takuCd;
    }
    
    /**
     * 宅配名称を取得します。
     * @return 宅配名称
     */
    public String getTakuName() {
        return takuName;
    }
    /**
     * 宅配名称を設定します。
     * @param takuName 宅配名称
     */
    public void setTakuName(String takuName) {
        this.takuName = takuName;
    }
    
    /**
     * 宅配明細コードを取得します。
     * @return 宅配明細コード
     */
    public String getTakuDetailCd() {
        return takuDetailCd;
    }
    /**
     * 宅配明細コードを設定します。
     * @param takuDetailCd 宅配明細コード
     */
    public void setTakuDetailCd(String takuDetailCd) {
        this.takuDetailCd = takuDetailCd;
    }
    
    /**
     * 宅配明細名称を取得します。
     * @return 宅配明細名称
     */
    public String getTakuDetailName() {
        return takuDetailName;
    }
    /**
     * 宅配明細名称を設定します。
     * @param takuDetailName 宅配明細名称
     */
    public void setTakuDetailName(String takuDetailName) {
        this.takuDetailName = takuDetailName;
    }
    
}
