/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao;


import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenInfo;

/**
 * モスチキン予約情報取得entity
 * 
 * @author inazawa
 */
public interface TrnMosChickenInfoDao {
    
    public static final Class BEAN = TrnMosChikenInfo.class;
    public static final String getMosChickenInfo_ARGS = "ckanriNo, miseCd,companyCd,reserveDt";
    public static final String getMaxSeqNo_ARGS = "ckanriNo, miseCd,companyCd";
    public static final String insertMosChickenInfo_ARGS = "entity";
    public static final String updateMosChickenInfo_ARGS = "entity";
    public static final String updateMosChickenInfoCancel_ARGS = "entity";
    public static final String updateMosChickenInfo___PERSISTENT_PROPS 
    = "ckanriNo, companyCd, miseCd, seqNo, reserveDt, acceptDt, reserveHh, reserveMm,remark,paymentFlg,memo,cancelFlg,cancelDt,lastUser, lastPgm, premiumFlg";
    /**
     * モスチキン予約情報取得
     * @param ckanriNo
     * @param miseCd
     * @param reserveDt
     * @param companyCd
     * @return
     */
    public List getMosChickenInfo(String ckanriNo, String miseCd, String companyCd,String reserveDt);
    /**
     * モスチキン予約情報取得
     * @param ckanriNo
     * @param miseCd
     * @param reserveDt
     * @param companyCd
     * @return
     */
    public int getMaxSeqNo(String ckanriNo, String miseCd,String companyCd);
    /**
     * モスチキン予約情報登録
     * @param entity
     * @return
     */
    public int insertMosChickenInfo(TrnMosChikenInfo entity);
    /**
     * モスチキン予約情報更新
     * @param entity
     * @return
     */
    public int updateMosChickenInfo(TrnMosChikenInfo entity);
    /**
     * モスチキン予約情報キャンセル更新
     * @param entity
     * @return
     */
    public int updateMosChickenInfoCancel(TrnMosChikenInfo entity);
 
    
}
