/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.itemtotal.action;

/**
 * 商品別合計のアクション
 * 
 * @author xlee
 */
public interface ShobetuGoukeiAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * オーナー検索フォーム：検索
     * @return　画面遷移情報
     */
    public String callOnerForm();
    
    /**
     * 明細・履歴検索
     * @return　画面遷移情報
     */
    public String callDetailForm();

    /**
     * オーナーコード選択した後、実行ボタンの処理
     * 
     * @return 画面遷移情報
     */
    public String taishoTenpoExecute();

    /**
     * 対象店舗や対象期間を選択した後、実行ボタンの処理
     * 
     * @return 画面遷移情報
     */
    public String searchExecute();
   
}
