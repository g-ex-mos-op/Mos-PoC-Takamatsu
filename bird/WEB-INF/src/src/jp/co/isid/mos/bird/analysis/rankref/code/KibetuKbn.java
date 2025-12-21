package jp.co.isid.mos.bird.analysis.rankref.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * 期別期報区分コード定数クラス
 * 
 * @author xkinu
 */
public class KibetuKbn extends CodeUtil {

    /** 期別期報区分：通期 */
    public static final String KI_TOTAL = "KI_TOTAL";
    /** 期別期報区分：上期 */
    public static final String KI_KAMI = "KI_KAMI";
    /** 期別期報区分：下期 */
    public static final String KI_SIMO = "KI_SIMO";
 
    /** 期別期報区分 */
    public static final String[][] LIST = new String [][]{
    	{KI_KAMI, "上期"}
        ,	{KI_SIMO, "下期"}
        ,	{KI_TOTAL, "通期"}
    };

    /**
     * 外部からインスタンス化できない
     */
    private KibetuKbn() {
    	super();
    }

    /**
     * コードの名称を取得する<br>
     * @param	code		コード
     * @return	String 		コード名称
     */
    public static String getName(String code) {
   		return CodeUtil.getName(code, LIST); 
    }
    /**
     * リストを取得する<br>
     * @return	List　リスト
     */
    public static List getPullDownList() {
    	return getPullDownList(LIST);
    }
    /**
     * リストを取得する<br>
	 * 渡されたコード分のリストを作成する。
	 * @param 	arrCode		コード配列
	 * @return	List		リスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getPullDownList(String[] arrCode) {
    	return getPullDownList(LIST, arrCode);
    }
}
