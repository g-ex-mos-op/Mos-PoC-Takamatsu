/*
 * 作成日: 2006/08/11
 * 
 */
package jp.co.isid.mos.bird.storemanage.msstantorankref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.UIRankingData;


/**
 * ランキング情報
 * 
 * @author xkinu
 */
public interface UIRankingDataDao {

    public static final Class BEAN = UIRankingData.class;

    public static final String select_ARGS = "userId, userTypeCd, limitFlg, searchType, nendo, kai, companyCd, svCd, onerCd, miseCd";
    
    /**
     * ランキング情報の取得
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param searchType
     * @param nendo
     * @param kai
     * @param companyCd
     * @param svCd
     * @param onerCd
     * @param miseCd
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
            , String searchType, String nendo, String kai, String companyCd, String svCd, String onerCd, String miseCd);

}