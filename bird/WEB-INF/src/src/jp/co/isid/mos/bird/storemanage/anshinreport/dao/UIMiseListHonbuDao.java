/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.anshinreport.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIMiseList;

/**
 * オーナーマスタDao
 * 
 * 作成日:2012/01/24
 * @author xkinu
 *
 */
public interface UIMiseListHonbuDao {
	/** エンティティクラス */
	public static final Class BEAN = UIMiseList.class;
	/** 検索：パラメータ */
    public static final String select_ARGS  = "userId, userTypeCd, limitFlg, companyCd, miseCd, sysDate";
    public static final String selectByOnerCd_ARGS  = "userId, userTypeCd, limitFlg, companyCd, onerCd, sysDate";
    /**
     * クローズ店を含む対象店舗取得
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param miseCd
     * @param sysDate
     * @return
     */
    public UIMiseList select(
    		String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String miseCd ,String sysDate);
    
    /**
     * オーナーに紐付くクローズ店を含む対象店舗取得
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param onerCd
     * @param sysDate
     * @return
     */
    public List selectByOnerCd(
            String userId, String userTypeCd, boolean limitFlg
            , String companyCd, String onerCd ,String sysDate);
}
