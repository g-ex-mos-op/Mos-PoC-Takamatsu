/*
 * 作成日: 2006/3/16
 */
package jp.co.isid.mos.bird.bizsupport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;


/**
 * PLデータ情報(TrnPLInfoDao)
 * @author itamoto
 */
public interface TrnPLInfoDao {

    public static final Class BEAN = TrnPLInfo.class;
    public static final String getPLInfo_ARGS  = "MISE_CD, PL_YM, PL_TYPE";
    public static final String getPLInfoStatus_ARGS = "plYm, onerCd, closeMiseFlg";
    
    /**
     * PLデータの取得(getPLInfo)
     * @param miseCd
     * @param plYm
     * @param plType
     * @return TrnPLInfo 検索結果
     */
    public TrnPLInfo getPLInfo (String miseCd, String plYm, String plType);
    
    public void insertPLInfo(TrnPLInfo trnPLInfo);

    public void deletePLInfo(TrnPLInfo trnPLInfo);
    
    
    /**
     * P/Lデータ登録状況取得
     * @param plYm 対象年月
     * @param onerCd オーナーコード
     * @param closeMiseFlg クローズ店フラグ true:クローズ店を含む false:含まない
     * @return P/Lデータリスト
     */
    public List getPLInfoStatus(String plYm, String onerCd, boolean closeMiseFlg);
    
    
}
