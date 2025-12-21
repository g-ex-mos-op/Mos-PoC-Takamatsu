/*
 * ì¬“ú: 2008/07/22
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UINoInputMeter;

/**
 * –¢“ü—Í“X•Üˆê——Dao
 * 
 * @author xnkusama
 */
public interface UINoInputMeterDao {

    public static final Class BEAN = UINoInputMeter.class;
    public static final String selectNoInput_ARGS = "companyCd, targetYM, targetYMZengetu, sibuCd, meterKbn, userId, limitFlg";
    public static final String selectUnusualMeter_ARGS = "companyCd, targetYM, targetYMZengetu, sibuCd, meterKbn, userId, limitFlg";

    /**
     * –¢“ü—Í“X•Üˆê——æ“¾
     * @param companyCd
     * @param targetYM
     * @param targetYMZengetu
     * @param sibuCd
     * @param meterKbn
     * @param userId
     * @param limitFlg
     * @return
     */
    public List selectNoInput(String companyCd, 
                               String targetYM, 
                               String targetYMZengetu, 
                               String sibuCd, 
                               String meterKbn,
                               String userId,
                               boolean limitFlg);

    /**
     * ˆÙí’l“X•Üˆê——æ“¾
     * @param companyCd
     * @param targetYM
     * @param targetYMZengetu
     * @param sibuCd
     * @param meterKbn
     * @param userId
     * @param limitFlg
     * @return
     */
    public List selectUnusualMeter(String companyCd, 
                                     String targetYM, 
                                     String targetYMZengetu, 
                                     String sibuCd, 
                                     String meterKbn,
                                     String userId,
                                     boolean limitFlg);
}