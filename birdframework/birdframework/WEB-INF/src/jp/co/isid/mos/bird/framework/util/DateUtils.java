/*
 * 作成日: 2006/05/24
 * 2007/01/20 storemanage.staffregist.utilよりコピー
 */
package jp.co.isid.mos.bird.framework.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日付操作ユーティリティ
 * 
 * @author xyuchida
 */
public class DateUtils {

    public static final String ERA_MEIJI = "1";
    public static final String ERA_TAISYOU = "2";
    public static final String ERA_SYOUWA = "3";
    public static final String ERA_HEISEI = "4";

    public static final String ERANAME_MEIJI = "明治";
    public static final String ERANAME_TAISYOU = "大正";
    public static final String ERANAME_SYOUWA = "昭和";
    public static final String ERANAME_HEISEI = "平成";

    private static final String STARTDATE_MEIJI = "18680908";
    private static final String STARTDATE_TAISYOU = "19120730";
    private static final String STARTDATE_SYOUWA = "19261225";
    private static final String STARTDATE_HEISEI = "19890108";

    private static final int MAX_ERAYEAR_MEIJI = 45;
    private static final int MAX_ERAYEAR_TAISYOU = 15;
    private static final int MAX_ERAYEAR_SYOUWA = 64;
    private static final int MAX_ERAYEAR_HEISEI = 9999; // ダミー値

    private DateUtils() {
        // インスタンス化禁止
    }

    /**
     * 年取得
     * @param date 日付文字列(YYYYMMDD)
     * @return 年
     */
    public static String getYear(String date) {
        return getDateField(date, Calendar.YEAR);
    }

    /**
     * 月取得
     * @param date 日付文字列(YYYYMMDD)
     * @return 月
     */
    public static String getMonth(String date) {
        String month = getDateField(date, Calendar.MONTH);
        if (month != null) {
            month = String.valueOf(Integer.parseInt(month) + 1);
        }
        return month;
    }

    /**
     * 日取得
     * @param date 日付文字列(YYYYMMDD)
     * @return 日
     */
    public static String getDay(String date) {
        return getDateField(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 
     * @param date 日付文字列(YYYYMMDD)
     * @param field フィールド値
     * @return 指定フィールド
     * @see java.util.Calendar
     */
    private static String getDateField(String date, int field) {
        Calendar calendar = Calendar.getInstance();
        Date parsedDate = parseDate(date);
        String result = null;
        if (parsedDate != null) {
            calendar.setTime(parsedDate);
            result = String.valueOf(calendar.get(field));
        }
        return result;
    }

    /**
     * 日付解析
     * @param date 日付文字列(YYYYMMDD)
     * @return 日付オブジェクト
     */
    private static Date parseDate(String date) {
        DateFormat parser = new SimpleDateFormat("yyyyMMdd");
        // 厳密な解析を行う
        parser.setLenient(false);
        Date result = null;
        try {
            // 日付解析
            result = parser.parse(date);
        } catch (Exception e) {
            // 無処理
        }
        return result;
    }

    /**
     * 元号種別取得
     * @param date 日付文字列(YYYYMMDD)
     * @return 元号種別
     */
    public static String getEra(String date) {

        Date meijiStartDate = parseDate(STARTDATE_MEIJI);
        Date taisyouStartDate = parseDate(STARTDATE_TAISYOU);
        Date syouwaStartDate = parseDate(STARTDATE_SYOUWA);
        Date heiseiStartDate = parseDate(STARTDATE_HEISEI);

        String result = null;
        Date targetDate = parseDate(date);
        if (targetDate != null) {
            if (targetDate.compareTo(heiseiStartDate) >= 0) {
                result = ERA_HEISEI;
            } else if (targetDate.compareTo(syouwaStartDate) >= 0) {
                result = ERA_SYOUWA;
            } else if (targetDate.compareTo(taisyouStartDate) >= 0) {
                result = ERA_TAISYOU;
            } else if (targetDate.compareTo(meijiStartDate) >= 0) {
                result = ERA_MEIJI;
            }
        }

        return result;
    }

    /**
     * 元号名取得
     * @param date 日付文字列(YYYYMMDD)
     * @return 元号名
     */
    public static String getEraName(String date) {
        String era = getEra(date);
        String eraName = null;
        if (era != null) {
            if (era.equals(ERA_HEISEI)) {
                eraName = ERANAME_HEISEI;
            } else if (era.equals(ERA_SYOUWA)) {
                eraName = ERANAME_SYOUWA;
            } else if (era.equals(ERA_TAISYOU)) {
                eraName = ERANAME_TAISYOU;
            } else if (era.equals(ERA_MEIJI)) {
                eraName = ERANAME_MEIJI;
            }
        }
        return eraName;
    }

    /**
     * 和暦年取得
     * @param date 日付文字列(YYYYMMDD)
     * @return 和暦年
     */
    public static String getEraYear(String date) {
        return getEraYear(getEra(date), date);
    }

    /**
     * 和暦年取得
     * @param era 元号種別
     * @param date 日付文字列(YYYYMMDD)
     * @return 和暦年
     */
    public static String getEraYear(String era, String date) {
        String result = null;
        if (era != null) {
            // 元号ごとの開始年を取得
            String baseYear = null;
            if (era.equals(ERA_HEISEI)) {
                baseYear = getYear(STARTDATE_HEISEI);
            } else if (era.equals(ERA_SYOUWA)) {
                baseYear = getYear(STARTDATE_SYOUWA);
            } else if (era.equals(ERA_TAISYOU)) {
                baseYear = getYear(STARTDATE_TAISYOU);
            } else if (era.equals(ERA_MEIJI)) {
                baseYear = getYear(STARTDATE_MEIJI);
            }
            // 対象年 - 元号開始年 + 1
            String year = getYear(date);
            if (year != null && baseYear != null) {
                result = String.valueOf(Integer.parseInt(year) - Integer.parseInt(baseYear) + 1);
            }
        }
        return result;
    }

    public static String getDateString(String era, String eraYear, String month, String day) {
        return getDateString(convYear(era, eraYear), month, day);
    }

    public static String getDateString(String year, String month, String day) {
        Calendar calendar = Calendar.getInstance();
        // 厳密な解析を行う
        calendar.setLenient(false);
        String result = null;
        try {
            calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            result = formatter.format(calendar.getTime());
        } catch (Exception e) {
            // 変換できない場合nullを返却する為、無処理
        }
        return result;
    }

    /**
     * 西暦変換
     * @param era 元号種別
     * @param eraYear 和暦年
     * @return 西暦年
     */
    private static String convYear(String era, String eraYear) {
        String result = null;
        if (isValidEraYear(era, eraYear)) {
            String startYear = null;
            if (era.equals("4")) {
                startYear = getYear(STARTDATE_HEISEI);
            } else if (era.equals("3")) {
                startYear = getYear(STARTDATE_SYOUWA);
            } else if (era.equals("2")) {
                startYear = getYear(STARTDATE_TAISYOU);
            } else if (era.equals("1")) {
                startYear = getYear(STARTDATE_MEIJI);
            }
            try {
                result = String.valueOf(Integer.parseInt(eraYear) + Integer.parseInt(startYear) - 1);
            } catch (Exception e) {
                // 変換できない場合nullを返却する為、無処理
            }
        }
        return result;
    }

    private static boolean isValidEraYear(String era, String eraYear) {
        boolean result = false;
        if (era != null && eraYear != null) {
            int targetYear = 0;
            try {
                targetYear = Integer.parseInt(eraYear);
            } catch (Exception e) {
                // 無処理
            }
            int eraMaxYear = 0;
            if (era.equals("4")) {
                eraMaxYear = MAX_ERAYEAR_HEISEI;
            } else if (era.equals("3")) {
                eraMaxYear = MAX_ERAYEAR_SYOUWA;
            } else if (era.equals("2")) {
                eraMaxYear = MAX_ERAYEAR_TAISYOU;
            } else if (era.equals("1")) {
                eraMaxYear = MAX_ERAYEAR_MEIJI;
            }

            if (targetYear > 0 && targetYear <= eraMaxYear) {
                result = true;
            }
        }
        return result;
    }
}
