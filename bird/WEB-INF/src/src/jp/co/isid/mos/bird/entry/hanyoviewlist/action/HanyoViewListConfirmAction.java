/*
 * 作成日: 2006/5/25
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.action;

/**
 * 研修申込状況照会 照会画面アクションインターフェース
 * @author Nakajima
 */
public interface HanyoViewListConfirmAction {

    /**
     * 条件画面(初期処理)
     */
    public String initialize();

    /**
     * CSVダウンロード
     */
    public String downloadCsv();
    
    /**
     * 戻る遷移
     */
    public String cancel();

   
}