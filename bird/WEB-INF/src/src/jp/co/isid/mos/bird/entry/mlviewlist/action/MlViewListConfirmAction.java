/*
 * 作成日: 2006/5/25
 */
package jp.co.isid.mos.bird.entry.mlviewlist.action;

/**
 * 研修申込状況照会 照会画面アクションインターフェース
 * @author Nakajima
 */
public interface MlViewListConfirmAction {

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

    /**
     * 受験番号採番
     * 2006/08/14 add
     */
     public String executeSaiban();
   
}