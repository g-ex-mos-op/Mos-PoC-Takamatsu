package jp.co.isid.mos.bird.bizreport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.entity.TenpoInfo;

/**
 * 店舗情報取得Daoクラス
 * 
 * @author xwatanabe
 */
public interface TenpoInfoDao {

    /** 店舗情報エンティティクラス */
    public static final Class BEAN = TenpoInfo.class;

    /** 店舗情報取得時のパラメータ */
    public static final String getMiseInfoByMiseCd_ARGS = "companyCd, miseCd";
    /** 店舗情報取得時のパラメータ */
    public static final String getMiseInfoByUserId_ARGS = "userId";

    /**
     * 店舗情報を取得する
     * @param companyCd 会社コード
     * @param miseCd    店コード
     * @return List  対象支部情報
     */
    public List getMiseInfoByMiseCd(String companyCd, String miseCd);

    /**
     * 店舗情報を取得する
     * @param userId ユーザID
     * @return List  対象支部情報
     */
    public List getMiseInfoByUserId(String userId);


}