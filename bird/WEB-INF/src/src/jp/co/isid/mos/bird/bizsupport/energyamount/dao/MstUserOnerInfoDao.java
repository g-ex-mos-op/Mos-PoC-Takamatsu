/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyamount.entity.MstUserOnerInfo;

/**
 * ユーザー対応オーナー情報
 * 
 * 作成日:2010/05/18
 * @author xkinu
 *
 */
public interface MstUserOnerInfoDao {

    public static final Class BEAN = MstUserOnerInfo.class;
    public static final String select_ARGS  = "userId, companyCd";

    /**
     * ユーザーオーナー情報取得
     * 
     * @param userId [必須]
     * @param companyCd [null可]
     * @return
     */
    public List select(String userId, String companyCd);
}
