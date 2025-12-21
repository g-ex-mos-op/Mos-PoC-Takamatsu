package jp.co.isid.mos.bird.bizreport.posreportref.action;

public interface PosReportRefMiseAction {

    
    /**
     * 店別処理POS速報アクション
     * @return String 遷移先ビューID
     */
    public String misePosRefSerch();
    /**
     * 初期処理
     * @return String 遷移先ビューID
     */
    public String initialize();

}
