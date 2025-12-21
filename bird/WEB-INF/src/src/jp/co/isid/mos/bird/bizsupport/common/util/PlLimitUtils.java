/*
 * 作成日: 2006/09/05
 *
 */
package jp.co.isid.mos.bird.bizsupport.common.util;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.CtlPLLimit;

/**
 * P/L上下限ユーティリティ
 * 
 * @author xyuchida
 */
public class PlLimitUtils {

    /**
     * P/Lタイプ 店舗P/L
     */
    private static final String PL_TYPE_TENPO = "1";
    /**
     * 上下限タイプ 金額
     */
    private static final String LIMIT_TYPE_SUM = "0";
    /**
     * 上下限タイプ 構成比
     */
    private static final String LIMIT_TYPE_RATIO = "1";
    /**
     * POS売上差対象カラム名称
     */
    private static final String DIFF_POS_COLUMN = "DIFF_POS";

    /**
     * P/L上下限チェック
     * 
     * @param plType P/Lタイプ
     * @param itemCode 項目カラム名
     * @param value チェック対象値
     * @param uriagedaka 売上高
     * @param plLimitList P/L上下限List
     * @return チェック結果 = true : OK, = false : NG
     */
    public static boolean checkLimit(
            String plType, String itemCode, BigDecimal value, BigDecimal uriagedaka, List plLimitList) {

        boolean result = false;

        // パラメータチェック
        if (plType != null && itemCode != null && value != null && uriagedaka != null && plLimitList != null) {
            // 上下限値取得
            BigDecimal maxValue = getMaxValue(plType, itemCode, uriagedaka, plLimitList);
            BigDecimal minValue = getMinValue(plType, itemCode, uriagedaka, plLimitList);

            // 上下限チェック
            if (maxValue != null && minValue != null) {
                // MIN値 ≦ パラメータ ≦ MAX値
                if (value.compareTo(maxValue) <= 0
                        && value.compareTo(minValue) >= 0) {
                    // OK
                    result = true;
                }
            }
        }

        return result;
    }

    /**
     * P/L上下限チェック
     * 
     * @param plType P/Lタイプ
     * @param itemCode 項目カラム名
     * @param value チェック対象値
     * @param uriagedaka 売上高
     * @param plLimitList P/L上下限List
     * @return チェック結果 = true : OK, = false : NG
     */
    public static boolean checkLimit(
            String plType, String itemCode, String value, String uriagedaka, List plLimitList) {

        // 数値変換
        BigDecimal valueNum = parseNumberString(value);
        BigDecimal uriagedakaNum = parseNumberString(uriagedaka);

        // 数値変換できた場合のみチェック実施
        boolean result = false;
        if (valueNum != null && uriagedakaNum != null) {
            // P/L上下限チェック
            result = checkLimit(plType, itemCode, valueNum, uriagedakaNum, plLimitList);
        }

        return result;
    }

    /**
     * POS売上差チェック
     * 
     * @param uriagedaka 売上高
     * @param posValue POS売上
     * @param plLimitList P/L上下限List
     * @return チェック結果 = true : OK, = false : NG
     */
    public static boolean checkPosDiff(BigDecimal uriagedaka, BigDecimal posValue, List plLimitList) {

        boolean result = false;

        // パラメータチェック
        if (uriagedaka != null && posValue != null && plLimitList != null) {
            // POS売上差上限値取得
            BigDecimal maxValue = getMaxValue(PL_TYPE_TENPO, DIFF_POS_COLUMN, posValue, plLimitList);
            BigDecimal minValue = getMinValue(PL_TYPE_TENPO, DIFF_POS_COLUMN, posValue, plLimitList);

            if (maxValue != null && minValue != null) {
                // 差分
                BigDecimal diffValue = uriagedaka.subtract(posValue);
                // 差分範囲チェック
                if (diffValue.compareTo(minValue) >= 0 && diffValue.compareTo(maxValue) <= 0) {
                    // OK
                    result = true;
                }
            }
        }

        return result;
    }

    /**
     * POS売上差チェック
     * 
     * @param uriagedaka 売上高
     * @param posValue POS売上
     * @param plLimitList P/L上下限List
     * @return チェック結果 = true : OK, = false : NG
     */
    public static boolean checkPosDiff(String uriagedaka, String posValue, List plLimitList) {

        // 数値変換
        BigDecimal uriagedakaNum = parseNumberString(uriagedaka);
        BigDecimal posValueNum = parseNumberString(posValue);

        // 数値変換できた場合のみチェック実施
        boolean result = false;
        if (uriagedakaNum != null && posValueNum != null) {
            // POS売上差チェック
            result = checkPosDiff(uriagedakaNum, posValueNum, plLimitList);
        }

        return result;
    }

    /**
     * 最大値取得
     * 
     * @param plType P/Lタイプ
     * @param itemCode 項目カラム名
     * @param baseValue 構成比算出基準値
     * @param plLimitList P/L上下限List
     * @return 最大値
     */
    private static BigDecimal getMaxValue(String plType, String itemCode, BigDecimal baseValue, List plLimitList) {
        // P/L上下限取得
        CtlPLLimit ctlPlLimit = searchPlLimit(plType, itemCode, plLimitList);
        BigDecimal maxValue = null;
        if (ctlPlLimit != null) {
            // 上下限タイプ判定
            if (ctlPlLimit.getLimitType().equals(LIMIT_TYPE_SUM)) {
                // 金額
                maxValue = ctlPlLimit.getLimitMax();
            } else if (ctlPlLimit.getLimitType().equals(LIMIT_TYPE_RATIO)) {
                // 構成比
                maxValue = calcRatio(ctlPlLimit.getLimitMax(), baseValue);
            }
        }
        return maxValue;
    }

    /**
     * 最小値取得
     * 
     * @param plType P/Lタイプ
     * @param itemCode 項目カラム名
     * @param baseValue 構成比算出基準値
     * @param plLimitList P/L上下限List
     * @return 最小値
     */
    private static BigDecimal getMinValue(String plType, String itemCode, BigDecimal baseValue, List plLimitList) {
        // P/L上下限取得
        CtlPLLimit ctlPlLimit = searchPlLimit(plType, itemCode, plLimitList);
        BigDecimal minValue = null;
        if (ctlPlLimit != null) {
            // 上下限タイプ判定
            if (ctlPlLimit.getLimitType().equals(LIMIT_TYPE_SUM)) {
                // 金額
                minValue = ctlPlLimit.getLimitMin();
            } else if (ctlPlLimit.getLimitType().equals(LIMIT_TYPE_RATIO)) {
                // 構成比
                minValue = calcRatio(ctlPlLimit.getLimitMin(), baseValue);
            }
        }
        return minValue;
    }

    /**
     * P/L上下限検索
     * 
     * @param plType P/Lタイプ
     * @param itemCode 対象項目カラム名
     * @param plLimitList P/L上下限List
     * @return 対象項目P/L上下限
     */
    private static CtlPLLimit searchPlLimit(String plType, String itemCode, List plLimitList) {
        CtlPLLimit result = null;
        if (plLimitList != null) {
            for (Iterator it = plLimitList.iterator(); it.hasNext();) {
                CtlPLLimit ctlPLLimit = (CtlPLLimit) it.next();
                if (plType.equals(ctlPLLimit.getPlType())
                        && itemCode.equals(ctlPLLimit.getColumnName())) {
                    result = ctlPLLimit;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 構成比計算
     * 
     * @param value 構成比
     * @param baseValue 構成比算出基準値
     * @return 算出金額
     */
    private static BigDecimal calcRatio(BigDecimal value, BigDecimal baseValue) {
        return baseValue.multiply(value).divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_DOWN);
    }

    /**
     * 数値変換
     * 
     * @param value 数値文字列
     * @return 数値
     */
    private static BigDecimal parseNumberString(String value) {
        BigDecimal result = null;
        try {
            result = new BigDecimal(value);
        } catch (NumberFormatException e) {
            // 変換できない場合nullを返却する為処理なし
        }
        return result;
    }
}
