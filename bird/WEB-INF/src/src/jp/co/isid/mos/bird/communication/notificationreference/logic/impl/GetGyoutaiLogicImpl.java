/*
 * çÏê¨ì˙: 2006/03/01
 *
 */
package jp.co.isid.mos.bird.communication.notificationreference.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.communication.notificationreference.dao.MstUserGyotaiDao;
import jp.co.isid.mos.bird.communication.notificationreference.logic.GetGyoutaiLogic;

/**
 * @author m.onodera
 *
 */
public class GetGyoutaiLogicImpl implements GetGyoutaiLogic {

    public static final String LOGIC_ID = "BCM002L02";

    private MstUserGyotaiDao mstUserGyotaiDao;

    public MstUserGyotaiDao getMstUserGyotaiDao() {
        return mstUserGyotaiDao;
    }
    public void setMstUserGyotaiDao(MstUserGyotaiDao mstUserGyotaiDao) {
        this.mstUserGyotaiDao = mstUserGyotaiDao;
    }

    public List execute(String userId) {
        return getMstUserGyotaiDao().getGyotai(userId);
    }
}
