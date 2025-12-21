/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UIMiseInfo;

/**
 * ユーザー対応店舗情報
 * 
 * 作成日:2010/05/18
 * @author xkinu
 *
 */
public interface UIMiseInfoDao {

    public static final Class BEAN = UIMiseInfo.class;
    public static final String select_ARGS  = "companyCd, miseCd";
    public static final String selectByUserMise_ARGS  = "userId, companyCd";

    /**
     * 店舗情報取得
     * 
     * @param companyCd [必須]
     * @param miseCd [null可]
     * @return
     */
    public List select(String companyCd, String miseCd);
    /**
     * ユーザー対応店舗情報取得
     * 
     * @param userId [必須]
     * @param companyCd [null可]
     * @return
     */
    public List selectByUserMise(String userId, String companyCd);
}
