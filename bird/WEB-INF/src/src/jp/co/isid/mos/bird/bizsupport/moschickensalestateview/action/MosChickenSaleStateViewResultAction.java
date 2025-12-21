/*
 * 作成日: 2006/09/20
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.action;

/**
 * モスチキン販売状況一覧のアクション
 * 
 * @author xlee
 */
public interface MosChickenSaleStateViewResultAction {
    
    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 再検索ボタンの処理
     * @return 画面遷移情報
     */
    public String backMove();
    
    /**
     * 再検索ボタンの処理
     * @return 画面遷移情報
     */
    public String onchangeTitle();
    
    /**
     * 再検索ボタンの処理
     * @return 画面遷移情報
     */
    public String reSearchExecute();    
        
    /**
     * ページの処理
     * @return 画面遷移情報
     */
    public String changePage();
}
