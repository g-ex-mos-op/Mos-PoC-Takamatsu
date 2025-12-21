/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao;


import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.TrnMosChikenInfo;

/**
 * モスチキン予約情報取得entity
 * 
 * @author inazawa
 */
public interface TrnMosChickenInfoDao {
    
    public static final Class BEAN = TrnMosChikenInfo.class;
    public static final String getMosChickenInfo_ARGS = "ckanriNo, miseCd,companyCd"
    		+",reserveDt,reserveFrom,reserveTo"
    		+", premiumFlg, paymentFlg";
    /**
     * モスチキン予約情報取得
     * 
     * @param ckanriNo
     * @param miseCd
     * @param companyCd
     * @param reserveDt
     * @param reserveFrom
     * @param reserveTo
     * @param premiumFlg
     * @param paymentFlg
     * @return
     */
    public List getMosChickenInfo(String ckanriNo, String miseCd
    		,String companyCd, String reserveDt
    		,String reserveFrom
    		,String reserveTo
    		,String premiumFlg
    		,String paymentFlg);
 
    
}
