/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.storeinfo.miseref.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 個店情報の検索ロジック インターフェイス
 * @author xnkusama
 */
public interface KotenJohoLogic {
    /**
     * 個店情報の検索を行う
     * @param String miseCd 店コード
     * @param String companyCd 会社コード
     * @return List  
     * @exception ApplicationException
     */
    public Map execute(String miseCd, String companyCd) throws ApplicationException;
}