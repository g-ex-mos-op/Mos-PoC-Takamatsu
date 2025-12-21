/**
 * 
 */
package jp.co.isid.mos.bird.commonform.miseonersearch.action;

/**
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
    /**
     * 個店ポータル画面呼び出し
     * @return
     */
    public String callMisePortal();
    /**
     * オーナーポータル画面呼び出し
     * @return
     */
    public String callOnerPortal();

}
