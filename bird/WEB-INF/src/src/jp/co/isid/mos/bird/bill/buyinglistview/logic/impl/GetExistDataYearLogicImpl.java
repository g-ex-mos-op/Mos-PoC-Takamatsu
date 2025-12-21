/*
 * 作成日: 2006/10/24
 *
 */
package jp.co.isid.mos.bird.bill.buyinglistview.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bill.buyinglistview.dao.UISeikyuPDFDao;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.GetExistDataYearLogic;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

/**
 *  請求データ存在年取得ロジック
 * 
 * @author kusama
 */
public class GetExistDataYearLogicImpl implements GetExistDataYearLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS012L07";

    /**
     * お買上明細報報DAO
     */
    private UISeikyuPDFDao uiSeikyuPDFDao;

    /**
     * お買上明細報報DAOを取得します。
     * @return お買上明細報報DAO
     */
    public UISeikyuPDFDao getUISeikyuPDFDao() {
        return uiSeikyuPDFDao;
    }

    /**
     * お買上明細報報DAOを設定します。
     * @param trnPLAuthorInfoDao お買上明細報報DAO
     */
    public void setUISeikyuPDFDao(UISeikyuPDFDao uiSeikyuPDFDao) {
        this.uiSeikyuPDFDao = uiSeikyuPDFDao;
    }

    /**
     * 請求データ存在年取得
     * @param　onerCd オーナーコード
     * @return 存在年情報
     */
    public List execute() {
        List tmpResultList = getUISeikyuPDFDao().getSeikyuYear();
        
        if (tmpResultList == null || tmpResultList.size() == 0) {
            throw new NoResultException(); //E0103　お買上明細
        }
        return tmpResultList;
    }
    
}