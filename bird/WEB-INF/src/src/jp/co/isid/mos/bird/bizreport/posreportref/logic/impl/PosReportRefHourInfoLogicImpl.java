package jp.co.isid.mos.bird.bizreport.posreportref.logic.impl;
    /**
     * “X•ÊŠÔ‘Ñ•ÊPOSî•ñæ“¾ƒƒWƒbƒN
     * @author inazawa
     * 2007/02/07
     */
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.dao.TrnPosReportRefHourDao;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.PosReportRefHourInfoLogic;

public class PosReportRefHourInfoLogicImpl implements PosReportRefHourInfoLogic{
    
    /**LOGIC_ID**/
    public static final String LOGIC_ID = "BBR006L05";

    /*DAO[“X•ÊŠÔ‘Ñ•ÊPOSî•ñ]**/
    TrnPosReportRefHourDao trnPosReportRefHourDao;
    /**
     * “X•ÊŠÔ‘Ñ•ÊPOSî•ñæ“¾
     * @param posReportRefDto
     * @return “X•ÊŠÔ‘Ñ•ÊPOSî•ñ
     */
    public List execute(PosReportRefDto posReportRefDto) {
        return getTrnPosReportRefHourDao().getPosReportRefHour(posReportRefDto.getCompanyCd(),posReportRefDto.getMiseCd(),posReportRefDto.getLatestDate());
    }
    /**
     * trnPosReportRefHourDao‚ğæ“¾
     * @return trnPosReportRefHourDao
     */
    public TrnPosReportRefHourDao getTrnPosReportRefHourDao() {
        return trnPosReportRefHourDao;
    }
    /**
     * trnPosReportRefHourDao‚ğİ’è
     * @param trnPosReportRefHourDao
     */
    public void setTrnPosReportRefHourDao(
            TrnPosReportRefHourDao trnPosReportRefHourDao) {
        this.trnPosReportRefHourDao = trnPosReportRefHourDao;
    }
}
