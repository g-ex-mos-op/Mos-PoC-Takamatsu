package jp.co.isid.mos.bird.bizreport.common.entity;

/**
 * 対象支部情報エンティティクラス
 * 
 * @author xjung
 */
public class MstSibuInfo {
    /** テーブル名称 */
    public static final String TABLE = "BM10GSIB";
    /** カラム名称：支部コード */     
    public static final String sibuCd_COLUMN = "SIBU_CD";
    /** カラム名称：支部名称 */      
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
}
