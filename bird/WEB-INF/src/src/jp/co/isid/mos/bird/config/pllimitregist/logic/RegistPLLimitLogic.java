/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.logic;

import java.util.List;

/**
 * P/L上下限登録ロジック
 * 
 * @author xyuchida
 */
public interface RegistPLLimitLogic {

    /**
     * P/L上下限登録
     * 
     * @param plLimitList P/L上下限リスト
     * @param userId ユーザID
     */
    public void execute(List plLimitList, String userId);
}
