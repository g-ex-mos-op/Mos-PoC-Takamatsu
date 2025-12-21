/*
 * 作成日: 2006/02/09
 */
package jp.co.isid.mos.bird.communication.documentdownload.action;

/**
 * 文書公開 画面アクション インターフェイス
 * @author xnkusama
 */
public interface DocumentDownloadAction {

    /**
     * 初期処理
     * @return
     */
	public String initialize();
    
    /**
     * タイトルによる文書一覧検索処理
     * @return
     */
    public String searchByTitle();

    /**
     * カテゴリによる文書一覧検索処理
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
     * 文書選択
     * @return
     */
    public String viewDetail();
}
