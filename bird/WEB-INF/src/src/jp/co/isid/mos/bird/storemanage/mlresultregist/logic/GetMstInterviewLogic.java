/*
 * 作成日: 2006/07/27
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 面接マスタ取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetMstInterviewLogic {
    /**
     * 面接マスタを取得する
     * @return List
     * @exception ApplicationException
     */
    public List execute() throws ApplicationException;
}