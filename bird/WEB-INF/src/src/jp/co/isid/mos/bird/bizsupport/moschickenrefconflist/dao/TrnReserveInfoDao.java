/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao;


import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.TrnReserveInfo;

/**
 * 予約販売情報Dao
 * 
 * @author xkinu
 */
public interface TrnReserveInfoDao {
    /** Entityクラス：予約販売情報Entityクラス */
    public static final Class BEAN = TrnReserveInfo.class;
    /** 検索処理用パラメーター */
    public static final String select_ARGS = "ckanriNo, miseCd,companyCd"
		+",reserveDt,reserveFrom,reserveTo"
		+", premiumFlg, paymentFlg";
    /**
     * 検索処理
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
    public List select(String ckanriNo, String miseCd
    		,String companyCd, String reserveDt
    		,String reserveFrom
    		,String reserveTo
    		,String premiumFlg
    		,String paymentFlg);
 
    
}
