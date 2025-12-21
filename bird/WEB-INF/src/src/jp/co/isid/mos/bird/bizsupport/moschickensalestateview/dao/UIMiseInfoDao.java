/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UIMiseInfo;

/**
 * @author xlee
 *
 */
public interface UIMiseInfoDao {

    public static final Class BEAN = UIMiseInfo.class;

    public static final String getHonbuUserMiseInfo_ARGS = "companyCd, onerCd";
    public static final String getOnerUserMiseInfo_ARGS  = "companyCd, userId";
    public static final String getTenpoUserMiseInfo_ARGS = "companyCd, userId";

    /**
     * 店舗取得(本部ユーザー)
     * @param companyCd
     * @param onerCd
     * @return
     */
    public List getHonbuUserMiseInfo(String companyCd, String onerCd);
    /**
     * 店舗取得（オーナーユーザー）
     * @param companyCd
     * @param userId
     * @return
     */
    public List getOnerUserMiseInfo(String companyCd, String userId);
    /**
     * 店舗取得（店舗ユーザー）
     * @param companyCd
     * @param userId
     * @return
     */
    public List getTenpoUserMiseInfo(String companyCd, String userId);

}
