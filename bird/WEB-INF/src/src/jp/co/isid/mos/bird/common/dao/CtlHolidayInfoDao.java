package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.CtlHolidayInfo;

/**
 * j“úæ“¾ˆ—
 * @author inazawa
 * 2007/02/27
 */
public interface CtlHolidayInfoDao {
    public Class BEAN = CtlHolidayInfo.class;
    public static final String getHoliday_ARGS = "date";
    
    
    /**
     * j“ú‚Ìæ“¾
     * @param regDate   “ú•t‚¯
     * @return
     */
    public List getHoliday(String date);
}
