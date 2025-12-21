/*
 * 作成日: 2008/12/08
 */
package jp.co.isid.mos.bird.config.scheduleregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.scheduleregist.entity.CtlSchedule;

/**
 * スケジュール管理DaoDao
 * 
 * @author xnkusama
 */
public interface CtlScheduleDao {

    public static final Class BEAN = CtlSchedule.class;
    public static final String selectScheduleList_ARGS = "taishoCd, companyCd, kikanFrom, kikanTo";
    public static final String getMaxScheduleId_ARGS = "taishoCd, companyCd, taishoDt";

    /**
     * スケジュール取得
     * @param taishoCd
     * @param companyCd
     * @param kikanFrom
     * @param kikanTo
     * @return
     */
    public List selectScheduleList(String taishoCd, String companyCd, String kikanFrom, String kikanTo);
    
    /**
     * 最大スケジュールID取得
     * @param taishoCd
     * @param companyCd
     * @param taishoDt
     * @return
     */
    public String getMaxScheduleId(String taishoCd, String companyCd, String taishoDt);
    
    /**
     * 新規登録
     * @param entity
     */
    public void insertSchedule(CtlSchedule entity);
    
    /**
     * 更新登録
     * @param entity
     */
    public void updateScheduleList(CtlSchedule entity);
    
    /**
     * 削除
     * @param sumMenuCd
     */
    public void updateScheduleListSakujoFlg(CtlSchedule entity);
    
}