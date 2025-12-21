package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstTO;

/**
 * テイクオーバー履歴
 * @author xnkusama
 */
public interface MstTODao {

    public static final Class BEAN = MstTO.class;
    public static final String selectTO_ARGS = "companyCd, miseCd";

    /**
     * テイクオーバー履歴
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectTO(String companyCd, String miseCd);
    
}