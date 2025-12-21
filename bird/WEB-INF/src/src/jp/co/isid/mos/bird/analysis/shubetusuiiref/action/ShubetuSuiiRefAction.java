package jp.co.isid.mos.bird.analysis.shubetusuiiref.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * 種別売上推移表 アクション
 * @author xnkusama
 *
 */
public interface ShubetuSuiiRefAction extends CommonAction {

    /**
     * 実行アクション
     */
    public String execute();
    
    /**
     * 日次CSVダウンロード
     */
    public String downloadDayCsv();
    
    /**
     * 月次CSVダウンロード
     */
    public String downloadMonthCsv();
    
    /**
     * 店検索遷移
     */
    public String callMiseForm();
}
