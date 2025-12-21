/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.logic;

import java.util.List;

/**
 * P/L上下限取得ロジック
 * 
 * @author xyuchida
 */
public interface GetPLLimitLogic {

    /**
     * P/L上下限取得
     * 
     * @param plType P/Lタイプ
     * @return P/L上下限リスト
     */
    public List execute(String plType);
}
