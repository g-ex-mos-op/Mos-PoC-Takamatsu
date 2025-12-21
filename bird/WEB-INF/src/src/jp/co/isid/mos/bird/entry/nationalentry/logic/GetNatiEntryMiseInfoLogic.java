/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.logic;

import java.util.List;

/**
 * 店舗情報取得ロジック
 * 
 * @author xlee
 */
public interface GetNatiEntryMiseInfoLogic {

    /**
     * 店舗コードを取得
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @param sysDate システム日付
     * @return 店舗リスト
     */
    public List execute(String companyCd, String onerCd, String sysDate);
}
