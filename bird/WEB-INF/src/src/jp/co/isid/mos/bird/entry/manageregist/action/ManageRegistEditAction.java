package jp.co.isid.mos.bird.entry.manageregist.action;

/**
 * 全国店長勉強会マスタ登録 編集アクション・インターフェース
 *
 * @author xjung
 */
public interface ManageRegistEditAction {
    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 確認 アクション
     * @return 画面遷移情報
     */
    public String confirm();

    /**
     * 戻る アクション
     * @return 画面遷移情報
     */
    public String back();

}