package jp.co.isid.mos.bird.config.scheduleregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * スケジュール登録 編集画面アクション
 * @author xnkusama
 *
 */
public interface ScheduleRegistEditAction extends CommonAction {

    /**
     * 戻るアクション
     * @return
     */
    public String back();
    
    /**
     * 確認アクション
     * @return
     */
    public String confirm();
    
}
