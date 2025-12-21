/*
 * 作成日: 2006/12/26
 *
 */
package jp.co.isid.mos.bird.entry.nationalentry.action;

/**
 * 全国大会申込のアクション
 * 
 * @author xlee
 */
public interface NatiEntryEditAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();
    
    
    /**
     * 各種イベント申込画面へ戻る
     * @return 画面遷移情報
     */
    public String backMove();
    
    /**
     * スタッフ選択処理
     * 
     * @return 画面遷移情報
     */
    public String callStaffForm();
    
    /**
     * 入力欄追加処理
     * 
     * @return 画面遷移情報
     */
    public String addInputArea();
    
    /**
    * 入力データのDB登録処理
    * 
    * @return 画面遷移情報
    */
   public String registEntry();
}
