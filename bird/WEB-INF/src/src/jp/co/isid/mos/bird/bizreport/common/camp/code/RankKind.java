/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.code;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.code.CodeUtil;

/**
 * 順位定数クラス
 * @author xkinu
 *
 */
public class RankKind extends CodeUtil {
	public static final String CODE_KINKOUSEIHI = "KINGAKU_KOSEIHI";
	/** コード：個数 */
	public static final String CODE_KOSU = "KOSU";
	/** テーブル：デフォルト */
	public static final String [][] CODE_TABLE = {
		{CODE_KINKOUSEIHI, "金額構成比"}
	 ,  {CODE_KOSU,		   "個数"}
	};
	
	/**
	 * 
	 */
	private RankKind() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
    /**
     * 順位コードの名称を取得する
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return getName(code, CODE_TABLE);    
    }

    /**
     * 順位リストを取得する
     * @return	List　順位リスト
     */
    public static List getPullDownList() {
    	return getPullDownList(CODE_TABLE);
    }
    /**
     * 順位リストを取得する
     * 
     * 渡されたコード分のリストを作成する
     * @return	List　順位リスト
     */
    public static List getPullDownList(String[] codes) {
    	return getPullDownList(CODE_TABLE, codes);
    }
}
