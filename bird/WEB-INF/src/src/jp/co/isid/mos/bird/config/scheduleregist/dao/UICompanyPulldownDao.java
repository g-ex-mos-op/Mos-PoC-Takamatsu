/*
 * ì¬“ú: 2008/12/08
 */
package jp.co.isid.mos.bird.config.scheduleregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.scheduleregist.entity.UICompanyPulldown;

/**
 * “X•Üƒ[ƒ^[ŠÇ—ó‹µDao
 * 
 * @author xnkusama
 */
public interface UICompanyPulldownDao {

    public static final Class BEAN = UICompanyPulldown.class;
    public static final String getCompanyList_ARGS = "userId";

    /**
     * ‰ïĞˆê——æ“¾
     * @param userId
     * @return
     */
    public List getCompanyList(String userId);
   
}