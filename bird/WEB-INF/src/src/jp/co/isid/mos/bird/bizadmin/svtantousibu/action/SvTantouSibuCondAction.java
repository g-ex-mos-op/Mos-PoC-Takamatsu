package jp.co.isid.mos.bird.bizadmin.svtantousibu.action;

/**
 * リモート閲覧支部・SV担当店登録 初期画面アクション・インターフェース
 *
 * @author xnkusama
 */
public interface SvTantouSibuCondAction {
    /**
     * 初期処理 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 実行
     * @return 画面遷移情報
     */
    public String execute();

    /**
     * ユーザー検索
     * @return 画面遷移情報
     */
    public String callUserSearch();

}