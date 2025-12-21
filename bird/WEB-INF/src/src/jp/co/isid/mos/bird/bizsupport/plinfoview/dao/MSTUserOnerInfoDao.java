/*
 * 作成日: 2006/04/06
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MSTUserOnerInfo;

/**
 * ユーザー対応オーナー情報(MSTUserOnerInfoDao)
 * @author xnkusama
 */
public interface MSTUserOnerInfoDao {

    public static final Class BEAN = MSTUserOnerInfo.class;
    public static final String getOnerCd_ARGS  = "companyCd, userId";

    /**
     * オーナーコードの取得(getOnerCd)
     * @param companyCd 企業コード
     * @param userId ユーザーID
     * @return List 検索結果
     */
    public List getOnerCd (String companyCd, String userId);
}
