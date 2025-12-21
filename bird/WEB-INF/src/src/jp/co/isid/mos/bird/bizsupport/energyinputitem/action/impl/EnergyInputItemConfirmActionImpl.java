package jp.co.isid.mos.bird.bizsupport.energyinputitem.action.impl;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.action.EnergyInputItemConfirmAction;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.common.EnergyInputItemConst;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemRequestDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.EnergyInputItemRegistLogic;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.util.SessionKeyUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 店舗メーター管理状況メンテナンス 確認画面アクション
 * @author xnkusama
 *
 */
public class EnergyInputItemConfirmActionImpl implements EnergyInputItemConfirmAction {
    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = "BBS033A21";
    public static final String back_ACTION_ID       = "BBS033A22";
    public static final String execute_ACTION_ID    = "BBS033A23";

    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    /* LOGIC */
    private EnergyInputItemRegistLogic energyinputitemRegistLogic;
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
        //更新処理
        getEnergyinputitemRegistLogic().execute(getEnergyinputitemSessionDto());
        
        return EnergyInputItemConst.VIEW_ID_CONDITION;
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
        return EnergyInputItemConst.VIEW_ID_EDIT;
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

    public EnergyInputItemRegistLogic getEnergyinputitemRegistLogic() {
        return energyinputitemRegistLogic;
    }

    public void setEnergyinputitemRegistLogic(
            EnergyInputItemRegistLogic energyinputitemRegistLogic) {
        this.energyinputitemRegistLogic = energyinputitemRegistLogic;
    }

    public EnergyInputItemRequestDto getEnergyinputitemRequestDto() {
        return energyinputitemRequestDto;
    }

    public void setEnergyinputitemRequestDto(
            EnergyInputItemRequestDto energyinputitemRequestDto) {
        this.energyinputitemRequestDto = energyinputitemRequestDto;
    }

}