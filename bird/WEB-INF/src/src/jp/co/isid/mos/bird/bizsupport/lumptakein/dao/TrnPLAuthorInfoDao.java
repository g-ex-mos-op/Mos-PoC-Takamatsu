/*
 * 作成日: 2006/03/17
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.dao;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLAuthorInfo;

/**
 * P/Lデータ作成者情報DAO
 * 
 * @author xyuchida
 */
public interface TrnPLAuthorInfoDao {

    public static final Class BEAN = TrnPLAuthorInfo.class;

    public static final String getAuthor_QUERY ="COMPANY_CD = '00' and ONER_CD = /*onerCd*/'36005'";

    /**
     * P/L作成者情報取得
     * @param onerCd オーナーコード
     * @return P/L作成者情報
     */
    public TrnPLAuthorInfo getAuthor(String onerCd);

    /**
     * P/L作成者情報登録
     * @param trnPLAuthorInfo 登録P/Lデータ
     */
    public void insertAuthor(TrnPLAuthorInfo trnPLAuthorInfo);

    /**
     * P/L作成者情報削除
     * @param trnPLAuthorInfo 削除P/Lデータ
     */
    public void deleteAuthor(TrnPLAuthorInfo trnPLAuthorInfo);
}
