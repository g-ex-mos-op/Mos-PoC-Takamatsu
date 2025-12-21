/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.basicviewlist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.basicviewlist.dao.UIBasicEntryListDao;
import jp.co.isid.mos.bird.entry.basicviewlist.dao.UIBasicEntryCsvListDao;
import jp.co.isid.mos.bird.entry.basicviewlist.logic.GetBasicEntryListLogic;
import jp.co.isid.mos.bird.entry.basicviewlist.entity.UIBasicEntryInfo;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * ベーシック研修一覧取得ロジック
 * @author Nakajima
 */
public class GetBasicEntryListLogicImpl implements GetBasicEntryListLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BEN003L02";

    /**
     * ベーシック研修エントリー者一覧情報（UIBasicEntryListDao）
     */
    private UIBasicEntryListDao uIBasicEntryListDao;
    
    /**
     * ベーシック研修エントリーCSV一覧情報（UIBasicEntryCsvListDao）
     */
    private UIBasicEntryCsvListDao uIBasicEntryCsvListDao;
    
	/**
	 * ベーシック研修エントリー者一覧Daoの設定
	 * @return codCompanyJohoDao を戻します。
	 */
	public UIBasicEntryListDao getUIBasicEntryListDao() {
		return uIBasicEntryListDao;
	}
	/**
	 * ベーシック研修エントリー者一覧Daoの設定
	 * @param UIBasicEntryListDao uIBasicEntryListDao を設定。
	 */
	public void setUIBasicEntryListDao(UIBasicEntryListDao uIBasicEntryListDao) {
		this.uIBasicEntryListDao = uIBasicEntryListDao;
	}
    
    /**
     * ベーシック研修エントリーCSV一覧Daoの設定
     * @return codCompanyJohoDao を戻します。
     */
    public UIBasicEntryCsvListDao getUIBasicEntryCsvListDao() {
        return uIBasicEntryCsvListDao;
    }
    /**
     * ベーシック研修エントリーCSV一覧Daoの設定
     * @param UIBasicEntryCsvListDao uIBasicEntryCsvListDao を設定。
     */
    public void setUIBasicEntryCsvListDao(UIBasicEntryCsvListDao uIBasicEntryCsvListDao) {
        this.uIBasicEntryCsvListDao = uIBasicEntryCsvListDao;
    }

	/**
	 * ベーシック研修エントリー者一覧の検索
	 * @return 検索結果
	 */
	public List execute(String sysdate, String entryCd, String entryYear, String entryKai, String kbn) {
        
        List basicList;
        
        if(!kbn.equals("CSV")){
            // ベーシック研修一覧取得
            basicList = uIBasicEntryListDao.getBasicEntryInfo(sysdate, entryCd, entryYear, entryKai);
        }else{
            // CSVベーシック研修データ取得
            basicList = uIBasicEntryCsvListDao.getBasicEntryCsvInfo(sysdate, entryCd, entryYear, entryKai);
        }

		return basicList;
	}
}
