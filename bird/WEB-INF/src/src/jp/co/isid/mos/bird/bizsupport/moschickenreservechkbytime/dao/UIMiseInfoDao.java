/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIMiseInfo;

/**
 * @author xlee
 *
 */
public interface UIMiseInfoDao {
    
    public static final Class BEAN = UIMiseInfo.class;
    
    public static final String getHonbuUserMiseInfo_ARGS = "companyCd, onerCd, sysDate";
    public static final String getOnerUserMiseInfo_ARGS  = "companyCd, userId, sysDate";
    public static final String getTenpoUserMiseInfo_ARGS = "companyCd, userId, sysDate";
    
    /**
     * 店舗取得(本部ユーザー)
     * @param companyCd
     * @param onerCd
     * @param fromDt
     * @param toDt
     * @return
     */
    public List getHonbuUserMiseInfo(String companyCd, String onerCd, String sysDate);
    /**
     * 店舗取得（オーナーユーザー）
     * @param companyCd
     * @param userId
     * @param fromDt
     * @param toDt
     * @return
     */
    public List getOnerUserMiseInfo(String companyCd, String userId, String sysDate);
    /**
     * 店舗取得（店舗ユーザー）
     * @param companyCd
     * @param userId
     * @param fromDt
     * @param toDt
     * @return
     */
    public List getTenpoUserMiseInfo(String companyCd, String userId, String sysDate);
    
}
