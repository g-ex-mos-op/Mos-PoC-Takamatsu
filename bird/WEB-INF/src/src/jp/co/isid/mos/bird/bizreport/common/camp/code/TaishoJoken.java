/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.code;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.code.CodeUtil;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;

/**
 * 対象条件定数クラス
 * 
 * @author xkinu
 *
 */
public class TaishoJoken extends CodeUtil {

	/**
	 * 
	 */
	private TaishoJoken() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
    /** コード値：全社(全店) */
    public static final String CODE_ALL           = "ALL";
    /** コード値：セグメント */
    public static final String CODE_SEGMENT       = "SEGMENT";
    /** コード値：事業本部 */
    public static final String CODE_JIGYOU        = "JIGYOU";
    /** コード値：営業エリア */
    public static final String CODE_SLAREA        = "SLAREA";
    /** コード値：支部 */
    public static final String CODE_SIBU          = "SIBU";
    /** コード値：支部取り込み(エリア大) */
    public static final String CODE_AREADAI       = "AREADAI";
    /** コード値：店舗(個店) */
    public static final String CODE_MISE          = "MISE";

    /** 対象条件配列：本部用：(モス・SVなし) */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_ALL, "全社"},
        {CODE_SEGMENT, "セグメント"},
        {CODE_JIGYOU, "事業本部"},
    	{CODE_SLAREA, "営業エリア"},
        {CODE_SIBU, "支部"},
        {CODE_AREADAI, "エリア大"},
        {CODE_MISE, "個店"}};
    
    /** 対象条件配列：本部用：(モス・支部制限なし) */
    public static final String[][] CODE_TABLE_HONBU = new String [][]{
    	{CODE_ALL, "全社"},
        {CODE_JIGYOU, "事業本部"},
    	{CODE_SLAREA, "営業エリア"},
        {CODE_SIBU, "支部"},
        {CODE_MISE, "個店"}};
    /** 対象条件配列：本部用：(モス・支部制限あり) */
    public static final String[][] CODE_TABLE_HONBU_SV = new String [][]{
    	{CODE_SLAREA, "営業エリア"},
        {CODE_SIBU, "支部"},
        {CODE_MISE, "個店"}};

    /** 対象条件配列：オーナー用 */
    public static final String[][] CODE_TABLE_ONER = new String [][]{
        {CODE_ALL, "全店"},
        {CODE_MISE, "店舗"}};

    /** 対象条件配列：店舗用 */
    public static final String[][] CODE_TABLE_MISE = new String [][]{
        {CODE_MISE, "店舗"}};

    /**
     * 対象条件リストを取得する<br>
     * @param  String   ユーザタイプ
     * @param  String   会社コード
     * @param  limitKbn 制限区分(本部ユーザー時のみ使用)
     * @return List　   対象条件リスト
     */
    public static List getPullDownList(String userTypeCd, String companyCd, boolean limitKbn) {

        //入力チェック
        if(userTypeCd == null || userTypeCd.length() == 0) {
            return null;
        }
        if(companyCd == null || companyCd.length() == 0) {
            return null;
        }
        
        //本部ユーザの時
        if (UserType.HONBU.equals(userTypeCd)) {

            //モス
            if (CommonUtil.COMPANY_CD_MOS.equals(companyCd)){
	            if(!limitKbn) {
	            	//支部制限なし
	                return CodeUtil.getPullDownList(CODE_TABLE_HONBU); 
	            }
	            else{
	            	//支部制限あり
	                return CodeUtil.getPullDownList(CODE_TABLE_HONBU_SV); 
	            }
            }
            //モス以外
            else{
            	//SVなしの時
            	if (!limitKbn) {
	            	String codes[] = new String []{CODE_ALL,CODE_SIBU, CODE_MISE};
	                return CodeUtil.getPullDownList(CODE_TABLE_HONBU, codes); 
	            }
	            //SVの時
	            else {
	            	String codes[] = new String []{CODE_SIBU, CODE_MISE};
	                return CodeUtil.getPullDownList(CODE_TABLE_HONBU_SV, codes); 
	            }
            }
        }
        //オーナーの時
        else if(UserType.ONER.equals(userTypeCd)) {
            return CodeUtil.getPullDownList(CODE_TABLE_ONER);  
        } 
        
        //店舗ユーザの時
        else if(UserType.TENPO.equals(userTypeCd)) {
            return CodeUtil.getPullDownList(CODE_TABLE_MISE);  
        }
        return null;   
    }

    /**
     * 対象条件リストを取得する<br>
     * @param  String  ユーザタイプ
     * @param  String  会社コード
     * @return List　  対象条件リスト
     */
    public static List getPullDownList(String userTypeCd, String companyCd, String[] codes) {
 
        //入力チェック
        if(userTypeCd == null || userTypeCd.length() == 0) {
            return null;
        }
        if(codes == null || codes.length == 0) {
            return null;
        }

        //本部ユーザの時
        if (UserType.HONBU.equals(userTypeCd)) {
            
            if (companyCd != null && CommonUtil.COMPANY_CD_MOS.equals(companyCd)) {
                return CodeUtil.getPullDownList(CODE_TABLE_HONBU, codes); 
            } else {
                return CodeUtil.getPullDownList(CODE_TABLE_HONBU_SV, codes); 
            }
        }
        //オーナーの時
        else if(UserType.ONER.equals(userTypeCd)) {
            return CodeUtil.getPullDownList(CODE_TABLE_ONER, codes);  
        } 
        //店舗ユーザの時
        else if(UserType.TENPO.equals(userTypeCd)) {
            return CodeUtil.getPullDownList(CODE_TABLE_MISE, codes);  
        }

        return null;   
    }

    /**
     * コードの名称を取得する<br>
     * @param	String	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return CodeUtil.getName(code, CODE_TABLE);
    }
}
