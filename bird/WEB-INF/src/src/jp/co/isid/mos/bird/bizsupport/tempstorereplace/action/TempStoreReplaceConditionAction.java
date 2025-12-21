package jp.co.isid.mos.bird.bizsupport.tempstorereplace.action;

/**
 * 仮店舗置換えメンテナンスAction
 * 
 * @author Aspac
 */
public interface TempStoreReplaceConditionAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();
    
    
    /**
     * 会社プルダウン変更
     * 
     * @return 画面遷移情報
     */
    public String onChangeCompany();


    /**
     * ダウンロード
     * 
     */
    public void downloadCsv();
    
    
    /**
     * 実行
     * 
     * @return 画面遷移情報
     */
    public String doSearchTempStroe();
    
    

}
