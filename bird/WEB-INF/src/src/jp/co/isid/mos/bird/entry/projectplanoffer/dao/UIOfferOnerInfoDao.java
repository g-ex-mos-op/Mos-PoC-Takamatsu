/*
 * çÏê¨ì˙: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dao;

import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferOnerInfo;

/**
 * @author xlee
 *
 */
public interface UIOfferOnerInfoDao {
    
    public static final Class BEAN = UIOfferOnerInfo.class;
    
    public static final String getOfferOnerInfo_ARGS = "companyCd, onerCd";
    
    /**
     * ìXï‹éÊìæ
     * @param companyCd
     * @param onerCd
     * @param sysDate
     * @return
     */
    public UIOfferOnerInfo getOfferOnerInfo(String companyCd, String onerCd);
}
