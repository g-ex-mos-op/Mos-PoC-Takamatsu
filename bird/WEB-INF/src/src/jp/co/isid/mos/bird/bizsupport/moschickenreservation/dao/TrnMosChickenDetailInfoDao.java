/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao;


import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenDet;

/**
 * モスチキン予約明細情報取得entity
 * 
 * @author inazawa
 */
public interface TrnMosChickenDetailInfoDao {
    
    public static final Class BEAN = TrnMosChikenDet.class;
    public static final String getMosChickenDetInfo_ARGS = "ckanriNo, companyCd,miseCd, seqNo";
    public static final String getMosChickenMstMenuDetInfo_ARGS = "ckanriNo, companyCd,miseCd,seqNo";
    public static final String updateMosChickenDetInfo_PERSISTENT_PROPS 
        = "ckanriNo, companyCd, miseCd, menuCd, seqNo, reserveAmt, lastUser, lastPgm";
    public static final String deleteMosChickenDetInfo_PERSISTENT_PROPS 
        = "ckanriNo, companyCd, miseCd, menuCd, seqNo";
   /**
     * モスチキン予約明細情報取得
     * @param
     * @return
     */
    public List getMosChickenDetInfo(String ckanriNo, String compnayCd,String miseCd, int seqNo);
    /**
     * モスチキン予約明細情報(マスタ商品)取得
     * @param
     * @return
     */
    public List getMosChickenMstMenuDetInfo(String ckanriNo, String compnayCd,String miseCd,int seqNo);
    /**
     * モスチキン予約明細情報登録
     * @param entity
     * @return
     */
    public int insertMosChickenDetInfo(TrnMosChikenDet entity);
    /**
     * モスチキン予約明細情報更新
     * @param entity
     * @return
     */
    public int updateMosChickenDetInfo(TrnMosChikenDet entity);

    /**
     * モスチキン予約明細情報削除
     * @param entity モスチキン予約明細情報
     */
    public void deleteMosChickenDetInfo(TrnMosChikenDet entity);
}
