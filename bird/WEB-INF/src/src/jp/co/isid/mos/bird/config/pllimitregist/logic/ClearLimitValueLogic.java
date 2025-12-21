/**
 * 
 */
package jp.co.isid.mos.bird.config.pllimitregist.logic;

import java.util.List;

/**
 * P/L上下限MAX/MIN値クリアロジック
 * 
 * @author xyuchida
 */
public interface ClearLimitValueLogic {

    /**
     * P/L上下限MAX/MIN値クリア
     * 
     * @param plLimitList P/L上下限リスト
     */
    public void execute(List plLimitList);
}
