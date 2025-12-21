/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.ownerchange.common.OwnerChangeConstants;
import jp.co.isid.mos.bird.entry.ownerchange.dao.MstStaffInfoDao;
import jp.co.isid.mos.bird.entry.ownerchange.logic.GetStaffInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * スタッフ情報取得ロジック
 * @author xkonishi
 *
 */
public class GetStaffInfoLogicImpl implements GetStaffInfoLogic {
    
    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BEN023L04";
    
    /**
     * スタッフ情報Dao
     */
    private MstStaffInfoDao mstStaffInfoDao;
    

    /**
     * スタッフ情報取得ロジック
     * @param 会社コード
     * @param 条件区分
     * @param 店コード
     * @param オーナーコード
     * @return スタッフ情報
     */
    public List execute(String companyCd, String kbn, String miseCd,
            String onerCd, String sysDate) {        
        
        List staffInfo = null;
        
        // 条件区分が0(店舗)の場合
        if(kbn.equals(OwnerChangeConstants.KBN_MISE)){
            
            // Dao【スタッフ情報DAO.店舗スタッフ情報取得】を実行。
            staffInfo = mstStaffInfoDao.selectByMiseCd(companyCd, miseCd);
            
            // スタッフ情報を取得できなかった場合
            if(staffInfo == null || staffInfo.size() == 0) {
                throw new NotExistException("該当データ","miseText",null);
            }
        }

        // 条件区分が1(オーナー)の場合
        if(kbn.equals(OwnerChangeConstants.KBN_OWNER)){
            
            // Dao【スタッフ情報DAO.オーナー保有スタッフ情報取得】を実行。
            staffInfo = mstStaffInfoDao.selectByOnerCd(companyCd, onerCd);
            
            // スタッフ情報を取得できなかった場合
            if(staffInfo == null || staffInfo.size() == 0) {
                throw new NotExistException("該当データ","ownerText",null);
            }
        }        
        return staffInfo;
    }

    
    public MstStaffInfoDao getMstStaffInfoDao() {
        return mstStaffInfoDao;
    }

    public void setMstStaffInfoDao(MstStaffInfoDao mstStaffInfoDao) {
        this.mstStaffInfoDao = mstStaffInfoDao;
    }
}