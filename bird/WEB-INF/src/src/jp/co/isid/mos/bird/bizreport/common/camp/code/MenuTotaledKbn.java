/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * メニュー集計区分定数クラス
 * @author xkinu
 *
 */
public class MenuTotaledKbn extends CodeUtil {
	/** コード：メニュー合計 */
	public static final String CODE_TOTAL = "TOTAL";
	/** コード：単品 */
	public static final String CODE_TANPIN = "TANPIN";
	/** コード：単品(集約) */
	public static final String CODE_SHUYAKU = "SHUYAKU";
	/** コード：メニュー合計 */
	public static final String[][] CODE_TABLE = {
				{CODE_TOTAL, "合計"}
			 ,  {CODE_TANPIN,"単品"}
			 ,  {CODE_SHUYAKU,"単品(集約)"}
		};
	/**
	 * 
	 */
	private MenuTotaledKbn() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
    /**
     * メニュー集計区分コードの名称を取得する
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return getName(code, CODE_TABLE);    
    }

    /**
     * メニュー集計区分リストを取得する
     * @return	List　メニュー集計区分リスト
     */
    public static List getPullDownList() {
    	return getPullDownList(CODE_TABLE);
    }
    /**
     * メニュー集計区分リストを取得する
     * 
     * 渡されたコード分のリストを作成する
     * @return	List　メニュー集計区分リスト
     */
    public static List getPullDownList(String[] codes) {
    	return getPullDownList(CODE_TABLE, codes);
    }

}
