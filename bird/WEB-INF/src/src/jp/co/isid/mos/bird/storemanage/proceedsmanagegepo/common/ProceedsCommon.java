package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common;

import java.math.BigDecimal;

/**
 * 売上金管理月報 共通クラス
 * 
 * @author xjung
 */
public class ProceedsCommon {

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
        	ProceedsConstants.EMPTY : str.trim();
    }

    /**
     * 渡されたBigDecimalがNullの場合、"0"に変換する
     * @param num BigDecimal
     * @return パラメータがNULLの場合BigDecimal("0")
     */
    public static BigDecimal setBicEmpty(BigDecimal num) {
        return num == null ? new BigDecimal(ProceedsConstants.ZERO) : num;
    }

    /**
     * データ部・項目名称番号を文字列("00")に変換する
     * @param num  番号
     * @param size 桁数
     * @return String 項目名称番号
     */
    public static String getStringNum(int num, int size) {
        String strNum = String.valueOf(num);
        while (strNum.length() < size) {
            strNum = ProceedsConstants.ZERO + strNum;
        }
        return strNum;
    }
}