package jp.co.isid.mos.bird.commonform.publictargetsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodMiseInfo;
/**
 * @author xytamura
 */
public interface CodMiseInfoDao {

    public static final Class BEAN = CodMiseInfo.class;

    public static final String getMiseInfo_ARGS = "gyotaiKbn, miseCd, companyCd";
        
    public List getMiseInfo(String gyotaiKbn, List miseCd, String companyCd);
}
