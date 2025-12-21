/*
 * 作成日:2007/01/15
 */
package jp.co.isid.mos.bird.entry.ownerchange.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.ownerchange.entity.MstMiseInfo;
/**
 * 店舗情報Dao
 * @author xkonishi
 *
 */
public interface MstMiseInfoDao {
    
    public static final Class BEAN = MstMiseInfo.class;
    
    public static final String select_ARGS = "companyCd, onerCd, sysDate";
    
    /**
     * 店舗情報検索
     * @param 会社コード
     * @param オーナーコード
     * @param システム日付
     * @return 店舗情報
     */
    public List select(String companyCd, String onerCd, String sysDate);    
}