/*
 * 作成日: 2005/12/06
 */
package jp.co.isid.mos.bird.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 各種変換処理クラス.
 * @author xnkusama
 */
public class Converter {
    /** 数値フォーマッタ */
    private static DecimalFormat _decf = new DecimalFormat();
    /** 日付フォーマッタ */
    private static SimpleDateFormat _datef = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
    /** フォーマットパターン：YYYYMMDD */
    public static final java.lang.String PATTERN_NORMAL = "yyyyMMdd";
    /** フォーマットパターン：YYYYMM */
    public static final java.lang.String PATTERN_MONTH = "yyyyMM";
    /** フォーマットパターン：YYYYMMDD */
    public static final java.lang.String PATTERN_YEAR = "yyyy";

    /**
     * 日付文字列解析処理.
     * <P>
     * 任意の日付文字列（String）を日付（Date）へ変換します。<BR>
     * @return java.lang.String 日付
     * @param pattern java.lang.String 変換前の日付文字列の型
     * @param text java.lang.String 変換前の日付文字列
     * @exception java.lang.Exception 例外記述
     */
    public static Date stringToDate(String pattern, String text) throws java.lang.Exception {
        synchronized ( _datef ) {
            _datef.applyPattern( pattern );
            _datef.setLenient(false);
            return _datef.parse( text );
        }
    }

    /**
     * 日付フォーマット処理.
     * <P>
     * 日付型（Date）を任意の日付文字列（String）へ変換します。<BR>
     * @return java.lang.String 変換後の日付文字列
     * @param pattern java.lang.String 変換後の日付文字列の型
     * @param date java.util.Date 日付型
     * @exception java.lang.Exception 一般例外
     */
    public static String dateToString(String pattern, Date date) throws java.lang.Exception {
        if (date == null) return "";
        synchronized ( _datef ) {
            _datef.applyPattern( pattern );
            return _datef.format( date );
        }
    }

    /**
     * 数値文字列解析処理.
     * <P>
     * 「###,###,###,##0.###」の文字列を数値型（BigDecimal）へ変換します。<BR>
     * @return java.math.BigDecimal 変換後の数値
     * @param text java.lang.String 変換する文字列
     * @exception java.lang.Exception 一般例外
     */
    public static BigDecimal stringToDec(String text) throws Exception {

        return new BigDecimal(removeComma(text).trim());
    }

    /**
     * カンマ（,）を取り除く
     * @return java.lang.String
     * @param source java.lang.String
     */
    private static String removeComma(String source) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<source.length();i++) {
            if ( source.charAt(i) != ',' ) {
                sb.append(source.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 数値フォーマット処理.
     * <P>
     * 数値型（BigDecimal）を「###,###,###,##0」の文字列へ変換します。<BR>
     * 作成日 : (2000/05/16 11:42:46)
     * @return java.lang.String 変換後の文字列
     * @param big java.math.BigDecimal 変換する数値
     */
    public static String decToString(BigDecimal big) {
        return decToString( big, 0 );
    }
    /**
     * 数値フォーマット処理.
     * <P>
     * 数値型（BigDecimal）を「###,###,###,##0」で、指定の小数点以下桁数の文字列へ変換します。<BR>
     * 作成日 : (2000/05/16 11:42:46)<BR>
     * 更新日 ：(2001/04/16 15:00:00) DecimalFormatのバグ(？)対応<BR>
     * <PRE>
     * 症状）引数：bigが「0」の時、format後に「1」を返す事がある。
     * 条件）以下の３つを満たす時
     * 　　　１：前回当メソッドを呼出された時の引数：bigの頭1桁が６～９の時(桁数は関係なし)
     * 　　　２：引数：digitsNumが「0」
     * 　　　３：VisualAge3.5
     * 対処）formatメソッドを２回呼ぶ。(これにより、条件１に該当しなくなる）
     * </PRE>
     * @return java.lang.String 変換後の文字列
     * @param big java.math.BigDecimal 変換する数値
     * @param digitsNum int 小数点以下桁数
     */
    public static String decToString(BigDecimal big, int digitsNum) {
        if (big == null) return "";
        String pattern = "#,##0";
        for (int i=0; i<digitsNum; i++) {
            if (i==0) pattern += ".";
            pattern += "0";
        }
        synchronized (_decf) {
            _decf.applyPattern(pattern);
            _decf.setMaximumFractionDigits( digitsNum );
//    -- Add Start 2001/04/16 N.Kusama(ASPAC) DecimalFormatのバグ(？)対応
            String dummyRet = _decf.format( big.doubleValue() );
//    -- Add End   2001/04/16
            return _decf.format( big.doubleValue() );
        }
    }
    /**
     * 数値フォーマット処理.
     * <P>
     * 数値型（BigDecimal）を「###,###,###,##0」で、指定の小数点以下桁数の文字列へ変換します。<BR>
     * <PRE>
     * 丸めモード(roundMode)定数について
     * 　　BigDecimal.ROUND_DOWN (int 1)    ： 切捨て
     * 　　BigDecimal.ROUND_HALF_UP (int 4) ： 四捨五入
     * 　　（その他についてはJDK BigDecimalクラスを参照）
     * </PRE>
     * 作成日 : (2000/05/16 11:42:46)
     * @return java.lang.String 変換後の文字列
     * @param big java.math.BigDecimal 変換する数値
     * @param digitsNum int 小数点以下桁数
     * @param roundMode int 丸めモード
     */
    public static String decToString(BigDecimal big, int digitsNum, int roundMode) {
        if (big == null) return "";
        BigDecimal retBig = big.divide( new BigDecimal("1"), digitsNum, roundMode );
        return decToString( retBig, digitsNum );
    }
    /**
     * 数値フォーマット処理.
     * <P>
     * 数値型（BigDecimal）を 指定されたパターンの文字列へ変換します。<BR>
     * 作成日 : (2000/05/16 11:42:46)
     * @return java.lang.String 変換後の文字列
     * @param big java.math.BigDecimal 変換する数値
     * @param pattern java.lang.Stirngl 編集パターン 
     */
    public static String decToString(BigDecimal big,String pattern) {
        if (big == null) return "";
        synchronized (_decf) {
            _decf.applyPattern(pattern);
//    -- Add Start 2001/04/16 N.Kusama(ASPAC) DecimalFormatのバグ(？)対応
            String dummyRet = _decf.format( big.doubleValue() );
//    -- Add End   2001/04/16
            return _decf.format( big.doubleValue() );
        }
    }
}
