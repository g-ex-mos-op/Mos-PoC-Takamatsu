/*
 * ì¬“ú: 2007/01/31
 */
package jp.co.isid.mos.bird.sysadmin.userregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userregist.entity.CodRoleType;

/**
 * “X‹æ•ªƒ[ƒ‹•ÏŠ·Dao
 * @author xamaruyama
 */
public interface CodRoleTypeDao {

    public Class BEAN = CodRoleType.class;
    
    public static final String getKeiyakuRole_ARGS = "arrCompanyCd, keiyakuChoice";
    
    public static final String getOptionRole_ARGS = "arrCompanyCd, arrOption";
    
    public static final String getMiseKbnRole_ARGS = "arrCompanyCd, sysDate, miseCd";

    /**
     * “X‹æ•ªƒ[ƒ‹‚Ìæ“¾
     * @param arrCompanyCd, keiyakuChoice
     * @param arrCompanyCd, arrOption
     * @param arrCompanyCd, sysDate, miseCd
     * @return List
     */

    public List getKeiyakuRole(List arrCompanyCd, String keiyakuChoice);
    
    public List getOptionRole(List arrCompanyCd, List arrOption);
    
    public List getMiseKbnRole(List arrCompanyCd, String sysDate, String miseCd);
}
