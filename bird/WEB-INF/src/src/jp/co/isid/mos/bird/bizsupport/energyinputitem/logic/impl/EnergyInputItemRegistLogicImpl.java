package jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.impl;

import java.util.Iterator;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.common.EnergyInputItemConst;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dao.MstMeterKanriDao;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.entity.MstMeterKanri;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.EnergyInputItemRegistLogic;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

public class EnergyInputItemRegistLogicImpl implements EnergyInputItemRegistLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBS033L04";

    /*DAO*/
    private MstMeterKanriDao energyinputitemMstMeterKanriDao;
    
    public void execute(EnergyInputItemDto dto) {
        //事前条件
        validate(dto);
        
        //登録処理
        for (Iterator ite = dto.getListEditData().iterator(); ite.hasNext();) {
            MstMeterKanri entity = (MstMeterKanri) ite.next();
            
            if (entity.isUpdateFlg()) {
                //更新者情報セット
                entity.setLastUser(dto.getUserId());
                entity.setLastPgm(EnergyInputItemConst.SCREEN_ID);
                //使用フラグセット
                entity.setElectricFlg(getInputFlg(entity.isElectricFlgDisp()));
                entity.setPowerFlg(getInputFlg(entity.isPowerFlgDisp()));
                entity.setGasFlg(getInputFlg(entity.isGasFlgDisp()));
                entity.setWaterFlg(getInputFlg(entity.isWaterFlgDisp()));
                
                getEnergyinputitemMstMeterKanriDao().updateInputItem(entity);
            }
        }
    }
    /**
     * 事前条件
     * @param companyCd
     * @param sysDate
     */
    private void validate(EnergyInputItemDto dto) {
        if (dto.getListEditData() == null || dto.getListEditData().isEmpty()) {
            throw new MissingDataException("登録情報");
        }
    }
    
    /**
     * 使用フラグをbooleanで取得
     */
    private String getInputFlg(boolean flg) {
        if (flg) {
            return EnergyInputItemConst.INPUT_FLG_INPUT;
        }
        return EnergyInputItemConst.INPUT_FLG_NO_INPUT;
    }

    public MstMeterKanriDao getEnergyinputitemMstMeterKanriDao() {
        return energyinputitemMstMeterKanriDao;
    }
    public void setEnergyinputitemMstMeterKanriDao(
            MstMeterKanriDao energyinputitemMstMeterKanriDao) {
        this.energyinputitemMstMeterKanriDao = energyinputitemMstMeterKanriDao;
    }
}