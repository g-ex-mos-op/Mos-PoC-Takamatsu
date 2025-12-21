package jp.co.isid.mos.bird.bizreport.urimaintenance.action;

/**
 * 売上修正（会計区分確認画面）
 */
public interface KaikeiKbnConfirmAction {
   
    /**
     * 明細(照会)
     * @return
     */
    public String view();

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
     * タブ切替
     * @return
     */
    public String changeTab();
    

}
