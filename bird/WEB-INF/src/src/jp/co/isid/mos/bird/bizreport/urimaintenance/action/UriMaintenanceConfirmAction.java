package jp.co.isid.mos.bird.bizreport.urimaintenance.action;

/**
 * 売上修正（確認画面）
 */
public interface UriMaintenanceConfirmAction {
   
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
     * 登録
     * @return
     */
    public String regist();
    
    
    /**
     * タブ切替
     * @return
     */
    public String changeMainTab();
    
    
    /**
     * サブタブ切替
     * @return
     */
    public String changeSubTab();

}
