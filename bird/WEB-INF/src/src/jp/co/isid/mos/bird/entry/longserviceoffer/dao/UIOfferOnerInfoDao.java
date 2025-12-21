/*
 * ì¬“ú: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.dao;

import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferOner;

/**
 * @author xlee
 *
 */
public interface UIOfferOnerInfoDao {
    
    public static final Class BEAN = UIOfferOner.class;
    
    public static final String getOfferOnerInfo_ARGS = "companyCd, onerCd";
    
    /**
     * “X•Üæ“¾
     * @param companyCd
     * @param onerCd
     * @param sysDate
     * @return
     */
    public UIOfferOner getOfferOnerInfo(String companyCd, String onerCd);
}
