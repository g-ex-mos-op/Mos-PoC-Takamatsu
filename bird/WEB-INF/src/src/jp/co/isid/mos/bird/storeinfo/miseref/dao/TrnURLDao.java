package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.TrnURL;

/**
 * 地図URL情報
 * @author xnkusama
 */
public interface TrnURLDao {

    public static final Class BEAN = TrnURL.class;
    public static final String selectMapURL_ARGS = "companyCd, miseCd";

    /**
     * 地図URL情報の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectMapURL(String companyCd, String miseCd);
    
}