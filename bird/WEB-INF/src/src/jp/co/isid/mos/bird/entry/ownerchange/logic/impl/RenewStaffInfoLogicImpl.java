/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic.impl;

import jp.co.isid.mos.bird.entry.ownerchange.common.OwnerChangeConstants;
import jp.co.isid.mos.bird.entry.ownerchange.dao.MstStaffInfoDao;
import jp.co.isid.mos.bird.entry.ownerchange.entity.MstStaffInfo;
import jp.co.isid.mos.bird.entry.ownerchange.logic.RenewStaffInfoLogic;

/**
 * 加盟店スタッフマスタ更新
 * @author xkonishi
 *
 */
public class RenewStaffInfoLogicImpl implements RenewStaffInfoLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BEN023L09";
    
    
    /**
     * スタッフ情報Dao
     */
    private MstStaffInfoDao mstStaffInfoDao;
    
    
    /**
     * 加盟店スタッフマスタ更新ロジック
     * @param 更新後オーナーコード
     * @param 更新後店コード
     * @param 異動日
     * @param ユーザーID
     * @param スタッフ情報
     */    
    public void execute(String newOnerCd, String newMiseCd, String moveDt, 
                         String userId, MstStaffInfo mstStaffInfo) {

        // entityに更新する情報をセット。
        mstStaffInfo.setOldOnerCd(mstStaffInfo.getOnerCd());
        mstStaffInfo.setOnerCd(newOnerCd);
        mstStaffInfo.setMiseCd1(newMiseCd);
        mstStaffInfo.setMoveDt(moveDt);
        mstStaffInfo.setLastUser(OwnerChangeConstants.SCREEN_ID);
        mstStaffInfo.setLastPgm(userId);

        // Dao【スタッフ情報．加盟店スタッフマスタ更新】を実行。
        mstStaffInfoDao.update(mstStaffInfo);       
    }

    
    public MstStaffInfoDao getMstStaffInfoDao() {
        return mstStaffInfoDao;
    }

    public void setMstStaffInfoDao(MstStaffInfoDao mstStaffInfoDao) {
        this.mstStaffInfoDao = mstStaffInfoDao;
    }
}