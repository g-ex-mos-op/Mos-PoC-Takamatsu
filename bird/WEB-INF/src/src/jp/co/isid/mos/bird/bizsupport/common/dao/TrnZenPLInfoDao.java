/*
 * 作成日: 2006/3/16
 */
package jp.co.isid.mos.bird.bizsupport.common.dao;

import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;

/**
 * 前月PLデータ情報(TrnZenPLInfoDao)
 * @author itamoto
 */
public interface TrnZenPLInfoDao {

    public static final Class BEAN = TrnZenPLInfo.class;
    public static final String getZenPLInfo_ARGS  = "MISE_CD, PL_YM, PL_TYPE";

    /**
     * 前月PLデータの取得(getZenPLInfo)
     * @param miseCd
     * @param plYm
     * @param plType
     * @return TrnPLInfo 検索結果
     */
    public TrnZenPLInfo getZenPLInfo (String miseCd, String plYm, String plType);
}
