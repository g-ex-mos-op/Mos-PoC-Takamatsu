/*
 * 作成日:2007/01/18
 */
package jp.co.isid.mos.bird.entry.ownerchange.action;
/**
 * オーナー間異動登録 編集画面アクション
 * @author xkonishi
 *
 */
public interface OwnerChangeEditAction {
    /**
     * 初期処理
     * @return
     */
    public String initialize();
    /**
     * オーナー検索
     * @return
     */
    public String ownerSearch();
    /**
     * 確認
     * @return
     */
    public String confirm();
    /**
     * 戻る
     * @return
     */
    public String back();

}