/*
 * 作成日: 2006/04/10
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.action;

/**
 * マスタライセンス研修修了登録
 * 確認画面アクション インターフェイス
 * 
 * @author xkinu
 */
public interface MlCompletionregistConfirmAction extends MlCompletionregistAction {

    /** アクションID:初期処理 BSM006A08 */
    public static String initialize_ACTION_ID   = SCREEN_ID+"A08";
    /** アクションID:戻る BSM006A09 */
    public static String back_ACTION_ID         = SCREEN_ID+"A09";
    /** アクションID:登録実行 BSM006A10 */
    public static String execute_ACTION_ID      = SCREEN_ID+"A10";
}
