/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.eiseiref.entity.TrnBd34vstb;

/**
 * DAO【PDF訪店報告書】
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public interface TrnBd34vstbDao {
	/** エンティティクラス */
    public static final Class BEAN = TrnBd34vstb.class;
    /** パラメータ：検索 */
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, miseCd, nendo, statusCd";
    /**
     * 検索処理
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param miseCd
     * @param nendo
     * @param statusCd
     * @return
     */
    public List select(
    		String userId, String userTypeCd, boolean limitFlg
    	  , String companyCd, String miseCd, String nendo, String statusCd);
}
