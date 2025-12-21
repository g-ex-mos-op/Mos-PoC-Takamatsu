/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao.UIMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.util.MosChickenReserveChkBytimeUtil;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 店舗情報取得ロジック
 * 
 * @author xlee
 */
public class GetMiseInfoLogicImpl implements GetMiseInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS019L02";

    /**
     * 店舗情報DAOを取得します。
     */
    private UIMiseInfoDao uiMiseInfoDao;

    /**
     * 店舗情報DAOを取得します。
     * @return 店舗情報DAO
     */
    public UIMiseInfoDao getUIMiseInfoDao() {
        return uiMiseInfoDao;
    }

    /**
     * 店舗情報DAOを設定します。
     * @param uiMiseInfoDao 店舗情報DAO
     */
    public void setUIMiseInfoDao(UIMiseInfoDao uiMiseInfoDao) {
        this.uiMiseInfoDao = uiMiseInfoDao;
    }

    /**
     * 店舗リストを取得
     * @param onerCd オーナーコード
     * @param userID ユーザID
     * @param sysDate システム日付
     * @param userType ユーザタイプ
     * @param　companyCd　会社コード
     * @return 店舗リスト
     */
    
    public List execute(String onerCd, String userID, String sysDate, String userType, String companyCd) {
    	
    	//エラー処理：
    	if(MosChickenReserveChkBytimeUtil.isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 対象期間開始日
        }
    	List tmpResult = new ArrayList();
    	
        if(userType.equals(MosChickenReserveChkBytimeUtil.USER_TYPE_HONBU)) {
        	tmpResult = getUIMiseInfoDao().getHonbuUserMiseInfo(companyCd, onerCd, sysDate);
        } if(userType.equals(MosChickenReserveChkBytimeUtil.USER_TYPE_ONER)) {
            tmpResult = getUIMiseInfoDao().getOnerUserMiseInfo(companyCd, userID, sysDate);
        } else if(userType.equals(MosChickenReserveChkBytimeUtil.USER_TYPE_TENPO)) {
        	tmpResult = getUIMiseInfoDao().getTenpoUserMiseInfo(companyCd, userID, sysDate);
        }
        
        if (tmpResult == null || tmpResult.size() == 0) {
			if(userType.equals(MosChickenReserveChkBytimeUtil.USER_TYPE_HONBU) || 
					userType.equals(MosChickenReserveChkBytimeUtil.USER_TYPE_ONER)) {
				throw new NotExistException("オーナーコード"); //E0103 店舗情報
			} else if(userType.equals(MosChickenReserveChkBytimeUtil.USER_TYPE_TENPO)) {
				throw new NotExistException("対応店舗情報"); //E0103 店舗情報
			}
        }
        return tmpResult;
    }
}
