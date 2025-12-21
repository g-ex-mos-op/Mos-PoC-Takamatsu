package jp.co.isid.mos.bird.bill.itemtotal.entity;

public class UIBunruiInfo {
    
    public static final String TABLE = "TC26SEIK";
    
    public static final String seikyuBnrui_COLUMN = "SEIKYU_BNRUI";
    
    public static final String seBnrName_COLUMN = "SE_BNR_NAME";
    
    public static final String seikyuBunruiKigou_COLUMN = "SEIKYU_BUNRUI_KIGOU";
    
    public static final String seikyuBunruiSeq_COLUMN = "SEIKYU_BUNRUI_SEQ";
    
    /**
     * 請求書用分類
     */
    private String seikyuBnrui;
    
    /**
     * 請求書分類名称
     */
    private String seBnrName;
    
    /**
     * 請求書用分類記号
     */
    private String seikyuBunruiKigou;
    
    /**
     * 請求書用分類ソート番号
     */
    private String seikyuBunruiSeq;
    
    /**
     * 請求書用分類を取得します。
     * @return 請求書用分類
     */
    public String getSeikyuBnrui() {
        return seikyuBnrui;
    }
    /**
     * 請求書用分類を設定します。
     * @param seikyuBnrui 請求書用分類
     */
    public void setSeikyuBnrui(String seikyuBnrui) {
        this.seikyuBnrui = seikyuBnrui;
    }
    
    /**
     * 請求書分類名称を取得します。
     * @return 請求書分類名称
     */
    public String getSeBnrName() {
        return seBnrName;
    }
    /**
     * 請求書分類名称を設定します。
     * @param seBnrName 請求書分類名称
     */
    public void setSeBnrName(String seBnrName) {
        this.seBnrName = seBnrName;
    }
    
    /**
     * 請求書用分類記号を取得します。
     * @return 請求書用分類記号
     */
    public String getSeikyuBunruiKigou() {
        return seikyuBunruiKigou;
    }
    /**
     * 請求書用分類記号を設定します。
     * @param seikyuBunruiKigou 請求書用分類記号
     */
    public void setSeikyuBunruiKigou(String seikyuBunruiKigou) {
        this.seikyuBunruiKigou = seikyuBunruiKigou;
    }
    
    /**
     * 請求書用分類ソート番号を取得します。
     * @return 請求書用分類ソート番号
     */
    public String getSeikyuBunruiSeq() {
        return seikyuBunruiSeq;
    }
    /**
     * 請求書用分類ソート番号を設定します。
     * @param seikyuBunruiSeq 請求書用分類ソート番号
     */
    public void setSeikyuBunruiSeq(String seikyuBunruiSeq) {
        this.seikyuBunruiSeq = seikyuBunruiSeq;
    }
    
}
