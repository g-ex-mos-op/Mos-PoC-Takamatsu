/*
 * 作成日: 2007/12/11
 *
 */
package jp.co.isid.mos.bird.bill.buyinglistview.logic;


/**
 * オーナー保有店舗数取得
 * 
 * @author kusama
 */
public interface GetOnerMiseCntLogic {

    /**
     * オーナーコード取得
     * @return　UISeikyuPDFInfo
     */
    public int execute(String onerCd, String fromDt, String toDt);
}