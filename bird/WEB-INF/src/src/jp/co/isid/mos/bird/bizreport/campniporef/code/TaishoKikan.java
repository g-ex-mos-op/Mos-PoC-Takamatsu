/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.code;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.code.CodeUtil;

/**
 * タブ定数クラス
 * @author xkinu
 *
 */
public class TaishoKikan extends CodeUtil {
	/** コード：対象日*/
	public static final String DAY = "DAY";
	/** コード：期間合計 */
	public static final String TOTAL = "TOTAL";
	/** コード：期間合計 */
	public static final String DAYS = "DAYS";
	/** テーブル：デフォルト */
	public static final String [][] TABLE = {
		{DAY, "単日"}
		 ,  {TOTAL,"期間合計"}
		 ,  {DAYS,"期間指定"}
	};
	
	/**
	 * 
	 */
	private TaishoKikan() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
    /**
     * タブコードの名称を取得する
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return getName(code, TABLE);    
    }

    /**
     * タブリストを取得する
     * @return	List　タブリスト
     */
    public static List getPullDownList() {
		return getPullDownList(TABLE);
    }
    /**
     * タブリストを取得する
     * 
     * 渡されたコード分のリストを作成する
     * @return	List　タブリスト
     */
    public static List getPullDownList(String[] codes) {
    	return getPullDownList(TABLE, codes);
    }
}
