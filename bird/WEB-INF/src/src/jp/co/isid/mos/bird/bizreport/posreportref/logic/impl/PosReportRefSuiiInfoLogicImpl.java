package jp.co.isid.mos.bird.bizreport.posreportref.logic.impl;
    /**
     * „ˆÚPOSî•ñæ“¾ƒƒWƒbƒN
     * @author inazawa
     * 2007/02/07
     */
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.dao.TrnPosReportRefDao;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.PosReportRefSuiiInfoLogic;

public class PosReportRefSuiiInfoLogicImpl implements PosReportRefSuiiInfoLogic{

    /*LOGIC_ID**/
    public static final String LOGIC_ID = "BBR006L03";

    /*DAO[“X•ÊPOS„ˆÚŒ”î•ñ]**/
    TrnPosReportRefDao trnPosReportRefDao;
    /**
     * „ˆÚPOSî•ñæ“¾
     * @param posReportRefDto
     * @return „ˆÚPOSî•ñæ“¾
     */
    public List execute(PosReportRefDto posReportRefDto) {
        return getTrnPosReportRefDao().getPosReportRefSuii(posReportRefDto.getCompanyCd(),posReportRefDto.getMiseCd(),posReportRefDto.getLatestDate());
    }
    /**
     * trnPosReportRefDaoæ“¾
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
