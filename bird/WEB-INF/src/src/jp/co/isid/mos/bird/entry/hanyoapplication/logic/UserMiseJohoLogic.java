/*
 * 作成日: 2006/0/14
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * ユーザー保有店情報取得 ロジックインターフェイス
 * @author xnkusama
 */
public interface UserMiseJohoLogic {
    /**
     * ユーザーの保有店舗一覧の取得
     * @param String companyCd 会社コード
     * @param String userId ユーザーID
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String companyCd, String userId) throws ApplicationException;
}