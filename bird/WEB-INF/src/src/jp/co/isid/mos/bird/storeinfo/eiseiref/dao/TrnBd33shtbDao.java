/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.eiseiref.entity.TrnBd33shtb;

/**
 * DAO【PDF店舗衛生情報】
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public interface TrnBd33shtbDao {
	/** エンティティクラス */
    public static final Class BEAN = TrnBd33shtb.class;
    /** パラメータ：検索 */
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, miseCd, nendo";
    /** パラメータ：検索店情報 */
    public static final String selectMiseInfo_ARGS = "userId, userTypeCd, limitFlg, companyCd, miseCd, nendo";
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
    /**
     * 検索店情報処理
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param miseCd
     * @param nendo
     * @return
     */
    public List selectMiseInfo(
    		String userId, String userTypeCd, boolean limitFlg
    	  , String companyCd, String miseCd, String nendo);
}
