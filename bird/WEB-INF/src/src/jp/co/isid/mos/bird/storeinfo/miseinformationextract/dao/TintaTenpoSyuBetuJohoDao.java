package jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.TintaTenpoSyuBetuJoho;

/**
 * 賃貸店舗種別情報の取得インターフェース
 * @author boukoumei
 */
public interface TintaTenpoSyuBetuJohoDao {
    public static final Class BEAN = TintaTenpoSyuBetuJoho.class;
    public static final String select_ARGS = "inClose,shukeKubu,svCd,siBuCd";

    /**
     * 賃貸店舗種別情報の取得
     * @return List
     */
    public List select(String inClose,String shukeKubu,String svCd,String siBuCd);
}
