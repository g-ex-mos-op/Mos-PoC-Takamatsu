/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.code;

import jp.co.isid.mos.bird.bizreport.common.code.CodeUtil;


/**
 * 合計行種類定数クラス
 * 
 * @author xkinu
 *
 */
public class RowType extends CodeUtil {
	public static final String CD_TOTAL = "8_TOTAL";
	public static final String CD_MENU = "7_MENU_TOTAL";
	public static final String CD_SEGMENT = "6_SEGMENT_TOTAL";
	public static final String CD_HONBU = "5_HONBU_TOTAL";
	public static final String CD_JIGYOU = "4_JIGYOU_TOTAL";
	public static final String CD_SLAREA = "3_SLAREA_TOTAL";
	public static final String CD_SIBU = "2_SIBU_TOTAL";
	public static final String CD_BLOCK = "1_BLOCK_TOTAL";
	
    /** 合計行種類配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CD_TOTAL, "総合計"},
        {CD_SEGMENT, "計"},
        {CD_HONBU, "計"},
        {CD_JIGYOU, "計"},
    	{CD_SLAREA, "計"},
        {CD_BLOCK, "計"},
        {CD_SIBU, "計"},
        {CD_MENU, "計"}};
	/**
	 * 
	 */
	private RowType() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
    /**
     * コードの名称を取得する<br>
     * @param	String	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return CodeUtil.getName(code, CODE_TABLE);
    }

}
