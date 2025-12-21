/*
 * 作成日: 2006/01/25
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.action;

/**
 * 通達登録 確認画面アクション インターフェイス
 * @author m.onodera
 */
public interface ConfirmAction {

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
     * アップロード
     * @return
     */
    public String upload();
    
    /**
     * 公開対象選択フォーム
     * @return
     */
    public String callForm() throws Exception;
}
