package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.MstInterview;

/**
 * 面接マスタDAO
 * @author kusama
 */
public interface MstInterviewDao {

    public static final Class BEAN = MstInterview.class;

    /**
     * 面接マスタの取得
     * @return List
     */
    public List getMstInterview();
}