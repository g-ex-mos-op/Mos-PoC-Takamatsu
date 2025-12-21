package jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.dao.MstMeterKanriDao;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.EnergyInputItemGetDataLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

public class EnergyInputItemGetDataLogicImpl implements EnergyInputItemGetDataLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBS033L02";

    /*DAO*/
    private MstMeterKanriDao energyinputitemMstMeterKanriDao;
    
    public void execute(EnergyInputItemDto dto) {
        //事前条件
        validate(dto);
        
        //検索処理
        List listEditData = getEnergyinputitemMstMeterKanriDao()
                                .selectInputItem(dto.getCompanyCd(), dto.getMeterKbn(), dto.getTaishoSibu(), dto.getSysDate());
        
        //データ存在チェック
        if (listEditData == null || listEditData.isEmpty()) {
            throw new NoResultException();
        }
        
        dto.setListEditData(listEditData);

    }
    /**
     * 事前条件
     * @param companyCd
     * @param sysDate
     */
    private void validate(EnergyInputItemDto dto) {
        if (CommonUtil.isNull(dto.getTaishoSibu())) {
            throw new NotNullException("対象支部");
        }
        if (CommonUtil.isNull(dto.getMeterKbn())) {
            throw new NotNullException("メーター区分");
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