/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.jikanbeturef.entity.UIWeekShubetuKikan;

/**
 * 曜日別売上種別Dao
 *
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public interface UIWeekShubetuKikanDao {
    /** エンティティ：曜日別売上種別 */
    public static final Class BEAN = UIWeekShubetuKikan.class;
   
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, kikanSitei, targetMonth, isAnHour ,kikanSiteiTo ,kiknFromYM ,kikanToYM";

    /**
     * 検索条件より時間帯別曜日別売上情報サマリを取得
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param kikanSitei
     * @param csvMode
     * @param targetMonth
     * @param isAnHour 一時間単位データ含むかの判断フラグ
     * @param kikanSiteiTo
     * 
     * @return List 検索条件
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
            , String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
            , String kikanSitei, String targetMonth, boolean isAnHour, String kikanSiteiTo ,String kikanSiteiFromYm ,String kikanSiteiToYm);
}
