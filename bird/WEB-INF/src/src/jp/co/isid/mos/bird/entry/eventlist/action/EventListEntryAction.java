package jp.co.isid.mos.bird.entry.eventlist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * @author xyuchida
 *
 */
public interface EventListEntryAction extends CommonAction {

    /**
     * オーナー選択呼出
     * 
     * @return 画面遷移情報
     */
    public String callOwnerSearch();

    /**
     * オーナー選択
     * 
     * @return 画面遷移情報
     */
    public String selectOwner();

    /**
     * イベント選択
     * 
     * @return 画面遷移情報
     */
    public String selectEvent();

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back();
}
