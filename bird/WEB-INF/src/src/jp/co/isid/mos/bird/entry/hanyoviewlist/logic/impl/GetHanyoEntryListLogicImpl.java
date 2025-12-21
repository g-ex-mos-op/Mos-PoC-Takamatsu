/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoviewlist.dao.UIHanyoEntryListDao;
import jp.co.isid.mos.bird.entry.hanyoviewlist.dao.UIHanyoEntryCsvListDao;
import jp.co.isid.mos.bird.entry.hanyoviewlist.logic.GetHanyoEntryListLogic;
import jp.co.isid.mos.bird.entry.hanyoviewlist.entity.UIHanyoEntryInfo;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 研修(出張/更新)一覧取得ロジック
 * @author Nakajima
 */
public class GetHanyoEntryListLogicImpl implements GetHanyoEntryListLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BEN006L02";

    /**
     * 研修(出張/更新)エントリー者一覧情報（UIHanyoEntryListDao）
     */
    private UIHanyoEntryListDao uIHanyoEntryListDao;
    
    /**
     * 研修(出張/更新)エントリーCSV一覧情報（UIHanyoEntryCsvListDao）
     */
    private UIHanyoEntryCsvListDao uIHanyoEntryCsvListDao;
    
	/**
	 * 研修(出張/更新)エントリー者一覧Daoの設定
	 * @return codCompanyJohoDao を戻します。
	 */
	public UIHanyoEntryListDao getUIHanyoEntryListDao() {
		return uIHanyoEntryListDao;
	}
	/**
	 * 研修(出張/更新)エントリー者一覧Daoの設定
	 * @param UIHanyoEntryListDao uIHanyoEntryListDao を設定。
	 */
	public void setUIHanyoEntryListDao(UIHanyoEntryListDao uIHanyoEntryListDao) {
		this.uIHanyoEntryListDao = uIHanyoEntryListDao;
	}
    
    /**
     * 研修(出張/更新)エントリーCSV一覧Daoの設定
     * @return codCompanyJohoDao を戻します。
     */
    public UIHanyoEntryCsvListDao getUIHanyoEntryCsvListDao() {
        return uIHanyoEntryCsvListDao;
    }
    /**
     * 研修(出張/更新)エントリーCSV一覧Daoの設定
     * @param UIHanyoEntryCsvListDao uIHanyoEntryCsvListDao を設定。
     */
    public void setUIHanyoEntryCsvListDao(UIHanyoEntryCsvListDao uIHanyoEntryCsvListDao) {
        this.uIHanyoEntryCsvListDao = uIHanyoEntryCsvListDao;
    }

	/**
	 * 研修(出張/更新)エントリー者一覧の検索
	 * @return 検索結果
	 */
	public List execute(String sysdate, String entryCd, String entryYear, String entryKai, String kbn) {
        
        List hanyoList;
        
        if(!kbn.equals("CSV")){
            // 研修(出張/更新)一覧取得
            hanyoList = uIHanyoEntryListDao.getHanyoEntryInfo(sysdate, entryCd, entryYear, entryKai);
        }else{
            // CSV研修(出張/更新)データ取得
            hanyoList = uIHanyoEntryCsvListDao.getHanyoEntryCsvInfo(sysdate, entryCd, entryYear, entryKai);
        }

		return hanyoList;
	}
}
