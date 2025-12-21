/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.action;

/**
 * 【データ分析】売上ランク画面
 * 照会アクションインターフェース
 * 作成日:2008/10/20
 * @author xkinu
 *
 */
public interface ViewAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 実行or再検索
     * @return
     */
    public String search();
    /**
     * ランク対象タブ切り替え処理
     * @return
     */
    public String changeTab();
}
