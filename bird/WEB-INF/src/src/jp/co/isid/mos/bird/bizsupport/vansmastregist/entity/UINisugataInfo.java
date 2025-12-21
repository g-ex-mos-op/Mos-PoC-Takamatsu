/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.entity;

/**
 * 荷姿情報
 * @author narita
 */
public class UINisugataInfo {
    
    public static final String TABLE = "TC03NISU";
    
    public static final String shoNisugata_COLUMN = "SHO_NISUGATA";
    
    public static final String nisuName_COLUMN = "NISU_NAME";
    
    /**
     * 商品荷姿コード
     */
    private String shoNisugata;
    
    /**
     * 荷姿名称
     */
    private String nisuName;
    
    /**
     * 商品荷姿コードを取得します。
     * @return 商品荷姿コード
     */
    public String getShoNisugata() {
        return shoNisugata;
    }
    /**
     * 商品荷姿コードを設定します。
     * @param shoNisugata 商品荷姿コード
     */
    public void setShoNisugata(String shoNisugata) {
        this.shoNisugata = shoNisugata;
    }
    
    /**
     * 荷姿名称を取得します。
     * @return 荷姿名称
     */
    public String getNisuName() {
        return nisuName;
    }
    /**
     * 荷姿名称を設定します。
     * @param nisuName 荷姿名称
     */
    public void setNisuName(String nisuName) {
        this.nisuName = nisuName;
    }
    
}
