/*
 * 作成日: 2006/04/06
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MSTUserMiseInfo;

/**
 * ユーザー対応店舗情報(MSTUserOnerMiseInfoDao)
 * @author xnkusama
 */
public interface MSTUserMiseInfoDao {

    public static final Class BEAN = MSTUserMiseInfo.class;
    public static final String getMiseCd_ARGS  = "companyCd, onerCd, sysDate";

    /**
     * ユーザー対応店舗情報の取得(getMiseCd)
     * @param companyCd
     * @param onerCd
     * @return List 検索結果
     */
    public List getMiseCd (String companyCd, String onerCd, String sysDate);
}