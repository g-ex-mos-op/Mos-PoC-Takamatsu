package jp.co.isid.mos.bird.entry.manageregist.common;

/**
 * 全国店長勉強会マスタ登録 共通クラス
 * 
 * @author xjung
 */
public class ManageRegistCommon {

    /**
     * Null判断処理を行う
     * @param str チェック対象の文字列
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str != null && str.trim().length() > 0) ? false : true;
    }
}