package jp.co.isid.mos.bird.entry.mlentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.entity.MstOner;


/**
 * 管理会社情報取得
 * @author Aspac
 */
public interface MstOnerDao {

    public static final Class BEAN = MstOner.class;
    public static final String getOnerInfo_ARGS = "companyCd, onerCd";

    /**
     * オーナー情報の取得
     * @param String companyCd 会社コード
     * @param String onerCd オーナーコード
     * @return List
     */
    public List getOnerInfo(String companyCd, String onerCd);
}