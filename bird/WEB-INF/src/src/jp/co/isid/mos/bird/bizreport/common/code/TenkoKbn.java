package jp.co.isid.mos.bird.bizreport.common.code;

/**
 * 天候区分コード定数クラス
 * 
 * @author xjung
 */
public class TenkoKbn {

    /**
     * 天候区分：晴
     */
    public static final String CODE_HARE = "1";
    /**
     * 天候区分：曇
     */
    public static final String CODE_KUMORI = "2";
    /**
     * 天候区分：雨
     */
    public static final String CODE_AME = "3";
    /**
     * 天候区分：雪
     */
    public static final String CODE_YUKI = "4";
    /**
     * 天候区分：嵐
     */
    public static final String CODE_ARASHI = "5";

    /** 天候区分配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_HARE, "晴"},
    	{CODE_KUMORI, "曇"},
    	{CODE_AME, "雨"},
    	{CODE_YUKI, "雪"},
    	{CODE_ARASHI, "嵐"}};
 
    /**
     * 外部からインスタンス化できない
     */
    private TenkoKbn() {
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
}
