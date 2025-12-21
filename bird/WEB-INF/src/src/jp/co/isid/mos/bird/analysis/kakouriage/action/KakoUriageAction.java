package jp.co.isid.mos.bird.analysis.kakouriage.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * 過去売上 アクション
 * @author xnkusama
 *
 */
public interface KakoUriageAction extends CommonAction {

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
}
