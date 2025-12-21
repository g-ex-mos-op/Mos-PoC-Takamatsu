/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.jikanbeturef.entity.UIWeek;

/**
 * 時間帯別曜日別売上Dao
 *
 * 作成日:2008/11/07
 * @author xkinu
 *
 */
public interface UIWeekDao {
	/** エンティティ：時間帯別曜日別売上 */
    public static final Class BEAN = UIWeek.class;
    
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd, kikanSitei, targetMonth, zennenYM, isAnHour";

    /**
     * 検索条件より時間帯別曜日別売上情報を取得
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param blockCd
     * @param kikanSitei
     * @param targetMonth
     * @param zennenYM
     * @param isAnHour
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho, String blockCd
            , String kikanSitei, String targetMonth, String zennenYM, boolean isAnHour);

}
