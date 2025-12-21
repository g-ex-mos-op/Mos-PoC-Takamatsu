/*
 * 作成日: 2005/01/20
 */
package jp.co.isid.mos.bird.commonform.documentsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;

/**
 * 文書情報取得
 * @author m.onodera
 */
public interface UIDocSearchDao {

    public Class BEAN = UIDocSearch.class;
    public static final String selectNtcm_ARGS = "INFO_SHU, CATE_ID, SUB_CATE_ID, TITLE, regDate, fromRowNumber, toRowNumber";
    public static final String selectDocm_ARGS = "sysDate, INFO_SHU, CATE_ID, SUB_CATE_ID, TITLE, regDate, fromRowNumber, toRowNumber";
    public static final String selectFrmm_ARGS = "sysDate, INFO_SHU, CATE_ID, SUB_CATE_ID, TITLE, regDate, fromRowNumber, toRowNumber";

    /**
     * 検索条件より文書情報を取得
     * @param infoShu     登録日
     * @param cateId      カテゴリID
     * @param subCateId   サブカテゴリID
     * @param title       タイトル
     * @param date        発行日or掲載期間FROM
     * @return List      検索結果
     */

    //タイプが通達の場合
    public List selectNtcm(String infoShu
                          , String cateId
                          , String subCateId
                          , String title
                          , String regDate
                          , int fromRowNumber
                          , int toRowNumber);
    //タイプが文書の場合
    public List selectDocm(String sysDate
				 		  , String infoShu
                          , String cateId
                          , String subCateId
                          , String title
                          , String regDate
                          , int fromRowNumber
                          , int toRowNumber);
    //タイプがフォームの場合
    public List selectFrmm(String sysDate
				 		  , String infoShu
                          , String cateId
                          , String subCateId
                          , String title
                          , String regDate
                          , int fromRowNumber
                          , int toRowNumber);
}
