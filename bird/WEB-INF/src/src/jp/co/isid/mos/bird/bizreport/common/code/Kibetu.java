package jp.co.isid.mos.bird.bizreport.common.code;

import java.util.List;

/**
 * 期別定数クラス
 * 
 * @author xjung
 */
public class Kibetu {

    /** 期間指定(期)：第一四半期*/
    public static final String ICHI_SIHANKI = "SIHANKI1";
    
    /** 期間指定(期)：第二四半期*/
    public static final String NI_SIHANKI = "SIHANKI2";
    
    /** 期間指定(期)：上期*/
    public static final String KAMIKI = "KAMI";
    
    /** 期間指定(期)：第三四半期*/
    public static final String SAN_SIHANKI = "SIHANKI3";
    
    /** 期間指定(期)：第四四半期*/
    public static final String YON_SIHANKI = "SIHANKI4";
    
    /** 期間指定(期)：下期*/
    public static final String SIMOKI = "SIMO";
    
    /** 期間指定(期)：年度*/
    public static final String NENDO = "NENDO";

    /** 期間指定(期)配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{ICHI_SIHANKI, "第一四半期"},
    	{NI_SIHANKI, "第二四半期"},
    	{KAMIKI, "上期"},
    	{SAN_SIHANKI, "第三四半期"},
    	{YON_SIHANKI, "第四四半期"},
    	{SIMOKI, "下期"},
    	{NENDO, "年度"}};
 
    /**
     * 外部からインスタンス化できない
     */
    private Kibetu() {
    	super();
    }  

    /**
     * コードの名称を取得する
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return CodeUtil.getName(code, CODE_TABLE);    
    }

    /**
     * 期間指定(期)リストを取得する
     * @return	List　集計区分リスト
*/
    public static List getPullDownList() {
    	return CodeUtil.getPullDownList(CODE_TABLE);
    }

    /**
     * 期間指定(期)リストを取得する
	 * 渡されたコード分のリストを作成する。
	 * @param 	arrCode		コード配列
	 * @return	List		期間指定(期)リスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getPullDownList(String[] arrCode) {
    	return CodeUtil.getPullDownList(CODE_TABLE, arrCode);
    }
}