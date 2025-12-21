/*
 * çÏê¨ì˙: 2006/1/12
 */
package jp.co.isid.mos.bird.commonform.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.common.entity.CodSibu;

/**
 * éxïîÉäÉXÉgDao
 * @author itamoto
 */
public interface CodSibuDao {

    public static final Class BEAN = CodSibu.class;
    public static final String select_ARGS = "COMPANY_CD";
    public static final String select_QUERY 
            = "BM10GSIB.COMPANY_CD = /*COMPANY_CD*/'00000000000'"
            + "ORDER BY SIBU_CD";

    public List select(String companyCd);
}
