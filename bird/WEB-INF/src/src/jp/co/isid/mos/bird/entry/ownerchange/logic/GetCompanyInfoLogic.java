/*
 * 作成日:2007/01/16
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic;

import java.util.List;

/**
 * 会社情報取得ロジック
 * @author xkonishi
 *
 */
public interface GetCompanyInfoLogic {
    
    /**
     * 会社情報取得ロジック
     * @param ユーザーID
     * @return 会社情報
     */
    public List execute(String userId);
}