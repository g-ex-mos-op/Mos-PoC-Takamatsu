/*
 * çÏê¨ì˙: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.misesearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.misesearch.entity.UIGroupTogoMise;

/**
 * @author xyuchida
 *
 */
public interface UIGroupTogoMiseDao {

    public static final Class BEAN = UIGroupTogoMise.class;

    public static final String select_ARGS = "COMPANY_CD, SIBU_CD, ONER_NAME_KJ_0, ONER_CD_0, MISE_NAME_KJ, MISE_CD, MISE_NAME_KNA0, MISE_NAME_KNA1, MISE_NAME_KNA2, MISE_NAME_KNA3, SORT_SEQ, IN_CLOSE";

    public List select(
            String companyCd,
			String sibuCd,
            String onerNameKj,
            String onerCd,
            String miseNameKj,
            String miseCd,
			String miseNameKna0,
			String miseNameKna1,
			String miseNameKna2,
            String miseNameKna3,
            String sortSeq,
            String inClose);
}
