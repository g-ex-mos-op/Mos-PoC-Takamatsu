/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.code;

import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * 三曜日区分
 * 
 * 作成日:2008/10/01
 * @author xkinu
 *
 */
public class ThreeKbn extends CodeUtil {
    /** 時間帯別区分：平日 */
    public static final String CODE_WEEKDAY= "1";
    /** 時間帯別区分：土曜日 */
    public static final String CODE_SATURDAY= "2";
    /** 時間帯別区分：日祝 */
    public static final String CODE_HOLIDAY= "3";
    /** 対象条件配列：本部用 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_WEEKDAY, "平日"}
        ,{CODE_SATURDAY, "土曜"}
        ,{CODE_HOLIDAY, "日祝"}
        };
    /**
     * 外部からインスタンス化できない
     */
    private ThreeKbn() {
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
