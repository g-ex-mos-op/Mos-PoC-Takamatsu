/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic;

import java.util.List;

/**
 * 店舗情報取得ロジック
 * @author xkonishi
 *
 */
public interface GetMiseInfoLogic {

    /**
     * 店舗情報取得ロジック
     * @param 会社コード
     * @param オーナーコード
     * @param システム日付
     * @return 店舗情報
     */
    public List execute(String companyCd, String onerCd, String sysDate);    
}