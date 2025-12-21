/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstEventStatus;

/**
 * イベント実施状況
 * @author xnkusama
 */
public interface MstEventStatusDao {

    public static final Class BEAN = MstEventStatus.class;
    public static final String selectEvent_ARGS = "companyCd, miseCd";
    public static final String deleteEvent_ARGS = "companyCd, miseCd";

    /**
     * イベント実施状況の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectEvent(String companyCd, String miseCd);
    
    /**
     * イベント実施状況の削除
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return int
     */
    public int deleteEvent(String companyCd, String miseCd);
    
    /**
     * イベント実施状況の挿入
     * @param entity
     * @return
     */
    public int insertEvent(MstEventStatus entity);
}            
