package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MstMiseInfo;

/**
 * 対象店舗情報Daoクラス
 *
 * @author xjung
 */
public interface MstMiseInfoDao {

    /**
     * 対象店舗情報エンティティクラス
     */
    public static final Class BEAN = MstMiseInfo.class;

    /**
     * 対象店舗情報リスト取得時のパラメータ
     */
    public static final String select_ARGS =
          "companyCd"
        + ", onerCd"
        + ", appDate";

    /**
     * 対象店舗(店コード+店名称)取得時のパラメータ
     */
    public static final String selectMiseCdName_ARGS =
          "companyCd"
        + ", miseCd";
 
    /**
     * 対象店舗情報リストを取得する
     * @param companyCd 企業コード
     * @param onerCd    オーナーコード
     * @param appDate   アプリ日付
     * @return List 対象店舗情報リスト
     */
    public List select(String companyCd, String onerCd, String appDate);

    /**
     * 店舗(店コード+店名称)情報を取得する
     * @param companyCd 企業コード
     * @param miseCd    店コード
     * @return String   店舗情報(店コード+店名称)
     */
    public String selectMiseCdName(String companyCd, String miseCd);
}