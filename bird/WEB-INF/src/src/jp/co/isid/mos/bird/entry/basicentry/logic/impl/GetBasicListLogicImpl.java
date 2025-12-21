/*
 * 作成日: 2006/06/16
 */
package jp.co.isid.mos.bird.entry.basicentry.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.basicentry.logic.GetBasicListLogic;

/**
 * ベーシック研修一覧取得ロジック
 * @author Nakajima
 */
public class GetBasicListLogicImpl implements GetBasicListLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BEN002L01";

    /**
     * ベーシック研修一覧情報
     */
    private UIEntryMstDao uiEntryMstBasicEntryDao;
    
	/**
	 * ベーシック研修一覧の検索
	 * @return 検索結果
	 */
	public List execute(String sysdate, String sysNextDate, String entryCd, String onerCd, String userTypeCd) {
        
		// ベーシック研修一覧取得
        List basicList = getUiEntryMstBasicEntryDao()
                            .getBasicListInfo(sysdate,sysNextDate, entryCd, onerCd, userTypeCd);
        
		return basicList;
	}

    public UIEntryMstDao getUiEntryMstBasicEntryDao() {
        return uiEntryMstBasicEntryDao;
    }

    public void setUiEntryMstBasicEntryDao(UIEntryMstDao uiBasicEntryBasicListDao) {
        this.uiEntryMstBasicEntryDao = uiBasicEntryBasicListDao;
    }
}
