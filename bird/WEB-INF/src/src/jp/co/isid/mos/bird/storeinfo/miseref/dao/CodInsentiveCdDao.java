package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.CodInsentiveCd;


/**
 * インセンティブコード
 * @author xnkusama
 */
public interface CodInsentiveCdDao {

    public static final Class BEAN = CodInsentiveCd.class;
    public static final String selectInsentive_ARGS = "companyCd";

    /**
     * インセンティブコードの検索
     * @param String companyCd 会社コード
     * @return List
     */
    public List selectInsentive(String companyCd);
    
}