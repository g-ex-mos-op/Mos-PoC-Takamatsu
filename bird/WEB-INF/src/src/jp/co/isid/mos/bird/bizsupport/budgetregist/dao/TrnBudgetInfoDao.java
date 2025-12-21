package jp.co.isid.mos.bird.bizsupport.budgetregist.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.budgetregist.entity.TrnBudgetInfo;

/**
 * —\Zî•ñ‚ğXV‚µ‚Ü‚·B
 * 
 * @author Aspac
 */
public interface TrnBudgetInfoDao {

    public static final Class BEAN = TrnBudgetInfo.class;

    public static final String insert_ARGS  = "budgetInfo";
    public static final String updateBudgetInfo_ARGS  = "budgetInfo";
    public static final String updateBudgetInfoCancel_ARGS  = "companyCd, fromDate, toDate," +
                                                                 "lastUser, lastPgm, lastDate, lastTmsp";
    public static final String deleteBudgetInfo_ARGS  = "lstMise, companyCd, fromDate, toDate";
    public static final String selectBudgetInfoClearDone_ARGS = "companyCd, fromDate, toDate"; 
    public static final String selectTempMiseCdList_ARGS = "nendo, companyCd";

    /**
     * —\Zî•ñ‚ğ“o˜^(’Ç‰Á)‚·‚é
     * @return “Xî•ñƒŠƒXƒg
     */
    public int insert(TrnBudgetInfo budgetInfo);
    
    /**
     * —\Zî•ñ‚ğ“o˜^(XV)‚·‚é
     * @return “Xî•ñƒŠƒXƒg
     */
    public int updateBudgetInfo(TrnBudgetInfo budgetInfo);
    
    /**
     * —\Zî•ñ‚ğƒNƒŠƒA‚·‚é
     * @return “Xî•ñƒŠƒXƒg
     */
    public int updateBudgetInfoCancel(String companyCd, String fromDate, String toDate,
                                        String lastUser, String lastPgm, String lastDate, Timestamp lastTmsp);

    /**
     * —\Zî•ñ‚ğíœ‚·‚é
     * @return “Xî•ñƒŠƒXƒg
     */
    public int deleteBudgetInfo(List lstMise, String companyCd, String fromDate, String toDate);

    /**
     * —\ZƒNƒŠƒA—š—ğ‚ğŠm”F‚·‚é
     * @return ƒJƒEƒ“ƒg
     */
    public int selectBudgetInfoClearDone(String companyCd, String fromDate, String toDate);

    /**
     * ‰¼“X”ÔƒŠƒXƒgæ“¾
     * @param nendo ”N“x(YYYY)
     * @param companyCd ‰ïĞƒR[ƒh
     * @return ‰¼“X”ÔƒŠƒXƒg
     */
    public List selectTempMiseCdList(String nendo, String companyCd);
}
