/*
 * 作成日:2007/01/118
 */
package jp.co.isid.mos.bird.entry.ownerchange.action;


/**
 * オーナー間異動　確認画面
 * @author xkonishi
 *
 */
public interface OwnerChangeConfirmAction {
    
    /**
     * 初期処理
     * @return
     */
    public String initialize();
    /**
     * 登録
     * @return
     */
    public String regist();
    /**
     * 戻る
     * @return
     */
    public String back();

}
