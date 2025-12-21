/*
 * 作成日: 2006/04/04
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * オーナー一覧取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetUserOnerLogic {
    /**
     * オーナー一覧を取得する
     * @param String 企業コード
     * @param String ユーザーID
     * @param String システム日付
     * @exception ApplicationException
     */
    public List execute(String companyCd, String userId, String sysDate) throws ApplicationException;
}