/*
 * DateFormatter.java
 * 
 * Created by xytamura on 2005/10/27
 * Copy from 2ndGenesis
 */
package jp.co.isid.mos.bird.framework.util.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日付文字列用フォーマッタ
 * 
 * 日付タイプがDATE_TYPE_YMの場合、「20061」を「2006/01」にフォーマットするように修正
 * @since : 2003.01.10
 * @author: ISID MOS PROJECT
 */
public class DateFormatter extends DefaultFormatter {

    /** 日付フォーマッタ */
    private static SimpleDateFormat _datef =
        new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);

    private int parsePattern;

    // 日付タイプ
    /**
     * 
     * @param DATE_TYPE_YMD DATE_TYPE_YMD
     */
    public static final int DATE_TYPE_YMD = 1;

    /**
     * @param DATE_TYPE_YM DATE_TYPE_YM
     */
    public static final int DATE_TYPE_YM = 2;

    /**
     * @param DATE_TYPE_MD DATE_TYPE_MD
     */
    public static final int DATE_TYPE_MD = 3;

    /** フォーマットパターン：YYYYMMDD */
    public static final java.lang.String PATTERN_NORMAL = "yyyyMMdd";
    /** フォーマットパターン：YYYY/MM/DD */
    public static final java.lang.String PATTERN_SLASH = "yyyy/MM/dd";
    /** フォーマットパターン：YYYY-MM-DD */
    public static final java.lang.String PATTERN_CROSS = "yyyy-MM-dd";
    /** フォーマットパターン：yyyy/MM/dd('E') */
    public static final String PATTERN_SLASH_WEEK = "yyyy/MM/dd(E)";
    /** フォーマットパターン：YYMMDD */
    public static final String PATTERN_SHORT_YMD = "yyMMdd";
    /** フォーマットパターン：YY/MM/DD */
    public static final String PATTERN_SHORT_YMD_SLASH = "yy/MM/dd";
    /** フォーマットパターン：YYYY年MM月DD日（？曜日） */
    public static final java.lang.String PATTERN_KANJI =
        "yyyy'年'MM'月'dd'日（'E'曜日）'";
    /** フォーマットパターン：YYYY年MM月DD日 */
    public static final java.lang.String PATTERN_KANJI_YMD =
        "yyyy'年'MM'月'dd'日'";
    /** フォーマットパターン：YYYYMM */
    public static final java.lang.String PATTERN_MONTH = "yyyyMM";
    /** フォーマットパターン：YYYY/MM */
    public static final java.lang.String PATTERN_MONTH_SLASH = "yyyy/MM";
    /** フォーマットパターン：YYYY年MM日 */
    public static final java.lang.String PATTERN_MONTH_KANJI_YM =
        "yyyy'年'MM'月'";
    /** フォーマットパターン：YYYY */
    public static final java.lang.String PATTERN_YEAR = "yyyy";
    /** フォーマットパターン：YY */
    public static final java.lang.String PATTERN_SHORT_YEAR = "yy";
    /** フォーマットパターン：曜日一文字のみ */
    public static final java.lang.String PATTERN_DAY_OF_WEEK = "E";
    /** フォーマットパターン：d日 （前０抜き） */
    public static final java.lang.String PATTERN_DAY_OF_MONTH = "d'日'";
    /** フォーマットパターン：MM月DD日（？曜） */
    public static final java.lang.String PATTERN_DAY_KANJI = "MM'月'dd'日（'E'）'";
    /** フォーマットパターン：MM月DD日 */
    public static final java.lang.String PATTERN_MMDD_KANJI = "MM'月'dd'日'";

    /** フォーマットパターン：MMDD */
    public static final java.lang.String PATTERN_MMDD = "MMdd";
    /** フォーマットパターン：MM/DD */
    public static final java.lang.String PATTERN_MMDD_SLASH = "MM/dd";

    /**
     * DateFormatter コンストラクター・コメント。
     * 　当コンストラクタを使用した場合は
     * 　　変換前パターン：YYYYMMDD
     * 　　変換後パターン：YYYY/MM/DD
     * 　がデフォルトのパターンになります。
     */
    public DateFormatter() {
        this.parsePattern = DATE_TYPE_YMD;
        this.formatPattern = PATTERN_SLASH;
    }

    /**
     * DateFormatter コンストラクター・コメント。
     * 変換前・後を指定しDateFormatterを生成します。
     * @param  parsePattern  変換前の日付文字列のタイプ
     * @param  formatPattern 変換後の日付文字列の型
     */
    public DateFormatter(int parsePattern, String formatPattern) {
        this.parsePattern = parsePattern;
        this.formatPattern = formatPattern;
    }



    /**
     * 日付文字列(date)が指定されたタイプの中で、どんなパターンかを判別します。
     * @return java.lang.String     日付文字列のパターン
     * @param date java.lang.String 日付文字列
     * @param type int              「年月日」、「年月」、「月日」のタイプ
     */
    private String getDateType(final String date, final int type) {

        if (date == null) {
            return "";
        }

        int length = date.length();
        int slashCnt = countAppointChar(date, "/");
        String parsePattern = "";

        switch (type) {
            case DATE_TYPE_YMD :
                if (date.length() < 6 || date.length() > 10) {
                    return "";
                }
                if (!(slashCnt == 0 || slashCnt == 2)) {
                    return "";
                }

                if (length == 10) {
                    // YYYY/MM
                    parsePattern = "yyyy/MM/dd";
                }
                else if (length == 9) {
                    if (date.lastIndexOf("/") == 7) {
                        // YYYY/MM/D
                        parsePattern = "yyyy/MM/d";
                    }
                    else {
                        // YYYY/M/DD
                        parsePattern = "yyyy/M/dd";
                    }
                }
                else if (length == 8) {
                    if (date.indexOf("/") == -1) {
                        // YYYYMMDD
                        parsePattern = "yyyyMMdd";
                    }
                    else {
                        if (date.indexOf("/") == 2) {
                            // YY/MM/DD
                            parsePattern = "yy/MM/dd";
                        }
                        else {
                            // YYYY/M/D
                            parsePattern = "yyyy/M/d";
                        }
                    }
                }
                else if (length == 7) {
                    if (date.lastIndexOf("/") == 5) {
                        // YY/MM/D
                        parsePattern = "yy/MM/d";
                    }
                    else {
                        // YY/M/DD
                        parsePattern = "yy/M/dd";
                    }
                }
                else if (length == 6) {
                    if (date.indexOf("/") == -1) {
                        // YYMMDD
                        parsePattern = "yyMMdd";
                    }
                    else {
                        // YY/M/D
                        parsePattern = "yy/M/d";
                    }
                }
                break;
            case DATE_TYPE_YM :
                if (date.length() < 4 || date.length() > 7) {
                    return "";
                }

                if (!(slashCnt == 0 || slashCnt == 1)) {
                    return "";
                }

                if (length == 7) {
                    // YYYY/MM
                    parsePattern = "yyyy/MM";
                }
                else if (length == 6) {
                    if (date.indexOf("/") == -1) {
                        // YYYYMM
                        parsePattern = "yyyyMM";
                    }
                    else {
                        // YYYY/M
                        parsePattern = "yyyy/M";
                    }
                }
                else if (length == 5) {
                    //2006/04/05 追加 sart   
                    // YYYYMM
                    if (date.indexOf("/") == -1) {
                        parsePattern = "yyyyMM";
                    }
                    //2006/04/05 追加 end   
                    else {
                        // YY/MM
                        parsePattern = "yy/MM";
                    }
                }
                else if (length == 4) {
                    if (date.indexOf("/") == -1) {
                        // YYMM
                        parsePattern = "yyMM";
                    }
                    else {
                        // YY/M
                        parsePattern = "yy/M";
                    }
                }

                break;
            case DATE_TYPE_MD :
                if (date.length() < 3 || date.length() > 5) {
                    return "";
                }
                if (!(slashCnt == 0 || slashCnt == 1)) {
                    return "";
                }

                if (length == 5) {
                    // MM/DD
                    parsePattern = "MM/dd";
                }
                else if (length == 4) {
                    if (date.indexOf("/") == -1) {
                        // MMDD
                        parsePattern = "MMdd";
                    }
                    else {
                        if (date.indexOf("/") == 2) {
                            // MM/D
                            parsePattern = "MM/d";
                        }
                        else {
                            // M/DD
                            parsePattern = "M/dd";
                        }
                    }
                }
                else if (length == 3) {
                    // M/D
                    parsePattern = "M/d";
                }

                break;
            default :
                break;
        }
        return parsePattern;
    }

    /**
     * 日付文字列変換処理.
     * <P>
     * 日付文字列を指定パターンへ変換します。<BR>
     * 注1)　「yyyyMMdd」型からのみ変換できます。
     * 注2)　変換できる型は限定されています。
     * @return java.lang.String 変換後の日付文字列
     * @param sDate java.lang.String 変換前の日付文字列
     * @param sDate java.lang.String 変換パターン
     * @exception java.lang.Exception 一般例外
     */
    private String stringToDateFormat(
        final String sDate,
        final String sPattern)
        throws Exception {

        //数値チェック
        Integer.valueOf(sDate);

        String sRet = sDate;
        String sYear = sDate.substring(0, 4);
        String sMonth = sDate.substring(4, 6);
        String sDay;
        if (sDate.length() == 6) {
            sDay = "01";
        }
        else {
            sDay = sDate.substring(6);
        }

        //「yyyyMMdd」
        if (sPattern.equals(PATTERN_NORMAL)) {
            sRet = sDate;
        }

        //「yyyy/MM/dd」
        if (sPattern.equals(PATTERN_SLASH)) {
            sRet = sYear + "/" + sMonth + "/" + sDay;
        }

        //「yyyy-MM-dd」
        if (sPattern.equals(PATTERN_CROSS)) {
            sRet = sYear + "-" + sMonth + "-" + sDay;
        }

        //「yyyy年MM月dd日（?曜日）」, 「yyyy年MM月dd日」
        if (sPattern.equals(PATTERN_KANJI)
            || sPattern.equals(PATTERN_KANJI_YMD)) {

            sRet = sYear + "年" + sMonth + "月" + sDay + "日";
        }

        //「yyyyMM」
        if (sPattern.equals(PATTERN_MONTH)) {

            sRet = sYear + sMonth;
        }

        //「yyyy/MM」
        if (sPattern.equals(PATTERN_MONTH_SLASH)) {

            sRet = sYear + "/" + sMonth;
        }
        //「MMdd」
        if (sPattern.equals(PATTERN_MMDD)) {

            sRet = sMonth + sDay;
        }

        //「MM/dd」
        if (sPattern.equals(PATTERN_MMDD_SLASH)) {

            sRet = sMonth + "/" + sDay;
        }

        return sRet;
    }

    /**
     * フォーマット処理を行います。
     * 日付文字列(target)のパターンを解析してから、フォーマットを行います。
     * 　注) 日付文字列が日付タイプ(parseType)として判断できない場合は、デフォルト文字列を返します。
     * @since  2003.01.10
     * @param  target     対象文字列
     * @param  formatPattern 変換後の日付文字列の型
     * @param  parseType  変換前文字列の日付タイプ
     * @return フォーマット済文字列
     */
    public String format(
        final String target,
        final String formatPattern,
        final int parseType) {

        String ptn = getDateType(target, parseType);

        return format(target, ptn, formatPattern, true);
    }

    /**
     * フォーマット処理を行います。
     * パラメータで指定されたパターンでフォーマットします。
     * @param  target        対象文字列
     * @param  parsePattern  変換前の日付文字列の型
     * @param  formatPattern 変換後の日付文字列の型
     * @param  isUseDefault  Exceptionが発生した時の戻り値判別フラグ true：デフォルトの値 / false：空値
     * @return フォーマット済文字列
     */
    public String format(
        final String target,
        final String parsePattern,
        final String formatPattern,
        final boolean isUseDefault) {

        String ret = "";

        if (target == null) {
            return getDefaultText();
        }

        try {

            if (parsePattern.equals(PATTERN_NORMAL)
                && (target.length() == 6 || target.length() == 8)) {
                if (target.substring(0, 4).equals("0000")) {
                    return stringToDateFormat(target, formatPattern);
                }
            }

            synchronized (_datef) {
                _datef.applyPattern(parsePattern);
                _datef.setLenient(false);
                Date date = _datef.parse(target);
                _datef.applyPattern(formatPattern);
                ret = _datef.format(date);
            }
        }
        catch (Exception ex) {
            ret = target;
        }

        return ret;
    }

    /**
     * フォーマット処理を行います。
     * デフォルト指定されたパターンを使用しフォーマットされます。
     * @since  2003.01.10
     * @param  target     対象文字列
     * @param  isForView  true：表示用 / false：値処理用
     * @return フォーマット済文字列
     */
    public String format(final Object target, final boolean isForView) {
        String sDate = (String) target;
        if (isForView) {
            
            String parsePtn = getDateType(sDate, parsePattern);
            return format(sDate, parsePtn, formatPattern, isForView);
        } 
        else {
            String parsePtn = getDateType(sDate, parsePattern);
            String unformatedPtn = "";
            for (int i = 0; i < formatPattern.length(); i++) {
                char c = formatPattern.charAt(i);
                if (c == 'y' || c == 'M' || c == 'd') {
                    unformatedPtn += formatPattern.substring(i, i + 1);
                }
            }
            return format(sDate, parsePtn, unformatedPtn, isForView);
        }
    }

    /**
     * @param target 対象文字列
     * @return value
     */
    public Object parseValue(final String target) {
        return target;
    }
}