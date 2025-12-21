/*
 * 作成日: 2007/12/11
 *
 */
package jp.co.isid.mos.bird.bill.buyinglistview.logic.impl;

import jp.co.isid.mos.bird.bill.buyinglistview.dao.UISeikyuMeisaiDao;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.GetOnerMiseCntLogic;

/**
 *  請求データ存在年取得ロジック
 * 
 * @author kusama
 */
public class GetOnerMiseCntLogicImpl implements GetOnerMiseCntLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS012L08";

    private UISeikyuMeisaiDao uiSeikyuMeisaiDao;

    /**
     * オーナー保有店舗数取得処理
     * @param　onerCd オーナーコード
     * @return 店舗数
     */
    public int execute(String onerCd, String fromDt, String toDt) {
        return getUiSeikyuMeisaiDao().getMiseCnt(onerCd, fromDt, toDt);
    }

    public UISeikyuMeisaiDao getUiSeikyuMeisaiDao() {
        return uiSeikyuMeisaiDao;
    }

    public void setUiSeikyuMeisaiDao(UISeikyuMeisaiDao uiSeikyuMeisaiDao) {
        this.uiSeikyuMeisaiDao = uiSeikyuMeisaiDao;
    }
    
}