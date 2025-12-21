/*
 * 作成日: 2006/08/03
 * 
 */
package jp.co.isid.mos.bird.storemanage.mssonerpointref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.UIPoint;


/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * 中分類総合得点情報
 * 
 * @author xkinu
 */
public interface UIPointDao {

    public static final Class BEAN = UIPoint.class;

    public static final String select_ARGS = "userId, userTypeCd, limitFlg, searchType, nendo, kai, companyCd, sibuCd, onerCd, miseCd";
    public static final String selectSv_ARGS = "userId, userTypeCd, limitFlg, nendo, kai, companyCd, sibuCd, svCd, miseCd";
    
    /**
     * 総合得点情報の取得
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param searchType
     * @param nendo
     * @param kai
     * @param companyCd
     * @param sibuCd
     * @param onerCd
     * @param miseCd
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
            , String searchType, String nendo, String kai, String companyCd, String[] sibuCd, String onerCd, String[] miseCd);

    /**
     * SV指定時総合得点情報の取得
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param nendo
     * @param kai
     * @param companyCd
     * @param sibuCd
     * @param svCd
     * @param miseCd
     * @return
     */
    public List selectSv(String userId, String userTypeCd, boolean limitFlg
            , String nendo, String kai, String companyCd, String sibuCd, String svCd, String[] miseCd);

}