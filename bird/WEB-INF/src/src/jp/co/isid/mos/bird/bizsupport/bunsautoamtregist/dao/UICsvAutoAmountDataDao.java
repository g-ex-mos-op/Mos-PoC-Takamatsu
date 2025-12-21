/*
 * ì¬“ú: 2008/02/13
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity.UICsvAutoAmountData;


/**
 * ƒoƒ“ƒY©“®İ’èî•ñæ“¾
 * 
 * @author xnkusama
 */
public interface UICsvAutoAmountDataDao {

    public static final Class BEAN = UICsvAutoAmountData.class;
    public static final String selectAll_ARGS = "userId, limitKbn, sysDate, companyCd, taishoJoken, onerCd, miseCd, svCd";
    public static final String selectUnset_ARGS = "userId, limitKbn, sysDate, companyCd, taishoJoken, onerCd, miseCd, svCd";

    /**
     * ‘S“Xˆê——ŒŸõˆ—
     * 
     * @param userId
     * @param limitKbn
     * @param sysDate
     * @param companyCd
     * @return
     */
    public List selectAll(String userId, boolean limitKbn, String sysDate
            , String companyCd, String taishoJoken, String onerCd, String miseCd, String svCd);

    /**
     * –¢İ’è“X•Üˆê——ŒŸõˆ—
     * 
     * @param userId
     * @param limitKbn
     * @param sysDate
     * @return
     */
    public List selectUnset(String userId, boolean limitKbn, String sysDate
            , String companyCd, String taishoJoken, String onerCd, String miseCd, String svCd);
}