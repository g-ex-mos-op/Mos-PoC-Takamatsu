/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.action;

/**
 * 事業方針説明会申込のアクション
 * 
 * @author xlee
 */
public interface ProjectPlanOfferIninAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();
    
    /**
    * 入力データのDB登録処理
    * 
    * @return 画面遷移情報
    */
   public String registOffer();
   
   /**
    * 各種イベント申込画面へ戻る
    * @return 画面遷移情報
    */
   public String backMove();
}
