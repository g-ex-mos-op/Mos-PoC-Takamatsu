package jp.co.isid.mos.bird.bizsupport.plinfoview.dao;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MstMiseInfo;


/**
 * “Xî•ñ
 * @author kusama
 */
public interface MstMiseInfoDao {

    public static final Class BEAN = MstMiseInfo.class;

    public static final String getMiseInfo_ARGS  = "companyCd, miseCd";
    
    /**
     * “Xî•ñ‚Ìæ“¾
     * @param companyCd
     * @param miseCd
     * @return MstMiseInfo
     */
    public MstMiseInfo getMiseInfo(String companyCd, String miseCd);
    
}