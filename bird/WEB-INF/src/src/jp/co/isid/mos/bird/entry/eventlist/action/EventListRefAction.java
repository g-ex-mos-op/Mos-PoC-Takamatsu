/**
 * 
 */
package jp.co.isid.mos.bird.entry.eventlist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * @author xyuchida
 *
 */
public interface EventListRefAction extends CommonAction {

    /**
     * イベント選択
     * 
     * @return 画面遷移情報
     */
    public String selectEvent();
}
