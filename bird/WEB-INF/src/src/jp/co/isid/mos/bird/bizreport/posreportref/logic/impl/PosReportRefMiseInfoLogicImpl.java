package jp.co.isid.mos.bird.bizreport.posreportref.logic.impl;
/**
 * “Xˆê——POSî•ñæ“¾ƒƒWƒbƒN
 * @author inazawa
 * 2007/02/07
 */
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.dao.TrnPosReportRefDao;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.PosReportRefMiseInfoLogic;

public class PosReportRefMiseInfoLogicImpl implements PosReportRefMiseInfoLogic{
    /*LOGIC_ID**/
    public static final String LOGIC_ID = "BBR006L02";
    
    /**DAO[POS‘¬•ñ]**/
    TrnPosReportRefDao trnPosReportRefDao;
    
    /**
     * “Xˆê——POSî•ñ
     * @param posReportRefDto
     * @return ŒŸõŒ‹‰Ê
     */
    public List execute(PosReportRefDto posReportRefDto) {
        return getTrnPosReportRefDao().getPosReportRefMise(posReportRefDto.getCompanyCd(),posReportRefDto.getLatestDate());
    }
    /**
     * trnPosReportRefDao‚ğæ“¾
     * @return trnPosReportRefDao
     */
    public TrnPosReportRefDao getTrnPosReportRefDao() {
        return trnPosReportRefDao;
    }

    /**
     * trnPosReportRefDao‚ğİ’è
     * @param trnPosReportRefDao
     */
    public void setTrnPosReportRefDao(TrnPosReportRefDao trnPosReportRefDao) {
        this.trnPosReportRefDao = trnPosReportRefDao;
    }
}
