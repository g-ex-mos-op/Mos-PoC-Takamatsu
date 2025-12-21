/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.miselumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.GasAircon;

/**
 * 店舗ガス種別
 * 
 * 作成日:2011/07/11
 * @author xkinu
 *
 */
public interface GasAirconDao {
    public static final Class BEAN = GasAircon.class;
    public static final String select_ARGS = "companyCd, sibuCd, closeFlg";
    /**
     * 店舗ガス種別(店舗拡張マスタ)の検索
     * 
     * @param String companyCd 会社コード
     * @param String sibuCd    支部コード
     * @param String closeFlg  店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List select(String companyCd, String sibuCd, String closeFlg);
}
