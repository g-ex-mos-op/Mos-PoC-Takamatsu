/*
 * çÏê¨ì˙: 2006/02/10
 *
 */
package jp.co.isid.mos.bird.communication.contactreference.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.communication.contactreference.dao.MstUserGyotaiDao;
import jp.co.isid.mos.bird.communication.contactreference.logic.GetGyoutaiLogic;

/**
 * @author xyuchida
 *
 */
public class GetGyoutaiLogicImpl implements GetGyoutaiLogic {

    public static final String LOGIC_ID = "BCM001L02";

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
