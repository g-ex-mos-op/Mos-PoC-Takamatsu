/*
 * 作成日: 2006/07/05
 */
package jp.co.isid.mos.bird.office.bbsregist.action;

/**
 * 掲示板登録 条件画面アクション インターフェイス
 * @author xytamura
 */
public interface KeijibanConditionAction {

    /**
     * 初期アクション
     * @return
     */
	public String initialize();
    
    /**
     * 掲示板検索アクション
     * @return
     */
    public String searchKeyWord();

    /**
     * タブ変更アクション
     * @return
     */
    public String changeTab();
    
    
    /**
     * 参照ボタンアクション  
     * @return
     */
    public String goReference();
    
    /**
     * ページ切替アクション
     * @return
     */
    public String changePage();

    
    /**
     * 新規登録アクション
     * @return
     */
    public String goInsert();
}
