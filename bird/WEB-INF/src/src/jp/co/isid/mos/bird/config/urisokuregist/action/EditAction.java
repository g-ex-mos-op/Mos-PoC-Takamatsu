/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.action;

/**
 * [機能設定]【売上速報設定】編集画面
 * アクションインターフェース
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface EditAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * タブ切り替え
     * @return
     */
    public String changeTab();
    /**
     * 前月分コピー
     * (商品メニュー設定用)
     * @return
     */
    public String lastMonthDataCopy();
    /**
     * 全クリア
     * (商品メニュー設定用)
     * @return
     */
    public String allClear();
    /**
     * メニュー検索
     * (商品メニュー設定用)
     * @return
     */
    public String searchMenu();
    /**
     * 確認
     * @return
     */
    public String confirm();
    /**
     * 戻る
     * @return
     */
    public String back();

}
