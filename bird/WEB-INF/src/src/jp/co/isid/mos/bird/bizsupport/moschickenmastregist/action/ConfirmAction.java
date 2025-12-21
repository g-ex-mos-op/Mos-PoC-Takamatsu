package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action;

/**
 * モスチキン管理マスタ登録
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
