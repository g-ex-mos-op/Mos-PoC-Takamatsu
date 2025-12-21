/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.logic;

import java.util.List;

/**
 * P/L上下限チェックロジック
 * 
 * @author xyuchida
 */
public interface CheckPLLimitLogic {

    /**
     * P/L上下限チェック
     * 
     * @param plLimitList P/L上下限リスト
     */
    public void execute(List plLimitList);
}
