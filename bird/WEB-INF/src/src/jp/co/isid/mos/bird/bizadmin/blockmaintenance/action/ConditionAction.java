package jp.co.isid.mos.bird.bizadmin.blockmaintenance.action;

/**
 * ブロックメンテナンス
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
     * 実行 アクション
     * 
     * @return 編集画面ID
     */
    public String search();

}
