package jp.co.isid.mos.bird.bizreport.zendougeturegist.dao;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.entity.CtlYosanHiritu;

/**
 * —\ZˆÂ•ª”ä—¦Dao
 * 
 * @author Aspac
 */
public interface CtlYosanHirituDao {

    public static final Class BEAN = CtlYosanHiritu.class;

    public static final String count_ARGS = "companyCd, eigyoDt";

    /**
     * —\Z“o˜^§Œä“ú•tŠÇ—î•ñæ“¾
     * @return 
     */
    public int count(String companyCd, String eigyoDt);
}