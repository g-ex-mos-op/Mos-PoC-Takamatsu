/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.logic.impl;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.config.pllimitregist.common.PlLimitRegistConstants;
import jp.co.isid.mos.bird.config.pllimitregist.entity.CtlPLLimit;
import jp.co.isid.mos.bird.config.pllimitregist.logic.CheckPLLimitLogic;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * P/L上下限チェックロジック
 * 
 * @author xyuchida
 */
public class CheckPLLimitLogicImpl implements CheckPLLimitLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BCF005L02";

    /**
     * 数値バリデータ
     */
    private Verifier limitNormalVerifier;
    /**
     * 金額バリデータ
     */
    private Verifier limitSumVerifier;
    /**
     * 構成比バリデータ
     */
    private Verifier limitRatioVerifier;
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
     * P/L上下限チェック
     * 
     * @param plLimitList P/L上下限リスト
     */
    public void execute(List plLimitList) {

        // 全件処理
        for (int i = 0, n = plLimitList.size(); i < n; i++) {
            CtlPLLimit ctlPLLimit = (CtlPLLimit) plLimitList.get(i);

            // 設定タイプチェック
            String limitType = ctlPLLimit.getLimitType();
            if (limitType == null || limitType.trim().length() == 0) {
                throw new NotNullException(ctlPLLimit.getItemName() + "の設定タイプ", "limitType", i);
            }

            // 設定タイプにより分岐
            if (limitType.equals(PlLimitRegistConstants.LIMIT_TYPE_SUM)) {
                // 金額チェック
                checkLimitTypeSum(ctlPLLimit, i);
            } else if (limitType.equals(PlLimitRegistConstants.LIMIT_TYPE_RATIO)) {
                // 構成比チェック
                checkLimitTypeRatio(ctlPLLimit, i);
            } else {
                // 設定タイプ不正
                throw new InvalidInputException(ctlPLLimit.getItemName() + "の設定タイプ", "limitType", i);
            }

            // 上限値、下限値ともに設定ありの場合のみチェックを行う
            String limitMin = ctlPLLimit.getLimitMin();
            String limitMax = ctlPLLimit.getLimitMax();
            if (limitMax != null && limitMax.trim().length() > 0
                    && limitMin != null && limitMin.trim().length() > 0) {
                // 上限値 ≧ 下限値
                if ((new BigDecimal(limitMax.trim())).compareTo((new BigDecimal(limitMin.trim()))) < 0) {
                    NotRelevantException ne = new NotRelevantException(ctlPLLimit.getItemName(), "最小値≦最大値" , " ( " + ctlPLLimit.getItemName() + " )");
                    ne.setHtmlElementName("limitMin");
                    ne.setHtmlElementIndex(i);
                    throw ne;
                }
            }
        }
    }

    /**
     * 金額チェック
     * 
     * @param ctlPLLimit P/L上下限
     * @param listIndex 件数インデックス
     */
    private void checkLimitTypeSum(CtlPLLimit ctlPLLimit, int listIndex) {

        // 下限値
        // 設定ありの場合のみチェックを行う
        String limitMin = ctlPLLimit.getLimitMin();
        if (limitMin != null && limitMin.trim().length() > 0) {
            // 数値チェック
            if (!getLimitNormalVerifier().validate(limitMin)) {
                throw new InvalidInputException(ctlPLLimit.getItemName() + "の最小値", "limitMin", listIndex);
            }
            // 整数チェック
            if (!getLimitSumVerifier().validate(limitMin)) {
                NotRelevantException ne =  new NotRelevantException("金額を選択した場合", "整数", " ( " + ctlPLLimit.getItemName() + " )");
                ne.setHtmlElementName("limitMin");
                ne.setHtmlElementIndex(listIndex);
                throw ne;
            }
            // 数値範囲チェック
            if ((new BigDecimal(limitMin.trim())).compareTo(getLimitSumMinValue()) < 0
                    || (new BigDecimal(limitMin.trim())).compareTo(getLimitSumMaxValue()) > 0) {
                throw new NotRelevantException(
                        ctlPLLimit.getItemName() + "の最小値",
                        getLimitSumMinValue() + "以上、" + getLimitSumMaxValue() + "以下",
                        "limitMin",
                        listIndex);
            }
        }

        // 上限値
        // 設定ありの場合のみチェックを行う
        String limitMax = ctlPLLimit.getLimitMax();
        if (limitMax != null && limitMax.trim().length() > 0) {
            // 数値チェック
            if (!getLimitNormalVerifier().validate(limitMax)) {
                throw new InvalidInputException(ctlPLLimit.getItemName() + "の最大値", "limitMax", listIndex);
            }
            // 整数チェック
            if (!getLimitSumVerifier().validate(limitMax)) {
                NotRelevantException ne = new NotRelevantException("金額を選択した場合", "整数", " ( " +  ctlPLLimit.getItemName() + " )");
                ne.setHtmlElementName("limitMax");
                ne.setHtmlElementIndex(listIndex);
                throw ne;
            }
            // 数値範囲チェック
            if ((new BigDecimal(limitMax.trim())).compareTo(getLimitSumMinValue()) < 0
                    || (new BigDecimal(limitMax.trim())).compareTo(getLimitSumMaxValue()) > 0) {
                throw new NotRelevantException(
                        ctlPLLimit.getItemName() + "の最大値",
                        getLimitSumMinValue() + "以上、" + getLimitSumMaxValue() + "以下",
                        "limitMax",
                        listIndex);
            }
        }
    }

    /**
     * 構成比チェック
     * 
     * @param ctlPLLimit P/L上下限
     * @param listIndex 件数インデックス
     */
    private void checkLimitTypeRatio(CtlPLLimit ctlPLLimit, int listIndex) {

        // 下限値
        // 設定ありの場合のみチェックを行う
        String limitMin = ctlPLLimit.getLimitMin();
        if (limitMin != null && limitMin.trim().length() > 0) {
            // 数値チェック
            if (!getLimitNormalVerifier().validate(limitMin)) {
                throw new InvalidInputException(ctlPLLimit.getItemName() + "の最小値", "limitMin", listIndex);
            }
            // 小数点以下桁数チェック
            if (!getLimitRatioVerifier().validate(limitMin)) {
                NotRelevantException ne = new NotRelevantException("構成比", "小数第2位まで", " ( " + ctlPLLimit.getItemName() + " )");
                ne.setHtmlElementName("limitMin");
                ne.setHtmlElementIndex(listIndex);
                throw ne;
            }
            // 数値範囲チェック
            if ((new BigDecimal(limitMin.trim())).compareTo(getLimitRatioMinValue()) < 0
                    || (new BigDecimal(limitMin.trim())).compareTo(getLimitRatioMaxValue()) > 0) {
                throw new NotRelevantException(
                        ctlPLLimit.getItemName() + "の最小値",
                        getLimitRatioMinValue() + "以上、" + getLimitRatioMaxValue() + "以下",
                        "limitMin",
                        listIndex);
            }
        }

        // 上限値
        // 設定ありの場合のみチェックを行う
        String limitMax = ctlPLLimit.getLimitMax();
        if (limitMax != null && limitMax.trim().length() > 0) {
            // 数値チェック
            if (!getLimitNormalVerifier().validate(limitMax)) {
                throw new InvalidInputException(ctlPLLimit.getItemName() + "の最大値", "limitMax", listIndex);
            }
            // 小数点以下桁数チェック
            if (!getLimitRatioVerifier().validate(limitMax)) {
                NotRelevantException ne = new NotRelevantException("構成比", "小数第2位まで", " ( " + ctlPLLimit.getItemName() + " )");
                ne.setHtmlElementName("limitMax");
                ne.setHtmlElementIndex(listIndex);
                throw ne;
            }
            // 数値範囲チェック
            if ((new BigDecimal(limitMax.trim())).compareTo(getLimitRatioMinValue()) < 0
                    || (new BigDecimal(limitMax.trim())).compareTo(getLimitRatioMaxValue()) > 0) {
                throw new NotRelevantException(
                        ctlPLLimit.getItemName() + "の最大値",
                        getLimitRatioMinValue() + "以上、" + getLimitRatioMaxValue() + "以下",
                        "limitMax",
                        listIndex);
            }
        }
    }

    public Verifier getLimitNormalVerifier() {
        return limitNormalVerifier;
    }

    public void setLimitNormalVerifier(Verifier limitNormalVerifier) {
        this.limitNormalVerifier = limitNormalVerifier;
    }

    public Verifier getLimitSumVerifier() {
        return limitSumVerifier;
    }

    public void setLimitSumVerifier(Verifier limitSumVerifier) {
        this.limitSumVerifier = limitSumVerifier;
    }

    public Verifier getLimitRatioVerifier() {
        return limitRatioVerifier;
    }

    public void setLimitRatioVerifier(Verifier limitRatioVerifier) {
        this.limitRatioVerifier = limitRatioVerifier;
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
