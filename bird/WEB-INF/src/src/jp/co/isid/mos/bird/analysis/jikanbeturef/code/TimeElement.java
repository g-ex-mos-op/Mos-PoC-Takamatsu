/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.code;

import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * 時間帯別区分
 * 
 * 作成日:2008/10/01
 * @author xkinu
 *
 */
public class TimeElement extends CodeUtil {
    /** 時間帯別区分：深夜 */
    public static final String CODE_LATENIGHT= "01";
    /** 時間帯別区分：午前 */
    public static final String CODE_MORNING= "02";
    /** 時間帯別区分：ランチ */
    public static final String CODE_LUNCH= "03";
    /** 時間帯別区分：午後 */
    public static final String CODE_AFTERNOON= "04";
    /** 時間帯別区分：ディナー */
    public static final String CODE_DINNER= "05";
    /** 時間帯別区分：夜 */
    public static final String CODE_NIGHT= "06";
    /** 時間帯別区分：その他 */
    public static final String CODE_ETC= "99";
    /** 対象条件配列：本部用 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_LATENIGHT, "深夜","00", "5"}
        ,{CODE_MORNING, "午前","05","6"}
        ,{CODE_LUNCH, "ランチ","11", "3"}
    	,{CODE_AFTERNOON, "午後","14", "4"}
    	,{CODE_DINNER, "ディナー","18", "2"}
    	,{CODE_NIGHT, "夜","20", "4"}
    	,{CODE_ETC, "その他","", "1"}
        };
    /**
     * 外部からインスタンス化できない
     */
    private TimeElement() {
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
