/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.jikanbeturef.entity.UIWeekShubetu;

/**
 * 曜日別売上種別Dao
 *
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public interface UIWeekShubetuDao {
	/** エンティティ：曜日別売上種別 */
    public static final Class BEAN = UIWeekShubetu.class;
    
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd, kikanSitei, targetMonth, isAnHour";

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
     * @param csvMode
     * @param targetMonth
     * @param isAnHour 一時間単位データ含むかの判断フラグ
     * 
     * @return List 検索条件
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho, String blockCd
    		, String kikanSitei, String targetMonth, boolean isAnHour);
}
