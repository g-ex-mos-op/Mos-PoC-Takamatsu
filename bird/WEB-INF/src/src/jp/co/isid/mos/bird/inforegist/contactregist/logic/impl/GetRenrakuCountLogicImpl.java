/*
 * ì¬“ú: 2006/03/02
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic.impl;

import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.inforegist.contactregist.dao.UIRenrakuInfoDao;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.GetRenrakuCountLogic;

/**
 * @author xyuchida
 *
 */
public class GetRenrakuCountLogicImpl implements GetRenrakuCountLogic {

    public static final String LOGIC_ID = "BIF001L06";

    private UIRenrakuInfoDao uIRenrakuInfoDao;

    /**
     * @return uIRenrakuInfoDao ‚ğ–ß‚µ‚Ü‚·B
     */
    public UIRenrakuInfoDao getUIRenrakuInfoDao() {
        return uIRenrakuInfoDao;
    }
    /**
     * @param renrakuInfoDao uIRenrakuInfoDao ‚ğİ’èB
     */
    public void setUIRenrakuInfoDao(UIRenrakuInfoDao renrakuInfoDao) {
        uIRenrakuInfoDao = renrakuInfoDao;
    }
    /**
     * 
     */
    public int execute(String pubDate, String cateId, MstUser mstUser) {

        // LIKEğŒ€–Ú‚É%‚ğ•t—^
        String pubDateCondition = (pubDate == null || pubDate.length() <= 0) ? pubDate : pubDate + "%";
		String userId = mstUser.getUser_id();
		String rcompanyCd = mstUser.getRCompanyCd();

        // select
        return getUIRenrakuInfoDao().getRenrakuCount(pubDateCondition, cateId, userId, rcompanyCd);
    }
}
