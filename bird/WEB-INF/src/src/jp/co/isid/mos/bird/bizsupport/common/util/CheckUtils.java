/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yushuncheng
 *
 */
public class CheckUtils {

    // 日付Format：yyyy/MM/dd
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy/MM/dd";
	// 日付Format：yyyyMMdd
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * Numberチェック
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 日付チェック
     * @param dateStr
     * @param format
     * @return
     */
    public static boolean isDate(String dateStr, String format) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    	dateFormat.setLenient(false);
    	if (dateStr == null || dateStr.trim().equals(""))
    		return false;
    	dateStr = dateStr.trim();
    	try {
    		Date date = dateFormat.parse(dateStr);
    	}catch(Exception e) {
    		return false;
    	}
    	return true;
    }

	/**
	 * 入力桁数チェック
	 * @param name
	 * @param len
	 * @return
	 */
    public static boolean isLengthOver(String name, int len) {
    	return(name != null && name.trim().getBytes().length<= len) ? true : false;
    }

    /**
     * 係数チェック
     * @param regex
     * @param orginal
     * @return
     */
    private static boolean isMatch(String regex, String orginal){
    	if (orginal == null || orginal.trim().equals("")) {
    		return false;
    	}
    	Pattern pattern = Pattern.compile(regex);
    	Matcher isNum = pattern.matcher(orginal);
    	return isNum.matches();
    }

    /**
     * intチェック
     * @param orginal
     * @return
     */
    public static boolean isPositiveInteger(String orginal) {
    	return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }

}
