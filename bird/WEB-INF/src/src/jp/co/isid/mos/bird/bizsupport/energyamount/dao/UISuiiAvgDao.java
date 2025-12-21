/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UISuiiUnit;

/**
 * 平均原単位Dao
 * 
 * 作成日:2010/05/06
 * @author xkinu
 *
 */
public interface UISuiiAvgDao {
	/** エンティティ:平均原単位 */
	public static final Class BEAN = UISuiiUnit.class;
	/** SQLパラメーター：検索用 */
    public static final String select_ARGS = "sysYm, companyCd" +
    		", nendoFrom, nendoTo, lastNendoFrom, lastNendoTo" +
    		", meterKbn, taishoJoken, taishoCd, userTypeCd, userId, limitFlg";
    /**
     * 検索処理
     * 
     * @param nendoFrom     当年度開始年月
     * @param nendoTo       当年度開始年月
     * @param lastNendoFrom 前年度開始年月
     * @param lastNendoTo   前年度終了年月
     * @param meterKbn      メーター区分
     * @param taishoJoken
     * @param taishoCd
     * @param userTypeCd
     * @param userId
     * @param limitFlg
     * @return
     */
    public List select(String sysYm, String companyCd
    		, String nendoFrom, String nendoTo, String lastNendoFrom, String lastNendoTo
    		, String meterKbn, String taishoJoken, String taishoCd
    		, String userTypeCd, String userId, boolean limitFlg);

	/** SQLパラメーター：検索用 */
    public static final String selectByOner_ARGS = "sysYm, companyCd" +
	", nendoFrom, nendoTo, lastNendoFrom, lastNendoTo" +
	", meterKbn, taishoJoken, taishoCd, userTypeCd, userId, limitFlg";
    /**
     * 検索処理
     * 
     * @param sysYm
     * @param companyCd
     * @param nendoFrom
     * @param nendoTo
     * @param lastNendoFrom
     * @param lastNendoTo
     * @param meterKbn
     * @param taishoJoken
     * @param taishoCd
     * @param userTypeCd
     * @param userId
     * @param limitFlg
     * @return
     */
    public List selectByOner(String sysYm, String companyCd
    		, String nendoFrom, String nendoTo, String lastNendoFrom, String lastNendoTo
    		, String meterKbn, String taishoJoken, String taishoCd
    		, String userTypeCd, String userId, boolean limitFlg);
}
