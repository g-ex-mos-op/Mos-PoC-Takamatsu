/*
 * 作成日: 2006/03/23
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.MstUserMiseInfo;

/**
 * 店情報DAO
 * 
 * @author xyuchida
 */
public interface MstUserMiseInfoDao {

    public static final Class BEAN = MstUserMiseInfo.class;

    public static final String getMiseCd_ARGS = "onerCd, plYm";

    /**
     * 店情報取得
     * @param onerCd オーナーコード
     * @param plYm 対象年月
     * @return 店情報リスト
     */
    public List getMiseCd(String onerCd, String plYm);
}
