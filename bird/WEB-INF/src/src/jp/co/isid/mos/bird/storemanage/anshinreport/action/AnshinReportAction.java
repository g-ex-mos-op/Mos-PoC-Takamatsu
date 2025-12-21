package jp.co.isid.mos.bird.storemanage.anshinreport.action;

public interface AnshinReportAction {

    /*オーナー検索 */
    public String onerSearch();
    /*店検索 */
    public String miseSrearch();
    /*検索 */
    public String execute()throws Exception;;

    /*初期処理 */
    public String initialize() throws Exception;
}
