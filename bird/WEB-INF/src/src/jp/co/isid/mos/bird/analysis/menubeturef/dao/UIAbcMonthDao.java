/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIAbcMonth;

/**
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public interface UIAbcMonthDao {
	/** エンティティ：ABC分析表日別 */
    public static final Class BEAN = UIAbcMonth.class;
    
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, kikanSitei, kikanSiteiZennen, kikanSiteiLastMonth, isCsv, isSmenu";
    public static final String selectOner_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, kikanSitei, kikanSiteiZennen, kikanSiteiLastMonth, isCsv, isSmenu";
    public static final String selectSibu_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, kikanSitei, kikanSiteiZennen, kikanSiteiLastMonth, isCsv, isSmenu";
    public static final String selectHanbaiTenpoCnt_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, kikanSitei, kikanSiteiZennen, kikanSiteiLastMonth, isCsv, isSmenu";
    public static final String selectTaishoTenpoCnt_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, kikanSitei, kikanSiteiZennen, kikanSiteiLastMonth";

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
     * @param kikanSiteiZennen
     * @param kikanSiteiLastMonth
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
    		, String kikanSitei, String kikanSiteiZennen, String kikanSiteiLastMonth, boolean isCsv, boolean isSmenu);

    /**
     * 検索条件よりメニュー別売上情報を取得
     * 　オーナーユーザー用
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param kikanSitei
     * @param kikanSiteiZennen
     * @param kikanSiteiLastMonth
     * @return
     */
    public List selectOner(String userId, String userTypeCd, boolean limitFlg
            , String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
            , String kikanSitei, String kikanSiteiZennen, String kikanSiteiLastMonth, boolean isCsv, boolean isSmenu);

    /**
     * 検索条件よりメニュー別売上情報を取得
     * 　本部ユーザー：全社or支部指定 用
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param kikanSitei
     * @param kikanSiteiZennen
     * @param kikanSiteiLastMonth
     * @return
     */
    public List selectSibu(String userId, String userTypeCd, boolean limitFlg
            , String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
            , String kikanSitei, String kikanSiteiZennen, String kikanSiteiLastMonth, boolean isCsv, boolean isSmenu);
    
    /**
     * 販売店舗数 取得
     * 　本部ユーザー：全社or支部指定 用
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param kikanSitei
     * @param kikanSiteiZennen
     * @param kikanSiteiLastMonth
     * @return
     */
    public List selectHanbaiTenpoCnt(String userId, String userTypeCd, boolean limitFlg
            , String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
            , String kikanSitei, String kikanSiteiZennen, String kikanSiteiLastMonth, boolean isCsv, boolean isSmenu);
    /**
     * 対象店舗数 取得
     * 　本部ユーザー：全社or支部指定 用
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param kikanSitei
     * @param kikanSiteiZennen
     * @param kikanSiteiLastMonth
     * @return
     */
    public int selectTaishoTenpoCnt(String userId, String userTypeCd, boolean limitFlg
            , String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
            , String kikanSitei, String kikanSiteiZennen, String kikanSiteiLastMonth);
}
