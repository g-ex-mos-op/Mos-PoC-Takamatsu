package jp.co.isid.mos.bird.bizadmin.blockmaintenance.action;

/**
 * ブロックメンテナンス
 * 確認(or照会)画面用アクションインターフェース
 * 
 * @author xkinu
 *
 */
public interface ConfirmAction {
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
     * 登録 アクション
     * 
     * @return 初期画面ID
     */
    public String regist();

}
