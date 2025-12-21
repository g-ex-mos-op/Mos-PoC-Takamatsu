/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * サブカテゴリの取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetSubCategoryLogic {
    /**
     * サブカテゴリを取得する
     * @param String infoShu
     * @param String cateId
     * @return List
     * @exception ApplicationException
     */
    public List getSubCategory(String infoShu, String cateId) throws ApplicationException;
}
