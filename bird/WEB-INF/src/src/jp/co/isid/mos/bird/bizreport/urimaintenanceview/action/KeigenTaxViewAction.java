/*
 * 作成日:2019/08/16
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.action;

/**
 * 売上高ど消費税確認詳細　画面アクション
 * @author USI張
 *
 */
public interface KeigenTaxViewAction {

    /**
     * 初期処理
     */
    public String initialize();

    /**
     * 明細処理
     */
    public String view();

    /**
     * 再検索
     */
    public String search();

}
