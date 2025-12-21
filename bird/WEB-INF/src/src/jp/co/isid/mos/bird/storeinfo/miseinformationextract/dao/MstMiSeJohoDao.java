package jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.MstMiSeJoho;

/**
 * 店マスタ情報の取得インターフェース
 * @author boukoumei
 */
public interface MstMiSeJohoDao {
    public static final Class BEAN = MstMiSeJoho.class;
    public static final String selectMiSeJoho_ARGS = "outPutDiv, inClose, shukeKubu, svCd, siBuCd";

    /**
     * 店マスタ情報の取得
     * @return List
     */
    public List selectMiSeJoho(String outPutDiv, String inClose, String shukeKubu, String svCd, String siBuCd);
}
