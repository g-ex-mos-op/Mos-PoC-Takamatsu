/*
 * 作成日: 2006/01/24
 */
package jp.co.isid.mos.bird.office.formregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * フォームダウンロード順序登録ロジック インターフェイス
 * @author xytamura
 */
public interface RegFormSortLogic {
    /**
     * フォームダウンロード順序登録
     * @param List フォーム情報EntityのList
     * @param String ユーザＩＤ 
     * @exception ApplicationException
     */
    public void execute(List entityList, String userId) throws ApplicationException;
}