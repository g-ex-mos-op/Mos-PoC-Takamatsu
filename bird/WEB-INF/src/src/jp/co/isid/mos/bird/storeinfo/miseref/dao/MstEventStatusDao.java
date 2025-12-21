package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstEventStatus;

/**
 * イベント実施状況
 * @author xnkusama
 */
public interface MstEventStatusDao {

    public static final Class BEAN = MstEventStatus.class;
    public static final String selectEvent_ARGS = "companyCd, miseCd";

    /**
     * イベント実施状況の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectEvent(String companyCd, String miseCd);
    
}