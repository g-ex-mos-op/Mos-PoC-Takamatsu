/*
 * 作成日: 2006/01/31
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.action;

/**
 * 通達登録 
 * @author m.onodera
 */
public interface RegistAction {
    /**
     * 初期処理
     * @return
     */
    public String initialize();
    /**
     * 取消  
     * @return
     */
    public String cancel();
    /**
     * 登録処理
     * @return String
     */
    public String regist();
    /**
     * 公開対象選択フォーム
     * @return
     */
    public String callForm() throws Exception;
    /**
     * 通達ファイル削除処理
     * @return
     */
    public String deleteMainFile();
    
    /**
     * 添付ファイル削除処理
     * @return
     */
    public String deleteAttachFile();
    /**
     * 通達ファイルアップロード処理
     * @return
     */
    public String uploadMainFile();
    /**
     * 添付ファイルアップロード処理
     * @return
     */
    public String uploadAttachFile();
    
    /**
     * 関連文書選択フォーム
     * @return
     */
    public String callFormKanrenBunsho();
    
    /**
     * 関連文書削除
     * @return
     */
    public String deleteKanrenBunsho();
}
