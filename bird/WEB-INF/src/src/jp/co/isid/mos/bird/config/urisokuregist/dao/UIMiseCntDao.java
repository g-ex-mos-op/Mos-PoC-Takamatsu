/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.dao;

import jp.co.isid.mos.bird.config.urisokuregist.entity.UIMiseCnt;

/**
 * 売上速報レポート用店舗数Dao
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface UIMiseCntDao {
    public static final Class BEAN = UIMiseCnt.class;
    /**
     * 対象年月の店舗数の取得時のパラメータ
     */
    public static final String select_ARGS = "taishoYm";
    /**
     * 対象年月の店舗数の取得
     * 
     * @param taishoYm 対象年月
     * @return
     */
    public UIMiseCnt select(String taishoYm);

	
}
