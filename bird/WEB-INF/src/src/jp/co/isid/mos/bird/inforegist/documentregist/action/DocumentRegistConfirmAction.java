/*
 * 作成日: 2006/01/25
 */
package jp.co.isid.mos.bird.inforegist.documentregist.action;

/**
 * 文書登録 編集画面アクション インターフェイス
 * @author xnkusama
 */
public interface DocumentRegistConfirmAction {

    /**
     * 初期処理
     * @return
     */
	public String initialize();
    
//    /**
//     * タブ変更処理
//     * @return
//     */
//    public String changeTab();
    
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
