/*
 * 作成日: 2005/12/06
 */
package jp.co.isid.mos.bird.framework.util;

import java.math.BigDecimal;

/**
 * 汎用計算クラス.
 * @author xnkusama
 */
public class Calculator {
    /** 百分率用数値 */
    private final static java.math.BigDecimal BIG_HUNDRED = new BigDecimal("100");

    /**
     * 百分率計算処理.
     * <P>
     * 百分率計算を行います。小数点を四捨五入します。<BR>
     * @return java.math.BigDecimal 計算結果
     * @param numerator java.math.BigDecimal 分子
     * @param denominator java.math.BigDecimal 分母
     */
    public static BigDecimal percentage(BigDecimal numerator, BigDecimal denominator) {
        return percentage(numerator, denominator, 0);
    }
    /**
     * 百分率計算処理.
     * <P>
     * 百分率計算を行います。<BR>
     * 小数点以下を指定の桁まで計算し、四捨五入します。<BR>
     * @return java.math.BigDecimal 計算結果
     * @param numerator java.math.BigDecimal 分子
     * @param denominator java.math.BigDecimal 分母
     * @param scale int 小数点以下の桁数
     */
    public static BigDecimal percentage(BigDecimal numerator, BigDecimal denominator, int scale) {
        if (denominator == null) return new BigDecimal("0");
        if (denominator.signum() == 0) return new BigDecimal("0");
        if (numerator == null) return divide(new BigDecimal("0"), denominator, scale);
        return divide( numerator.multiply(BIG_HUNDRED), denominator, scale );
    }
    /**
     * 百分率計算処理.
     * <P>
     * 百分率計算を行います。小数点を四捨五入します。<BR>
     * @return java.math.BigDecimal 計算結果
     * @param numerator java.lang.String 分子
     * @param denominator java.lang.String 分母
     * @exception java.lang.Exception StringからBigDecimalへの変換時に起こる例外
     */
    public static BigDecimal percentage(String numerator, String denominator) throws java.lang.Exception {
        return percentage(numerator, denominator,0);
    }
    /**
     * 百分率計算処理.
     * <P>
     * 百分率計算を行います。<BR>
     * 小数点以下を指定の桁まで計算し、四捨五入します。<BR>
     * @return java.math.BigDecimal 計算結果
     * @param numerator java.lang.String 分子
     * @param denominator java.lang.String 分母
     * @param scale int 小数点以下の桁数
     * @exception java.lang.Exception StringからBigDecimalへの変換時に起こる例外
     */
    public static BigDecimal percentage(String numerator, String denominator, int scale) throws java.lang.Exception {
        if (denominator == null || denominator.equals("")) return new BigDecimal("0");
        if (numerator == null || numerator.equals("")) return percentage(Converter.stringToDec("0"), Converter.stringToDec(denominator), scale);
        return percentage( Converter.stringToDec(numerator), Converter.stringToDec(denominator), scale );
    }

    /**
     * 割算処理.
     * <P>
     * 割り算処理を行います。小数点を四捨五入します。<BR>
     * @return java.math.BigDecimal 計算結果
     * @param numerator java.lang.String 分子
     * @param denominator java.lang.String 分母
     * @exception java.lang.Exception StringからBigDecimalへの変換で起こる例外 
     */
    public static BigDecimal divide(String numerator, String denominator) throws Exception {
        return divide(numerator, denominator, 0);
    }
    /**
     * 割算処理.
     * <P>
     * 割り算処理を行います。<BR>
     * 小数点以下を指定の桁まで計算し、四捨五入します。<BR>
     * @return java.math.BigDecimal 計算結果
     * @param numerator java.lang.String 分子
     * @param denominator java.lang.String 分母
     * @param scale int 小数点以下の桁数
     * @exception java.lang.Exception StringからBigDecimalへの変換で起こる例外
     */
    public static BigDecimal divide(String numerator, String denominator, int scale) throws Exception {
        if (denominator == null || denominator.equals("")) return new BigDecimal("0");
        if (numerator == null || numerator.equals("")) return divide(Converter.stringToDec("0"), Converter.stringToDec(denominator), scale);
        return divide(Converter.stringToDec(numerator), Converter.stringToDec(denominator), scale);
    }
    /**
     * 割算処理.
     * <P>
     * 割り算処理を行います。小数点を四捨五入します。<BR>
     * @return java.math.BigDecimal 計算結果
     * @param numerator java.math.BigDecimal 分子
     * @param denominator java.math.BigDecimal 分母
     */
    public static BigDecimal divide(BigDecimal numerator, BigDecimal denominator) {
        return divide(numerator, denominator, 0);
    }
    /**
     * 割算処理.
     * <P>
     * 割り算処理を行います。<BR>
     * 小数点以下を指定の桁まで計算し、四捨五入します。<BR>
     * @return java.math.BigDecimal 計算結果
     * @param numerator java.math.BigDecimal 分子
     * @param denominator java.math.BigDecimal 分母
     * @param scale int 小数点以下の桁数
     */
    public static BigDecimal divide(BigDecimal numerator, BigDecimal denominator, int scale) {
        if (denominator == null) return new BigDecimal("0");
        if (denominator.signum() == 0) return new BigDecimal("0");
        if (numerator == null) return (new BigDecimal("0")).divide(denominator, scale, BigDecimal.ROUND_HALF_UP);
        return numerator.divide(denominator, scale, BigDecimal.ROUND_HALF_UP);
    }
}
