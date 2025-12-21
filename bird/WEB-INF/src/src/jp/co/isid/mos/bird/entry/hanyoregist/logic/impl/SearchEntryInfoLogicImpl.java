/*
 * 作成日: 2006/5/31
 */
package jp.co.isid.mos.bird.entry.hanyoregist.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.common.dao.UIEntryCourseDao;
import jp.co.isid.mos.bird.entry.hanyoregist.common.HanyoRegistCommon;
import jp.co.isid.mos.bird.entry.hanyoregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.hanyoregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.hanyoregist.dao.UIEntryNoticeDao;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryNotice;
import jp.co.isid.mos.bird.entry.hanyoregist.logic.SearchEntryInfoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * エントリーマスタ管理の検索ロジック
 * @author itamoto
 */
public class SearchEntryInfoLogicImpl implements SearchEntryInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN004L04";


    
    
    /**
     * エントリー日付管理（UIEntryDateDao）
     */
    private UIEntryDateDao uiEntryDateDao;

    /**
     * エントリーマスタ管理（UIEntryMstDao）
     */
    private UIEntryMstDao uiEntryMstDao;

    /**
     * エントリーコース管理
     */
    private UIEntryCourseDao uiEntryCourseDao;
// add start inazawa 2007/01/09 マスタライセンス４次対応
    /**
     * エントリー文言情報
     */
    UIEntryNoticeDao entryNoticeDao;
    
// add end   
    /**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @return uiEntryDateDao を戻します。
	 */
	public UIEntryDateDao getUiEntryDateDao() {
		return uiEntryDateDao;
	}
	/**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @param uiEntryDateDao uiEntryDateDao を設定。
	 */
	public void setUiEntryDateDao(UIEntryDateDao uiEntryDateDao) {
		this.uiEntryDateDao = uiEntryDateDao;
	}

	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @return uiEntryMstDao を戻します。
	 */
	public UIEntryMstDao getUiEntryMstDao() {
		return uiEntryMstDao;
	}
	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @param uiEntryMstDao uiEntryMstDao を設定。
	 */
	public void setUiEntryMstDao(UIEntryMstDao uiEntryMstDao) {
		this.uiEntryMstDao = uiEntryMstDao;
	}

    /**
     * エントリーコース管理（UIEntryCourseDao）の設定
     * @return uiEntryCourseDao を戻します。
     */
    public UIEntryCourseDao getUiEntryCourseDao() {
        return uiEntryCourseDao;
    }
    /**
     * エントリーコース管理（UIEntryCourseDao）の設定
     * @param uiEntryCourseDao uiEntryCourseDao を設定。
     */
    public void setUiEntryCourseDao(UIEntryCourseDao uiEntryCourseDao) {
        this.uiEntryCourseDao = uiEntryCourseDao;
    }

	/**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto     
     * */
    public Map execute(String code, String year, String kai) {
        validate(code, year, kai);
        
        List listEntryKey = new ArrayList();
    	listEntryKey.add(code + year + kai);
//add start inazawa マスタライセンス４次対応
        // １．Dao【エントリー文言情報】
        UIEntryNotice uIEntryNotice = new UIEntryNotice();
        if(!code.equals(HanyoRegistCommon.KOUSIN_KENSHU)){
            uIEntryNotice =  getEntryNoticeDao().getEntryNotice(null, listEntryKey);
            if(uIEntryNotice == null) {
            	uIEntryNotice = new UIEntryNotice();
            }
        }
//add end        
    	// ２．Dao【エントリーマスタ管理．エントリーマスタ管理の取得】を実行する。
        List listEntryMst = getUiEntryMstDao().getEntry(null, listEntryKey);
        
    	// ３．Dao【エントリー日付管理．エントリー日付管理の取得】を実行する。
        List listEntryDate = getUiEntryDateDao().getEntryDate(null, listEntryKey);

        //エントリーコース管理
        List listEntryCourse = getUiEntryCourseDao().getEntryCourseList(code, year, kai);

        Map retMap = new HashMap();
        retMap.put("UIEntryMst", listEntryMst);
        retMap.put("UIEntryDate", listEntryDate);
        retMap.put("UIEntryCourse", listEntryCourse);
//      add start inazawa 2007/01/05 マスタライセンス４次対応
        retMap.put("UIEntryNotice", uIEntryNotice);
//      add end        
        
        return retMap;
    }

    /**
     * 必須、妥当性チェック
     * @param hanyoRegistDto
     */
    private void validate(String code, String year, String kai) throws ApplicationException{
        if (isNull(code)) {
            throw new NotNullException("エントリーコード");
        }
        if (isNull(year)) {
            throw new NotNullException("エントリー年");
        }
        if (isNull(kai)) {
            throw new NotNullException("エントリー回");
        }
    }

    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
//  add start inazawa 2007/01/09 マスタライセンス４次対応
    /**
     * エントリー文言情報の取得
     * @return entryNoticeDao
     */
    public UIEntryNoticeDao getEntryNoticeDao() {
        return entryNoticeDao;
    }
    /**
     * エントリー文言情報の設定
     * @param entryNoticeDao
     */
    public void setEntryNoticeDao(UIEntryNoticeDao entryNoticeDao) {
        this.entryNoticeDao = entryNoticeDao;
    }
//  add end
}