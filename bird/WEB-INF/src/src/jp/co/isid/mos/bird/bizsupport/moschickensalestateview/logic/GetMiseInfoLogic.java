/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic;

import java.util.List;

/**
 * 店舗情報取得ロジック
 *
 * @author xlee
 */
public interface GetMiseInfoLogic {

    /**
     * 店舗コードを取得
     * @param onerCdList オーナーコードリスト
     * @param sysDate システム日付
     * @param userType ユーザータイプ
     * @param companyCd 会社コード
     * @return 店舗リスト
     */
    public List execute(String onerCd, String userID, String userType, String companyCd);
}
