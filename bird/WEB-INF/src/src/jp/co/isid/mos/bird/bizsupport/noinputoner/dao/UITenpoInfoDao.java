package jp.co.isid.mos.bird.bizsupport.noinputoner.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.UITenpoInfo;


/**
 * “XPLî•ñ
 * @author kusama
 */
public interface UITenpoInfoDao {

    public static final Class BEAN = UITenpoInfo.class;

    public static final String getTenpoInfo_ARGS = "companyCd, sibuCd, nengetuFrom, nengetuTo closeMiseFlg";
    
    /**
     * “Xî•ñ‚Ìæ“¾
     * @return List
     */
    public List getTenpoInfo(String companyCd, String sibuCd, String nengetuFrom, String nengetuTo, boolean closeMiseFlg);
    
}