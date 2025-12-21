/*
 * 作成日:2007/02/27
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.action;

/**
 * 売上修正確認　画面アクション
 * @author xwatanabe
 *
 */
public interface UriMainteViewAction {

    /**
     * 初期処理
     */ 
    public String initialize();

    /**
     * 実行（検索処理）
     */
    public String execute();

    /**
     * 表示タブ変更時処理
     */
    public String changeTab();

}
