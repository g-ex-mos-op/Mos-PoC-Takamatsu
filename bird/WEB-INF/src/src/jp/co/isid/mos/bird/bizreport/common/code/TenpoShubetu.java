package jp.co.isid.mos.bird.bizreport.common.code;

import java.util.List;

/**
 * 店舗種別コード定数クラス
 * 
 * @author xjung
 */
public class TenpoShubetu {

    /**
     * 店舗種別：全店
     */
    public static final String CODE_ALL = "ALL";
    /**
     * 店舗種別：前年対象店
     */
    public static final String CODE_ZENNEN = "1";
    /**
     * 店舗種別：予算対象店
     */
    public static final String CODE_YOSAN = "2";
    /**
     * 店舗種別：新店
     */
    public static final String CODE_SIN = "3";

    /** 対象店舗配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_ALL, "全店"},
    	{CODE_ZENNEN, "前年対象店"},
    	{CODE_YOSAN, "予算対象店"},
    	{CODE_SIN, "新店"}};

    /** 対象店舗配列：本部ユーザ用 */
    public static final String[][] CODE_TABLE_HONBU = new String [][]{
        {CODE_ALL, "全店"},
        {CODE_ZENNEN, "前年対象店"},
        {CODE_YOSAN, "予算対象店"},
        {CODE_SIN, "新店"}};

    /** 対象店舗配列：オーナー用 */
    public static final String[][] CODE_TABLE_ONER = new String [][]{
        {CODE_ALL, "全店"}};

    /** 対象店舗配列：店舗ユーザ用 */
    public static final String[][] CODE_TABLE_TENPO = new String [][]{
        {CODE_ALL, "全店"}};
 
    /**
     * 外部からインスタンス化できない
     */
    private TenpoShubetu() {
    	super();
    }  

    /**
     * コードの名称を取得する<br>
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return CodeUtil.getName(code, CODE_TABLE);    
    }

    /**
     * 店舗種別リストを取得する<br>
     * @return	List　店舗種別リスト
     */
    public static List getPullDownList() {
    	return CodeUtil.getPullDownList(CODE_TABLE);
    }

    /**
     * ユーザタイプに応じた店舗種別リストを取得する<br>
     * @param   userTypeCd　ユーザタイプコード
     * @return  List　  店舗種別リスト
     */
    public static List getPullDownList(String userTypeCd) {
        // ユーザータイプが本部の場合
        if(UserType.HONBU.equals(userTypeCd)){
            return CodeUtil.getPullDownList(CODE_TABLE_HONBU);
        }
        // ユーザータイプがオーナーの場合
        else if (UserType.ONER.equals(userTypeCd)) {
            return CodeUtil.getPullDownList(CODE_TABLE_ONER); 
        }
        // ユーザータイプが店舗の場合
        else if (UserType.TENPO.equals(userTypeCd)){
            return CodeUtil.getPullDownList(CODE_TABLE_TENPO); 
        }
        return null;
    }

    /**
     * 店舗種別リストを取得する<br>
	 * 渡されたコード分のリストを作成する。
	 * @param 	arrCode		コード配列
	 * @return	List		店舗種別リスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getPullDownList(String[] arrCode) {
    	return CodeUtil.getPullDownList(CODE_TABLE, arrCode);
    }
}
