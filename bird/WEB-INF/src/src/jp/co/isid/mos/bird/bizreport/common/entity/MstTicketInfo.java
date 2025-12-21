package jp.co.isid.mos.bird.bizreport.common.entity;

/**
 * チケット情報エンティティクラス
 * 
 * @author mwatanabe
 */
public class MstTicketInfo {

    /** テーブル名称 */       
    public static final String TABLE = "BM49TCKT";
    /** カラム名称：チケッドコード */    
    public static final String tcktCd_COLUMN = "TCKT_CD";
    /** カラム名称：チケッド名称 */    
    public static final String tcktName_COLUMN = "TCKT_NAME";
 
    /**
     * チケッドコード
     */
    private String tcktCd;
    
    /**
     * チケッド名称
     */
    private String tcktName;

    /**
     * チケッドコードを取得します。
     * @return チケッドコード
     */
    public String getTcktCd() {
        return tcktCd;
    }
    /**
     * チケッドコードを設定します。
     * @param tcktCd チケッドコード
     */
    public void setTcktCd(String tcktCd) {
        this.tcktCd = tcktCd;
    }
    
    /**
     * チケッド名称を取得します。
     * @return チケッド名称
     */
    public String getTcktName() {
        return tcktName;
    }
    /**
     * チケッド名称を設定します。
     * @param tcktName チケッド名称
     */
    public void setTcktName(String tcktName) {
        this.tcktName = tcktName;
    }    
}
