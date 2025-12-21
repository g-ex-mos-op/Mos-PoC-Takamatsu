package jp.co.isid.mos.bird.bizreport.common.code;

import java.util.List;

/**
 * 対象期間コード定数クラス
 * 
 * @author xjung
 */
public class TaishoKikan {

    /** 対象期間：期日指定日報 */
    public static final String DAY1 = "DAY1";

    /** 対象期間：期間指定 */
    public static final String DAYS = "DAYS";

    /** 対象期間：月別月報告 */
    public static final String MONTH = "MONTH";

    /** 対象期間：当月月報 */
    public static final String MONTHAPP = "APPMONTH";

    /** 対象期間：期別期報 */
    public static final String KIBETU = "KIBETU";
 
    /** 対象期間:本部用 */
    public static final String[][] CODE_TABLE_HONBU = new String [][]{
    	{DAY1, "期日指定日報"},
    	{MONTHAPP, "当月月報"},
    	{MONTH, "月別月報告"},
    	{KIBETU, "期別期報"},
    	{DAYS, "期間指定"}};

    /** 対象期間:オーナー用 */
    public static final String[][] CODE_TABLE_ONER = new String [][]{
    	{DAY1, "期日指定"},
    	{MONTHAPP, "当月"},
    	{MONTH, "月別"},
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
    public static String getName(String userType, String code) {
    	if (UserType.HONBU.equals(userType)) {
    		return CodeUtil.getName(code, CODE_TABLE_HONBU); 
    	} else if(UserType.ONER.equals(userType)) {
    		return CodeUtil.getName(code, CODE_TABLE_ONER);  
    	}
    	return null;
    }
 
    /**
     * 対象期間リストを取得する<br>
     * @param 	userType	ユーザタイプ
     * @return	List 		対象期間リスト
     */
    public static List getPullDownList(String userType) {
    	if (UserType.HONBU.equals(userType)) {
    		return CodeUtil.getPullDownList(CODE_TABLE_HONBU); 
    	} else if(UserType.ONER.equals(userType)) {
    		return CodeUtil.getPullDownList(CODE_TABLE_ONER);  
    	}
    	return null;   
    }

    /**
     * 対象期間リストを取得する<br>
	 * 渡されたコード分のリストを作成する。
     * @param 	userType	ユーザタイプ
	 * @param 	arrCode		コード配列
	 * @return	List		対象期間リスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getPullDownList(String userType, String[] arrCode) {
    	if (UserType.HONBU.equals(userType)) {
    		return CodeUtil.getPullDownList(CODE_TABLE_HONBU, arrCode);
    	} else if(UserType.ONER.equals(userType)) {
    		return CodeUtil.getPullDownList(CODE_TABLE_ONER, arrCode);
    	}
    	return null; 
    }

}
