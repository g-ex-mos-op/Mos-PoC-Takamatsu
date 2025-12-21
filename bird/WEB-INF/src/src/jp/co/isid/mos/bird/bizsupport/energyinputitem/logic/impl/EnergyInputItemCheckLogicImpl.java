package jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.impl;

import java.util.Iterator;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.common.EnergyInputItemConst;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dao.MstMeterKanriDao;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.entity.MstMeterKanri;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.EnergyInputItemCheckLogic;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

public class EnergyInputItemCheckLogicImpl implements EnergyInputItemCheckLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBS033L03";

    /*DAO*/
    private MstMeterKanriDao energyinputitemMstMeterKanriDao;
    
    public void execute(EnergyInputItemDto dto) {
        //事前条件
        validate(dto);
        
        //更新対象チェック
        boolean existRegistData = false;
        for (Iterator ite = dto.getListEditData().iterator(); ite.hasNext();) {
            MstMeterKanri entity = (MstMeterKanri) ite.next();
            //電灯
            if (isInput(entity.getElectricFlgSave()) != entity.isElectricFlgDisp()
                    || isInput(entity.getPowerFlgSave()) != entity.isPowerFlgDisp()
                    || isInput(entity.getGasFlgSave()) != entity.isGasFlgDisp()
                    || isInput(entity.getWaterFlgSave()) != entity.isWaterFlgDisp()) 
            {
                entity.setUpdateFlg(true);
                existRegistData = true;
            }
            else {
                entity.setUpdateFlg(false);
            }
        }
        
        dto.setExistRegistData(existRegistData);

    }
    
    /**
     * 使用フラグをbooleanで取得
     */
    private boolean isInput(String value) {
        if (EnergyInputItemConst.INPUT_FLG_INPUT.equals(value)) {
            return true;
        }
        return false;
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
    public MstMeterKanriDao getEnergyinputitemMstMeterKanriDao() {
        return energyinputitemMstMeterKanriDao;
    }
    public void setEnergyinputitemMstMeterKanriDao(
            MstMeterKanriDao energyinputitemMstMeterKanriDao) {
        this.energyinputitemMstMeterKanriDao = energyinputitemMstMeterKanriDao;
    }
}