package jp.co.isid.mos.bird.bizreport.zendougeturegist.common;
/**
 * 前年同月設定共通処理
 * @author inazawa
 * 2007/03/01
 */
public class ZenDougetuRegistCommon {
    /**
     * Null判断処理を行う
     * @param str チェック対象の文字列
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str != null && str.trim().length() > 0) ? false : true;
    }
    
}
