package jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UIBlockInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetBlockInfoLogic;

/**
 * 店舗情報取得ロジック
 * 
 * @author xlee
 */
public class GetBlockInfoLogicImpl implements GetBlockInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS027L02";

    /**
     * ブロック情報DAOを取得します。
     */
    private UIBlockInfoDao uiBlockInfoDao;

    /**
     * ブロック情報DAOを取得します。
     * @return ブロック情報DAO
     */
    public UIBlockInfoDao getUIBlockInfoDao() {
        return uiBlockInfoDao;
    }

    /**
     * ブロック情報DAOを設定します。
     * @param uiBlockInfoDao ブロック情報DAO
     */
    public void setUIBlockInfoDao(UIBlockInfoDao uiBlockInfoDao) {
        this.uiBlockInfoDao = uiBlockInfoDao;
    }

    /**
    * ブロックリストを取得
    * @return ブロックリスト
    */
    public List execute() {
    	List tmpResult = getUIBlockInfoDao().getBlockInfo();
        return tmpResult;
    }
}
