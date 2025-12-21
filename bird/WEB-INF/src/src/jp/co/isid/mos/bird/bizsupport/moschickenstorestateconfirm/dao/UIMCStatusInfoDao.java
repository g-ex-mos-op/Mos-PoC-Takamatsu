package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity.UIMCStatusInfo;

/**
 * モスチキン予約状況情報Dao
 * 
 * @author xkinu
 *
 */
public interface UIMCStatusInfoDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = UIMCStatusInfo.class;
    
    /**
     * 検索する時のパラメータ
     */
    public static final String select_ARGS = "companyCd, ckanriNo, shokuCd, sibuCd, dateFrom, dateTo, sysDate";
    
    /**
     * 検索
     * 
     * @param companyCd
     * @param ckanriNo
     * @param shokuCd
     * @param sibuCd
     * @param dateFrom
     * @param dateTo
     * @param sysDate
     * @return
     */
    public List select(String companyCd, String ckanriNo, String shokuCd, String sibuCd, String dateFrom, String dateTo, String sysDate);
}
