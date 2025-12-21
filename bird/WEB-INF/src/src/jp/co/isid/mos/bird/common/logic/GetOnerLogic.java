/**
 * 
 */
package jp.co.isid.mos.bird.common.logic;

import jp.co.isid.mos.bird.common.entity.MstOner;

/**
 * @author xyuchida
 *
 */
public interface GetOnerLogic {

    /**
     * オーナーマスタ情報取得
     * 
     * @param companyCd
     * @param onerCd
     * @return オーナーマスタ情報
     */
    public MstOner execute(String companyCd, String onerCd);
}
