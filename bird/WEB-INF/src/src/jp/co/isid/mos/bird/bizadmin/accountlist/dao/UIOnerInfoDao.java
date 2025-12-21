/*
 * 作成日: 2008/12/03
 */
package jp.co.isid.mos.bird.bizadmin.accountlist.dao;

import jp.co.isid.mos.bird.bizadmin.accountlist.entity.UIOnerInfo;

/**
 * オーナー情報DAO
 * 
 * @author xnkusama
 */
public interface UIOnerInfoDao {

    public static final Class BEAN = UIOnerInfo.class;
    /**
     * オーナー情報取得
     */
    public static final String getOnerInfo_ARGS = "companyCd, onerCd, kizyunDt, userId";

    /**
     * オーナー情報取得
     * @param companyCd
     * @param onerCd
     * @param kizyunDt
     * @param userId
     * @return
     */
    public UIOnerInfo getOnerInfo(String companyCd, String onerCd, String kizyunDt, String userId);
    
}