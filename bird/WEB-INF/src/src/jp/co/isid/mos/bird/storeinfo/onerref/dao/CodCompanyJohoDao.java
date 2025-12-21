/*
 * ì¬“ú: 2006/3/7
 *
 */
package jp.co.isid.mos.bird.storeinfo.onerref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.onerref.entity.CodCompanyJoho;

/**
 * ‰ïĞƒR[ƒhæ“¾Dao
 * @author itamoto
 */
public interface CodCompanyJohoDao {

    public static final Class BEAN = CodCompanyJoho.class;
    public static final String select_ARGS  = "USER_ID";

    /**
     * ŠÇ—‰ïĞŠé‹Æî•ñ‚ÌŒŸõ(select)
     */
    public List select(String userId);
}
