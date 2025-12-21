package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstSB;

/**
 * スクラップビルド履歴
 * @author xnkusama
 */
public interface MstSBDao {

    public static final Class BEAN = MstSB.class;
    public static final String selectSB_ARGS = "companyCd, miseCd";

    /**
     * スクラップビルド履歴の取得
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectSB(String companyCd, String miseCd);
    
}