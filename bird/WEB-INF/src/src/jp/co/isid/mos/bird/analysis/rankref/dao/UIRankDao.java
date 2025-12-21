/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.rankref.entity.UIRank;

/**
 * 売上ランク情報取得Dao
 * 
 * 作成日:2008/10/21
 * @author xkinu
 *
 */
public interface UIRankDao {
	/** エンティティクラス：売上ランク情報 */
    public static final Class BEAN = UIRank.class;
    /** 検索実行処理パラメータ*/
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoKikan, kikanSitei, kikanSiteiTo, rankType, rankTarget";
    /**
     * 検索実行処理
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoKikan
     * @param kikanSitei
     * @param kikanSiteiTo
     * @param rankType
     * @param rankTarget
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String tenpoShubetu
    		, String taishoKikan, String kikanSitei, String kikanSiteiTo
    		, String rankType, String rankTarget);

}
