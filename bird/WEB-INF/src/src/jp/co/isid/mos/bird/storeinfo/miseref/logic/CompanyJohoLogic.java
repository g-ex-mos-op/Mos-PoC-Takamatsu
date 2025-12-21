/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.storeinfo.miseref.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * ユーザー所属管理会社の検索ロジック インターフェイス
 * @author xnkusama
 */
public interface CompanyJohoLogic {
    /**
     * ユーザー所属管理会社の検索を行う
     * @param String userId ユーザーID
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String userId) throws ApplicationException;
}