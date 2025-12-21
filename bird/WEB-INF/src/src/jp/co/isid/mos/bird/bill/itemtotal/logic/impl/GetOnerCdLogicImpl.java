/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic.impl;

import jp.co.isid.mos.bird.bill.itemtotal.dao.UIOnerCdDao;
import jp.co.isid.mos.bird.bill.itemtotal.entity.UIOnerCd;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetOnerCdLogic;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * オーナーコード報取得ロジック
 * 
 * @author xlee
 */
public class GetOnerCdLogicImpl implements GetOnerCdLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS011L01";

    /**
     * オーナーコードDAOを取得します。
     */
    private UIOnerCdDao uiOnerCdDao;

    /**
     * オーナーコードDAOを取得します。
     * @return P/L作成者情報DAO
     */
    public UIOnerCdDao getUIOnerCdDao() {
        return uiOnerCdDao;
    }

    /**
     * オーナーコードDAOを設定します。
     * @param uiOnerCdDao オーナーコードDAO
     */
    public void setUIOnerCdDao(UIOnerCdDao uiOnerCdDao) {
        this.uiOnerCdDao = uiOnerCdDao;
    }

    /**
     * オーナーコードを取得
     * @param　userId ユーザーID
     * @return  オーナーコード
     */
    public UIOnerCd execute(String userId) {

    	//エラー処理：
    	if(isNull(userId)){
            throw new NotNullException("ユーザーID"); //E0506 ユーザーID
        }
        
    	UIOnerCd tmpResult = getUIOnerCdDao().getOnerCd(userId);
        
        if (isNull(tmpResult.getOnerCd())) {
            throw new NoResultException(); //E0103 オーナーコード
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
