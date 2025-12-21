package jp.co.isid.mos.bird.bizsupport.energyinputitem.action.impl;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.action.EnergyInputItemCondAction;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.common.EnergyInputItemConst;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemRequestDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.EnergyInputItemGetDataLogic;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.util.SessionKeyUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * 店舗メーター管理状況メンテナンス 条件画面アクション
 * @author xnkusama
 *
 */
public class EnergyInputItemCondActionImpl implements EnergyInputItemCondAction {
    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = "BBS033A01";
    public static final String execute_ACTION_ID = "BBS033A02";

    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    private PullDownMenuDto pullDownMenuDto;
    /* LOGIC */
    private ConditionInfoLogic energyinputitemConditionInfoLogic;
    private EnergyInputItemGetDataLogic energyinputitemGetDataLogic;
    
    /* DTO */
    private EnergyInputItemDto energyinputitemSessionDto;
    private EnergyInputItemRequestDto energyinputitemRequestDto;
    
    public String initialize() {

        //DTO初期化処理
        initDto();
        //セッションキー作成・保持
        createSessionKey();
        
        //メニューからの遷移
        if (getPullDownMenuDto().isClearFlg()) {
            //条件情報取得ロジック実行
            getEnergyinputitemConditionInfoLogic().execute(getEnergyinputitemSessionDto());
            
            getPullDownMenuDto().setClearFlg(false);
        }
        return null;
    }
    
    /**
     * 実行アクション
     * @return
     */
    public String execute() {
        //セッションキーチェック
        if (!SessionKeyUtil.isValidSessionKey(getEnergyinputitemRequestDto().getSessionKey(), getEnergyinputitemSessionDto().getSessionKey())) {
            return EnergyInputItemConst.operationErr_VIEW_ID;
        }
        //セッションキー作成・保持
        createSessionKey();
        //検索処理
        getEnergyinputitemGetDataLogic().execute(getEnergyinputitemSessionDto());
        
        return EnergyInputItemConst.VIEW_ID_EDIT;
    }
    
    /**
     * セッションキー作成・保持
     */
    private void createSessionKey() {
        String sessionKey = SessionKeyUtil.makeSessionKey();
        getEnergyinputitemRequestDto().setSessionKey(sessionKey);
        getEnergyinputitemSessionDto().setSessionKey(sessionKey);
    }
    /**
     * DTO初期化処理
     * @return
     */
    private void initDto() {
        getEnergyinputitemSessionDto().setUserId(getBirdUserInfo().getUserID());
        getEnergyinputitemSessionDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
        getEnergyinputitemSessionDto().setExistRegistData(false);
        getEnergyinputitemSessionDto().setListEditData(null);
        getEnergyinputitemSessionDto().setSysDate(getBirdDateInfo().getSysDate());
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
    
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public ConditionInfoLogic getEnergyinputitemConditionInfoLogic() {
        return energyinputitemConditionInfoLogic;
    }

    public void setEnergyinputitemConditionInfoLogic(
            ConditionInfoLogic energyinputitemConditionInfoLogic) {
        this.energyinputitemConditionInfoLogic = energyinputitemConditionInfoLogic;
    }

    public EnergyInputItemDto getEnergyinputitemSessionDto() {
        return energyinputitemSessionDto;
    }

    public void setEnergyinputitemSessionDto(
            EnergyInputItemDto energyinputitemSessionDto) {
        this.energyinputitemSessionDto = energyinputitemSessionDto;
    }

    public EnergyInputItemGetDataLogic getEnergyinputitemGetDataLogic() {
        return energyinputitemGetDataLogic;
    }

    public void setEnergyinputitemGetDataLogic(
            EnergyInputItemGetDataLogic energyinputitemGetDataLogic) {
        this.energyinputitemGetDataLogic = energyinputitemGetDataLogic;
    }

    public EnergyInputItemRequestDto getEnergyinputitemRequestDto() {
        return energyinputitemRequestDto;
    }

    public void setEnergyinputitemRequestDto(EnergyInputItemRequestDto energyinputitemRequestDto) {
        this.energyinputitemRequestDto = energyinputitemRequestDto;
    }
}