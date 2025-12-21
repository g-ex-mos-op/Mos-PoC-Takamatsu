/**
 * 
 */
package jp.co.isid.mos.bird.common.logic.impl;

import jp.co.isid.mos.bird.common.dao.MstOnerDao;
import jp.co.isid.mos.bird.common.entity.MstOner;
import jp.co.isid.mos.bird.common.logic.GetOnerLogic;

/**
 * @author xyuchida
 *
 */
public class GetOnerLogicImpl implements GetOnerLogic {

    private MstOnerDao mstOnerDao;

    /**
     * オーナーマスタ情報取得
     * 
     * @param companyCd
     * @param onerCd
     * @return オーナーマスタ情報
     */
    public MstOner execute(String companyCd, String onerCd) {
        return getMstOnerDao().selectOner(companyCd, onerCd);
    }

    public MstOnerDao getMstOnerDao() {
        return mstOnerDao;
    }

    public void setMstOnerDao(MstOnerDao mstOnerDao) {
        this.mstOnerDao = mstOnerDao;
    }
}
