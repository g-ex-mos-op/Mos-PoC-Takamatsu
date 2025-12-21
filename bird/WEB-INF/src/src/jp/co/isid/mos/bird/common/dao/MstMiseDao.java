/**
 * 
 */
package jp.co.isid.mos.bird.common.dao;

import jp.co.isid.mos.bird.common.entity.MstMise;

/**
 * @author xyuchida
 *
 */
public interface MstMiseDao {

    public static final Class BEAN = MstMise.class;

    public static final String selectMise_QUERY = "COMPANY_CD = ? and MISE_CD = ?";

    /**
     * 店マスタ情報取得
     * 
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @return 店マスタ情報
     */
    public MstMise selectMise(String companyCd, String miseCd);
}
