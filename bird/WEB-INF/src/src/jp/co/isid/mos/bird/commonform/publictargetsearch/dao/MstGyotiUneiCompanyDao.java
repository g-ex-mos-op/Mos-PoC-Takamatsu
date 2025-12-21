/*
 * 作成日: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstGyotiUneiCompany;

/**
 * @author xytamura
 */
public interface MstGyotiUneiCompanyDao {

    public static final Class BEAN = MstGyotiUneiCompany.class;

    public static final String getGyotai_ARGS = "companyCd";

    public static final String getGyotaiFromSibu_ARGS = "companyCd, sibuCd";

    
    /**
     * 業態を取得
     * @param companyCd 企業コード
     * @return
     */
    public List getGyotai(final List companyCd);
    
    /**
     * 支部から業態を取得
     * @param companyCd 企業コード
     * @param sibuCd 支部コード
     * @return
     */
    public List getGyotaiFromSibu(final String companyCd, final List sibuCd);
}
