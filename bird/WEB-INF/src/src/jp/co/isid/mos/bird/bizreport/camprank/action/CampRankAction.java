package jp.co.isid.mos.bird.bizreport.camprank.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * キャンペーンベスト１００画面Action Interface
 *
 */
public interface CampRankAction extends CommonAction {

    /**
     * 実行
     * @return
     */
    public String doExecute();
    
    /**
     * タブ切替
     * @return 
     */
    public String doTabChange();
    
    /**
     * CSVダウンロード
     * @return
     */
    public String doCsvDownload();
}