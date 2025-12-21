/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.config.pllimitregist.common.PlLimitRegistConstants;
import jp.co.isid.mos.bird.config.pllimitregist.dao.CtlPLLimitDao;
import jp.co.isid.mos.bird.config.pllimitregist.entity.CtlPLLimit;
import jp.co.isid.mos.bird.config.pllimitregist.logic.RegistPLLimitLogic;

/**
 * P/L上下限登録ロジック
 * 
 * @author xyuchida
 */
public class RegistPLLimitLogicImpl implements RegistPLLimitLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BCF005L03";

    /**
     * P/L上下限チェック改DAO
     */
    private CtlPLLimitDao ctlPLLimitDao;
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
     * P/L上下限登録
     * 
     * @param plLimitList P/L上下限リスト
     * @param userId ユーザID
     */
    public void execute(List plLimitList, String userId) {

        // 全件処理
        for (Iterator it = plLimitList.iterator(); it.hasNext();) {
            CtlPLLimit ctlPLLimit = (CtlPLLimit) it.next();

            // 上下限ともに未設定は金額とみなす
            if ((ctlPLLimit.getLimitMax() == null || ctlPLLimit.getLimitMax().trim().length() == 0)
                    && (ctlPLLimit.getLimitMin() == null || ctlPLLimit.getLimitMin().trim().length() == 0)) {
                ctlPLLimit.setLimitType(PlLimitRegistConstants.LIMIT_TYPE_SUM);
            }

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

            // 上限値が未設定の場合、MAX値をセット
            if (ctlPLLimit.getLimitMax() == null || ctlPLLimit.getLimitMax().trim().length() == 0) {
                ctlPLLimit.setLimitMax(maxValue.toString());
            }
            // 下限値が未設定の場合、MIN値をセット
            if (ctlPLLimit.getLimitMin() == null || ctlPLLimit.getLimitMin().trim().length() == 0) {
                ctlPLLimit.setLimitMin(minValue.toString());
            }

            // 共通項目設定
            ctlPLLimit.setLastUser(userId);
            ctlPLLimit.setLastPgm(PlLimitRegistConstants.SCREENID_PL_LIMIT_REGIST);

            // update
            getCtlPLLimitDao().updatePLLimit(ctlPLLimit);
        }
    }

    public CtlPLLimitDao getCtlPLLimitDao() {
        return ctlPLLimitDao;
    }

    public void setCtlPLLimitDao(CtlPLLimitDao ctlPLLimitDao) {
        this.ctlPLLimitDao = ctlPLLimitDao;
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
