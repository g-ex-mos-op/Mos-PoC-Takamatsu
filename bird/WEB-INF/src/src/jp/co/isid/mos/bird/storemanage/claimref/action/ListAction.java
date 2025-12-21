/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimref.action;

/**
 * 個店管理：『お客様の声』
 * 一覧画面アクションインターフェース
 * 
 * @author xkinu
 *
 */
public interface ListAction {
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
     * 個店ポータル遷移処理
     *
     */
    public String kotenPortal();
    /**
     * 詳細画面表示処理
     *
     */
    public String viewInfo();
    /**
     * オーナー検索フォーム呼び出し処理
     * @return オーナー検索フォームViewID
     */
    public String callOnerForm();

}
