package jp.co.isid.mos.bird.bizreport.posreportregist.action;

/**
 * POS速報設定登録
 */
public interface PosReportRegistAction {
   
    /**
     * 初期表示
     */
    public String initialize();
    
    /**
     * 確認
     */
    public String confirm();
    
    
    /**
     * 行追加
     */
    public String addRow();

    
    /**
     * 店舗検索ボタン
     */
    public String searchStore();
    
}
