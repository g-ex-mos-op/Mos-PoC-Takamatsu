/*
 * 作成日: 2006/02/09
 */
package jp.co.isid.mos.bird.office.formdownload.action;

/**
 *  フォームダウンロード画面アクション インターフェイス
 * @author xytamura
 */
public interface FormDownloadAction {

    /**
     * 初期処理
     * @return
     */
	public String initialize();
    
    /**
     * タイトルによるフォーム一覧検索処理
     * @return
     */
    public String searchByTitle();

    /**
     * カテゴリによるフォーム一覧検索処理
     * @return
     */
    public String searchByCate();

    /**
     * ページ切替アクション
     * @return
     */
    public String changePage();
    
    /**
     * タブ変更処理
     * @return
     */
    public String changeTab();
    
    /**
     * フォーム選択
     * @return
     */
    public String viewDetail();
}
