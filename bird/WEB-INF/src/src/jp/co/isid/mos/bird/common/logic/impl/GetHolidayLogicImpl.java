package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.CtlHolidayInfoDao;
import jp.co.isid.mos.bird.common.logic.GetHolidayLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

public class GetHolidayLogicImpl implements GetHolidayLogic{
    
    /*Dao[j“úæ“¾]**/
    private CtlHolidayInfoDao ctlHolidayInfoDao;
    /**
     * jÕ“ú–¼Ìæ“¾
     * @param “ú•t
     * @return jÕ“ú–¼Ì
     * @exception ApplicationException
     */
    public List getHoliday(String date){
        
        return getCtlHolidayInfoDao().getHoliday(date);
    }
    /**
     * holidayInfoDao‚ğæ“¾
     * @return holidayInfoDao
     */
    public CtlHolidayInfoDao getCtlHolidayInfoDao() {
        return ctlHolidayInfoDao;
    }
    /**
     * holidayInfoDao‚ğİ’è
     * @param holidayInfoDao
     */
    public void setCtlHolidayInfoDao(CtlHolidayInfoDao holidayInfoDao) {
        ctlHolidayInfoDao = holidayInfoDao;
    }
}
