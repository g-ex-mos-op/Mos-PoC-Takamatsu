/**
 * 
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.MstUserCompanyDao;
import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;

/**
 * ユーザ所属会社リスト取得Logic
 * 
 * @author xyuchida
 */
public class GetUserCompanyByManageLogicImpl implements GetUserCompanyLogic {

    /**
     * ユーザ所属会社DAO
     */
    private MstUserCompanyDao mstUserCompanyDao;

    /**
     * ユーザ所属会社リスト取得
     * 
     * @param userId ユーザID
     * @param zokuseiKbnList 属性区分リスト  = null : 条件としない
     * @return ユーザ所属会社リスト
     */
    public List execute(String userId, List zokuseiKbnList) {
        // ユーザ所属会社リスト取得
        return getMstUserCompanyDao().selectUserCompanyListByManage(userId, zokuseiKbnList);
    }

    /**
     * @return mstUserCompanyDao を戻します。
     */
    public MstUserCompanyDao getMstUserCompanyDao() {
        return mstUserCompanyDao;
    }

    /**
     * @param mstUserCompanyDao 設定する mstUserCompanyDao。
     */
    public void setMstUserCompanyDao(MstUserCompanyDao mstUserCompanyDao) {
        this.mstUserCompanyDao = mstUserCompanyDao;
    }
}
