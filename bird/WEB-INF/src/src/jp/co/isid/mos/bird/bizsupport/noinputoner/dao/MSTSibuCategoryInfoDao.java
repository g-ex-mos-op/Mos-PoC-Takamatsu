package jp.co.isid.mos.bird.bizsupport.noinputoner.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.MSTSibuCategoryInfo;


/**
 * x•”î•ñ
 * @author kusama
 */
public interface MSTSibuCategoryInfoDao {

    public static final Class BEAN = MSTSibuCategoryInfo.class;

    public static final String getSibuInfo_ARGS = "companyCd";
    
    /**
     * x•”î•ñ‚Ìæ“¾
     * @param companyCd Šé‹ÆƒR[ƒh
     * @return List
     */
    public List getSibuInfo(String companyCd);
    
}