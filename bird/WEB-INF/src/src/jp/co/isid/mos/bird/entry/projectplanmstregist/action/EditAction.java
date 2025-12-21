package jp.co.isid.mos.bird.entry.projectplanmstregist.action;

/**
 * 事業方針説明会マスタ登録
 * 編集画面用アクションインターフェース
 * 
 * @author xkinu
 *
 */
public interface EditAction {
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize();
    /**
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back();
    /**
     * 確認 アクション
     * 
     * @return 確認画面ID
     */
    public String confirm();

}
