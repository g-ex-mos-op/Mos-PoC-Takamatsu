package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity.CodShokuhouzaiList;

/**
 * モスチキン予約状況情報Dao
 * 
 * @author xkinu
 *
 */
public interface CodShokuhouzaiListDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = CodShokuhouzaiList.class;
    
    /**
     * 検索する時のパラメータ
     */
    public static final String select_ARGS = "ckanriNo";
    /**
     * 検索
     * 
     * @param ckanriNo
     * @return
     */
    public List select(String ckanriNo);
}
