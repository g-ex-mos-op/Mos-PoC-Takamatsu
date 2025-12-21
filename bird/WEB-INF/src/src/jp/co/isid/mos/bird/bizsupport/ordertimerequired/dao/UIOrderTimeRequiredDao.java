/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.ordertimerequired.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.ordertimerequired.entity.UIOrderTimeRequired;

/**
 * オーダー提供時間情報Dao
 * 
 * 作成日:2009/10/15
 * @author xkinu
 *
 */
public interface UIOrderTimeRequiredDao {
	/**
     * オーダー提供時間情報エンティティクラス
     */
    public static final Class BEAN = UIOrderTimeRequired.class;
    /**
     * 検索情報取得時のパラメータ
     */
    public static final String select_ARGS = "userId, limitFlg, companyCd, miseCd, taishoKikan, kikanFrom, kikanTo";
    /**
     * 検索情報取得
     * 
     * @param userId
     * @param limitFlg 支部制限フラグ
     * @param companyCd
     * @param miseCd
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @return
     */
    public List select(String userId, boolean limitFlg
    		, String companyCd, String miseCd
    		, String taishoKikan, String kikanFrom, String kikanTo);

}
