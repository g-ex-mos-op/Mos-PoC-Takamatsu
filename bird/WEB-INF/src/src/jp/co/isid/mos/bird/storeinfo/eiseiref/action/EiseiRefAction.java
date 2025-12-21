/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.action;

/**
 * 店舗衛生結果アクションインターフェース
 * 
 * 作成日:2012/12/05
 * @author xkinu
 *
 */
public interface EiseiRefAction {
    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 店検索画面呼出し処理
     * @return 画面遷移情報
     */
    public String callMiseForm();

    /**
     * 実行・再検索処理
     * @return 画面遷移情報
     */
    public String search();
    /**
     * ダウンロード店舗衛生監査処理
     * @return 画面遷移情報
     */
    public void downloadBd32setb();
    /**
     * ダウンロード店舗衛生情報処理
     * @return 画面遷移情報
     */
    public void downloadBd33shtb();
    /**
     * ダウンロード衛生指導員訪店報告処理
     * @return 画面遷移情報
     */
    public void downloadBd34vstb();
}
