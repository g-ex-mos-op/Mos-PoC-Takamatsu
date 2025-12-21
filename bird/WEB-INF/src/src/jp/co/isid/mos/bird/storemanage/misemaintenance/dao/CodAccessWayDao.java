/*
 * ì¬“ú: 2006/02/28
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodAccessWay;

/**
 * Œğ’Êè’iî•ñæ“¾
 * @author kusama
 */
public interface CodAccessWayDao {

    public static final Class BEAN = CodAccessWay.class;

    /**
     * Œğ’Êè’iî•ñ‚ÌŒŸõ
     * @return List
     */
    public List selectAccessWay();
    
}            
