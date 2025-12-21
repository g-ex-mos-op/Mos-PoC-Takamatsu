/*
 * 作成日: 2006/04/10
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.action;

/**
 * マスタライセンス研修修了登録
 * 編集画面アクション インターフェイス
 * 
 * @author xkinu
 */
public interface MlCompletionregistEditAction extends MlCompletionregistAction {

    /** アクションID:初期処理 BSM006A04 */
    public static String initialize_ACTION_ID = SCREEN_ID+"A04";
    /** アクションID:戻る処理 BSM006A05 */
    public static String back_ACTION_ID      = SCREEN_ID+"A05";
    /** アクションID:登録処理 BSM006A06 */
    public static String execute_ACTION_ID    = SCREEN_ID+"A06";
    
    /**
     * 一括設定処理
     */
    public abstract String paramSetUp();
}
