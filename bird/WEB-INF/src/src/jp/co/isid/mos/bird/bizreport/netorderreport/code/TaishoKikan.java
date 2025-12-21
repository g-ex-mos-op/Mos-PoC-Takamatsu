package jp.co.isid.mos.bird.bizreport.netorderreport.code;

import java.util.List;

public class TaishoKikan {
    /** 対象期間：期間指定 */
    public static final String DAYS = "DAYS";

    /** 対象期間：対象年月 */
    public static final String MONTH = "MONTH";

    /** 対象期間 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{MONTH, "対象年月"},
    	{DAYS, "期間指定"}};


    /**
     * 外部からインスタンス化できない
     */
    private TaishoKikan() {
    	super();
    }

    /**
     * コードの名称を取得する<br>
     * @param	code		コード
     * @param	userType	ユーザタイプ
     * @return	String 		コード名称
     */
    public static String getName( String code) {
    		return CodeUtil.getName(code, CODE_TABLE);
    }

    /**
     * 対象期間リストを取得する<br>
     * @param 	userType	ユーザタイプ
     * @return	List 		対象期間リスト
     */
    public static List getPullDownList(String userType) {
    		return CodeUtil.getPullDownList(CODE_TABLE);
    }

    /**
     * 対象期間リストを取得する<br>
	 * 渡されたコード分のリストを作成する。
     * @param 	userType	ユーザタイプ
	 * @param 	arrCode		コード配列
	 * @return	List		対象期間リスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getPullDownList(String[] arrCode) {
    		return CodeUtil.getPullDownList(CODE_TABLE, arrCode);
    }

}
