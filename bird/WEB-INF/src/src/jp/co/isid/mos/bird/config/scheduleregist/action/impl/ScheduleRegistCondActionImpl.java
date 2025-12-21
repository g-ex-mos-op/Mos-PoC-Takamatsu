package jp.co.isid.mos.bird.config.scheduleregist.action.impl;

import java.util.Iterator;

import jp.co.isid.mos.bird.config.scheduleregist.action.ScheduleRegistCondAction;
import jp.co.isid.mos.bird.config.scheduleregist.code.ScheduleRegistConst;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistRequestDto;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;
import jp.co.isid.mos.bird.config.scheduleregist.entity.UICompanyPulldown;
import jp.co.isid.mos.bird.config.scheduleregist.logic.ScheduleRegistCondtionLogic;
import jp.co.isid.mos.bird.config.scheduleregist.logic.SearchScheduleLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * スケジュール登録 条件画面アクション
 * @author xnkusama
 *
 */
public class ScheduleRegistCondActionImpl implements ScheduleRegistCondAction {

    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = "BCF010A01";
    public static final String execute_ACTION_ID    = "BCF010A02";
    
    /*DTO*/
    private ScheduleRegistSessionDto scheduleregistDto;
    private ScheduleRegistRequestDto scheduleregistReqDto;
    private PullDownMenuDto pullDownMenuDto;
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    /*LOGIC*/
    private ScheduleRegistCondtionLogic scheduleregistConditionLogic;
    private SearchScheduleLogic scheduleregistSearchScheduleLogic;
    /* OTHER */
    private MakeSessionKey sessionKey = new MakeSessionKey();
    
    /**
     * 実行アクション
     * @return
     */
    public String execute() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getScheduleregistReqDto().getSessionKey(), getScheduleregistDto().getSessionKey())) {
            return ScheduleRegistConst.operationErr_VIEW_ID;
        }
        //セッションキー作成・保持
        createSessionKey();

        // １．選択された会社の名称を取得し、DTOへセット
        for (Iterator ite = getScheduleregistDto().getListCondCompany().iterator(); ite.hasNext();) {
            UICompanyPulldown entity = (UICompanyPulldown) ite.next();
            if (entity.getCompanyCd().equals(trim(getScheduleregistDto().getCondCompany()))) {
                getScheduleregistDto().setCondCompanyName(entity.getCompanyName());
            }
        }
        
        // ２．【ロジック】編集画面情報の取得
        getScheduleregistSearchScheduleLogic().execute(getScheduleregistDto());
        
        return ScheduleRegistConst.VIEW_ID_EDIT;
    }

    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        //セッションキー作成・保持
        createSessionKey();
        
        if (getPullDownMenuDto().isClearFlg()) {
            // ０．ユーザー、日付情報をDTOへセット
            getScheduleregistDto().setBirdUserInfo(getBirdUserInfo());
            getScheduleregistDto().setBirdDateInfo(getBirdDateInfo());
            // １．メニューより遷移された場合、【ロジック】条件項目の取得を実行
            getScheduleregistConditionLogic().execute(getScheduleregistDto());
            // メニューDTOクリア
            getPullDownMenuDto().setClearFlg(false);
        }
        
        return null;
    }

    /**
     * セッションキー作成・保持
     */
    private void createSessionKey() {
        String key = sessionKey._makeSessionKey();
        getScheduleregistReqDto().setSessionKey(key);
        getScheduleregistDto().setSessionKey(key);
    }
    
    /**
     * トリム処理。nullの場合はブランクをリターン
     * @param value
     * @return
     */
    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
    
    public ScheduleRegistCondtionLogic getScheduleregistConditionLogic() {
        return scheduleregistConditionLogic;
    }

    public void setScheduleregistConditionLogic(
            ScheduleRegistCondtionLogic scheduleregistConditionLogic) {
        this.scheduleregistConditionLogic = scheduleregistConditionLogic;
    }

    public ScheduleRegistSessionDto getScheduleregistDto() {
        return scheduleregistDto;
    }

    public void setScheduleregistDto(ScheduleRegistSessionDto scheduleregistDto) {
        this.scheduleregistDto = scheduleregistDto;
    }

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public ScheduleRegistRequestDto getScheduleregistReqDto() {
        return scheduleregistReqDto;
    }

    public void setScheduleregistReqDto(
            ScheduleRegistRequestDto scheduleregistReqDto) {
        this.scheduleregistReqDto = scheduleregistReqDto;
    }

    public SearchScheduleLogic getScheduleregistSearchScheduleLogic() {
        return scheduleregistSearchScheduleLogic;
    }

    public void setScheduleregistSearchScheduleLogic(
            SearchScheduleLogic scheduleregistSearchScheduleLogic) {
        this.scheduleregistSearchScheduleLogic = scheduleregistSearchScheduleLogic;
    }

}
