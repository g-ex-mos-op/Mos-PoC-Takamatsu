package jp.co.isid.mos.bird.bizsupport.energyinputitem.action.impl;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.action.EnergyInputItemEditAction;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.common.EnergyInputItemConst;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemRequestDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.EnergyInputItemCheckLogic;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.util.SessionKeyUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 店舗メーター管理状況メンテナンス 編集画面アクション
 * @author xnkusama
 *
 */
public class EnergyInputItemEditActionImpl implements EnergyInputItemEditAction {
    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = "BBS033A11";
    public static final String back_ACTION_ID       = "BBS033A12";
    public static final String execute_ACTION_ID    = "BBS033A13";
    
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    /* LOGIC */
    private EnergyInputItemCheckLogic energyinputitemCheckLogic;
    /* DTO */
    private EnergyInputItemDto energyinputitemSessionDto;
    private EnergyInputItemRequestDto energyinputitemRequestDto;
    
    public String initialize() {
        //セッションキーチェック
        if (!SessionKeyUtil.isValidSessionKey(getEnergyinputitemRequestDto().getSessionKey(), getEnergyinputitemSessionDto().getSessionKey())) {
            return EnergyInputItemConst.operationErr_VIEW_ID;
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
        //入力データチェック
        getEnergyinputitemCheckLogic().execute(getEnergyinputitemSessionDto());
        //セッションキー作成・保持
        createSessionKey();
        
        return EnergyInputItemConst.VIEW_ID_CONFIRM;
    }
    
    /**
     * 戻るアクション
     * @return
     */
    public String back() {
        //セッションキーチェック
        if (!SessionKeyUtil.isValidSessionKey(getEnergyinputitemRequestDto().getSessionKey(), getEnergyinputitemSessionDto().getSessionKey())) {
            return EnergyInputItemConst.operationErr_VIEW_ID;
        }
        return EnergyInputItemConst.VIEW_ID_CONDITION;
    }

    /**
     * セッションキー作成・保持
     */
    private void createSessionKey() {
        String sessionKey = SessionKeyUtil.makeSessionKey();
        getEnergyinputitemRequestDto().setSessionKey(sessionKey);
        getEnergyinputitemSessionDto().setSessionKey(sessionKey);
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

    public EnergyInputItemDto getEnergyinputitemSessionDto() {
        return energyinputitemSessionDto;
    }

    public void setEnergyinputitemSessionDto(
            EnergyInputItemDto energyinputitemSessionDto) {
        this.energyinputitemSessionDto = energyinputitemSessionDto;
    }

    public EnergyInputItemCheckLogic getEnergyinputitemCheckLogic() {
        return energyinputitemCheckLogic;
    }

    public void setEnergyinputitemCheckLogic(
            EnergyInputItemCheckLogic energyinputitemCheckLogic) {
        this.energyinputitemCheckLogic = energyinputitemCheckLogic;
    }

    public EnergyInputItemRequestDto getEnergyinputitemRequestDto() {
        return energyinputitemRequestDto;
    }

    public void setEnergyinputitemRequestDto(
            EnergyInputItemRequestDto energyinputitemRequestDto) {
        this.energyinputitemRequestDto = energyinputitemRequestDto;
    }

}