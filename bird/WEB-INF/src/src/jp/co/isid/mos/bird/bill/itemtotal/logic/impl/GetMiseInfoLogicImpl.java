/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.dao.UIMiseInfoDao;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 店情報取得ロジック
 * 
 * @author xlee
 */
public class GetMiseInfoLogicImpl implements GetMiseInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS011L02";

    /**
     * 店情報取得DAOを取得します。
     */
    private UIMiseInfoDao uiMiseInfoDao;

    /**
     * 店情報取得DAOを取得します。
     * @return 店情報DAO
     */
    public UIMiseInfoDao getUIMiseInfoDao() {
        return uiMiseInfoDao;
    }

    /**
     * 店情報取得DAOを設定します。
     * @param uiMiseInfoDao 店情報DAO
     */
    public void setUIMiseInfoDao(UIMiseInfoDao uiMiseInfoDao) {
        this.uiMiseInfoDao = uiMiseInfoDao;
    }

    /**
     * 店情報取得を取得
     * 
     * @param　sysDate システムデータ
     * @param　onerCd オーナーコード
     * 
     * @return  店情報リスト
     */
    public List execute(String sysDate,String onerCd) {
    	
    	//エラー処理：
    	if(isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 システム日付
        }

    	//エラー処理：
    	if(isNull(onerCd)){
            throw new NotNullException("オーナーコード"); //E0506 システム日付
        }
    	
    	List tmpResult = getUIMiseInfoDao().getMiseInfo(sysDate, onerCd);
        
    	if (tmpResult == null || tmpResult.size() == 0) {
            throw new NoResultException(); //E0103 店情報
        }
    	
        return tmpResult;
    }
    
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
