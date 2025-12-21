package jp.co.isid.mos.bird.entry.longserviceoffer.common;

import java.math.BigDecimal;

/**
 * 永年勤続マスタ登録 共通クラス
 * 
 * @author narita
 */
public class LongserviceOfferCommon {

    /**
     * Null判断処理を行う
     * @param str チェック対象の文字列
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str != null && str.trim().length() > 0) ? false : true;
    }

    /**
     * 渡された文字列がNullの場合、空白("”)に変換する
     * @param str 文字列
     * @return String パラメータがNULLの場合空白("")
     */
    public static String setEmpty(String str) {
        return (str == null || str.trim().length() == 0) ?
        		LongserviceOfferConstants.EMPTY : str.trim();
    }

    /**
     * 渡されたBigDecimalがNullの場合、"0"に変換する
     * @param num BigDecimal
     * @return パラメータがNULLの場合BigDecimal("0")
     */
    public static BigDecimal setBicEmpty(BigDecimal num) {
        return num == null ? new BigDecimal(LongserviceOfferConstants.ZERO) : num;
    }

    /**
     * 渡された文字列を渡されたサイズの文字列に変換する(前ゼロ不可)
     * @param str  番号
     * @param size 桁数
     * @return String 前ゼロ不可した文字列
     */
    public static String getConverNum(String str, int size) {
        while (str.length() < size) {
            str = LongserviceOfferConstants.ZERO + str;
        }
        return str;
    }
    
    /**
     * レングスチェック
     * @return boolean true:チェックNG
     */
    public static boolean isLengthOver(String val, int length) {
        if (val == null) {
            return false;
        }
        return val.trim().getBytes().length > length ? true : false;
    }
    
    /**
     * 誕生日から年齢を取得
     * @return String 年齢
     */
    public static String getAge(String sysDate, String birthDay){
    	int age = 0;
    	if(!LongserviceOfferCommon.isNull(birthDay)) {
        	
	    	int sysYY = Integer.parseInt(sysDate.substring(0,4));
	    	int sysMM = Integer.parseInt(sysDate.substring(4,6));
	    	int sysDD = Integer.parseInt(sysDate.substring(6,8));
	    	
	    	int birthYY = Integer.parseInt(birthDay.substring(0,4));
	    	int birthMM = Integer.parseInt(birthDay.substring(4,6));
	    	int birthDD = Integer.parseInt(birthDay.substring(6,8));
	    	
	    	age = sysYY - birthYY;
	    	if(sysMM < birthMM) {
	    		age -= 1;
	    	} else if(sysMM == birthMM) {
	    		if(sysDD < birthDD) {
	    			age -= 1;
	    		}
	    	}
    	}
    	return String.valueOf(age);
    }
}