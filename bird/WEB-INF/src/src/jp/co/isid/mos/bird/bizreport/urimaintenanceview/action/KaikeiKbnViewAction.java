/*
 * 作成日:2012/08/09
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.action;

/**
 * 売上修正確認詳細　画面アクション
 * @author xkawa
 *
 */
public interface KaikeiKbnViewAction {

    /**
     * 初期処理
     */ 
    public String initialize();

    /**
     * 再検索
     */
    public String view();

    /**
     * 明細処理
     */
    public String search();

}
