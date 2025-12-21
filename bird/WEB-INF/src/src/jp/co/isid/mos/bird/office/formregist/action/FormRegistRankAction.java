/*
 * 作成日: 2006/01/25
 */
package jp.co.isid.mos.bird.office.formregist.action;

/**
 * フォーム登録 編集画面アクション インターフェイス
 * @author xytamura
 */
public interface FormRegistRankAction {
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
}
