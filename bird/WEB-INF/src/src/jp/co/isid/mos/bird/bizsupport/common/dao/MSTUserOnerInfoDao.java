/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.common.dao;

import jp.co.isid.mos.bird.bizsupport.plregist.entity.MSTUserOnerInfo;

/**
 * ユーザー対応オーナー情報(MSTUserOnerInfoDao)
 * @author itamoto
 */
public interface MSTUserOnerInfoDao {

    public static final Class BEAN = MSTUserOnerInfo.class;
    public static final String getOnerCd_ARGS  = "USER_ID";

    /**
     * オーナーコードの取得(getOnerCd)
     * @param userIdユーザーID
     * @return List 検索結果
     */
    public MSTUserOnerInfo getOnerCd (String userId);
}
