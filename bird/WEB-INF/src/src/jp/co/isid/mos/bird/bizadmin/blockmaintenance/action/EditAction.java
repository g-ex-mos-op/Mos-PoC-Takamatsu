package jp.co.isid.mos.bird.bizadmin.blockmaintenance.action;

/**
 * ブロックメンテナンス
 * 編集画面用アクションインターフェース
 * 
 * @author xkinu
 *
 */
public interface EditAction {
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
     * 確認 アクション
     * 
     * @return 初期画面ID
     */
    public String confirm();
    /**
     * 対象ブロックプルダウン変更処理
     * 
     * @return
     */
    public String changeDispBlock();
    /**
     * 選択した店舗を移動 アクション
     * 
     * @return null 画面ID
     */
    public String moveBlock();
    /**
     * 移動をリセット アクション
     * 
     * @return null 画面ID
     */
    public String resetBlock();
    
}
