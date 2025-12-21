package jp.co.isid.mos.bird.bizsupport.noinputoner.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.UIPLDataStateInfo;


/**
 * PLƒf[ƒ^î•ñ
 * @author kusama
 */
public interface UIPLDataStateInfoDao {

    public static final Class BEAN = UIPLDataStateInfo.class;

    public static final String getInputState_ARGS = "companyCd, sibuCd, nengetuFrom, nengetuTo, closeMiseFlg";
    
    /**
     * x•”î•ñ‚Ìæ“¾
     * @return List
     */
    public List getInputState(String companyCd, String sibuCd, String nengetuFrom, String nengetuTo, boolean closeMiseFlg);
    
}