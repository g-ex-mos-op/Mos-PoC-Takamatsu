/*
 * 作成日: 2006/08
 *
 */
package jp.co.isid.mos.bird.bill.buyinglistview.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bill.buyinglistview.dao.UISeikyuPDFDao;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.GetPDFListLogic;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

/**
 *  お買上明細報取得ロジック
 *
 * @author xlee
 */
public class GetPDFListLogicImpl implements GetPDFListLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS012L02";

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
// modify 2023/02/14 USI範 begin
//    /**
//     * お買上明細報報取得
//     * @param　onerCd オーナーコード
//     * @param toDate 対象期間TO
//     * @param frDate　対象期間FROM
//     * @return お買上明細報報
//     */
    /**
     * お買上明細報報取得
     * @return お買上明細報報
     */
//  public List execute(String onerCd, String frDate, String toDate) {
    public List execute(String onerCd, String frDate, String toDate, String kinGaku, String kinGakuTo) {
// modify 2023/02/14 USI範  end
// delete 2023/02/14 USI範  begin
//      public List execute(String onerCd, String frDate, String toDate) {
//        //エラー処理：
//       if(isNull(onerCd)){
//              throw new NotNullException("オーナーコード"); //E0506　オーナーコード
//          }
//          if(isNull(toDate)){
//              throw new NotNullException("検収日付"); //E0506　検収日付
//          }
//          if(isNull(frDate)){
//              throw new NotNullException("FROM日付"); //E0506 FROM日付
//          }
// delete 2023/02/14 USI範  end
// modify 2023/02/14 USI範 begin
//        List tmpResultList = getUISeikyuPDFDao().getSeikyuPDFInfo(onerCd, frDate, toDate);
        List tmpResultList = getUISeikyuPDFDao().getSeikyuPDFInfo(onerCd, frDate, toDate,kinGaku,kinGakuTo);
// modify 2023/02/14 USI範  end

        if (tmpResultList == null || tmpResultList.size() == 0) {
            throw new NoResultException(); //E0103　お買上明細
        }
        return tmpResultList;
    }
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }


}
