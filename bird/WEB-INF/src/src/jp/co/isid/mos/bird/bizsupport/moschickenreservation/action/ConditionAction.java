package jp.co.isid.mos.bird.bizsupport.moschickenreservation.action;

/**
 * モスチキン予約
 * 初期画面用アクションインターフェース
 * 
 * @author inazawa
 *
 */
public interface ConditionAction {
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize() throws Exception;
    /**
     * 本部ユーザー用実行ボタン押下時
     */
    public String honbuExec() throws Exception;

    /**
     *オーナー検索
     */
    public String onerSearch();
    /**
     * 本部ユーザー初期画面に戻る
     */
    public String honbuUserBack();
    /**
     * 検索画面押下
     */
    public String exec();
    /**
     * タイトル変更時
     */
    public String changeTitle() throws Exception;
}
