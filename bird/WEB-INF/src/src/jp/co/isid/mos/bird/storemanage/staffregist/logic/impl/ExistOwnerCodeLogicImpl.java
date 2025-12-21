/*
 * 作成日: 2006/05/24
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import jp.co.isid.mos.bird.storemanage.staffregist.dao.MstStaffMiseDao;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.ExistOwnerCodeLogic;

/**
 * @author xyuchida
 */
public class ExistOwnerCodeLogicImpl implements ExistOwnerCodeLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM004L06";

    private MstStaffMiseDao mstStaffMiseDao;

    public MstStaffMiseDao getMstStaffMiseDao() {
        return mstStaffMiseDao;
    }
    public void setMstStaffMiseDao(MstStaffMiseDao mstStaffMiseDao) {
        this.mstStaffMiseDao = mstStaffMiseDao;
    }

    public boolean execute(String companyCd, String onerCd) {
        return getMstStaffMiseDao().getExistOwnerCount(companyCd, onerCd) > 0;
    }
}
