/**
 * 
 */
package jp.co.isid.mos.bird.common.dao;

import jp.co.isid.mos.bird.common.entity.MstOner;

/**
 * @author xyuchida
 *
 */
public interface MstOnerDao {

    public static final Class BEAN = MstOner.class;

    public static final String selectOner_QUERY = "COMPANY_CD = ? and ONER_CD = ?";

    /**
     * オーナーマスタ情報取得
     * 
     * @param companyCd
     * @param onerCd
     * @return オーナーマスタ情報
     */
    public MstOner selectOner(String companyCd, String onerCd);
}
