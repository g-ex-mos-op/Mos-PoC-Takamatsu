/*
 * 作成日: 2006/03/24
 */
package jp.co.isid.mos.bird.bizsupport.noinputoner.action;

/**
 * P/L未入力オーナー一覧画面アクション インターフェイス
 * @author xnkusama
 */
public interface NoInputOnerAction {

    /**
     * オーナー照会リンク
     * @return
     */
    public String linkOner();
    /**
     * 個店ポータルリンク
     * @return
     */
    public String linkMise();
    /**
     * 検索
     * @return
     */
    public String search();
    /**
     * ページ移動
     * @return
     */
    public String changePage();
    /**
     * 店コード指定ページ移動
     * @author xnkusama
     */
    public String jumpMise();
    /**
     * オーナーコード指定ページ移動
     * @author xnkusama
     */
    public String jumpOner();
}
