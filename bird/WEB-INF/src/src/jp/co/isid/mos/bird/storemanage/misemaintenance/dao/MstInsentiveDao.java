/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstInsentive;


/**
 * インセンティブ履歴
 * @author xnkusama
 */
public interface MstInsentiveDao {

    public static final Class BEAN = MstInsentive.class;
    public static final String selectInsentiveRireki_ARGS = "companyCd, miseCd";

    /**
     * インセンティブ履歴の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectInsentiveRireki(String companyCd, String miseCd);
    
}            
