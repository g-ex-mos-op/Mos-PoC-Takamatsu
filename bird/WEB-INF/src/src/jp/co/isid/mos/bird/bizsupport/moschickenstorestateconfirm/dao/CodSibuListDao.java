package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity.CodSibuList;

/**
 * モスチキン予約状況情報 
 * 対象支部情報Dao
 * 
 * @author xkinu
 *
 */
public interface CodSibuListDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = CodSibuList.class;
    
    /**
     * 検索する時のパラメータ
     */
    public static final String select_ARGS = "companyCd, userId, limitFlg";
    /**
     *  検索
     * @param companyCd
     * @param userId
     * @param limitFlg
     * @return
     */
    public List select(String companyCd, String userId, boolean limitFlg);
}
