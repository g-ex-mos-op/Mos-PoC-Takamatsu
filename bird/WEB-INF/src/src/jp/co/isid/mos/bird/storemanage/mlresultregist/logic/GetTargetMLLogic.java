/*
 * 作成日: 2006/07/18
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 対象一覧取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetTargetMLLogic {
    /**
     * 対象一覧を取得する
     * @param String sysDt
     * @parma String companyCd
     * @return List
     * @exception ApplicationException
     */
    public List execute(String companyCd, String sysDt) throws ApplicationException;
}