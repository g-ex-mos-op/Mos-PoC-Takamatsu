/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.dao;

import jp.co.isid.mos.bird.analysis.menubeturef.entity.UILastYearEigyoDt;

/**
 * 前年営業日情報Dao
 * 
 * 作成日:2008/09/17
 * @author xkinu
 *
 */
public interface UILastYearEigyoDtDao {
	/** エンティティ：前年営業日情報 */
    public static final Class BEAN = UILastYearEigyoDt.class;
    public static final String select_ARGS = "companyCd, eigyoDt";

    /**
     * 検索条件より前年営業日情報を取得
     * 
     * @param companyCd
     * @param eigyoDt
     * 
     * @return UILastYearEigyoDt 検索条件
     */
    public UILastYearEigyoDt select(String companyCd, String eigyoDt);
}
