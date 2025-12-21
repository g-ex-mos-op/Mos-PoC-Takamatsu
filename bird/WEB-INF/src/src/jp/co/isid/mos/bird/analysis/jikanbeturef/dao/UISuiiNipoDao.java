/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.jikanbeturef.entity.UISuiiNipo;

/**
 * 時間帯別推移日報表情報取得Dao
 * 
 * 作成日:2008/09/10
 * @author xkinu
 *
 */
public interface UISuiiNipoDao {
	/** エンティティクラス：時間帯別推移日報表情報 */
    public static final Class BEAN = UISuiiNipo.class;
    /** 検索実行処理パラメータ*/
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd, kikanSitei, targetMonth";
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
     * @param blockCd
     * @param kikanSitei
     * @param targetMonth
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho, String blockCd
    		, String kikanSitei, String targetMonth);

}
