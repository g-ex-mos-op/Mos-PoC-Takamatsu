/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstTO;

/**
 * テイクオーバー履歴
 * @author xnkusama
 */
public interface MstTODao {

    public static final Class BEAN = MstTO.class;
    public static final String selectTO_ARGS = "companyCd, miseCd";
    public static final String deleteTO_ARGS = "companyCd, miseCd";

    /**
     * テイクオーバー履歴
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectTO(String companyCd, String miseCd);
    
    /**
     * テイクオーバー履歴の削除
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public int deleteTO(String companyCd, String miseCd);
    
    /**
     * テイクオーバー履歴の追加
     * @param MstTO entity
     */
    public int insertTO(MstTO entity);
    
}