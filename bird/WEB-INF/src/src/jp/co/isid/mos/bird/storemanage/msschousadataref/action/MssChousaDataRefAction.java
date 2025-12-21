package jp.co.isid.mos.bird.storemanage.msschousadataref.action;

public interface MssChousaDataRefAction {

    /*オーナー検索 */
    public String onerSearch();
    /*店検索 */
    public String miseSrearch();
    /*検索 */
    public String execute()throws Exception;;

    /*初期処理 */
    public String initialize() throws Exception;
}
