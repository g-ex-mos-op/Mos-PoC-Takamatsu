/*
 * ì¬“ú: 2006/04/14
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.CodSibu;


/**
 * x•”î•ñ
 * 
 * @author xkinu
 */
public interface CodSibuDao {

    public static final Class BEAN = CodSibu.class;

    public static final String select_ARGS = "companyCd";
    
    /**
     * x•”î•ñ‚Ìæ“¾
     * @param companyCd Šé‹ÆƒR[ƒh
     * @return List
     */
    public List select(String companyCd);
    
}