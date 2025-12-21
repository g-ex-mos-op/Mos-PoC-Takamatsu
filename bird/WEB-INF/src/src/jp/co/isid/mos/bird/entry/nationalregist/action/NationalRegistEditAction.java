package jp.co.isid.mos.bird.entry.nationalregist.action;

/**
 * 全国大会マスタ登録 編集アクション・インターフェース
 *
 * @author xjung
 */
public interface NationalRegistEditAction {
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