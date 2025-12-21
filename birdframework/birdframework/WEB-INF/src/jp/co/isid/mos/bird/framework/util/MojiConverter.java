package jp.co.isid.mos.bird.framework.util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * 各種変換処理クラス
 * @author ISID MOS PROJECT
 * @version 2003.09.17
 */
public final class MojiConverter {

    private static boolean _isCp932 = false;

    static {
        String p = System.getProperty("iscp932");
        String os = System.getProperty("os.name");
        if (Locale.getDefault().getLanguage().equals("ja")) {
            if (p != null && Boolean.getBoolean(p)) {
                _isCp932 = true;
            }
            //            else if (os != null &&  
            //                     (os.equals("Windows 95") || os.equals("Windows NT")))
            else if (os != null && os.indexOf("Windows") >= 0) {
                _isCp932 = true;
            }
        }
    }

    /**
     * Constructor
     */
    private MojiConverter() {
    }

    /**
     * Windowsクライアント(MS932)からの入力文字列をUnicode文字列に変換
     * @return String 
     * @param str String 入力文字列
     */
    public static String convertMS932toUnicode(final String str) {
        StringBuffer newString = new StringBuffer();

        if (str == null)
            return str;

        for (int i = 0; i < str.length(); i++) {
            char c = str.substring(i, i + 1).toCharArray()[0];
            try {
                if (c
                    == (new String("－".getBytes("MS932"), "JISAutoDetect"))
                        .toCharArray()[0]) {
                    newString.append('\u2212');
                }
                else if (
                    c
                        == (new String("～".getBytes("MS932"), "JISAutoDetect"))
                            .toCharArray()[0]) {
                    newString.append('\u301c');
                }
                else if (
                    c
                        == (new String("∥".getBytes("MS932"), "JISAutoDetect"))
                            .toCharArray()[0]) {
                    newString.append('\u2016');
                }
                else if (
                    c
                        == (new String("￠".getBytes("MS932"), "JISAutoDetect"))
                            .toCharArray()[0]) {
                    //newString.append('\u00a2');
                    newString.append('\uffe0');
                }
                else if (
                    c
                        == (new String("￡".getBytes("MS932"), "JISAutoDetect"))
                            .toCharArray()[0]) {
                    //newString.append('\u00a3');
                    newString.append('\uffe1');
                }
                else if (
                    c
                        == (new String("￢".getBytes("MS932"), "JISAutoDetect"))
                            .toCharArray()[0]) {
                    newString.append('\u00ac');
                }
                else if (
                    c
                        == (new String("―".getBytes("MS932"), "JISAutoDetect"))
                            .toCharArray()[0]) {
                    newString.append('\u2014');
                }
                else {
                    newString.append(c);
                }
            }
            catch (UnsupportedEncodingException uex) {
            }
            catch (NullPointerException npex) {
            }
        }
        return newString.toString();
    }

    /**
     * Unicode文字列をWindowsクライアント(MS932)の入力文字列に変換
     * @return String 
     * @param str String 入力文字列
     */
    public static String convertUnicodetoMS932(final String str) {
        StringBuffer newString = new StringBuffer();

        if (str == null)
            return str;

        for (int i = 0; i < str.length(); i++) {
            char c = str.substring(i, i + 1).toCharArray()[0];
            try {
                if (c == '\u2212') {
                    newString.append("－");
                }
                else if (c == '\u301c') {
                    newString.append("～");
                }
                else if (c == '\u2016') {
                    newString.append("∥");
                }
                else if (c == '\uffe0') {
                    //newString.append('\u00a2');
                    newString.append("￠");
                }
                else if (c == '\uffe1') {
                    //newString.append('\u00a3');
                    newString.append("￡");
                }
                else if (c == '\u00ac') {
                    newString.append("￢");
                }
                else if (c == '\u2014') {
                    newString.append("―");
                }
                else {
                    newString.append(c);
                }
            }
            catch (NullPointerException npex) {
            }
        }
        return newString.toString();
    }

    /**
     * This method converts Cp932 to JIS.
     * @param s 変換前文字列
     * @return String 
     */
    public static String toJIS(final String s) {
        if (!_isCp932 || s == null) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case 0xff3c : // FULLWIDTH REVERSE SOLIDUS ->
                    c = 0x005c; // REVERSE SOLIDUS
                    break;
                case 0xff5e : // FULLWIDTH TILDE ->
                    c = 0x301c; // WAVE DASH
                    break;
                case 0x2225 : // PARALLEL TO ->
                    c = 0x2016; // DOUBLE VERTICAL LINE
                    break;
                case 0xff0d : // FULLWIDTH HYPHEN-MINUS ->
                    c = 0x2212; // MINUS SIGN
                    break;
                case 0xffe0 : // FULLWIDTH CENT SIGN ->
                    c = 0x00a2; // CENT SIGN
                    break;
                case 0xffe1 : // FULLWIDTH POUND SIGN ->
                    c = 0x00a3; // POUND SIGN
                    break;
                case 0xffe2 : // FULLWIDTH NOT SIGN ->
                    c = 0x00ac; // NOT SIGN
                    break;
                case 0x2015 : // HORIZONTAL BAR ->
                    c = 0x2014; // EM DASH
                    break;
                default :
                    break;
            }
            sb.append(c);
        }
        return new String(sb);
    }

    /**
     * This method convert JIS to Cp932.
     * @param s 変換前文字列
     * @return String
     */
    public static String toCp932(final String s) {
        if (!_isCp932 || s == null) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case 0x005c : // REVERSE SOLIDUS ->
                    c = 0xff3c; // FULLWIDTH REVERSE SOLIDUS
                    break;
                case 0x301c : // WAVE DASH ->
                    c = 0xff5e; // FULLWIDTH TILDE
                    break;
                case 0x2016 : // DOUBLE VERTICAL LINE ->
                    c = 0x2225; // PARALLEL TO
                    break;
                case 0x2212 : // MINUS SIGN ->
                    c = 0xff0d; // FULLWIDTH HYPHEN-MINUS
                    break;
                case 0x00a2 : // CENT SIGN ->
                    c = 0xffe0; // FULLWIDTH CENT SIGN
                    break;
                case 0x00a3 : // POUND SIGN ->
                    c = 0xffe1; // FULLWIDTH POUND SIGN
                    break;
                case 0x00ac : // NOT SIGN ->
                    c = 0xffe2; // FULLWIDTH NOT SIGN
                    break;
                case 0x2014 : // EM DASH ->
                    c = 0x2015; // HORIZONTAL BAR
                    break;
                default :
                    break;
            }
            sb.append(c);
        }
        return new String(sb);
    }

}
