/*
 * 作成日: 2006/09/12
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.action;

/**
 * 時間帯別予約状況確認表のアクション
 * 
 * @author xlee
 */
public interface MosChickenReserveChkBytimeAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * オーナー検索フォーム：検索
     * @return 画面遷移情報
     */
    public String callOnerForm();

    /**
     * 本部ユーザーの場合、実行ボタンの処理
     * @return 画面遷移情報
     */
    public String execute();
    
    /**
     * 検索条件のタイトルを切替る場合
     * @return 画面遷移情報
     */
    public String onchangeTitle();
   
   /**
    * 本部ユーザーの場合、オーナー検索画面へ戻る
    * @return 画面遷移情報
    */
   public String backMove();
   
}
