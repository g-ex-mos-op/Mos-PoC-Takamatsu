/*
 * ì¬“ú: 2006/02/28
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodSmoke;

/**
 * •ª‰Œí•Êî•ñæ“¾
 * @author kusama
 */
public interface CodSmokeDao {

    public static final Class BEAN = CodSmoke.class;

    /**
     * •ª‰Œí•Êî•ñ‚ÌŒŸõ
     * @return List
     */
    public List selectSmoke();
    
}            
