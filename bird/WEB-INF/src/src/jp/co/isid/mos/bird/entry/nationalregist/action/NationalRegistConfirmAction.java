package jp.co.isid.mos.bird.entry.nationalregist.action;

/**
 * 全国大会マスタ登録 確認アクション・インターフェース
 *
 * @author xjung
 */
public interface NationalRegistConfirmAction {
    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 登録
     * @return 画面遷移情報
     */
    public String regist();

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back();
}
