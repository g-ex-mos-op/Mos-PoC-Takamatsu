package jp.co.isid.mos.bird.bizsupport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.plregist.entity.MSTUserMiseInfo;

/**
 * ユーザー対応店舗情報(MSTUserOnerMiseInfoDao)
 * @author Aspac
 */
public interface MSTUserMiseInfoDao {

    public static final Class BEAN = MSTUserMiseInfo.class;
    public static final String getMiseCdByClose_ARGS  = "closeMiseFlg, onerCd, targetYM";
  
    
    /**
     * ユーザー対応店舗情報の取得(getMiseCd)
     * @param boolean closeMiseFlg
     * @param onerCd
     * @param targetYM
     * @return List 検索結果
     */
    public List getMiseCdByClose (boolean closeMiseFlg, String onerCd, String targetYM);
    
}
