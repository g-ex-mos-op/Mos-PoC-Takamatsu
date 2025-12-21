package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.MstOwner;

/**
 * 管理会社情報取得
 * @author kusama
 */
public interface MstOwnerDao {

    public static final Class BEAN = MstOwner.class;
    public static final String getOnerInfo_ARGS = "companyCd, onerCd";

    /**
     * オーナー情報の取得
     * @param String companyCd 会社コード
     * @param String onerCd オーナーコード
     * @return List
     */
    public List getOnerInfo(String companyCd, String onerCd);
}