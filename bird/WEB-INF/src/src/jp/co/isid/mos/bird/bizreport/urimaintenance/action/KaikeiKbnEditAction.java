package jp.co.isid.mos.bird.bizreport.urimaintenance.action;

/**
 * 売上修正（会計区分編集画面）
 */
public interface KaikeiKbnEditAction {
   
    /**
     * 初期表示
     * @return
     */
    public String initialize();
    
    
    /**
     * 戻る
     * @return
     */
    public String returnEdit();
    
    
    /**
     * 決定
     * @return
     */
    public String decide();
    
    
    /**
     * 再計算
     * @return
     */
    public String calculate();
    
    /**
     * タブ切替
     * @return
     */
    public String changeTab();
    
    
    /**
     * 修正
     * @return
     */
    public String revise();
}
