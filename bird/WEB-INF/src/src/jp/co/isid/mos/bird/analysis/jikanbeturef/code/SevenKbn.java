/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.code;

import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * 七曜日区分
 * 
 * 作成日:2008/10/01
 * @author xkinu
 *
 */
public class SevenKbn extends CodeUtil {
    /** 時間帯別区分：月曜日 */
    public static final String CODE_MONDAY= "1";
    /** 時間帯別区分：火曜日 */
    public static final String CODE_TUESDAY= "2";
    /** 時間帯別区分：水曜日 */
    public static final String CODE_WEDNESDAY= "3";
    /** 時間帯別区分：木曜日 */
    public static final String CODE_THURSDAY= "4";
    /** 時間帯別区分：金曜日 */
    public static final String CODE_FRIDAY= "5";
    /** 時間帯別区分：土曜日 */
    public static final String CODE_SATURDAY= "6";
    /** 時間帯別区分：日曜日 */
    public static final String CODE_SUNDAY= "7";
    /** 対象条件配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_MONDAY, "月",ThreeKbn.CODE_WEEKDAY}
        ,{CODE_TUESDAY, "火",ThreeKbn.CODE_WEEKDAY}
        ,{CODE_WEDNESDAY, "水",ThreeKbn.CODE_WEEKDAY}
        ,{CODE_THURSDAY, "木",ThreeKbn.CODE_WEEKDAY}
        ,{CODE_FRIDAY, "金",ThreeKbn.CODE_WEEKDAY}
        ,{CODE_SATURDAY, "土",ThreeKbn.CODE_SATURDAY}
        ,{CODE_SUNDAY, "日",ThreeKbn.CODE_HOLIDAY}
        };
    /**
     * 外部からインスタンス化できない
     */
    private SevenKbn() {
    	super();
    }
    /**
     * コードの名称を取得する<br>
     * @param	code		コード
     * @return	String 		コード名称
     */
    public static String getName(String code) {
   		return CodeUtil.getName(code, CODE_TABLE); 
    }

}
