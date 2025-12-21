package jp.co.isid.mos.bird.bizreport.campkako.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * キャンペーン売上推移 条件画面Action
 *
 */
public interface SuiiListAction extends CommonAction {

    /**
     * 店検索
     * @return
     */
    public String doSearchMise();
    
    /**
     * 検索
     * @return
     */
    public String doExecute();
    
    /**
     * 年度変更処理
     */
    public String changeNendo();
    
    /**
     * タブ切替
     */
    public String changeTab();
}