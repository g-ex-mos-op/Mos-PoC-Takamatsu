/*
 * 作成日: 2006/08/04
 *
 */
package jp.co.isid.mos.bird.bill.buyinglistview.action;

/**
 * お買上詳細照会のアクション
 * 
 * @author xlee
 */
public interface BuyingListViewAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * オーナー検索フォーム：検索
     * @return
     */
    public String callOnerForm();

    /**
     * 実行ボタンの処理
     * 
     * @return 画面遷移情報
     */
    public String execute();
    
}
