package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstMiseKaiso;

/**
 * 店改装履歴
 * @author xnkusama
 */
public interface MstMiseKaisoDao {

    public static final Class BEAN = MstMiseKaiso.class;
    public static final String selectMiseKaiso_ARGS = "companyCd, miseCd";

    /**
     * 店改装履歴の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectMiseKaiso(String companyCd, String miseCd);
    
}