/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic.impl;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bill.itemtotal.dao.UIUriageDakaInfoDao;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetUriageDakaInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * オーナーコード報取得ロジック
 * 
 * @author xlee
 */
public class GetUriageDakaInfoLogicImpl implements GetUriageDakaInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS011L04";

    /**
     * 売上高情報DAOを取得します。
     */
    private UIUriageDakaInfoDao uiUriageDakaInfoDao;

    /**
     * 売上高情報DAOを取得します。
     * @return 売上高情報DAO
     */
    public UIUriageDakaInfoDao getUIUriageDakaInfoDao() {
        return uiUriageDakaInfoDao;
    }

    /**
     * 売上高情報DAOを設定します。
     * @param uiUriagedakaInfoDao 売上高情報DAO
     */
    public void setUIUriagedakaInfoDao(UIUriageDakaInfoDao uiUriageDakaInfoDao) {
        this.uiUriageDakaInfoDao = uiUriageDakaInfoDao;
    }

    /**
     * 売上高情報を取得
     * @param　miseCd　　店コード
     * @param　urikakeYm　売掛年月
     * @return  前年同月対象当年売上高（同月売上）
     */
    public BigDecimal execute(String miseCd, String urikakeYm) {

    	//エラー処理：
    	if(isNull(miseCd)){
            throw new NotNullException("店コード"); //E0506 店コード
        }

    	//エラー処理：
    	if(isNull(urikakeYm)){
            throw new NotNullException("売掛年月"); //E0506 売掛年月
        }
    	
    	BigDecimal tmpResult = (BigDecimal) getUIUriageDakaInfoDao().getUriagedakaInfo(miseCd, urikakeYm);

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
