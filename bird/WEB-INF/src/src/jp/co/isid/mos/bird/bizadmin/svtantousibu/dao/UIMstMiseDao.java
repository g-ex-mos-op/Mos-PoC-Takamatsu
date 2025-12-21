/*
 * 作成日: 200/04/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.dao;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.UIMstMise;

/**
 *　店情報DAO
 * 
 * @author xnkusama
 */
public interface UIMstMiseDao {

    public static final Class BEAN = UIMstMise.class;

    public static final String getMiseInfo_ARGS = "companyCd, miseCd";
    
    /**
     * 店情報の取得
     * @param String 会社コード
     * @param String 店コード
     * @return UIMstMise
     */
    public UIMstMise getMiseInfo(String companyCd, String miseCd);
}
