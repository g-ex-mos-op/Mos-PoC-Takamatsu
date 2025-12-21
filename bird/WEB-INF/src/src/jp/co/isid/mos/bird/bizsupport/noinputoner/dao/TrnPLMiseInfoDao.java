package jp.co.isid.mos.bird.bizsupport.noinputoner.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.TrnPLMiseInfo;;

/**
 * P/L店舗データDAO
 * 
 * @author Aspac
 */
public interface TrnPLMiseInfoDao {

    public static final Class BEAN = TrnPLMiseInfo.class;

    public static final String getExistMiseInfo_ARGS = "companyCd, targetDt, closeMiseFlg, aryMise";


    /**
     * 店舗取得
     * @param onerCd オーナーコード
     * @param miseCd 店コード
     * @return 
     */
    public List getExistMiseInfo(String companyCd, String targetDt, boolean closeMiseFlg, List aryMise);

}
