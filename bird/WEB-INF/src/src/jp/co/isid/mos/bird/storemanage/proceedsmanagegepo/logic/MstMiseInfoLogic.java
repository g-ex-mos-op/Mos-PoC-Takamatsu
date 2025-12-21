package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic;

import java.util.List;

/**
 * 対象店舗情報取得ロジック インターフェイス
 * @author xjung
 */
public interface MstMiseInfoLogic {

    /**
     * 対象店舗リストを取得する
     * @param companyCd 企業コード
     * @param onerCd    オーナーコード
     * @param appDate   アプリ日付
     * @return List     対象店舗プルダウンリスト
     */
    public List execute(String companyCd, String onerCd, String appDate);

    /**
     * 店舗(店コード+店名称)情報を取得する
     * @param companyCd 企業コード
     * @param miseCd    店コード
     * @return String   店舗情報(店コード+店名称)
     */
    public String getMiseCdName(String companyCd, String miseCd);    
}