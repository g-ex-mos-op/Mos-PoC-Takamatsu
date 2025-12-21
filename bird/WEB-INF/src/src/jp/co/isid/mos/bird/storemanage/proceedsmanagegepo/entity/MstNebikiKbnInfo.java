package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity;


/**
 * 値引区分情報エンティティクラス
 * @author xsong
 *
 */
public class MstNebikiKbnInfo {
	 
	/** テーブル名称 */  
    public static final String TABLE = "BM56NKBN";
    
    /** カラム名称：値引区分コード */ 
    public static final String nkbnCd_COLUMN = "NKBN_CD";
    
    /** カラム名称：値引区分名称 */
    public static final String nkbnName_COLUMN = "NKBN_NAME";
    
    /**
     * 値引区分コード
     */
    private String nkbnCd;
    
    /**
     * 値引区分名称
     */
    private String nkbnName;
    
    /**
     * 値引区分コードを取得します。
     * @return 値引区分コード
     */
    public String getNkbnCd() {
        return nkbnCd;
    }
    /**
     * 値引区分コードを設定します。
     * @param nkbnCd 値引区分コード
     */
    public void setNkbnCd(String nkbnCd) {
        this.nkbnCd = nkbnCd;
    }
    
    /**
     * 値引区分名称を取得します。
     * @return 値引区分名称
     */
    public String getNkbnName() {
        return nkbnName;
    }
    /**
     * 値引区分名称を設定します。
     * @param nkbnName 値引区分名称
     */
    public void setNkbnName(String nkbnName) {
        this.nkbnName = nkbnName;
    }
    
}
