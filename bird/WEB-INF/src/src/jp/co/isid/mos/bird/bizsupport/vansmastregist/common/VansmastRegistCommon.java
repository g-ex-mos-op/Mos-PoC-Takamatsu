/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.common;

import java.math.BigDecimal;

/**
 * 永年勤続マスタ登録 共通クラス
 * 
 * @author narita
 */
public class VansmastRegistCommon {

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
        		VansmastRegistConstants.EMPTY : str.trim();
    }

    /**
     * 渡されたBigDecimalがNullの場合、"0"に変換する
     * @param num BigDecimal
     * @return パラメータがNULLの場合BigDecimal("0")
     */
    public static BigDecimal setBicEmpty(BigDecimal num) {
        return num == null ? new BigDecimal(VansmastRegistConstants.ZERO) : num;
    }

    /**
     * 渡された文字列を渡されたサイズの文字列に変換する(前ゼロ不可)
     * @param str  番号
     * @param size 桁数
     * @return String 前ゼロ不可した文字列
     */
    public static String getConverNum(String str, int size) {
        while (str.length() < size) {
            str = VansmastRegistConstants.ZERO + str;
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
}