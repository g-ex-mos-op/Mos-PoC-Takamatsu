package jp.co.isid.mos.bird.bizreport.common.entity;

/**
 * 会計区分情報エンティティクラス
 * 
 * @author mwatanabe
 */
public class MstAccountKbnInfo {
    /** テーブル名称 */    
    public static final String TABLE = "PM25KKBN";
    /** カラム名称：会計区分コード */    
    public static final String kkbnCd_COLUMN = "KKBN_CD";
    /** カラム名称：会計区分名称 */    
    public static final String kkbnName_COLUMN = "KKBN_NAME";

    /**
     * 会計区分コード
     */
    private String kkbnCd;
    
    /**
     * 会計区分名称
     */
    private String kkbnName;
    
    /**
     * 会計区分コードを取得します。
     * @return 会計区分コード
     */
    public String getKkbnCd() {
        return kkbnCd;
    }
    /**
     * 会計区分コードを設定します。
     * @param kkbnCd 会計区分コード
     */
    public void setKkbnCd(String kkbnCd) {
        this.kkbnCd = kkbnCd;
    }
    
    /**
     * 会計区分名称を取得します。
     * @return 会計区分名称
     */
    public String getKkbnName() {
        return kkbnName;
    }
    /**
     * 会計区分名称を設定します。
     * @param kkbnName 会計区分名称
     */
    public void setKkbnName(String kkbnName) {
        this.kkbnName = kkbnName;
    }    
}