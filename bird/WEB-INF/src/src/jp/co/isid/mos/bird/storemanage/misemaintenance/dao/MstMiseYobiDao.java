/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMiseYobi;

/**
 * 店舗拡張マスタ
 * @author xnkusama
 */
public interface MstMiseYobiDao {

    public static final Class BEAN = MstMiseYobi.class;
    public static final String selectMiseYobi_ARGS = "companyCd, miseCd";
    public static final String deleteMiseYobi_ARGS = "companyCd, miseCd";

    /**
     * 店舗拡張マスタの検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectMiseYobi(String companyCd, String miseCd);
    
    /**
     * 店舗拡張マスタの削除
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return int
     */
    public int deleteMiseYobi(String companyCd, String miseCd);
    
    /**
     * 店舗拡張マスタの挿入
     * @param entity
     * @return
     */
    public int insertMiseYobi(MstMiseYobi entity);
}