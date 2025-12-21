package jp.co.isid.mos.bird.entry.nationalregist.action;

/**
 * 全国大会マスタ登録 条件アクション・インターフェース
 *
 * @author xjung
 */
public interface NationalRegistSelectAction {
    /**
     * 初期処理 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 新規登録
     * @return 画面遷移情報
     */
    public String insert();

    /**
     * 編集 
     * @return 画面遷移情報
     */
    public String update();

    /**
     * 削除
     * @return 画面遷移情報
     */
    public String delete();

}