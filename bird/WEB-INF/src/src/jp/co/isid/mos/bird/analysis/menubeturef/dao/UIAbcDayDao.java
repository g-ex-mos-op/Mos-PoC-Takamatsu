/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIAbcDay;

/**
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public interface UIAbcDayDao {
	/** エンティティ：ABC分析表日別 */
    public static final Class BEAN = UIAbcDay.class;
    
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, kikanSitei, kikanSiteiZennen, tonenTable, zennenTable, isCsv, isSmenu";

    /**
     * 検索条件よりメニュー別売上情報を取得
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param kikanSitei
     * @param tonenTable
     * @param zennenTable
     * 
     * @return List 検索条件
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
    		, String kikanSitei, String kikanSiteiZennen
    		, String tonenTable, String zennenTable, boolean isCsv, boolean isSmenu);

}
