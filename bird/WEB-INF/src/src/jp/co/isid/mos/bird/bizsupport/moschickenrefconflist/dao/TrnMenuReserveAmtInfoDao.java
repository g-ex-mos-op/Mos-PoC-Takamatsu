/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao;


import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.TrnMosChikenDet;

/**
 * 対象SEQモスチキン予約明細情報取得Dao
 * 
 * @author xkinu
 */
public interface TrnMenuReserveAmtInfoDao {
    
    public static final Class BEAN = TrnMosChikenDet.class;

    /** 対象SEQモスチキン予約明細情報取得引数 */
    public static final String select_ARGS = "ckanriNo, companyCd, miseCd, seqNo";
    /**
     * 対象SEQモスチキン予約明細情報取得
     * @param
     * @return
     */
    public List select(String ckanriNo, String compnayCd, String miseCd, int seqNo);
}
