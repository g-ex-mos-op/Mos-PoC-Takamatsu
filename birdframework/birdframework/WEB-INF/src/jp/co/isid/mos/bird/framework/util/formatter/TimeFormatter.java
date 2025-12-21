/*
 * TimeFormatter.java
 * 
 * Created by yhli on 2003/11/26
 * Copy from 2ndGenesis
 */
package jp.co.isid.mos.bird.framework.util.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** 
 * @author ISID MOS PROJECT
 * @version 2003/11/26
 */
public class TimeFormatter extends DefaultFormatter {

    /** 日付フォーマッタ */
    private static SimpleDateFormat _datef =
        new SimpleDateFormat("HHmmss", Locale.JAPAN);

    /** HH:MM:SS  */
    public static final int TIME_TYPE_HMS = 1;
    /** HH:MM  */
    public static final int TIME_TYPE_HM = 2;
    /** MM:SS  */
    public static final int TIME_TYPE_MS = 4;

    private int _timeType = 0;
    private String _formatPtn = "";
    private String _unformatPtn = "";

    /**
     * DateValidator コンストラクター・コメント。
     */
    public TimeFormatter() {
        this(TIME_TYPE_HMS);
    }

    /**
     * DateValidator コンストラクター・コメント。
     * @param timeType チェックする日付タイプ
     */
    public TimeFormatter(int timeType) {
        _timeType = timeType;
        if (timeType == TIME_TYPE_HMS) {
            _formatPtn = "HH:mm:ss";
            _unformatPtn = "HHmmss";
        }
        else if (timeType == TIME_TYPE_HM) {
            _formatPtn = "HH:mm";
            _unformatPtn = "HHmm";
        }
        else if (timeType == TIME_TYPE_MS) {
            _formatPtn = "mm:ss";
            _unformatPtn = "mmss";
        }
    }

    /**
     * 
     * @param  target     対象文字列
     * @return フォーマット済文字列
     * @param  isForView  true/falseに関わらず表示用にフォーマットします
     */
    public String format(final Object target, final boolean isForView) {
        String sDate = (String) target;
        String parsePtn = getDateType(sDate, _timeType);
        if (isForView) {
            return format(sDate, parsePtn, _formatPtn, isForView);
        }
        else {
            return format(sDate, parsePtn, _unformatPtn, isForView);
        }
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

            /*if (parsePattern.equals(PATTERN_NORMAL)
                && (target.length() == 6 || target.length() == 8)) {
                if (target.substring(0, 4).equals("0000")) {
                    return stringToDateFormat(target, formatPattern);
                }
            }*/

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
        int slashCnt = countAppointChar(date, ":");
        String parsePattern = "";

        switch (type) {
            case TIME_TYPE_HMS :
                if (!(date.length() == 6 || date.length() == 8)) {
                    return "";
                }
                if (!(slashCnt == 0 || slashCnt == 2)) {
                    return "";
                }

                if (length == 8) {
                    // HH:MM:SS
                    parsePattern = "HH:mm:ss";
                }
                else if (length == 6) {
                    // HHMMSS
                    parsePattern = "HHmmss";
                }
                break;

            case TIME_TYPE_HM :
                if (!(date.length() == 4 || date.length() == 5)) {
                    return "";
                }

                if (!(slashCnt == 0 || slashCnt == 1)) {
                    return "";
                }

                if (length == 5) {
                    // HH:MM
                    parsePattern = "HH:mm";
                }
                else if (length == 4) {
                    //HHMM
                    parsePattern = "HHmm";
                }
                break;
            case TIME_TYPE_MS :
                if (!(date.length() == 4 || date.length() == 5)) {
                    return "";
                }
                if (!(slashCnt == 0 || slashCnt == 1)) {
                    return "";
                }

                if (length == 5) {
                    // MM:SS
                    parsePattern = "mm:ss";
                }
                else if (length == 4) {
                    // MMSS
                    parsePattern = "mmss";
                }
                break;
            default :
                break;
        }
        return parsePattern;
    }

    /**
     * @param target 対象文字列
     * @return value
     */
    public Object parseValue(final String target) {
        return target;
    }
}
