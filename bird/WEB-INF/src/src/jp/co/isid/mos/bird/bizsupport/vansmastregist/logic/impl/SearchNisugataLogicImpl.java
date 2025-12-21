/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.dao.UINisugataInfoDao;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SearchNisugataLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;


/**
 * 荷姿情報リスト取得ロジック
 * @author narita
 */
public class SearchNisugataLogicImpl implements SearchNisugataLogic {

    /** ロジックID 定義 */
	public static final String LOGIC_ID = "BBS029L03";

	/** バンズ倉庫別登録 荷姿情報取得DAO */
    private UINisugataInfoDao uINisugataInfoDao;

    /**
     * 荷姿情報のリストを取得する
     * return nisugataList 荷姿情報のリスト
     */
    public List execute() {

    	List nisugataList = getUINisugataInfoDao().getNisugataList();
    	
    	if (nisugataList == null || nisugataList.size() == 0) {
    		throw new NotExistException("荷姿情報"); //E0103 荷姿情報
        }
    	
    	return nisugataList;
    }
    
	public UINisugataInfoDao getUINisugataInfoDao() {
		return uINisugataInfoDao;
	}

	public void setUINisugataInfoDao(UINisugataInfoDao nisugataInfoDao) {
		uINisugataInfoDao = nisugataInfoDao;
	}
}
