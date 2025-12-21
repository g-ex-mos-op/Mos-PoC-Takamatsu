/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.inforegist.contactregist.dao.UIRenrakuInfoDao;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.GetRenrakuLogic;

/**
 * @author xyuchida
 *
 */
public class GetRenrakuLogicImpl implements GetRenrakuLogic {

    public static final String LOGIC_ID = "BIF001L01";

    private UIRenrakuInfoDao uIRenrakuInfoDao;
    /**
     * 
     */
    public List execute (String pubDate, String cateId, MstUser mstUser, int limit, int offset)
    {
        // LIKE条件項目に%を付与
        String pubDateCondition = (pubDate == null || pubDate.length() <= 0) ? pubDate : pubDate + "%";
		String userId = mstUser.getUser_id();
		String rcompanyCd = mstUser.getRCompanyCd();

        // select
        List contactList = getUIRenrakuInfoDao().getRenraku(pubDateCondition, cateId, userId, rcompanyCd);

        // ページ範囲インデックス設定
        int fromIndex = offset;
        int toIndex = offset + limit;
        fromIndex = (fromIndex > contactList.size()) ? contactList.size() : fromIndex;
        toIndex = (toIndex > contactList.size()) ? contactList.size() : toIndex;

        // ページ範囲リストを返却
        return contactList.subList(fromIndex, toIndex);
    }
    /**
     * @return uIRenrakuInfoDao を戻します。
     */
    public UIRenrakuInfoDao getUIRenrakuInfoDao() {
        return uIRenrakuInfoDao;
    }
    /**
     * @param renrakuInfoDao uIRenrakuInfoDao を設定。
     */
    public void setUIRenrakuInfoDao(UIRenrakuInfoDao renrakuInfoDao) {
        uIRenrakuInfoDao = renrakuInfoDao;
    }
    
}
