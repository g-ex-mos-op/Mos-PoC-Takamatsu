/*
 * 作成日: 2008/02/19
 */
package jp.co.isid.mos.bird.communication.docform.action;

/**
 * 文書公開 画面アクション インターフェイス
 * @author xnkusama
 */
public interface DocFormAction {

    /**
     * 初期処理
     * @return
     */
	public String initialize();
    
    /**
     * カテゴリによる文書一覧検索処理
     * @return
     */
    public String searchByCate();

    /**
     * タイトルによる文書一覧検索処理
     * @return
     */
    public String searchByButton();

    /**
     * タブ変更処理
     * @return
     */
    public String changeTab();
    
    /**
     * ページ切替アクション
     * @return
     */
    public String changePage();
    
    /**
     * 詳細情報の表示
     * @return
     */
    public String viewDetail();
}
