/*
 * 2006/12/01
 */
package jp.co.isid.mos.bird.bizsupport.contractstatement.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.contractstatement.entity.UIKeisanshoList;
/**
 * –‹ÆŒv‰æ—\ZãŠú”„ã’uŠ· Dao
 * @author xkonishi
 *
 */
public interface UIKeisanshoListDao {
    
    public static final Class BEAN = UIKeisanshoList.class;
    
    public static final String getKeisanshoList_ARGS = "companyCd, onerCd, hakkoDt";
    
    public List getKeisanshoList(String companyCd, String onerCd, String hakkoDt);
    
}