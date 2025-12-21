package jp.co.isid.mos.bird.bizsupport.budgetregist.action;

/**
 * 予算登録Action
 * 
 * @author Aspac
 */
public interface BudgetRegistAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 予算更新処理
     * 
     * @return 画面遷移情報
     */
    public String doBudgetRegist();


    /**
     * テンプレートCSVダウンロード
     * 
     * @return 画面遷移情報
     */
    public String downloadTempCsv();
    

    /**
     * エラー情報CSVダウンロード
     * 
     * @return 画面遷移情報
     */
    public String downloadErrInfoCsv();
    
    /**
     * 予算クリア処理
     * 
     * @return 画面遷移情報
     */
    public String doBudgetRegistClear();
    
    

}
