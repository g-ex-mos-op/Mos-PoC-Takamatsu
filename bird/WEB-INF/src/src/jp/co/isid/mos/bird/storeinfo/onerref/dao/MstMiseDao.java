/*
 * 作成日: 2006/07/07
 *
 */
package jp.co.isid.mos.bird.storeinfo.onerref.dao;

import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstMise;


/**
 * グループ統合店マスタの検索（MstMiseDao）
 * @author itamoto
 */
public interface MstMiseDao {

    public static final Class BEAN = MstMise.class;
    public static final String selectMiseMst_ARGS  = "companyCd, miseCd";

    /**
     * グループ統合オーナーマスタの検索(selectMstMise)
     */
    public MstMise selectMiseMst(String companyCd, String miseCd);
}
