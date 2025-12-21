/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao;


import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.TrnMosChikenDet;

/**
 * モスチキン予約明細情報取得entity
 * 
 * @author inazawa
 */
public interface TrnMosChickenDetailInfoDao {
    
    public static final Class BEAN = TrnMosChikenDet.class;
    public static final String getMosChickenDetInfo_ARGS = "ckanriNo, companyCd,miseCd, seqNo";
    public static final String getMosChickenMstMenuDetInfo_ARGS = "ckanriNo, companyCd,miseCd, seqNo";
    public static final String getTotalMoney_ARGS = "ckanriNo, companyCd,miseCd, seqNo";
    public static final String getMosChickenInfoReservAmtInfo_ARGS = "ckanriNo,miseCd,companyCd,seqNo";
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
     * モスチキン未払い時の金額取得
     * @param
     * @return
     */
    public List getTotalMoney(String ckanriNo, String compnayCd,String miseCd, int seqNo);
    /**
     * 登録マスタ用商品取得処理(予約数)
     * @param ckanriNo
     * @return
     */
    public List getMosChickenReserveAmtInfo(String ckanriNo,String miseCd,String companyCd,int seqNo);

}
