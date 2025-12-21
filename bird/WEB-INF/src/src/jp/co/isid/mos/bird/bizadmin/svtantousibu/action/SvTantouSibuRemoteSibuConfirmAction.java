package jp.co.isid.mos.bird.bizadmin.svtantousibu.action;

/**
 * リモート閲覧支部登録 確認画面アクション・インターフェース 
 *
 * @author xnkusama
 */
public interface SvTantouSibuRemoteSibuConfirmAction {
    /**
     * 初期処理 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 登録
     * @return 画面遷移情報
     */
    public String confirm();

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back();

}