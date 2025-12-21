package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.action;

/**
 * 予約・在庫状況確認(モスチキン用画面）
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
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back();
    /**
     * タイトル変更 アクション
     * 
     * @return null 画面ID
     */
    public String changeTitle();

    /**
     * 検索 アクション
     * 
     * @return 確認(or照会)画面ID
     */
    public String search();

    /**
     * 実行 アクション
     * 
     * 本部用のアクションです。
     * 対象オーナーを選択した時に呼ばれるアクションです。
     * 
     * @return 編集画面ID
     */
    public String selectOner();
    /**
     * オーナー検索ボタン アクション
     * 
     * 本部用のアクションです。
     * 対象オーナーを選択した時に呼ばれるアクションです。
     * 
     * @return 編集画面ID
     */
    public String callOnerForm();

}
