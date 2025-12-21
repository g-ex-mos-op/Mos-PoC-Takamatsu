/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action;

/**
 * テナント入金明細PDF登録画面
 * 確認画面アクションインターフェース
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public interface ConfirmAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 戻る処理
     * @return 編集画面へ遷移します。
     */
    public String back();
    /**
     * 登録(確定)処理
     * 
     * @return 初期画面へ遷移します。
     */
    public String regist();

}
