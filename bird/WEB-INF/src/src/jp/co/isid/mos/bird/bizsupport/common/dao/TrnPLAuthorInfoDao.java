/*
 * 作成日: 2006/3/16
 */
package jp.co.isid.mos.bird.bizsupport.common.dao;

import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPLAuthorInfo;

/**
 * PL作成者情報(TrnPLAuthorInfoDao)
 * @author itamoto
 */
public interface TrnPLAuthorInfoDao {

    public static final Class BEAN = TrnPLAuthorInfo.class;
    public static final String getAuther_ARGS  = "ONER_CD";

    /**
     * 作成者情報の取得(getAuthor)
     * @param onerCd オーナコード
     * @return TrnPLAuthorInfo 検索結果
     */
    public TrnPLAuthorInfo getAuther (String onerCd);

    /**
     * 作成者情報の削除(deleteAuthor)
     * @param trnPLAuthorInfo エンティティ
     * @return int 検索結果
     */
    public int deleteAuthor (TrnPLAuthorInfo trnPLAuthorInfo);

    /**
     * 作成者情報の挿入(insertAuthor)
     * @param trnPLAuthorInfo エンティティ
     * @return int 検索結果
     */
    public int insertAuthor (TrnPLAuthorInfo trnPLAuthorInfo);

}
