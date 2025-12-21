/**
 * 
 */
package jp.co.isid.mos.bird.common.logic.impl;

import jp.co.isid.mos.bird.common.dao.MstMiseDao;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;

/**
 * @author xyuchida
 *
 */
public class GetMiseLogicImpl implements GetMiseLogic {

    private MstMiseDao mstMiseDao;

    /**
     * 店マスタ情報取得
     * 
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @return 店マスタ情報
     */
    public MstMise execute(String companyCd, String miseCd) {
        return getMstMiseDao().selectMise(companyCd, miseCd);
    }

    public MstMiseDao getMstMiseDao() {
        return mstMiseDao;
    }

    public void setMstMiseDao(MstMiseDao mstMiseDao) {
        this.mstMiseDao = mstMiseDao;
    }
}
