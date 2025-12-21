package jp.co.isid.mos.bird.bizsupport.budgetregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.budgetregist.entity.MstMiseInfo;

/**
 * “Xî•ñDAO
 * 
 * @author Aspac
 */
public interface MstMiseInfoDao {

    public static final Class BEAN = MstMiseInfo.class;

    public static final String getMiseInfo_ARGS = "sysdate, companyCd";

    /**
     * “X•Üî•ñæ“¾
     * @return “Xî•ñƒŠƒXƒg
     */
    public List getMiseInfo(String sysdate, String companyCd);
}
