/*
 * ì¬“ú: 2008/03/18
 *
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.dao;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstMise;

/**
 * @author xnkusama
 *
 */
public interface MstMiseDao {

    public static final Class BEAN = MstMise.class;

    public static final String getMiseInfo_ARGS = "miseCd";

    /**
     * İ’èƒf[ƒ^‚Ìæ“¾
     * @param miseCd
     * @return
     */
    public MstMise getMiseInfo(String miseCd);
    
}