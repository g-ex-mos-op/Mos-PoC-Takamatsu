/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIShokuzaiMeyasu;

/**
 * 食材準備目安表情報取得Dao
 * 
 * 作成日:2008/09/10
 * @author xkinu
 *
 */
public interface UIShokuzaiMeyasuDao {
	/** エンティティクラス：食材準備目安表情報 */
    public static final Class BEAN = UIShokuzaiMeyasu.class;
    /** 検索実行処理パラメータ*/
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, taishoKikan, kikanSitei, targetMonth";
    /** 検索実行処理パラメータ（本部、支部or全社用）*/
    public static final String selectSibu_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, taishoKikan, kikanSitei, targetMonth";
    /** 検索実行処理パラメータ（期間指定用）*/
    public static final String selectKikan_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, hyojiTaisho, taishoKikan, kikanSiteiFrom, kikanSiteiTo, targetMonth1, targetMonth2, targetMonth3, targetMonth4, targetMonth5";

    
    /**
     * 検索実行処理
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param taishoKikan
     * @param kikanSitei
     * @param targetMonth
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
    		, String taishoKikan, String kikanSitei, String targetMonth);

    /**
     * 検索実行処理（本部、支部or全社用）
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param taishoKikan
     * @param kikanSitei
     * @param targetMonth
     * @return
     */
    public List selectSibu(String userId, String userTypeCd, boolean limitFlg
            , String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
            , String taishoKikan, String kikanSitei, String targetMonth);
    /**
     * 『期間指定』検索実行処理
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param hyojiTaisho(店舗コードのみ)
     * @param taishoKikan
     * @param kikanSiteiFrom
     * @param kikanSiteiTo
     * @param targetMonth1
     * @param targetMonth2
     * @param targetMonth3
     * @param targetMonth4
     * @param targetMonth5
     * @return
     */
    public List selectKikan(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String tenpoShubetu, String hyojiTaisho
    		, String taishoKikan, String kikanSiteiFrom, String kikanSiteiTo
    		, String targetMonth1, String targetMonth2, String targetMonth3, String targetMonth4, String targetMonth5);

}