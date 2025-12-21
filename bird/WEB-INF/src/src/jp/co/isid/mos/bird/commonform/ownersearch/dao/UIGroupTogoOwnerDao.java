/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.ownersearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.ownersearch.entity.UIGroupTogoOwner;

/**
 * オーナ検索情報取得
 * @author itamoto
 */
public interface UIGroupTogoOwnerDao {

    public static final Class BEAN = UIGroupTogoOwner.class;
    public static final String select_ARGS = "COMPANY_CD_0, SIBU_CD, ONER_NAME_KJ, ONER_CD, MISE_NAME_KJ_0, MISE_CD_0, SV_NAME_KJ, SV_CD, ONER_NAME_KNA_1, ONER_NAME_KNA_2, ONER_NAME_KNA_3, ONER_NAME_KNA_4, SORT_TYPE, IN_END";
    public static final String selectByIndexSearchKey_ARGS = "ONER_NAME_KNA_1, ONER_NAME_KNA_2, ONER_NAME_KNA_3";

    /**
     * 検索条件よりオーナ情報を取得
     * @param companyCd   会社コード
     * @param sibuCd      支部コード
     * @param onerNameKj オーナ名称
     * @param onerCd     オーナコード
     * @param miseNameKj 店名
     * @param miseCd     店コード
     * @param svNameKj   SV名称
     * @param svCd       SVコード
     * @param onerNameKna1 オーナ名称（カナ）1
     * @param onerNameKna2 オーナ名称（カナ）2
     * @param onerNameKna3 オーナ名称（カナ）3
     * @param onerNameKna4 オーナ名称（カナ）4
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
            String svNameKj,
            String svCd,
            String onerNameKna1, 
            String onerNameKna2,
            String onerNameKna3,
            String onerNameKna4,
            String sortType,
            String inEnd);
}
