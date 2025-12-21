package jp.co.isid.mos.bird.bizreport.urimaintenance.action;

/**
 * 売上修正（編集画面）
 */
public interface UriMaintenanceEditAction {
   
    /**
     * 初期表示
     * @return
     */
    public String initialize();
    
    
    /**
     * 戻る
     * @return
     */
    public String back();
    
    
    /**
     * 確認
     * @return
     */
    public String confirm();
    
    
    /**
     * 再計算
     * @return
     */
    public String calculate();
    
    
    /**
     * メインタブ切替
     * @return
     */
    public String changeMainTab();
    
    
    /**
     * サブタブ切替
     * @return
     */
    public String changeSubTab();

}
