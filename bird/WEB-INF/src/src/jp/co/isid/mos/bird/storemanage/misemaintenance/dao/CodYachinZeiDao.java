/*
 * ì¬“ú: 2006/02/28
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodYachinZei;

/**
 * ‰Æ’ÀÅ‹æ•ªî•ñæ“¾
 * @author kusama
 */
public interface CodYachinZeiDao {

    public static final Class BEAN = CodYachinZei.class;

    /**
     * ‰Æ’ÀÅ‹æ•ªî•ñ‚ÌŒŸõ
     * @return List
     */
    public List selectYachinZei();
    
}            
