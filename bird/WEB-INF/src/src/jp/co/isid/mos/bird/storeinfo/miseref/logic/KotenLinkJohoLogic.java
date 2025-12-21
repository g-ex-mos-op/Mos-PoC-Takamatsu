/*
 * 作成日: 2006/03/06
 */
package jp.co.isid.mos.bird.storeinfo.miseref.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 個店リンク情報の取得ロジック インターフェイス
 * @author xnkusama
 */
public interface KotenLinkJohoLogic {
    /**
     * 個店リンク情報の取得を行う
     * @param String userId ユーザーID
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String userId) throws ApplicationException;
}