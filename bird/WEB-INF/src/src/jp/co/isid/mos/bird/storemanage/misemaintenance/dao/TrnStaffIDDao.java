/*
 * 作成日: 2006/04/05
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.TrnStaffID;

/**
 * スタッフID採番
 * @author xylee
 */
public interface TrnStaffIDDao {

    public static final Class BEAN = TrnStaffID.class;
    public static final String updateStaffID_ARGS = "entity";
    /**
     * スタッフIDMAXの検索
     * @return List
     */
    public List selectStaffID();
    
    /**
     * スタッフID採番情報の更新
     * @param TrnStaffID entity
     * @return int
     */
    public int updateStaffID(TrnStaffID entity);

}            
