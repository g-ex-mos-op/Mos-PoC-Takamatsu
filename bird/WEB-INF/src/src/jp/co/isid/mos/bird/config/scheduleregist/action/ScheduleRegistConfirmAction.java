package jp.co.isid.mos.bird.config.scheduleregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * スケジュール登録 条件画面アクション
 * @author xnkusama
 *
 */
public interface ScheduleRegistConfirmAction extends CommonAction {


    /**
     * 戻るアクション
     * @return
     */
    public String back();
    
    /**
     * 登録アクション
     * @return
     */
    public String regist();
}
