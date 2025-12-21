package jp.co.isid.mos.bird.config.scheduleregist.action.impl;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.util.SessionKeyUtil;
import jp.co.isid.mos.bird.config.scheduleregist.action.ScheduleRegistConfirmAction;
import jp.co.isid.mos.bird.config.scheduleregist.code.ScheduleRegistConst;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistRequestDto;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;
import jp.co.isid.mos.bird.config.scheduleregist.logic.ScheduleRegistLogic;
import jp.co.isid.mos.bird.config.summenuregist.common.SumMenuRegistConst;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * スケジュール登録 条件画面アクション
 * @author xnkusama
 *
 */
public class ScheduleRegistConfirmActionImpl implements ScheduleRegistConfirmAction {

    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = "BCF010A21";
    public static final String back_ACTION_ID       = "BCF010A22";
    public static final String regist_ACTION_ID     = "BCF010A23";
    
    /*DTO*/
    private ScheduleRegistSessionDto scheduleregistDto;
    private ScheduleRegistRequestDto scheduleregistReqDto;
    /*LOGIC*/
    private ScheduleRegistLogic scheduleregistRegistLogic;
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
        //セッションキー作成
        createSessionKey();
        
        return ScheduleRegistConst.VIEW_ID_EDIT;
    }
    
    /**
     * 登録アクション
     * @return
     */
    public String regist() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getScheduleregistReqDto().getSessionKey(), getScheduleregistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        //セッションキー作成・保持
        createSessionKey();
        
        //登録処理
        getScheduleregistRegistLogic().execute(getScheduleregistDto());
        
        return ScheduleRegistConst.VIEW_ID_CONDITION;
    }

    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        //セッションキー作成・保持
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

    public ScheduleRegistLogic getScheduleregistRegistLogic() {
        return scheduleregistRegistLogic;
    }

    public void setScheduleregistRegistLogic(
            ScheduleRegistLogic scheduleregistRegistLogic) {
        this.scheduleregistRegistLogic = scheduleregistRegistLogic;
    }

    public ScheduleRegistRequestDto getScheduleregistReqDto() {
        return scheduleregistReqDto;
    }

    public void setScheduleregistReqDto(
            ScheduleRegistRequestDto scheduleregistReqDto) {
        this.scheduleregistReqDto = scheduleregistReqDto;
    }

    public MakeSessionKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(MakeSessionKey sessionKey) {
        this.sessionKey = sessionKey;
    }
}
