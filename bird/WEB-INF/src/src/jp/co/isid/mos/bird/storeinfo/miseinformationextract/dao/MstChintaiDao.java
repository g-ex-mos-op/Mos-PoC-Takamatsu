package jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.MstChintai;

/**
 * 賃貸店舗履歴情報の取得インターフェース
 * @author boukoumei
 */
public interface MstChintaiDao {
    public static final Class BEAN = MstChintai.class;
    public static final String selectChintai_ARGS = "inClose,shukeKubu,svCd,siBuCd";

    /**
     * 賃貸店舗履歴情報の取得
     * @return List
     */
    public List selectChintai(String inClose,String shukeKubu,String svCd,String siBuCd);
}
