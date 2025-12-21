package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstMiseYobi;

/**
 * 店舗拡張マスタ
 * @author xnkusama
 */
public interface MstMiseYobiDao {

    public static final Class BEAN = MstMiseYobi.class;
    public static final String selectMiseYobi_ARGS = "companyCd, miseCd";

    /**
     * 店舗拡張マスタの検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectMiseYobi(String companyCd, String miseCd);
    
}