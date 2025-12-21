/*
 * 作成日: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.businesssearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.businesssearch.dao.CtlManagementCompanyDao;
import jp.co.isid.mos.bird.commonform.businesssearch.logic.SearchCompanyListLogic;

/**
 * @author xyuchida
 * @modifier xkinu 2013/01/24 海外売上集信対応　海外の会社は含まないよう改修
 *
 */
public class SearchCompanyListLogicImpl implements SearchCompanyListLogic {

    public static final String LOGIC_ID = "BCO007L01";

    /**
     * 管理会社情報DAO
     */
    private CtlManagementCompanyDao ctlManagementCompanyDao;

    /**
     * 管理会社情報DAOを取得します。
     * 
     * @return 管理会社情報DAO
     */
    public CtlManagementCompanyDao getCtlManagementCompanyDao() {
        return ctlManagementCompanyDao;
    }

    /**
     * 管理会社情報DAOを設定します。
     * 
     * @param ctlManagementCompanyDao 管理会社情報DAO
     */
    public void setCtlManagementCompanyDao(
            CtlManagementCompanyDao ctlManagementCompanyDao) {
        this.ctlManagementCompanyDao = ctlManagementCompanyDao;
    }

    /**
     * 管理会社リスト取得
     * 
     * @modifier xkinu 2013/01/24 海外売上集信対応　海外フラグfalseを追加
     */
    public List execute() {
    	boolean isForeignIn = false;
        return getCtlManagementCompanyDao().select(isForeignIn);
    }
}
