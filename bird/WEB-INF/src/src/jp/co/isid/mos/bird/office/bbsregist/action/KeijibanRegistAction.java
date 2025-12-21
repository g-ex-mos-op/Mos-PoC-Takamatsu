/*
 * 作成日: 2006/07/05
 */
package jp.co.isid.mos.bird.office.bbsregist.action;

/**
 * 掲示板登録 登録画面アクション インターフェイス
 * @author xytamura
 */
public interface KeijibanRegistAction {
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
     * 添付ファイル削除処理
     * @return
     */
    public String deleteAttachFile();
        
    /**
     * 添付ファイルアップロード処理
     * @return
     */
    public String uploadAttachFile();
    
}
