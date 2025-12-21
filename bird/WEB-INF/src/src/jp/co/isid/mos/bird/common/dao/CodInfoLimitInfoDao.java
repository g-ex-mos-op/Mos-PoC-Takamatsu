package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.CodInfoLimitInfo;



/**
 * î•ñ§ŒÀî•ñ
 * @author xnkusama
 */
public interface CodInfoLimitInfoDao {

    public Class BEAN = CodInfoLimitInfo.class;
    
    /**
     * î•ñ§ŒÀî•ñ‚Ìæ“¾
     * @return
     */
    public List getInfoLimitKbn();
    
}
