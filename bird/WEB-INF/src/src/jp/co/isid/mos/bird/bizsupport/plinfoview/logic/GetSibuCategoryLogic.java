/*
 * 作成日: 2006/04/04
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 条件画面のプルダウン用の情報取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetSibuCategoryLogic {
    /**
     * 条件画面のプルダウン用の情報取得
     * @param String 企業コード
     * @return List 支部データ
     * @exception ApplicationException
     */
    public Map execute(String companyCd, String userId, boolean isLimit) throws ApplicationException;
}