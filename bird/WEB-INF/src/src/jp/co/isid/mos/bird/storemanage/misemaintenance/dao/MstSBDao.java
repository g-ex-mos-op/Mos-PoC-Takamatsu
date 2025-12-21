/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstSB;

/**
 * スクラップビルド履歴
 * @author xnkusama
 */
public interface MstSBDao {

    public static final Class BEAN = MstSB.class;
    public static final String selectSB_ARGS = "companyCd, miseCd";
    public static final String deleteSB_ARGS = "companyCd, miseCd";

    /**
     * スクラップビルド履歴の取得
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectSB(String companyCd, String miseCd);
    
    /**
     * スクラップビルド履歴の削除
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public int deleteSB(String companyCd, String miseCd);
    
    /**
     * スクラップビルド履歴の追加
     * @param MstSB entity
     */
    public int insertSB(MstSB entity);
    
}