/*
 * ì¬“ú: 2006/3/8
 *
 */
package jp.co.isid.mos.bird.storeinfo.onerref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstKyoei;

/**
 * ‹¤‰h‰ï–ğˆõÀÑiMstKyoeiDaoj
 * @author itamoto
 */
public interface MstKyoeiDao {

    public static final Class BEAN = MstKyoei.class;
    public static final String selectKyoei_ARGS  = "COMPANY_CD, ONER_CD";

    /**
     * ‹¤‰h‰ï–ğˆõÀÑ‚ÌŒŸõ(selectKyoei)
     */
    public List selectKyoei(String CompanyCd, String OnerCd);
}
