/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferMiseInfoDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.CheckOfferInputParamLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferMiseInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 店舗情報取得ロジック
 * 
 * @author xlee
 */
public class GetOfferMiseInfoLogicImpl implements GetOfferMiseInfoLogic{

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN011L01";

    /**
     * 店舗情報DAOを取得します。
     */
    private UIOfferMiseInfoDao uiOfferMiseInfoDao;
    
    /**
     * チェック処理を取得します。
     */
    private CheckOfferInputParamLogic checkOfferInputParamLogic;

    /**
     * 店舗情報DAOを取得します。
     * @return 店舗情報DAO
     */
    public UIOfferMiseInfoDao getUIOfferMiseInfoDao() {
        return uiOfferMiseInfoDao;
    }

    /**
     * 店舗情報DAOを設定します。
     * @param uiMiseInfoDao 店舗情報DAO
     */
    public void setUIOfferMiseInfoDao(UIOfferMiseInfoDao uiOfferMiseInfoDao) {
        this.uiOfferMiseInfoDao = uiOfferMiseInfoDao;
    }
    
    /**
     * 入力データチェックロジックを取得します。
     * @return 入力データチェックロジック
     */
    public CheckOfferInputParamLogic getCheckOfferInputParamLogic() {
        return checkOfferInputParamLogic;
    }

    /**
     * 入力データチェックロジックを設定します。
     * @param checkInputParam 入力データチェックロジック
     */
    public void setCheckInputParamLogic(CheckOfferInputParamLogic checkOfferInputParamLogic) {
        this.checkOfferInputParamLogic = checkOfferInputParamLogic;
    }
    
    /**
     * 店舗情報を取得
     * @param companyCd 会社コード
     * @param　onerCd オーナーコード
     * @param sysDate システム日付
     * @return  店舗リスト
     */
    
    public List execute(String companyCd, String onerCd, String sysDate) {
    	
    	//エラー処理：
    	if(getCheckOfferInputParamLogic().isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 システム日付
        }
    	if(getCheckOfferInputParamLogic().isNull(companyCd)){
            throw new NotNullException("会社コード"); //E0506 会社コード
        }
    	if(getCheckOfferInputParamLogic().isNull(onerCd)){
            throw new NotNullException("オーナーコード"); //E0506 オーナーコード
        }
    	
    	List tmpResult = getUIOfferMiseInfoDao().getOfferMiseInfo(companyCd, onerCd, sysDate);

    	if (tmpResult == null || tmpResult.size() == 0) {
    		throw new NotExistException("対象店舗"); //E0103 店舗情報
        }
        return tmpResult;
    }
}
