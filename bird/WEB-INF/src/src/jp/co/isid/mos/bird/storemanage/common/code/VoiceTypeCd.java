/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.common.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * お客様の声タイプコード定数クラス
 * 
 * @author xkinu
 *
 */
public class VoiceTypeCd extends CodeUtil{
    /** コード値：クレーム(全て) */
    public static final String CODE_CLAIM   = "1";
    /** コード値：お褒め*/
    public static final String CODE_OHOME       = "4";
    
    /** お客様の声タイプコード配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_CLAIM, "クレーム"},
    	{CODE_OHOME, "お褒め"}};

    /**
     * お客様の声タイプコードリストを取得する<br>
     * @return List　   お客様の声タイプコードリスト
     */
    public static List getPullDownList() {
        return getPullDownList(CODE_TABLE);
    }

    /**
     * お客様の声タイプコードリストを取得する<br>
     * @param  String  ユーザタイプ
     * @param  String  会社コード
     * @return List　  お客様の声タイプコードリスト
     */
    public static List getPullDownList(String[] codes) {
 
    	return getPullDownList(CODE_TABLE, codes);
    }
	/**
	 * コードリストよりコードの名称を取得する
	 * @param code			コード
	 * @param codeTable	コードリスト
	 * @return String      コード名称
	 */
    public static String getName(String code) {
		return CodeUtil.getName(code, CODE_TABLE);
	}
}
