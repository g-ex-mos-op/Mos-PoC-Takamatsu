/*
 * 作成日: 2006/5/25
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.action;

/**
 * 研修申込状況照会 条件画面アクションインターフェース
 * @author Nakajima
 */
public interface HanyoViewListSelectAction {

    /**
     * 条件画面(初期処理)
     */
    public String initialize();

    /**
     * プルダウンチェンジ
     */
    public String changePullDown();
    
    /**
     * 検索
     */
    public String execute();
    
   
}