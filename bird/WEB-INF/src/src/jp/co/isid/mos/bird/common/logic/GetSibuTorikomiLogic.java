/*
 * 作成日: 2007/01/11
 *
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

/**
 * 支部取込コードによる支部情報の取得
 * @author mwatanabe
 */
public interface GetSibuTorikomiLogic {
    
    /**
     * 支部取込コードによる支部情報の取得
     * @param companyCd 企業コード
     * @param userId    ユーザID
     * @param limit     制限区分
     * @return 支部情報
     */
    public abstract List execute(String companyCd, String userId, boolean limit);
}