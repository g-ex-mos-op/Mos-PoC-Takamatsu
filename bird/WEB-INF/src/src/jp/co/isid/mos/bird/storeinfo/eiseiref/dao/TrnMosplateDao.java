/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.eiseiref.entity.TrnMosplate;

/**
 * DAO【モスプレート検査】
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public interface TrnMosplateDao {
	/** エンティティクラス */
    public static final Class BEAN = TrnMosplate.class;
    /** パラメータ：検索 */
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, miseCd, nendo";
    /**
     * 検索処理
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param miseCd
     * @param nendo
     * @return
     */
    public List select(
    		String userId, String userTypeCd, boolean limitFlg
    	  , String companyCd, String miseCd, String nendo);
}
