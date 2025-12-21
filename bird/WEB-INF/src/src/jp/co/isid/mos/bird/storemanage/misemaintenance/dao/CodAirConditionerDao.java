/*
 * 作成日: 2011/07/08
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodAirConditioner;

/**
 * エアコン種別情報取得
 * 
 * 作成日:2011/07/08
 * @author xkinu
 *
 */
public interface CodAirConditionerDao {

    public static final Class BEAN = CodAirConditioner.class;

    /**
     * エアコン種別情報の検索
     * 
     * @return List
     */
    public List select();
    
}            
