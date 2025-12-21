/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * カテゴリの取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetCategoryLogic {
    /**
     * カテゴリを取得する
     * @param String infoShu
     * @return List
     * @exception ApplicationException
     */
    public List getCategory(String infoShu) throws ApplicationException;
}
