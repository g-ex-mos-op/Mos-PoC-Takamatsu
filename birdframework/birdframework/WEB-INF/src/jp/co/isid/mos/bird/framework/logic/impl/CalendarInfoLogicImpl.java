/*
 * 作成日: 2005/12/12
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import jp.co.isid.mos.bird.framework.logic.CalendarInfoLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * カレンダー情報ロジック
 * @author xnkusama
 */
public class CalendarInfoLogicImpl implements CalendarInfoLogic {

    /**
     * 前年同日取得処理.
     * <P>
     * 指定の「yyyyMMdd」型の日付の前年同日を求める。<BR>
     * 例） 20011018 → 20001018
     * @return java.lang.String 「yyyyMMdd」型の文字列
     * @param str java.lang.String 「yyyyMMdd」型の文字列
     */
    public String getZennenDoujitu(String str) throws Exception {
        String ret = "";
        
        if( str == null || str.length() != 8 ){
            return ret;
        }

        ret = DateManager.getPrevYear(str.substring(0,4),1) + str.substring(4);

        return ret;
    }

    /**
     * 前年同曜日取得処理.
     * <P>
     * 指定の「yyyyMMdd」型の日付の前年同曜日を求める。<BR>
     * 『指定日－364日』で算出
     * 例） 20011018(木) → 20001019(木)
     * @return java.lang.String 「yyyyMMdd」型の文字列
     * @param str java.lang.String 「yyyyMMdd」型の文字列
     */
    public String getZennenDouyou(String str) throws Exception {
        String ret = "";
        
        if( str == null || str.length() != 8 ){
            return ret;
        }

        ret = DateManager.getPrevDate(str, 364);

        return ret;
    }

}
