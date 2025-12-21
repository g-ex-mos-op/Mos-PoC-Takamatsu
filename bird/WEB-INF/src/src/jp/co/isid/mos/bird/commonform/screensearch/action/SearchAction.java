/**
 * 
 */
package jp.co.isid.mos.bird.commonform.screensearch.action;

/**
 * 画面メニュー検索アクションインターフェース
 * 
 * 作成日:2008/11/20
 * @author xkinu
 *
 */
public interface SearchAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 検索
     * @return
     */
    public String search();

}
