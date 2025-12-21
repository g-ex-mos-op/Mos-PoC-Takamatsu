package jp.co.isid.mos.bird.bizreport.urimaintenance.action;

/**
 * 売上修正（軽減税率確認画面）
 */
public interface GetKeigenTaxConfirmAction {

    /**
     * 明細(照会)
     * @return
     */
    public String getKeigenTaxData();

    /**
     * 初期表示
     * @return
     */
    public String initialize();


    /**
     * 戻る
     * @return
     */
    public String back();
    /**
     * タブ切替
     * @return
     */
    public String changeTab();


}
