package jp.co.isid.mos.bird.analysis.menubeturef.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;


/**
 * 対象条件コード定数クラス
 * コードデータのリストの生成、コードデータからの名称取得できる機能を保持しているクラスです。
 * 
 * 作成日:2008/09/18
 * @author xkinu
 *
 */
public class CsvDataKbn extends CodeUtil {

    /** CSVデータ区分：ABC分析表 */
    public static final String CODE_ABC= "ABC";
    /** CSVデータ区分：メニュー別種別売上 */
    public static final String CODE_SHUBETU= "SHUBETU";
    /** CSVデータ区分：メニュー別時間帯別売上表 */
    public static final String CODE_JIKANBETU= "JIKANBETU";
    /** CSVデータ区分：食材準備目安表用 */
    public static final String CODE_MEYASU= "MEYASU";
    
    /** 対象条件配列：本部用 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_ABC, "ABC分析表"},
        {CODE_SHUBETU, "売上種別"},
        {CODE_JIKANBETU, "時間帯別"},
    	{CODE_MEYASU, "食材準備目安表用"}};
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
