package jp.co.isid.mos.bird.analysis.jikanbeturef.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;


/**
 * 対象条件コード定数クラス
 * コードデータのリストの生成、コードデータからの名称取得できる機能を保持しているクラスです。
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public class CsvDataKbn extends CodeUtil {

    /** CSVデータ区分：曜日別 */
    public static final String CODE_WEEK= "WEEK";
    /** CSVデータ区分：時間帯別日報推移表 */
    public static final String CODE_SUII= "SUII";
    /** CSVデータ区分：メニュー別時間帯別売上表 */
    public static final String CODE_JIKANBETU= "JIKANBETU";
    /** CSVデータ区分：時間帯別曜日別売上種別 */
    public static final String CODE_SHUBETU= "SHUBETU";
    
    /** 対象条件配列：本部用 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_WEEK, "曜日別"},
        {CODE_SUII, "日別推移"},
        {CODE_JIKANBETU, "メニュー別"},
    	{CODE_SHUBETU, "売上種別"}};
    /**
     * 外部からインスタンス化できない
     */
    private CsvDataKbn() {
    	super();
    }
    /**
     * コードの名称を取得する<br>
     * @param	code		コード
     * @return	String 		コード名称
     */
    public static String getName(String code) {
   		return CodeUtil.getName(code, CODE_TABLE); 
    }
	/**
	 * プルダウンリストを取得する 
	 * @param 	codeTable	コードリスト
	 * @return	List		プルダウンリスト
	 */
    public static List getPullDownList() {
		return getPullDownList(CODE_TABLE);
	}
}
