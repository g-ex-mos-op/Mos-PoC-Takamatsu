/*
 * 作成日: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.businesssearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.businesssearch.dao.UIGyotaiDao;
import jp.co.isid.mos.bird.commonform.businesssearch.logic.SearchBusinessListLogic;

/**
 * @author xyuchida
 *
 */
public class SearchBusinessListLogicImpl implements SearchBusinessListLogic {

    public static final String LOGIC_ID = "BCO007L02";

    /**
     * 業態情報DAO
     */
    private UIGyotaiDao uiGyotaiDao;

    /**
     * 業態情報DAOを取得します。
     * 
     * @return 業態情報DAO
     */
    public UIGyotaiDao getUiGyotaiDao() {
        return uiGyotaiDao;
    }

    /**
     * 業態情報DAOを設定します。
     * 
     * @param uiGyotaiDao 業態情報DAO
     */
    public void setUiGyotaiDao(UIGyotaiDao uiGyotaiDao) {
        this.uiGyotaiDao = uiGyotaiDao;
    }

    /**
     * 業態リスト取得
     * 
     * @param companyCd 管理会社企業コード
     */
    public List execute(String companyCd) {
        return getUiGyotaiDao().select(companyCd);
    }
}
