package jp.co.isid.mos.bird.config.scheduleregist.action.impl;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.util.SessionKeyUtil;
import jp.co.isid.mos.bird.config.scheduleregist.action.ScheduleRegistEditAction;
import jp.co.isid.mos.bird.config.scheduleregist.code.ScheduleRegistConst;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistRequestDto;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;
import jp.co.isid.mos.bird.config.scheduleregist.logic.ScheduleRegistCheckLogic;
import jp.co.isid.mos.bird.config.summenuregist.common.SumMenuRegistConst;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * スケジュール登録 条件画面アクション
 * @author xnkusama
 *
 */
public class ScheduleRegistEditActionImpl implements ScheduleRegistEditAction {

    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = "BCF010A11";
    public static final String back_ACTION_ID       = "BCF010A12";
    public static final String confirm_ACTION_ID    = "BCF010A13";
    public static final String changeTab_ACTION_ID  = "BCF010A14";
    
    /*DTO*/
    private ScheduleRegistSessionDto scheduleregistDto;
    private ScheduleRegistRequestDto scheduleregistReqDto;
    /*LOGIC*/
    private ScheduleRegistCheckLogic scheduleregistCheckLogic;
    /* OTHER */
    private MakeSessionKey sessionKey = new MakeSessionKey();
    
    /**
     * 戻るアクション
     * @return
     */
    public String back() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getScheduleregistReqDto().getSessionKey(), getScheduleregistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        
        return ScheduleRegistConst.VIEW_ID_CONDITION;
    }
    
    /**
     * 確認アクション
     * @return
     */
    public String confirm() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getScheduleregistReqDto().getSessionKey(), getScheduleregistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        // 登録内容確認処理
        getScheduleregistCheckLogic().execute(getScheduleregistDto(), getScheduleregistReqDto());
        //セッションキー作成
        createSessionKey();
        
        return ScheduleRegistConst.VIEW_ID_CONFIRM;
    }
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        //セッションキー作成
        createSessionKey();
        
        return null;
    }

    /**
     * セッションキー作成・保持
     */
    private void createSessionKey() {
        String sessionKey = SessionKeyUtil.makeSessionKey();
        getScheduleregistReqDto().setSessionKey(sessionKey);
        getScheduleregistDto().setSessionKey(sessionKey);
    }
    
    public ScheduleRegistSessionDto getScheduleregistDto() {
        return scheduleregistDto;
    }

    public void setScheduleregistDto(ScheduleRegistSessionDto scheduleregistDto) {
        this.scheduleregistDto = scheduleregistDto;
    }

    public ScheduleRegistRequestDto getScheduleregistReqDto() {
        return scheduleregistReqDto;
    }

    public void setScheduleregistReqDto(
            ScheduleRegistRequestDto scheduleregistReqDto) {
        this.scheduleregistReqDto = scheduleregistReqDto;
    }

    public ScheduleRegistCheckLogic getScheduleregistCheckLogic() {
        return scheduleregistCheckLogic;
    }

    public void setScheduleregistCheckLogic(
            ScheduleRegistCheckLogic scheduleregistCheckLogic) {
        this.scheduleregistCheckLogic = scheduleregistCheckLogic;
    }

}
