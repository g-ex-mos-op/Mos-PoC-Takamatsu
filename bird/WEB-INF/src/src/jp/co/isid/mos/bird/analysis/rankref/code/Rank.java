/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * ランク
 * 
 * 作成日:2008/10/20
 * @author xkinu
 *
 */
public class Rank extends CodeUtil {
    /** ランク：ベスト１００ */
    public static final String BEST_100 = "DESC";
    /** ランク：ワースト１００ */
    public static final String WORST_100 = "ASC";
 
    /** ランク:本部用 */
    public static final String[][] LIST = new String [][]{
    	{BEST_100, "ベスト１００"}
    ,	{WORST_100, "ワースト１００"}};

    /**
     * 外部からインスタンス化できない
     */
    private Rank() {
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
	 * プルダウンリストを取得する 
	 * @return	List		プルダウンリスト
	 */
    public static List getPullDownList() {
		return getPullDownList(LIST);
	}
	/**
	 * プルダウンリストを取得する
	 * 渡されたコード分のリストを作成する
	 * @param 	codeTable	コードリスト
	 * @param 	arrCode		コード配列
	 * @return	List		ﾌﾟﾙﾀﾞｳﾝﾘｽﾄ(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
	 */
    public static List getPullDownList(String[] arrCode) {
		return getPullDownList(LIST, arrCode);
	}

}
