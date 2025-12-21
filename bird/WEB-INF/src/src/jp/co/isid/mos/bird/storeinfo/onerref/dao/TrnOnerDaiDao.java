/*
 * 作成日: 2006/3/8
 *
 */
package jp.co.isid.mos.bird.storeinfo.onerref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.onerref.entity.TrnOnerDai;

/**
 * オーナー代表者情報履歴（TrnOnerDaiDao）
 * @author itamoto
 */
public interface TrnOnerDaiDao {

    public static final Class BEAN = TrnOnerDai.class;
    public static final String selectOnerDaiRireki_ARGS  = "COMPANY_CD, ONER_CD";

    /**
     * オーナー代表者情報履歴の検索(selectOnerDaiRireki)
     */
    public List selectOnerDaiRireki(String CompanyCd, String OnerCd);
}
