/**
 * 
 */
package jp.co.isid.mos.bird.common.logic;

import jp.co.isid.mos.bird.common.entity.MstMise;

/**
 * @author xyuchida
 *
 */
public interface GetMiseLogic {

    /**
     * 店マスタ情報取得
     * 
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @return 店マスタ情報
     */
    public MstMise execute(String companyCd, String miseCd);
}
