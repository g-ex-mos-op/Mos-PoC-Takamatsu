/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.code;

import jp.co.isid.mos.bird.bizreport.common.code.CodeUtil;


/**
 * 合計行種類スタイルシート定数クラス
 * 
 * @author xkinu
 *
 */
public class RowTypeCssClass extends CodeUtil {
	public static final String CD_TOTAL = RowType.CD_TOTAL;
	public static final String CD_SEGMENT = RowType.CD_SEGMENT;
	public static final String CD_HONBU = RowType.CD_HONBU;
	public static final String CD_JOGYOU = RowType.CD_JIGYOU;
	public static final String CD_SLAREA = RowType.CD_SLAREA;
	public static final String CD_SIBU = RowType.CD_SIBU;
	public static final String CD_BLOCK = RowType.CD_BLOCK;
	public static final String CD_MENU = RowType.CD_MENU;
	
    /** 合計行種類配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CD_TOTAL, "body_sum5"},
        {CD_SEGMENT, "body_sum4"},
        {CD_HONBU, "body_sum3"},
        {CD_JOGYOU, "body_sum2"},
    	{CD_SLAREA, "body_sum1"},
        {CD_SIBU, "body_sum5"},
        {CD_BLOCK, "body_sum1"},
    	{CD_MENU, "body_sum3"}};
	/**
	 * 
	 */
	private RowTypeCssClass() {
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
