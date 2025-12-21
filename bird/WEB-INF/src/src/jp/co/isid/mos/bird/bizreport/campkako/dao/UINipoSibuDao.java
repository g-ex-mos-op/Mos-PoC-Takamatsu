/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campkako.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.entity.UINipoSibu;

/**
 * 支部一覧用キャンペーン日報Dao
 * 
 * @author xnkusama
 * 更新日:2011/06/24 xkinu ADD 期間合計時に店舗種別の条件追加対応に伴う店舗種別を引数へ追加
 *
 */
public interface UINipoSibuDao {
    public static final Class BEAN = UINipoSibu.class;
    
    public static final String selectGokeiSibu_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, campId, menuTotaledKbn, shukeiKbn, campFrom, campTo";
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, campId, menuTotaledKbn, shukeiKbn, campFrom, campTo";
    
    /**
     * 検索処理（エリア合計有）
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param campId
     * @param menuTotaledKbn
     * @param shukeiKbn
     * @param campFrom
     * @param campTo
     * @return
     */
    public List selectGokeiSibu(String userId, String userTypeCd, boolean limitFlg,
    		            String companyCd, String tenpoShubetu, String campId, String menuTotaledKbn, 
                        String shukeiKbn, String campFrom, String campTo);

    /**
     * 検索処理
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param campId
     * @param menuTotaledKbn
     * @param shukeiKbn
     * @param campFrom
     * @param campTo
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg,
                        String companyCd, String tenpoShubetu, String campId, String menuTotaledKbn, 
                        String shukeiKbn, String campFrom, String campTo);
}
