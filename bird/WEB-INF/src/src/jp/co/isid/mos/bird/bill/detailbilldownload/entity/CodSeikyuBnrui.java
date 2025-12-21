package jp.co.isid.mos.bird.bill.detailbilldownload.entity;

public class CodSeikyuBnrui {
    
    public static final String TABLE = "TC26SEIK";
    
    public static final String seikyuBnrui_COLUMN = "SEIKYU_BNRUI";
    
    public static final String seBnrName_COLUMN = "SE_BNR_NAME";
    
    /**
     * 請求書用分類
     */
    private String seikyuBnrui;
    
    /**
     * 請求書分類名称
     */
    private String seBnrName;
    
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
    
}
