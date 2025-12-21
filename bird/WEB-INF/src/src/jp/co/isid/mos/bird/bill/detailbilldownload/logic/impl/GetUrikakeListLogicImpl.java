/*
 * 作成日: 2006/08/04
 */
package jp.co.isid.mos.bird.bill.detailbilldownload.logic.impl;

import java.util.ArrayList;
import java.util.List;
import jp.co.isid.mos.bird.bill.detailbilldownload.dao.UIUrikakeListDao;
import jp.co.isid.mos.bird.bill.detailbilldownload.entity.UIUrikakeList;
import jp.co.isid.mos.bird.bill.detailbilldownload.logic.GetUrikakeListLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 売掛先リスト取得ロジック
 * 
 * @author xwatanabe
 */
public class GetUrikakeListLogicImpl implements GetUrikakeListLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS010L01";
    /** 売掛先一覧DAO */
    private UIUrikakeListDao uiUrikakeListDao;

     /**
     * 売掛先リスト取得ロジック
     * @param companyCd 会社コード
     * @param onerCd   オーナーコード
     * @return List
     */
    public List execute(String companyCd,String onerCd) {
 
        //パラメータチェック
        if (companyCd == null || companyCd.getBytes().length == 0) {
            throw new NotNullException("会社コード");
        }
        if (onerCd == null || onerCd.getBytes().length == 0) {
            throw new NotNullException("オーナーコード");
        }

        //売掛先一覧の取得
        List dbList = getUIUrikakeListDao().getUrikakeList(companyCd, onerCd);
        if (dbList == null || dbList.isEmpty()) {
            //存在しない時
            throw new NotExistException("ご請求先");
        }

        //-------------------
        //表示順の並び替え
        //-------------------
        List outputList = new ArrayList();
        List workList = new ArrayList();
        for (int i = 0; i < dbList.size(); i++) {
            
            //エンティティ取出
            UIUrikakeList entity = (UIUrikakeList)dbList.get(i);
            
            if (onerCd.equals(entity.getUrikakeCd())) {
                outputList.add(entity);
            }else{
                workList.add(entity);
            }
        }
        outputList.addAll(workList);
        return outputList;
    }

    /**
     * 売掛先一覧DAOを取得します。
     * @return 売掛先一覧DAO
     */
    public UIUrikakeListDao getUIUrikakeListDao() {
        return uiUrikakeListDao;
    }

    /**
     * 売掛先一覧DAOを設定します。
     * @param uiUrikakeListDao 売掛先一覧DAO
     */
    public void setUIUrikakeListDao(UIUrikakeListDao uiUrikakeListDao) {
        this.uiUrikakeListDao = uiUrikakeListDao;
    }
}
