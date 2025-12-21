package jp.co.isid.mos.bird.bill.common.dto;

/**
 * 請求書分類記号シート
 * @author xwatanabe
 */
public class SeikyuBunruiKigouDto{
       
    /** 請求書分類 */
    private String bunrui;
    /** 記号 */
    private String kigou;
    /** ソート番号 */
    private String sort;
    
    /////////////////////////////////ここより下、ゲッター・セッター//////////////////////////////////

    /**
     * 請求書分類の取得
     * @return 請求書分類
     */
    public String getBunrui() {
        return bunrui;
    }
    /**
     * 請求書分類の設定
     * @param 請求書分類
     */
    public void setBunrui(String bunrui) {
        this.bunrui = bunrui;
    }
    
    /**
     * 記号を取得
     * @return 記号
     */
    public String getKigou() {
        return kigou;
    }
    /**
     * 記号の設定
     * @param 記号
     */
    public void setKigou(String kigou) {
        this.kigou = kigou;
    }

    /**
     * ソート番号を取得
     * @return ソート番号
     */
    public String getSort() {
        return sort;
    }
    /**
     * ソート番号の設定
     * @param ソート番号
     */
    public void setSort(String sort) {
        this.sort = sort;
    }
}
