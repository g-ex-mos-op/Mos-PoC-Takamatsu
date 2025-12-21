/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.svsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.svsearch.entity.UISv;

/**
 * SV検索情報取得
 * @author kinugawa(ASPAC)
 */
public interface UISvDao {

    public static final Class BEAN = UISv.class;
    public static final String select_ARGS = "COMPANY_CD_0, SIBU_CD, ONER_NAME_KJ, ONER_CD, MISE_NAME_KJ_0, MISE_CD_0, SV_NAME_KNA_1, SV_NAME_KNA_2, SV_NAME_KNA_3, SORT_TYPE, IN_END";
    public static final String selectByIndexSearchKey_ARGS = "SV_NAME_KNA_1, SV_NAME_KNA_2, SV_NAME_KNA_3";

    /**
     * 検索条件よりオーナ情報を取得
     * @param companyCd   会社コード
     * @param sibuCd      支部コード
     * @param onerNameKj オーナ名称
     * @param onerCd     オーナコード
     * @param miseNameKj 店名
     * @param miseCd     店コード
     * @param svNameKna1 SV名称（カナ）1
     * @param svNameKna2 SV名称（カナ）2
     * @param svNameKna3 SV名称（カナ）3
     * @param sortType   ソートタイプ 
     * @param inClose    契約期間切れを含む
     * @return List      検索結果
     */
    public List select(
            String companyCd,
            String sibuCd,
            String onerNameKj,
            String onerCd,
            String miseNameKj,
            String miseCd,
            String svNameKna1, 
            String svNameKna2,
            String svNameKna3,
            String sortType,
            String inEnd);
}
