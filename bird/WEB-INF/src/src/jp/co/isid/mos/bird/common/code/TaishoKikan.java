package jp.co.isid.mos.bird.common.code;

import java.util.List;

/**
 * 対象期間コード定数クラス
 * 
 * @author xkinu
 */
public class TaishoKikan extends AbstractCodeUtil {

    /** 対象期間：単日 */
    public static final String DAY = "DAY";
    /** 対象期間：期間指定 */
    public static final String DAYS = "DAYS";
    /** 対象期間：月別 */
    public static final String MONTH = "MONTH";
 
    /** コードテーブル:オリジナル */
    public static final String[][] CT_ORIGINAL = new String [][]{
    	{MONTH, "月別"}
    ,	{DAY, "単日"}
    ,	{DAYS, "期間指定"}};

    /** コードテーブル:日単位 */
    public static final String[][] CT_DAY = new String [][]{
    	{DAY, "単日"}
    ,	{DAYS, "期間指定"}};

    /**
     * 外部からインスタンス化できない
     */
    private TaishoKikan() {
    	super();
    }

    /**
     * コードの名称を取得する<br>
     * @param	code		コード
     * @return	String 		コード名称
     */
    public static String getName(String code) {
   		return getName(code, CT_ORIGINAL); 
    }
	/**
	 * プルダウンリストを取得する 
	 * @return	List		プルダウンリスト
	 */
    public static List getPullDownList() {
		return getPullDownList(CT_ORIGINAL);
	}
    /**
     * プルダウンリストを取得する 
	 * @return	List		プルダウンリスト
	 */
    public static List getPullDownList(String[] arrCode) {
		return getPullDownList(CT_ORIGINAL, arrCode);
	}
	/**
	 * プルダウンリストを取得する 
	 * @return	List		プルダウンリスト
	 */
    public static List getUIListPullDownList() {
		return getUIListPullDownList(CT_ORIGINAL);
	}
	/**
	 * プルダウンリストを取得する
	 * 渡されたコード分のリストを作成する
	 * @param 	codeTable	コードリスト
	 * @param 	arrCode		コード配列
	 * @return	List		ﾌﾟﾙﾀﾞｳﾝﾘｽﾄ(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
	 */
    public static List getUIListPullDownList(String[] arrCode) {
		return getUIListPullDownList(CT_ORIGINAL, arrCode);
	}
}
