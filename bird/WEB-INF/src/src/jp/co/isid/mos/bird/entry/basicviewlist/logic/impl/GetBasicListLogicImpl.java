/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.basicviewlist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.basicviewlist.dao.UIBasicListDao;
import jp.co.isid.mos.bird.entry.basicviewlist.logic.GetBasicListLogic;
import jp.co.isid.mos.bird.entry.basicviewlist.entity.UIBasicListDataInfo;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * ベーシック研修一覧取得ロジック
 * @author Nakajima
 */
public class GetBasicListLogicImpl implements GetBasicListLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BEN003L01";

    /**
     * ベーシック研修一覧情報（UIOnerTenpoDao）
     */
    private UIBasicListDao uIBasicListDao;
    
	/**
	 * ベーシック研修一覧Daoの設定
	 * @return codCompanyJohoDao を戻します。
	 */
	public UIBasicListDao getUIBasicListDao() {
		return uIBasicListDao;
	}
	/**
	 * ベーシック研修一覧Daoの設定
	 * @param UIHanyoListDao uIBasicListDao を設定。
	 */
	public void setUIBasicListDao(UIBasicListDao uIBasicListDao) {
		this.uIBasicListDao = uIBasicListDao;
	}

	/**
	 * ベーシック研修一覧の検索
	 * @return 検索結果
	 */
	public List execute(String sysdate, String sysNextDate, String entryCd) {
        
		// ベーシック研修一覧取得
        List basicList = uIBasicListDao.getBasicListInfo(sysdate, sysNextDate, entryCd);
        
		return basicList;
	}
}
