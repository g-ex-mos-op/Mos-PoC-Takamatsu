/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMiseKaiso;

/**
 * 店改装履歴
 * @author xnkusama
 */
public interface MstMiseKaisoDao {

    public static final Class BEAN = MstMiseKaiso.class;
    public static final String selectMiseKaiso_ARGS = "companyCd, miseCd";
    public static final String deleteMiseKaiso_ARGS = "companyCd, miseCd";

    /**
     * 店改装履歴の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectMiseKaiso(String companyCd, String miseCd);
    
    /**
     * 店改装履歴の削除
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return int
     */
    public int deleteMiseKaiso(String companyCd, String miseCd);
    
    /**
     * 店改装履歴の挿入
     * @param entity
     * @return
     */
    public int insertMiseKaiso(MstMiseKaiso entity);
}