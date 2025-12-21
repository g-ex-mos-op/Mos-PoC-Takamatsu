/*
 * ì¬“ú: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefYMDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.entity.TrnBudgetRegistDummy;
;

/**
 * —\Z“o˜^î•ñæ“¾
 * @author inazawa
 */
public interface TrnBudgetRegistDummyDao {
    
    public static final Class BEAN = TrnBudgetRegistDummy.class;
    
    

    public static final String getYosanJituStore_ARGS  = "companyCd,dto";

    public static final String getYosanKariStore_ARGS= "companyCd,dto";
    
    public static final String getYosanStore_ARGS = "companyCd,dto";

    public static final String getMeisaiDL_ARGS = "companyCd,dto";

    public static final String getTukiBetuAllMiseTotal_ARGS = "companyCd,dto,i";

    public static final String getTukiBetuJituMiseTotal_ARGS = "companyCd,dto,i";

    public static final String getTukiBetuKariMiseTotal_ARGS = "companyCd,dto,i";
    /**
     * ”N“xæ“¾
     */
    public List getNendo();
    /**
     * À“X”Ô—\Z‡Œvæ“¾(x•”•Ê)
     */
    public List getYosanJituStore(String companyCd,BudgetRefYMDto dto);
    /**
     * ‰¼“X”Ô—\Z‡Œvæ“¾ix•”•Êj
     */
    public List getYosanKariStore(String companyCd,BudgetRefYMDto dto);
    /**
     * —\Z‡Œvæ“¾ix•”•Êj
     */
    public List getYosanStore(String companyCd,BudgetRefYMDto dto);
    /**
     * À“X”Ô—\Zæ“¾i“X•Ü•Êj
     */
    public List getMeisaiDL(String companyCd,BudgetRefYMDto dto);
    /**
     * Œ•Ê‘‡Œv(‘S“X•Ü)
     */
    public TrnBudgetRegistDummy getTukiBetuAllMiseTotal(String companyCd,BudgetRefYMDto dto, int i);
    /**
     * Œ•Ê‘‡Œv(‰¼“X•Ü)
     */
    public TrnBudgetRegistDummy getTukiBetuKariMiseTotal(String companyCd,BudgetRefYMDto dto, int i);
    /**
     * Œ•Ê‘‡Œv(À“X•Ü)
     */
    public TrnBudgetRegistDummy getTukiBetuJituMiseTotal(String companyCd,BudgetRefYMDto dto, int i);
}