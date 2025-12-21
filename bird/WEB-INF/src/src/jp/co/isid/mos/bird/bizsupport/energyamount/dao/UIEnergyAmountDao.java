/*
 * 作成日: 2008/07/22
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UIEnergyAmount;

/**
 * エネルギー使用量Dao
 * 
 * @author xnkusama
 */
public interface UIEnergyAmountDao {

    public static final Class BEAN = UIEnergyAmount.class;
    public static final String selectNengetu_ARGS = "companyCd, targetYM, sibuCd, meterKbn, zenTargetYM, targetYMplus1, userTypeCd, userId, targetYMZengetu, limitFlg";
    public static final String selectNendo_ARGS = "companyCd, sibuCd, meterKbn, kikanFrom, kikanTo, zennenFrom, zennenTo, sysDate, userTypeCd, userId, limitFlg";
    
    /**
     * 年月指定検索
     * @param companyCd
     * @param targetYM
     * @param sibuCd
     * @param meterKbn
     * @param zenTargetYM
     * @param targetYMplus1
     * @param userTypeCd
     * @param userId
     * @param targetYMZengetu
     * @param limitFlg
     * @return List
     */
    public List selectNengetu(String companyCd, 
                                String targetYM, 
                                String sibuCd, 
                                String meterKbn,
                                String zenTargetYM,
                                String targetYMplus1,
                                String userTypeCd,
                                String userId,
                                String targetYMZengetu,
                                boolean limitFlg);
    
    /**
     * 年度指定検索
     * @param companyCd
     * @param sibuCd
     * @param meterKbn
     * @param kikanFrom
     * @param kikanTo
     * @param zennenFrom
     * @param zennenTo
     * @param sysDate
     * @param userTypeCd
     * @param userId
     * @param limitFlg
     * @return List
     */
    public List selectNendo(String companyCd, 
                             String sibuCd, 
                             String meterKbn, 
                             String kikanFrom,
                             String kikanTo,
                             String zennenFrom,
                             String zennenTo,
                             String sysDate,
                             String userTypeCd,
                             String userId,
                             boolean limitFlg);
}