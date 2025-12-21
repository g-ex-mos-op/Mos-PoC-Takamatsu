/*
 * 作成日: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.businesssearch.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.businesssearch.dao.UIGyotaiDao;
import jp.co.isid.mos.bird.commonform.businesssearch.logic.SearchBusinessResultLogic;

/**
 * @author xyuchida
 *
 */
public class SearchBusinessResultLogicImpl implements SearchBusinessResultLogic {

    public static final String LOGIC_ID = "BCO007L03";

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
     * @param gyotaiKbnList 業態区分リスト
     */
    public List execute(List gyotaiKbnList) {
        if (gyotaiKbnList == null || gyotaiKbnList.size() <= 0) {
            return new ArrayList();
        }
        return getUiGyotaiDao().selectGyotaiResult(gyotaiKbnList);
    }
}
