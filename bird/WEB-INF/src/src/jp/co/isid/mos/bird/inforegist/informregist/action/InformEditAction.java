/*
 * 作成日: 2006/2/8
 */
package jp.co.isid.mos.bird.inforegist.informregist.action;

/**
 * インフォメーション登録編集アクションインターフェース
 * @author itamoto
 */
public interface InformEditAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 新規登録処理
     */
	public String regist();

    /**
     * 取消処理
     */
    public String cancel();

    /**
     * 公開対象の表示処理
     */
    public String startPublicTarget();
    
    /**
     * 添付ファイル削除処理
     * @return
     */
    public String deleteAttachFile();
        
    /**
     * 添付ファイルアップロード処理
     * @return
     */
    public String uploadAttachFile();
    
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
