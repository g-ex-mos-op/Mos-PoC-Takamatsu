package jp.co.isid.mos.bird.entry.mlentry.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetMlListLogic;

/**
 * マスターライセンス一覧取得ロジック
 * @author Aspac
 */
public class GetMlListLogicImpl implements GetMlListLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BEN008L03";

    /**
     * マスターライセンス一覧情報
     */
    private UIEntryMstDao uiEntryMstMlEntryDao;
    
	/**
	 * マスターライセンス一覧の検索
	 * @return 検索結果
	 */
	public List execute(String sysdate, String sysNextDate, String entryCd, String onerCd, String userTypeCd) {
        
		// マスターライセンス一覧取得
        List mlList = getUiEntryMstMlEntryDao()
                            .getMlListInfo(sysdate,sysNextDate, entryCd, onerCd, userTypeCd);
        
		return mlList;
	}

    public UIEntryMstDao getUiEntryMstMlEntryDao() {
        return uiEntryMstMlEntryDao;
    }

    public void setUiEntryMstMlEntryDao(UIEntryMstDao uiMlEntryMlListDao) {
        this.uiEntryMstMlEntryDao = uiMlEntryMlListDao;
    }
}
