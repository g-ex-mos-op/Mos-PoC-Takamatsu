package jp.co.isid.mos.bird.bizreport.posreportref.logic.impl;

/**
 * 最新日付取得ロジック
 * @author inazawa
 * 2007/02/07
 */
import jp.co.isid.mos.bird.bizreport.posreportref.dao.TrnPosReportRefDao;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.LatestDateLogic;

public class LatestDateLogicImpl implements LatestDateLogic {
    
    /*LOGIC_ID**/
    public static final String LOGIC_ID = "BBR006L01";

    /**DAO[POS速報]**/
    TrnPosReportRefDao trnPosReportRefDao;
    
    /**
     * 最新日付取得
     * @param posReportRefDto
     * @return 最新日付
     */
    public String execute(PosReportRefDto posReportRefDto) {
        return getTrnPosReportRefDao().getLatestDate(posReportRefDto);
    }

    /**
     * trnPosReportRefDaoを取得
     * @return trnPosReportRefDao
     */
    public TrnPosReportRefDao getTrnPosReportRefDao() {
        return trnPosReportRefDao;
    }
    /**
     * trnPosReportRefDaoを設定
     * @param trnPosReportRefDao
     */
    public void setTrnPosReportRefDao(TrnPosReportRefDao trnPosReportRefDao) {
        this.trnPosReportRefDao = trnPosReportRefDao;
    }
}
