/*
 * ì¬“ú:2007/03/07
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.poserrorref.entity.TrnMiseHolidayInfo;

/**
 * “X•Ü‹x“ú—\’èî•ñDao
 * @author xkonishi
 *
 */
public interface TrnMiseHolidayInfoDao {
    public static final Class BEAN = TrnMiseHolidayInfo.class;
    
    public static final String select_ARGS ="companyCd, from, to";

    /**
     * “X•Ü‹x“ú—\’èî•ñæ“¾
     * @param companyCd ‰ïĞƒR[ƒh
     * @param from      ‘OŒ
     * @param to        “–Œ
     * @return
     */
    public List select(String companyCd, String from, String to);
}