/*
 * 作成日: 2006/03/24
 */
package jp.co.isid.mos.bird.bizsupport.noinputoner.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 支部の種類を取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetSibuCategoryLogic {
    /**
     * 支部の種類を取得
     * @param String 企業コード
     * @return List 支部データ
     * @exception ApplicationException
     */
    public List execute(String companyCd) throws ApplicationException;
}