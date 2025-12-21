/*
 * 作成日:2007/01/18
 * 
 */
package jp.co.isid.mos.bird.entry.ownerchange.action;

/**
 * オーナー移動登録　対象者設定画面
 * @author xkonishi
 *
 */
public interface OwnerChangeSetUpAction {

    /**
     * 初期処理
     * @return
     */
    public String initialize();
    /**
     * 対象者選択
     * @return
     */
    public String select();
    /**
     * 戻る
     * @return
     */
    public String back();


}
