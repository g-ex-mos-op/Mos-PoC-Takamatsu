/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UIOutOfSubjectAmt;

/**
 * メータ入力店舗別管理項目設定情報Dao
 * 
 * 作成日:2010/05/06
 * @author xkinu
 *
 */
public interface UIOutOfSubjectAmtDao {
	/** エンティティ:対象外項目情報 */
	public static final Class BEAN = UIOutOfSubjectAmt.class;
	/** SQLパラメーター：検索用 */
    public static final String select_ARGS = "companyCd, meterKbn, taishoJoken, taishoCd, userTypeCd, userId, limitFlg";
    /**
     * 検索処理
     * 
     * @param companyCd
     * @param meterKbn
     * @param taishoJoken
     * @param taishoCd
     * @param userTypeCd
     * @param userId
     * @param limitFlg
     * @return
     */
    public List select(String companyCd, String meterKbn
    		, String taishoJoken, String taishoCd
    		, String userTypeCd, String userId, boolean limitFlg);
}
