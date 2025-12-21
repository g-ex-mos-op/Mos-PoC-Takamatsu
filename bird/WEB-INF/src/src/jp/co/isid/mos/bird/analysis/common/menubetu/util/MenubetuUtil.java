/**
 * 
 */
package jp.co.isid.mos.bird.analysis.common.menubetu.util;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * データ分析用定数クラス
 * 
 * 作成日:2008/09/09
 * @author xkinu
 *
 */
public class MenubetuUtil {
	public static final String SCREEN_ID = "BDT000";
    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();
	/**
	 * yyyyMMをyyyy月MM月変換処理します。
	 * @param ym
	 * @return
	 */
	public static String formattYMKj(String ym) {
		return formatter.format(ym, DateFormatter.PATTERN_MONTH_KANJI_YM
				, DateFormatter.DATE_TYPE_YM);
	}
	/**
	 * yyyyMMをyyyy月MM月変換処理します。
	 * @param ym
	 * @return
	 */
	public static String formattYMSlash(String ym) {
		return formatter.format(ym, DateFormatter.PATTERN_MONTH_SLASH
				, DateFormatter.DATE_TYPE_YM);
	}
	/**
	 * yyyyMMddをyyyy/MM/dd変換処理します。
	 * @param ymd
	 * @return
	 */
	public static String formattYMDSlash(String ymd) {
		return formatter.format(ymd, DateFormatter.PATTERN_SLASH
				, DateFormatter.DATE_TYPE_YMD);
	}
	/**
	 * null値の場合ブランクを返す。
	 * @param value
	 * @return
	 */
	public static String changeBlank(String value) {
		if(value == null) {
			return "";
		}
		return value;
	}
	/**
	 * 行Cssクラス文字列名称取得処理
	 * 
	 * @param rowType
	 * @return
	 */
	public static String getTrCssClass(String rowType) {
    	if(RowType.CD_TOTAL_ALL.equals(rowType)) {
    		return "body_sum5";
    	}
    	if(RowType.CD_TOTAL.equals(rowType)) {
    		return "body_sum5";
    	}
    	else if(RowType.CD_MENU_BUNRUI.equals(rowType)) {
    		return "body_sum4";
    	}
   		return "default_bkgColor";
		
	}
}
