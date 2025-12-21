/*
 * 作成日: 2006/01/26
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 情報制限区分の取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetLimitKbnInfoLogic {
    /**
     * 情報制限区分を取得する
     * @return List
     * @exception ApplicationException
     */
    public List execute() throws ApplicationException;
}
