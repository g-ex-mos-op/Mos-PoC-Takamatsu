/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstChintai;

/**
 * 賃貸店舗履歴
 * @author xnkusama
 */
public interface MstChintaiDao {

    public static final Class BEAN = MstChintai.class;
    public static final String selectChintai_ARGS = "companyCd, miseCd";
    public static final String deleteChintai_ARGS = "companyCd, miseCd";

    /**
     * 賃貸店舗履歴の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectChintai(String companyCd, String miseCd);
    
    /**
     * 賃貸店舗履歴の削除
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public int deleteChintai(String companyCd, String miseCd);
    
    /**
     * 賃貸店舗履歴の追加
     * @param MstChintai entity
     */
    public int insertChintai(MstChintai entity);
    
}            
