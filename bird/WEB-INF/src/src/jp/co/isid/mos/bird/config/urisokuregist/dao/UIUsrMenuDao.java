/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.urisokuregist.entity.UIUsrMenu;

/**
 * 売上速報レポート用商品グループDao
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface UIUsrMenuDao {
    public static final Class BEAN = UIUsrMenu.class;
    /**
     * 対象年月の店舗数の取得時のパラメータ
     */
    public static final String select_ARGS = "uriSokuYm, frameKbn";
    
    /**
     * 対象年月の店舗数の取得
     * 
     * @param taishoYm　対象年月
     * @param frameKbn　フレーム区分
     * @return
     */
    public List select(String uriSokuYm, String frameKbn);

}
