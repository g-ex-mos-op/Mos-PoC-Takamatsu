/*
 * 作成日: 2008/06/20
 */
package jp.co.isid.mos.bird.storemanage.claimtotal.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.claimtotal.entity.CodBunruiM;

/**
 * 中分類一覧Dao
 * 
 * @author xnkusama
 */
public interface CodBunruiMDao {

    public static final Class BEAN = CodBunruiM.class;
    
    public static final String select_ARGS = "typeCd";
    /**
     * 中分類一覧の取得
     * @param typeCd LIKE文で使用するため、「%」を付加した状態で指定
     * @return List
     */
    public List select(String typeCd);
    
}