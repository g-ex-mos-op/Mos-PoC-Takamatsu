/*
 * ì¬“ú: 2006/02/28
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodChintai;

/**
 * ’À‘İ“X•Üî•ñæ“¾
 * @author kusama
 */
public interface CodChintaiDao {

    public static final Class BEAN = CodChintai.class;
    public static final String selectChintai_ARGS = "companyCd";

    /**
     * ’À‘İ“X•Üí•Êî•ñ‚ÌŒŸõ
     * @param String companyCd ŠÇ—‰ïĞŠé‹ÆƒR[ƒh
     * @return List
     */
    public List selectChintai(String companyCd);
    
}            
