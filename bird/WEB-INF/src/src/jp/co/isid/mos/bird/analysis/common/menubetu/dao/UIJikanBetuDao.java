/**
 * 
 */
package jp.co.isid.mos.bird.analysis.common.menubetu.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIJikanBetu;

/**
 * 時間帯別情報Dao
 * 
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public interface UIJikanBetuDao {
	/** エンティティ：時間帯別情報 */
    public static final Class BEAN = UIJikanBetu.class;
    
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd, kikanSitei";

    /**
     * 検索条件よりメニュー別売上情報を取得
     * 
     * @param userId
     * @param userTypeCd
     * @param companyCd
     * @param tenpoShubetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param blockCd
     * @param kikanSitei
     * 
     * @return List 検索条件
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String tenpoShubetu, String taishoJoken, String hyojiTaisho, String blockCd
    		, String kikanSitei);

}
