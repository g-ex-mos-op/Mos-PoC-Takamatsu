/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic;

import java.util.List;

/**
 * 店舗情報取得ロジック
 * 
 * @author xlee
 */
public interface GetMiseInfoLogic {

    /**
     * 店舗リストを取得
     * @param onerCd オーナーコード
     * @param userID ユーザID
     * @param sysDate システム日付
     * @param userType ユーザタイプ
     * @param　companyCd　会社コード
     * @return 店舗リスト
     */
    public List execute(String onerCd, String userID, String sysDate, String userType, String companyCd);
}
