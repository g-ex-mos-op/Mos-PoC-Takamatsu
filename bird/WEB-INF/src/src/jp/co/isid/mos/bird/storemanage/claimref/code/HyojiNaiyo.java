/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimref.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;
import jp.co.isid.mos.bird.storemanage.common.code.VoiceTypeCd;

/**
 * 表示内容定数クラス
 * 
 * @author xkinu
 *
 */
public class HyojiNaiyo extends VoiceTypeCd{
    /** コード値：クレーム（未対応） */
    public static final String CODE_CLAIM_YET   = "1YET";
    /** コード値：クレーム（対応済） */
    public static final String CODE_CLAIM_COMP  = "1COMP";
    
    /** 表示内容配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_CLAIM, "クレーム(全て)"},
        {CODE_CLAIM_YET, "クレーム（未対応）"},
        {CODE_CLAIM_COMP, "クレーム（対応済）"},
    	{CODE_OHOME, "お褒め(全国)"}};

    /**
     * 外部からインスタンス化できない
     */
    private HyojiNaiyo() {
    	super();
    }  

    /**
     * 表示内容リストを取得する<br>
     * @return List　   表示内容リスト
     */
    public static List getPullDownList() {
        return getPullDownList(CODE_TABLE);
    }

    /**
     * 表示内容リストを取得する<br>
     * @param  String  ユーザタイプ
     * @param  String  会社コード
     * @return List　  表示内容リスト
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
