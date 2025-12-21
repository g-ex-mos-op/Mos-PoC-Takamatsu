/*
 * 作成日: 2006/3/8
 *
 */
package jp.co.isid.mos.bird.storeinfo.onerref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstOnerHoyuMise;

/**
 * オーナー保有店舗一覧(MstOnerHoyuMise）
 * @author itamoto
 */
public interface MstOnerHoyuMiseDao {

    public static final Class BEAN = MstOnerHoyuMise.class;
    public static final String selectOnerHoyuMise_ARGS  = "COMPANY_CD, ONER_CD";

    /**
     * オーナー保有店舗一覧の検索(selectOnerHoyuMise)
     */
    public List selectOnerHoyuMise(String CompanyCd, String OnerCd);
}
