/**
 * 
 */
package jp.co.isid.mos.bird.config.pllimitregist.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.config.pllimitregist.common.PlLimitRegistConstants;
import jp.co.isid.mos.bird.config.pllimitregist.entity.CtlPLLimit;
import jp.co.isid.mos.bird.config.pllimitregist.logic.ClearLimitValueLogic;

/**
 * P/L上下限MAX/MIN値クリアロジック
 * 
 * @author xyuchida
 */
public class ClearLimitValueLogicImpl implements ClearLimitValueLogic {

    /**
     * 金額:最大値
     */
    private BigDecimal limitSumMaxValue;
    /**
     * 金額:最小値
     */
    private BigDecimal limitSumMinValue;
    /**
     * 構成比:最大値
     */
    private BigDecimal limitRatioMaxValue;
    /**
     * 構成比:最小値
     */
    private BigDecimal limitRatioMinValue;

    /**
     * P/L上下限MAX/MIN値クリア
     * 
     * @param plLimitList P/L上下限リスト
     */
    public void execute(List plLimitList) {

        for (Iterator it = plLimitList.iterator(); it.hasNext();) {
            CtlPLLimit ctlPLLimit = (CtlPLLimit) it.next();

            // 設定タイプにより、MAX値、MIN値を決定
            BigDecimal maxValue = BigDecimal.valueOf(0);
            BigDecimal minValue = BigDecimal.valueOf(0);
            if (ctlPLLimit.getLimitType().equals(PlLimitRegistConstants.LIMIT_TYPE_SUM)) {
                maxValue = getLimitSumMaxValue();
                minValue = getLimitSumMinValue();
            } else if (ctlPLLimit.getLimitType().equals(PlLimitRegistConstants.LIMIT_TYPE_RATIO)) {
                maxValue = getLimitRatioMaxValue();
                minValue = getLimitRatioMinValue();
            }

            // 上下限値MAX
            if (canParseLimitValue(ctlPLLimit.getLimitMax())
                    && (new BigDecimal(ctlPLLimit.getLimitMax())).compareTo(maxValue) == 0) {
                ctlPLLimit.setLimitMax("");
            }

            // 下限値MIN
            if (canParseLimitValue(ctlPLLimit.getLimitMin())
                    && (new BigDecimal(ctlPLLimit.getLimitMin())).compareTo(minValue) == 0) {
                ctlPLLimit.setLimitMin("");
            }
        }
    }

    /**
     * 数値解析可否判定
     * 
     * @param limitValue 判定対象数値文字列
     * @return 判定結果 = true : 解析可能, = false : 解析不可
     */
    private boolean canParseLimitValue(String limitValue) {
        boolean result = false;
        if (limitValue != null) {
            try {
                // 数値解析
                new BigDecimal(limitValue);
                result = true;
            } catch (NumberFormatException e) {
                // 無処理
            }
        }
        return result;
    }

    public BigDecimal getLimitSumMaxValue() {
        return limitSumMaxValue;
    }

    public void setLimitSumMaxValue(BigDecimal limitSumMaxValue) {
        this.limitSumMaxValue = limitSumMaxValue;
    }

    public BigDecimal getLimitSumMinValue() {
        return limitSumMinValue;
    }

    public void setLimitSumMinValue(BigDecimal limitSumMinValue) {
        this.limitSumMinValue = limitSumMinValue;
    }

    public BigDecimal getLimitRatioMaxValue() {
        return limitRatioMaxValue;
    }

    public void setLimitRatioMaxValue(BigDecimal limitRatioMaxValue) {
        this.limitRatioMaxValue = limitRatioMaxValue;
    }

    public BigDecimal getLimitRatioMinValue() {
        return limitRatioMinValue;
    }

    public void setLimitRatioMinValue(BigDecimal limitRatioMinValue) {
        this.limitRatioMinValue = limitRatioMinValue;
    }
}
