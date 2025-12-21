package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstGyotai;

/**
 * 業態履歴
 * @author xnkusama
 */
public interface MstGyotaiDao {

    public static final Class BEAN = MstGyotai.class;
    public static final String selectGyotai_ARGS = "companyCd, miseCd";

    /**
     * 業態履歴の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectGyotai(String companyCd, String miseCd);
    
}            
