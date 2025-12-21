package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstChintai;


/**
 * 賃貸店舗履歴
 * @author xnkusama
 */
public interface MstChintaiDao {

    public static final Class BEAN = MstChintai.class;
    public static final String selectChintai_ARGS = "companyCd, miseCd";

    /**
     * 賃貸店舗履歴の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectChintai(String companyCd, String miseCd);
    
}