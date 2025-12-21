/*
 * 作成日: 2006/06/07
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.staffregist.dao.MstStaffMiseDao;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.GetMiseListLogic;

/**
 * @author xyuchida
 */
public class GetMiseListLogicImpl implements GetMiseListLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM004L03";

    private MstStaffMiseDao mstStaffMiseDao;

    public MstStaffMiseDao getMstStaffMiseDao() {
        return mstStaffMiseDao;
    }
    public void setMstStaffMiseDao(MstStaffMiseDao mstStaffMiseDao) {
        this.mstStaffMiseDao = mstStaffMiseDao;
    }

    public List execute(String companyCd, String onerCd, String sysDate) {
        return getMstStaffMiseDao().selectMiseCode(companyCd, onerCd, sysDate);
    }
}
