/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceoffer.dao.UIOfferMiseInfoDao;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferCommon;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferConstants;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.SearchOfferMiseLogic;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferMise;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 店舗情報取得ロジック
 * 
 * @author xlee
 */
public class SearchOfferMiseLogicImpl implements SearchOfferMiseLogic{

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN018L05";

    /**
     * 店舗情報DAOを取得します。
     */
    private UIOfferMiseInfoDao uiOfferMiseInfoDao;

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
     * 店舗情報を取得
     * @param companyCd 会社コード
     * @param　onerCd オーナーコード
     * @param sysDate システム日付
     * @return  店舗リスト
     */
    public List execute(String companyCd, String onerCd, String sysDate) {
    	
    	//エラー処理：
    	if(LongserviceOfferCommon.isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 システム日付
        }
    	if(LongserviceOfferCommon.isNull(companyCd)){
            throw new NotNullException("会社コード"); //E0506 会社コード
        }
    	if(LongserviceOfferCommon.isNull(onerCd)){
            throw new NotNullException("オーナーコード"); //E0506 オーナーコード
        }
	
    	List miseList = getUIOfferMiseInfoDao().getOfferMiseInfo(companyCd, onerCd, sysDate);

    	if (miseList == null || miseList.size() == 0) {
    		throw new NotExistException("対象店舗"); //E0103 店舗情報
        }
    	
    	
    	List result = new ArrayList();
    	    	
    	UIOfferMise noSelectData = new UIOfferMise();
    	noSelectData.setMiseCd(LongserviceOfferConstants.ZERO);
    	noSelectData.setMiseNameKj(LongserviceOfferConstants.EMPTY);
    	result.add(noSelectData);
    	
    	// 店一覧をデフォルト情報が格納されたリストへ追加する。
        for(int i = 0;i < miseList.size(); i++){
        	UIOfferMise uIOfferMise = (UIOfferMise)miseList.get(i);
    		result.add(uIOfferMise);
    	}
    	    	
        return result;
        //return miseList;
    }
    
    /**
     * 店舗名を取得
     * @param miseCd 店舗コード
     * @return  店舗名
     */
    public String getMiseName(List miseList,String miseCd) {
    	
    	// 店一覧から店舗コードが一致するデータを取得する
        for(int i = 0;i < miseList.size(); i++){
        	UIOfferMise uIOfferMise = (UIOfferMise)miseList.get(i);
        	// 一致する店データの店舗名を返却する
        	if (!LongserviceOfferCommon.isNull(miseCd)){
	        	if(uIOfferMise.getMiseCd().equals(miseCd.trim())){
	        		return uIOfferMise.getMiseNameKj();
	        	}
        	}
    	}
    	
    	return null;
    }
}
