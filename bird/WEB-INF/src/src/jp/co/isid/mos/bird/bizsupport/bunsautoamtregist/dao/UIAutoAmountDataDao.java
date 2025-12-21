/*
 * ì¬“ú: 2006/04/14
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity.UIAutoAmountData;


/**
 * ƒoƒ“ƒY©“®İ’èî•ñæ“¾
 * 
 * @author xkinu
 */
public interface UIAutoAmountDataDao {

    public static final Class BEAN = UIAutoAmountData.class;
    public static final String select_ARGS = "userId, limitKbn, sysDate, companyCd, taishoJoken, onerCd, miseCd, svCd";
    /**@“X•Üˆê——æ“¾SQLˆø” */
    public static final String selectMiseList_ARGS = "userId, limitKbn, sysDate, companyCd, taishoJoken, onerCd, miseCd, svCd";
    public static final String selectAll_ARGS = "userId, limitKbn, sysDate, companyCd, taishoJoken, onerCd, miseCd, svCd";
    public static final String selectUnset_ARGS = "userId, limitKbn, sysDate, companyCd, taishoJoken, onerCd, miseCd, svCd";
    public static final String insert_ARGS = "entity";
    public static final String update_ARGS = "entity";

    /**
     * ƒoƒ“ƒY©“®İ’èŒŸõˆ—
     * 
     * @param userId
     * @param limitKbn
     * @param sysDate
     * @param companyCd
     * @param taishoJoken
     * @param onerCd
     * @param miseCd
     * @param svCd
     * @return
     */
    public List select(String userId, boolean limitKbn, String sysDate
            , String companyCd, String taishoJoken, String onerCd, String miseCd, String svCd);
    /**
     * ƒoƒ“ƒY©“®İ’èŒŸõˆ—
     * 
     * @param userId
     * @param limitKbn
     * @param sysDate
     * @param companyCd
     * @param taishoJoken
     * @param onerCd
     * @param miseCd
     * @param svCd
     * @return
     */
    public List selectMiseList(String userId, boolean limitKbn, String sysDate
            , String companyCd, String taishoJoken, String onerCd, String miseCd, String svCd);
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
    /**
     * V‹K“o˜^
     * 
     * @param entity
     * @return
     */
    public int insert(UIAutoAmountData entity);
    /**
     * XV“o˜^
     * 
     * @param entity
     * @return
     */
    public int update(UIAutoAmountData entity);
    
}            
