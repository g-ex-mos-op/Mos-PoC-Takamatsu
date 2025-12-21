package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstInsentive;


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
