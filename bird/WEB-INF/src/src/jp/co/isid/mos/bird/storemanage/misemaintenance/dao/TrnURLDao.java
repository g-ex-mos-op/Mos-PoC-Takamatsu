/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.TrnURL;

/**
 * 地図URL情報
 * @author xnkusama
 */
public interface TrnURLDao {

    public static final Class BEAN = TrnURL.class;
    public static final String selectMapURL_ARGS = "companyCd, miseCd";
    public static final String deleteMapURL_ARGS = "companyCd, miseCd";

    /**
     * 地図URL情報の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectMapURL(String companyCd, String miseCd);
    
    /**
     * 地図URL情報の削除
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return int
     */
    public int deleteMapURL(String companyCd, String miseCd);
    
    /**
     * 地図URL情報の挿入
     * @param entity
     * @return
     */
    public int insertMapURL(TrnURL entity);
}            
