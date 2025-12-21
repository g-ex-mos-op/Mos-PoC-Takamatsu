/*
 * ì¬“ú: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferMiseInfo;

/**
 * @author xlee
 *
 */
public interface UIOfferMiseInfoDao {
    
    public static final Class BEAN = UIOfferMiseInfo.class;
    
    public static final String getOfferMiseInfo_ARGS = "companyCd, onerCd, sysDate";
    
    /**
     * “X•Üæ“¾
     * @param companyCd
     * @param onerCd
     * @param sysDate
     * @return
     */
    public List getOfferMiseInfo(String companyCd, String onerCd, String sysDate);
}
