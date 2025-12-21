/*
 * 作成日: 2007/01/31
 */
package jp.co.isid.mos.bird.sysadmin.userregist.entity;

/**
 * @author xamaruyama
 */

public class CodKeiyakuType {
    
    public static final String TABLE = "BC26KYTP";
    
    public static final String keiyakuType_COLUMN = "KEIYAKU_TYPE";
    
    public static final String keiyakuTypeNm_COLUMN = "KEIYAKU_TYPE_NM";
    
    /**
     * 契約タイプコード
     */
    private String keiyakuType;
    
    /**
     * 契約タイプ名
     */
    private String keiyakuTypeNm;
    
    /**
     * 契約タイプコードを取得します。
     * @return 契約タイプコード
     */
    public String getKeiyakuType() {
        return keiyakuType;
    }
    /**
     * 契約タイプコードを設定します。
     * @param keiyakuType 契約タイプコード
     */
    public void setKeiyakuType(String keiyakuType) {
        this.keiyakuType = keiyakuType;
    }
    
    /**
     * 契約タイプ名を取得します。
     * @return 契約タイプ名
     */
    public String getKeiyakuTypeNm() {
        return keiyakuTypeNm;
    }
    /**
     * 契約タイプ名を設定します。
     * @param keiyakuTypeNm 契約タイプ名
     */
    public void setKeiyakuTypeNm(String keiyakuTypeNm) {
        this.keiyakuTypeNm = keiyakuTypeNm;
    }
    
}
