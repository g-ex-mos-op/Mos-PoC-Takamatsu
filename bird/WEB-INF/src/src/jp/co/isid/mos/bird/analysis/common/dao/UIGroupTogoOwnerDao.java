/*
 * 作成日: 2006/3/29
 */
package jp.co.isid.mos.bird.analysis.common.dao;

import jp.co.isid.mos.bird.analysis.common.entity.UIGroupTogoOwner;

/**
 * オーナ情報取得
 * @author itamoto
 */
public interface UIGroupTogoOwnerDao {

    public static final Class BEAN = UIGroupTogoOwner.class;
    public static final String select_ARGS = "ONER_CD";

    /**
     * 検索条件よりオーナ情報を取得
     * @param onerCd     オーナコード
     * @return List      検索結果
     */
    public UIGroupTogoOwner select(String onerCd);
}
