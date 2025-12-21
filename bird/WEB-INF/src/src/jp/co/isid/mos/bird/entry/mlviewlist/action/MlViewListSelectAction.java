package jp.co.isid.mos.bird.entry.mlviewlist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * マスターライセンス申込状況照会 条件画面アクションインターフェース
 * @author Aspac
 */
public interface MlViewListSelectAction extends CommonAction {

    /**
     * 条件画面(初期処理)
     */
    public String initialize();
    
    /**
     * 検索
     */
    public String execute();
    
    /**
     *  店舗選択処理
     */
    public String miseSrearch();
   
    /**
     *  オーナー選択処理
     */
    public String onerSearch();
}