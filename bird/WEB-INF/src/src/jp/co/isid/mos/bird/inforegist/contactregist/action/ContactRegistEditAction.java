/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.action;

/**
 * @author xyuchida
 *
 */
public interface ContactRegistEditAction {
    
    /**
     * 初期処理
     * @return
     */
    public String initialize();

    /**
     * 確認
     * @return
     */
    public String confirm();

    /**
     * 戻る
     * @return
     */
    public String cancel();

    /**
     * 公開対象選択
     * @return
     */
    public String callForm();

    /**
     * ファイルアップロード
     * @return
     */
    public String upload();
    
    /**
     * ファイル削除
     * @return
     */
    public String delete();
    
    /**
     * 関連文書選択
     * @return
     */
    public String callFormKanrenBunsho();
    
    /**
     * 関連文書削除
     * @return
     */
    public String deleteKanrenBunsho();

}
