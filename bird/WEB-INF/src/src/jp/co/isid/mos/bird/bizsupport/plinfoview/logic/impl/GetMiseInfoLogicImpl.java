package jp.co.isid.mos.bird.bizsupport.plinfoview.logic.impl;

import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.MstMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MstMiseInfo;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 店情報取得ロジック
 * @author xkhata
 *
 */
public class GetMiseInfoLogicImpl implements GetMiseInfoLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BBS006L04";

    private MstMiseInfoDao mstMiseInfoDao;
    
    public MstMiseInfo execute(String MiseCd, String companyCd)
            throws ApplicationException {
        /*店舗*/
        return getMstMiseInfoDao().getMiseInfo(companyCd,MiseCd);
    }
    
    public MstMiseInfoDao getMstMiseInfoDao() {
        return mstMiseInfoDao;
    }
    public void setMstMiseInfoDao(MstMiseInfoDao mstMiseInfoDao) {
        this.mstMiseInfoDao = mstMiseInfoDao;
    }


}
