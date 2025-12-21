/*
 * 作成日: 2005/12/06
 */
package jp.co.isid.mos.bird.framework.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * @author xnkusama
 *
 * 日付管理クラス.
 * 当クラスはアプリケーション日付の取得や、日付の計算処理等を行います。
 */
public class DateManager {

    /**
     * 日付計算処理.
     * <P>
     * @return java.lang.String     計算後の「yyyyMMdd」型の文字列
     * @param str java.lang.String  元となる「yyyyMMdd」型の文字列
     * @param num int               計算する日
     * @exception java.lang.Exception 一般例外
     */
    private static String getCalcDate(final String strDate, final int num) throws Exception {
        Date date = Converter.stringToDate(Converter.PATTERN_NORMAL, strDate);
        
        Calendar calendar = Calendar.getInstance(Locale.JAPAN);
        
        calendar.setTime(date);
        
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day + num);
        
        return Converter.dateToString(Converter.PATTERN_NORMAL, calendar.getTime());
    }
    /**
     * 月計算処理.
     * <P>
     * @return java.lang.String     計算後の「yyyyMM」型の文字列
     * @param str java.lang.String  元となる「yyyyMM」型の文字列
     * @param num int               計算する月
     * @exception java.lang.Exception 一般例外
     */
    private static String getCalcMonth(String str, int num) throws Exception {

        java.util.Date date = Converter.stringToDate( Converter.PATTERN_MONTH, str );

        Calendar calendar = Calendar.getInstance(Locale.JAPAN);

        calendar.setTime(date);
        //月の取得
        int month = calendar.get(Calendar.MONTH);
        //元の月に計算する月を足す
        calendar.set(Calendar.MONTH, month + num);
        return Converter.dateToString( Converter.PATTERN_MONTH, calendar.getTime() );
     
    }
    /**
     * 年計算処理.
     * <P>
     * @return java.lang.String     計算後の「yyyy」型の文字列
     * @param str java.lang.String  元となる「yyyy」型の文字列
     * @param num int               計算する年
     * @exception java.lang.Exception 一般例外
     */
    private static String getCalcYear(String str, int num) throws Exception {

        java.util.Date date = Converter.stringToDate( Converter.PATTERN_YEAR, str );

        Calendar calendar = Calendar.getInstance(Locale.JAPAN);

        calendar.setTime(date);
        //年の取得
        int year = calendar.get(Calendar.YEAR);
        //元の年に計算する年を足す
        calendar.set(Calendar.YEAR, year + num);
        return Converter.dateToString( Converter.PATTERN_YEAR, calendar.getTime() );
     
    }

    /**
     * 現在から指定日後の日付を取得.
     * @return java.lang.Strin      計算後の「yyyyMMdd」型の文字列
     * @param str java.lang.String  元となる「yyyyMMdd」型の文字列
     * @param append int            何日足すか？
     */
    public static java.lang.String getNextDate(String str, int append) throws Exception {
        return getCalcDate(str, append);
    }
    /**
     * 現在から指定月後の日付を取得.
     * <P>
     * 作成日 : (00/04/14 午後 04:28:05)
     * @return java.lang.String     計算後の「yyyyMM」型の文字列
     * @param str java.lang.String  基となる「yyyyMM」型の文字列
     * @param append int            何ヶ月足すか？
     */
    public static String getNextMonth(String str, int append) throws Exception {
        return getCalcMonth(str, append);
    }
    /**
     * 現在から指定年後の年を取得.
     * <P>
     * 作成日 : (2000/05/18 16:53:57)
     * @return java.lang.String     計算後の「yyyy」型の文字列
     * @param str java.lang.String  元となる「yyyy」型の文字列
     * @param append int            何年足すか？
     */
    public static String getNextYear(String str, int append) throws Exception {
        return getCalcYear(str, append);
    }
    /**
     * 現在から指定日前の日付を取得.
     * <P>
     * @return java.lang.Strin      計算後の「yyyyMMdd」型の文字列
     * @param str java.lang.String  元となる「yyyyMMdd」型の文字列
     * @param subtract int          何日引くか？
     */
    public static String getPrevDate(String str, int subtract) throws Exception {
        return getCalcDate(str, -subtract);
    }
    /**
     * 現在から指定月前の日付を取得.
     * <P>
     * @return java.lang.String     計算後の「yyyyMM」型の文字列
     * @param str java.lang.String  基となる「yyyyMM」型の文字列
     * @param subtract int          何ヶ月引くか？
     */
    public static String getPrevMonth(String str, int subtract) throws Exception {
        return getCalcMonth(str, -subtract);
    }
    /**
     * 現在から指定年前の年を取得.
     * <P>
     * @return java.lang.Strin      計算後の「yyyy」型の文字列
     * @param str java.lang.String  元となる「yyyy」型の文字列
     * @param subtract int          何年引くか？
     */
    public static String getPrevYear(String str, int subtract) throws Exception {
        return getCalcYear(str, -subtract);
    }

    /**
     * 月末日取得処理<BR>
     * 指定した年月日の月の末日を取得します。
     * @return String 月末日
     * @param year int 
     * @param month int
     * @param day int
     */
    public static String getLastDayOfMonth(int year, int month, int day)
    {
        String yobi = "";

        // 指定した日付についてのカレンダーオブジェクトを作成する。
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        // 指定日付の月の末日を取得する。
        int num = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // ２桁の数字で返す
        String ret;
        if(num < 10)
        {
            ret = "0" + String.valueOf(num);
        }
        else
        {
            ret = String.valueOf(num);
        }
        return ret;
    }
    /**
     * 月末日取得処理<BR>
     * 指定した年月の月末の日を取得します。
     * @return String 月末日
     * @param date String 指定日付(yyyyMMdd形式 または yyyyMM形式の日付)
     */
    public static String getLastDayOfMonth(String date)
    {
        // 日付を年月日に展開する。
        int year  = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day;

        // yyyyMMdd か yyyyMMのどちらか？
        if(date.length() == 8)
        {
            day   = Integer.parseInt(date.substring(6, 8));
        }
        else
        {
            day = 1;
        }

        // 月末日を取得する
        return getLastDayOfMonth(year, month, day);
    }

    /**
     * うるう年判定処理
     * @return boolean true:うるう年 false 平年
     * @param year int 年
     */
    public static boolean isLeapYear(int year)
    {
        boolean ret = false;

        if((year % 400) == 0)
        {
            ret = true;
        }
        else if((year % 100) == 0)
        {
            ret = false;
        }
        else if((year % 4) == 0)
        {
            ret = true;
        }
        else
        {
            ret = false;
        }
        
        return ret;
    }

    /**
     *  年度取得処理
     *  指定した年月の年度を取得する
     * @param date 基となる(YYYYMM)または(YYYYMMDD)型の文字列
     * @return  year 年度(文字列)
     */
    public static String getCurrentYear(String date) {
        DateFormatter formatter = new DateFormatter(2,"yyyy/MM");
        String day = formatter.format( date , false );

    	int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4, 6));
        
        if ( month < 4 ) {
        	year--;
        }
        
        return String.valueOf( year ) ;
    }
    
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
    /**
     * 曜日取得処理<BR>
     * 指定した年月日の曜日値を取得します。
     * @return int 曜日値
     * @param yyyyMMdd String 
     * @param day int
     */
    public static int getWeek(String yyyyMMdd) throws Exception
    {
        java.util.Date date = Converter.stringToDate( Converter.PATTERN_NORMAL, yyyyMMdd);

        Calendar calendar = Calendar.getInstance(Locale.JAPAN);

        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

}