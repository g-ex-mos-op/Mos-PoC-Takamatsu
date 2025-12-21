package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstBukken;

/**
 * 物件情報履歴
 * @author xnkusama
 */
public interface MstBukkenDao {

    public static final Class BEAN = MstBukken.class;
    public static final String selectBukken_ARGS = "companyCd, miseCd";

    /**
     * 物件情報履歴の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectBukken(String companyCd, String miseCd);
    
}