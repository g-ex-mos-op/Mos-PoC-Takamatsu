/*
 * 作成日:2007/02/19
 */
package jp.co.isid.mos.bird.storemanage.staffregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.staffregist.entity.TrnEntryData;
/**
 * エントリー状況Dao
 * @author inazawa
 *
 */
public interface TrnEntryDataDao {
    
    public static final Class BEAN = TrnEntryData.class;
    
    public static final String selectByMastarLicense_ARGS = "staffId, sysDate";
    
    public static final String selectByTraining_ARGS = "staffId, sysDate";
    
    /**
     * マスタライセンスエントリー状況検索
     * @param スタッフID
     * @param システム日付
     * @return エントリー状況リスト
     */
    public List selectByMastarLicense(String staffId, String sysDate);
    
    /**
     * 研修エントリー状況検索
     * @param スタッフID
     * @param システム日付
     * @return エントリー状況リスト
     */
    public List selectByTraining(String staffId, String sysDate);   
}