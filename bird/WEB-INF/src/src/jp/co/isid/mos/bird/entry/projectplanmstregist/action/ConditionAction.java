package jp.co.isid.mos.bird.entry.projectplanmstregist.action;

/**
 * 事業方針説明会マスタ登録
 * 初期画面用アクションインターフェース
 * 
 * @author xkinu
 *
 */
public interface ConditionAction {
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize();

    /**
     * 参照 アクション
     * 
     * @return 確認(or照会)画面ID
     */
    public String delete();

    /**
     * 新規登録 アクション
     * 
     * @return 編集画面ID
     */
    public String insert();

    /**
     * 編集 アクション
     * 
     * @return 編集画面ID
     */
    public String update();

}
