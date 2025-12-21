/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIUriageShubetu;

/**
 * 売上種別情報取得Dao
 * 
 * 作成日:2008/09/10
 * @author xkinu
 *
 */
public interface UIUriageShubetuDao {
	/** エンティティクラス：食材準備目安表情報 */
    public static final Class BEAN = UIUriageShubetu.class;
    /** 検索実行処理パラメータ*/
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, taishoKikan, kikanSitei, targetMonth";
    public static final String selectSibu_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, taishoKikan, kikanSitei, targetMonth";
    public static final String selectTaishoTenpoCnt_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, taishoKikan, kikanSitei, targetMonth";
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
     * 検索実行処理
     * 　本部ユーザーかつ対象条件＝全社or支部
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
     * 対象店舗数取得
     * 　本部ユーザーかつ対象条件＝全社or支部
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
    public int selectTaishoTenpoCnt(String userId, String userTypeCd, boolean limitFlg
            , String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho
            , String taishoKikan, String kikanSitei, String targetMonth);
}
