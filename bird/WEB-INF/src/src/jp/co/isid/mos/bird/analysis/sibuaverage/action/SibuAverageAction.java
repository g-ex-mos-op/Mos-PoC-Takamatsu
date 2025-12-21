package jp.co.isid.mos.bird.analysis.sibuaverage.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * 支部平均比較 アクション
 * @author xnkusama
 *
 */
public interface SibuAverageAction extends CommonAction {

    /**
     * 実行アクション
     */
    public String execute();
    
    /**
     * CSVダウンロード
     */
    public String downloadCsv();
    
    /**
     * 店検索遷移
     */
    public String callMiseForm();
    /**
     * オーナー検索遷移
     */
    public String callOnerForm();
}
