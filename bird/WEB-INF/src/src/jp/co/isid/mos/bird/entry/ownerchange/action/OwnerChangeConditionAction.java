/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.action;

/**
 * オーナー間異動　条件画面アクション
 * @author xkonishi
 *
 */
public interface OwnerChangeConditionAction {
   
   /**
    * 初期処理
    * @return
    */ 
   public String initialize();
   /**
    * 店舗選択
    * @return
    */
   public String miseSearch();
   /**
    * オーナー選択
    * @return
    */
   public String ownerSearch();
   /**
    * 実行
    * @return
    */
   public String execute();

}
