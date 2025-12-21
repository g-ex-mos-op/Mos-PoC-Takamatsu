/*
 * 作成日: 2008/08/07
 */
package jp.co.isid.mos.bird.bizsupport.energyinputitem.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.entity.MstMeterKanri;

/**
 * 店舗メーター管理状況Dao
 * 
 * @author xnkusama
 */
public interface MstMeterKanriDao {

    public static final Class BEAN = MstMeterKanri.class;
    public static final String selectInputItem_ARGS = "companyCd, meterKbn, sibuCd, sysDate";
    public static final String updateInputItem_PERSISTENT_PROPS = "electricFlg, powerFlg, gasFlg, waterFlg, lastUser, lastPgm";    
    /**
     * 管理状況の取得
     * @param companyCd
     * @param meterKbn
     * @param sibuCd
     * @param sysDate
     * @return List
     */
    public List selectInputItem(String companyCd, 
                                 String meterKbn,
                                 String sibuCd,
                                 String sysDate);
    
    /**
     * 管理状況の更新
     * @param entity
     * @return int
     */
    public int updateInputItem(MstMeterKanri entity);
}