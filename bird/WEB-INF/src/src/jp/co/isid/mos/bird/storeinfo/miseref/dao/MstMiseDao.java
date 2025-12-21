package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstMise;

/**
 * 店統合マスタ
 * @author xnkusama
 */
public interface MstMiseDao {

    public static final Class BEAN = MstMise.class;
    public static final String selectMiseMst_ARGS = "companyCd, miseCd";
    public static final String selectMiseList_ARGS = "companyCd, onerCd";

    /**
     * 店統合マスタの検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectMiseMst(String companyCd, String miseCd);
    
    /**
     * 
     * 店リストの取得
     * @param String companyCd 会社コード
     * @param String onerCd    オーナーコード
     * @return List
     */
    public List selectMiseList(String companyCd, String onerCd);
}            
