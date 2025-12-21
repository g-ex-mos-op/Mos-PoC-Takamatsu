/*
 * 作成日: 2006/03/20
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;

/**
 * P/LデータDAO
 * 
 * @author xyuchida
 */
public interface TrnPLInfoDao {

    public static final Class BEAN = TrnPLInfo.class;

    public static final String getPLInfo_ARGS = "plType, plYm, miseCd";
    public static final String getPLInfo_QUERY
            = "PL_TYPE = /*plType*/'1' and PL_YM = /*plYm*/'200601' and COMPANY_CD = '00' and MISE_CD = /*miseCd*/'00018'";

    public static final String getPLInfoStatus_ARGS = "plYm, onerCd";

    public static final String getExistMiseCount_ARGS = "onerCd, miseCd";
    public static final String getNotClosedMiseCount_ARGS = "plYm, miseCd";

    /**
     * P/Lデータ取得
     * @param plType P/Lの種類
     * @param plYm 対象年月
     * @param miseCd 店コード
     * @return P/Lデータ
     */
    public TrnPLInfo getPLInfo(String plType, String plYm, String miseCd);

    /**
     * P/Lデータ登録状況取得
     * @param plYm 対象年月
     * @param onerCd オーナーコード
     * @return P/Lデータリスト
     */
    public List getPLInfoStatus(String plYm, String onerCd);

    /**
     * P/Lデータ登録
     * @param trnPLInfo 登録P/Lデータ
     */
    public void insertPLInfo(TrnPLInfo trnPLInfo);

    /**
     * P/Lデータ削除
     * @param trnPLInfo 削除P/Lデータ
     */
    public void deletePLInfo(TrnPLInfo trnPLInfo);

    /**
     * オーナーコード存在判定
     * @param onerCd オーナーコード
     * @return オーナーコード存在有無  > 0 : 存在  = 0 : 存在しない
     */
    public int getExistOwnerCount(String onerCd);

    /**
     * 指定店存在判定
     * @param onerCd オーナーコード
     * @param miseCd 店コード
     * @return 店存在有無  > 0 : 存在  = 0 : 存在しない
     */
    public int getExistMiseCount(String onerCd, String miseCd);

    /**
     * 指定店未クローズ判定
     * @param plYm 対象年月
     * @param miseCd 店コード
     * @return 店存在有無  > 0 : 未クローズ  = 0 : クローズ
     */
    public int getNotClosedMiseCount(String plYm, String miseCd);

    /**
     * オーナー名称取得
     * @param onerCd オーナーコード
     * @return オーナー名称
     */
    public String getOwnerName(String onerCd);

    /**
     * 店名称取得
     * @param miseCd 店コード
     * @return 店名称
     */
    public String getMiseName(String miseCd);
}
