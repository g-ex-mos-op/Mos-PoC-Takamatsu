/*
 * 作成日: 2006/01/24
 */
package jp.co.isid.mos.bird.inforegist.documentregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 文書照会順序ロジック インターフェイス
 * @author xnkusama
 */
public interface RegBunshoSortLogic {
    /**
     * 文書照会順序の登録
     * @param List 文書情報EntityのList
     * @exception ApplicationException
     */
    public void registBunsho(List entityList) throws ApplicationException;
}