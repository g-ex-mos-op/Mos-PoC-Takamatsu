/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.code;

import java.util.List;

import jp.co.isid.mos.bird.analysis.rankref.entity.UIRank;
import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * ランク対象
 * 
 * 作成日:2008/10/20
 * @author xkinu
 *
 */
public class RankTarget extends CodeUtil {
    /** 対象期間：売上 */
    public static final String URIAGE = UIRank.uriage_COLUMN;
    /** 対象期間：売上 */
    public static final String URIAGE_ZHI = UIRank.uriageZennenHi_COLUMN;
    /** 対象期間：客数 */
    public static final String KYAKUSU = UIRank.kyakusu_COLUMN;
    /** 対象期間：客数 */
    public static final String KYAKUSU_ZHI = UIRank.kyakusuZennenHi_COLUMN;
 
    /** 対象期間:本部用 */
    public static final String[][] LIST = new String [][]{
    	{URIAGE, "売上"}
        ,	{KYAKUSU, "客数"}
        ,	{URIAGE_ZHI, "前年比(売上)"}
        ,	{KYAKUSU_ZHI, "前年比(客数)"}
    };

    /**
     * 外部からインスタンス化できない
     */
    private RankTarget() {
    	super();
    }

    /**
     * コードの名称を取得する<br>
     * @param	code		コード
     * @return	String 		コード名称
     */
    public static String getName(String code) {
   		return getName(code, LIST); 
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
