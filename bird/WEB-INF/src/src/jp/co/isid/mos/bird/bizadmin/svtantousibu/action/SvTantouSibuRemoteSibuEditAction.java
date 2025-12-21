package jp.co.isid.mos.bird.bizadmin.svtantousibu.action;

/**
 * リモート閲覧支部登録 編集画面アクション・インターフェース 
 *
 * @author xnkusama
 */
public interface SvTantouSibuRemoteSibuEditAction {
    /**
     * 初期処理 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 確認
     * @return 画面遷移情報
     */
    public String confirm();

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back();

}