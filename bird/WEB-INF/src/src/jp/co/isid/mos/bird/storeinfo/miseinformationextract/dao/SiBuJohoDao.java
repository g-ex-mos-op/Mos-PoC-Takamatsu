package jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.SiBuJoho;

/**
 * 支部情報の取得インターフェース
 * @author boukoumei
 */
public interface SiBuJohoDao {

    public static final Class BEAN = SiBuJoho.class;
    public static final String select_ARGS = "shukeKubu";

    /**
     * 支部情報の取得
     * @return List
     */
    public List select(String shukeKubu);
}
