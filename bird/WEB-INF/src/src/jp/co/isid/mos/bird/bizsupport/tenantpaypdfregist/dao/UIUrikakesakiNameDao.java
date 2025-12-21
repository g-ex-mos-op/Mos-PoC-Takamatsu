/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.UIUrikakesakiName;

/**
 * 売掛先名称DAO
 * 
 * 作成日:2009/06/26
 * @author xkinu
 *
 */
public interface UIUrikakesakiNameDao {
	/**
     * 売掛先名称エンティティクラス
     */
    public static final Class BEAN = UIUrikakesakiName.class;
    /**
     * 検索情報取得時のパラメータ
     */
    public static final String select_ARGS = "companyCd, miseCd";
    /**
     * 検索情報取得
     * 
     * @param companyCd
     * @param miseCd
     * 
     * @return
     */
    public List select(String companyCd, String miseCd);
 
}
