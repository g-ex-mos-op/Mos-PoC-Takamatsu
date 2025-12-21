/*
 * 作成日: 2006/3/8
 *
 */
package jp.co.isid.mos.bird.storeinfo.onerref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstOner;

/**
 * グループ統合オーナーマスタの検索（MstOnerDao）
 * @author itamoto
 */
public interface MstOnerDao {

    public static final Class BEAN = MstOner.class;
    public static final String selectOnerMst_ARGS  = "COMPANY_CD, ONER_CD, USER_ID, IS_LIMIT";
    public static final String selectOnerName_ARGS  = "companyCd, onerCd";

    /**
     * グループ統合オーナーマスタの検索(selectOnerMst)
     */
    public MstOner selectOnerMst(String CompanyCd, String OnerCd, String userId, boolean isLimit);
    
    public List selectOnerName(String companyCd, String onerCd);
}
