/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.urisokuregist.entity.UIUsrCamp;

/**
 * 売上速報対象キャンペーンDao
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface UIUsrCampDao {
    public static final Class BEAN = UIUsrCamp.class;
    /**
     * 検索時のパラメータ
     */
    public static final String select_ARGS = "companyCd, taishoYm";
    
    /**
     * 売上速報対象キャンペーンの取得
     * 
     * @param companyCd 会社コード
     * @param taishoYm　対象年月
     * @return
     */
    public List select(String companyCd, String taishoYm);


}
