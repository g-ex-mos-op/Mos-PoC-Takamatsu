/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstBukken;

/**
 * 物件情報履歴
 * @author xnkusama
 */
public interface MstBukkenDao {

    public static final Class BEAN = MstBukken.class;
    public static final String selectBukken_ARGS = "companyCd, miseCd";
    public static final String deleteBukken_ARGS = "companyCd, miseCd";

    /**
     * 物件情報履歴の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectBukken(String companyCd, String miseCd);
    
    /**
     * 物件情報履歴の削除
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public int deleteBukken(String companyCd, String miseCd);
    
    /**
     * 物件情報履歴の追加
     * @param MstBukken entity
     */
    public int insertBukken(MstBukken entity);
    
}