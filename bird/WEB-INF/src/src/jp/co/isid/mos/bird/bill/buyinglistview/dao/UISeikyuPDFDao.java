/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.buyinglistview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bill.buyinglistview.entity.UISeikyuPDFInfo;

/**
 * オーナーコード情報
 *
 * @author xlee
 */
public interface UISeikyuPDFDao {

    public static final Class BEAN = UISeikyuPDFInfo.class;
// modify 2023/02/14 USI範 begin
    //public static final String getSeikyuPDFInfo_ARGS = "onerCd,frDate,toDate";
    public static final String getSeikyuPDFInfo_ARGS = "onerCd,frDate,toDate,kinGaku,kinGakuTo";


//    /**
//     * 請求書PDFファイル情報取得
//     * @param onerCd オーナーコード
//     * @param toDate 検収日付TO
//     * @param frDate　検収日付FROM
//     * @param sortKbn ソート区分
//     * @return 請求書PDF情報
//     */
    /**
     * 請求書PDFファイル情報取得
     * @param onerCd オーナーコード
     * @param toDate 対象期間TO
     * @param frDate　対象期間FROM
     * * @param toDate 金額TO
     * @param frDate　金額FROM
     * @param sortKbn ソート区分
     * @return 請求書PDF情報
     */
    //public List getSeikyuPDFInfo(String onerCd,String frDate, String toDate);
    public List getSeikyuPDFInfo(String onerCd,String frDate, String toDate,String kinGaku, String kinGakuTo);
// modify 2023/02/14 USI範 end

    /**
     * 請求書データ存在年取得処理
     */
    public List getSeikyuYear();
}
