/*
 * DateVerifier.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
/**
 * 日付のみ許可するバリデータ
 * 2004/04/01 getValidDescription()メソッド追加
 * @since : 2003.01.10
 * @author: Administrator
 */
public class DateVerifier extends DefaultVerifier {
    /** 日付フォーマッタ */
    private static SimpleDateFormat _datef =
        new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);

    // チェックする日付タイプ
    /**
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

    private int _type = 0;

    /**
     * DateVerifier コンストラクター・コメント。
     */
    public DateVerifier() {
        _type = DATE_TYPE_YMD;
    }

    /**
     * DateVerifier コンストラクター・コメント。
     * @param dateType チェックする日付タイプ
     */
    public DateVerifier(int dateType) {
        _type = dateType;
    }

    /**
     * 文字列(str)内に指定文字列(appointChar)が何個あるかを取得します
     * @return int
     * @param str java.lang.String
     * @param appointChar java.lang.String
     */
    private int countAppointChar(final String str, final String appointChar) {
        if (str == null) {
            return 0;
        }
        int count = 0;
        int startIndex = 0;

        while (str.indexOf(appointChar, startIndex) != -1) {
            startIndex =
                str.indexOf(appointChar, startIndex)
                    + appointChar.getBytes().length;
            count++;
        }

        return count;
    }

    /**
     * 日付文字列(formatDate)を変換前パターン(parsePattern)からパターン(formatPattern)に変換します。
     * 変換できた場合はtrueを、失敗の場合はfalseを返します。
     * @return boolean  変換可：true  変換不可：false
     * @param formatDate java.lang.String
     */
    private boolean format(
        final String formatDate,
        final String parsePattern,
        final String formatPattern) {
        boolean ret = false;

        try {
            synchronized (_datef) {
                _datef.applyPattern(parsePattern);
                _datef.setLenient(false);
                Date date = _datef.parse(formatDate);
                _datef.applyPattern(formatPattern);
                _datef.format(date);
            }
        }
        catch (IllegalArgumentException e1) {
            return false;
        }
        catch (ParseException e2) {
            return false;
        }

        return true;
    }

    /**
     * 「月日」タイプの妥当性チェック
     * 許されるタイプ： MM/DD , MMDD , MM/D , M/DD ,  M/D
     * @since : 2003.01.10
     * @return boolean
     * @param date java.lang.String
     */
    private boolean validateMD(final String date) {

        String tmpDate = "";
        String parsePattern = "";
        int length = date.length();

        if (date.length() < 3 || date.length() > 5) {
            return false;
        }
        int slashCnt = countAppointChar(date, "/");
        if (!(slashCnt == 0 || slashCnt == 1)) {
            return false;
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
                    // M/DD
                    parsePattern = "M/dd";
                }
                else {
                    // MM/D
                    parsePattern = "MM/d";
                }
            }
        }
        else if (length == 3) {
            // M/D
            parsePattern = "M/d";
        }

        return format(date, parsePattern, "yyyy/MM/dd");

    }

    /**
     * 「年月」タイプの妥当性チェック
     * 許されるタイプ： YYYY/MM , YYYY/M , YYYYMM , YY/MM , YY/M , YYMM
     * 作成日 : (2003/01/09 20:39:10)
     * @return boolean
     * @param date java.lang.String
     */
    private boolean validateYM(final String date) {

        String tmpDate = "";
        String parsePattern = "";
        int length = date.length();

        if (date.length() < 4 || date.length() > 7) {
            return false;
        }

        int slashCnt = countAppointChar(date, "/");
        if (!(slashCnt == 0 || slashCnt == 1)) {
            return false;
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
            // YY/MM
            parsePattern = "yy/MM";
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

        return format(date, parsePattern, parsePattern);

    }

    /**
     * 「年月日」タイプの妥当性チェック
     * 許されるタイプ： YYYY/MM/DD , YYYY/MM/D , YYYY/M/DD , YYYY/M/D , YYYYMMDD 
     * 　　　　　　　　 YY/MM/DD   , YY/MM/D   , YY/M/DD   , YY/M/D   , YYMMDD
     * @since : 2003.01.10
     * @return boolean
     * @param date java.lang.String
     */
    private boolean validateYMD(final String date) {

        String tmpDate = "";
        String parsePattern = "";
        int length = date.length();

        if (date.length() < 6 || date.length() > 10) {
            return false;
        }
        int slashCnt = countAppointChar(date, "/");
        if (!(slashCnt == 0 || slashCnt == 2)) {
            return false;
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
                if (date.indexOf("/") == 3) {
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
            if (date.lastIndexOf("/") == 6) {
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

        return format(date, parsePattern, "yyyy/MM/dd");

    }

    /**
     * チェックタイプ設定処理
    * @param type チェックタイプ
     */
    public void setCheckType(final int type) {
        _type = type;
    }


    /**
     * 妥当性チェックを行います。 
     * @param  target       対象となる文字列 
     * @return true：成功 / false：失敗
     */
    public boolean validate(final String target) {

        boolean ret = false;
        String pattern = "";

        if (target == null || target.trim().equals("")) {
            return _isNullable;
        }

        if (_type == DATE_TYPE_YM) {
            ret = validateYM(target);
        }

        if (_type == DATE_TYPE_MD) {
            ret = validateMD(target);
        }

        if (_type == DATE_TYPE_YMD) {
            ret = validateYMD(target);
        }

        return ret;

    }

    /**
     * 妥当性チェックを行います。 
     * @param  target       対象となる文字列
     * @param  type         日付タイプ
     * @return true：成功 / false：失敗
     */
    public boolean validate(final String target, final int type) {
        setCheckType(type);
        return validate(target);
    }
    
    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        String retDescription = "[日付";
        
        switch (_type) {
            case DATE_TYPE_MD:
                retDescription += "(月日)";
                break;
            case DATE_TYPE_YM:
                retDescription += "(年月)";
                break;
            case DATE_TYPE_YMD:
                retDescription += "(年月日)";
                break;
            default:
        }
        
        return retDescription + "]";
    }

}
