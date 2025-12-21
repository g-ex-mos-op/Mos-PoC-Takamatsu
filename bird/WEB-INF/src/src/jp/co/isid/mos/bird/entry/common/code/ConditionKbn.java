package jp.co.isid.mos.bird.entry.common.code;

import java.util.List;


/**
 * 条件項目『区分』コード定数クラス
 * 
 * @author xkinu
 */
public class ConditionKbn {

    /** 区分：全て "2" */
    public static final String VALUE_ALL = "2";
    /** 区分：出席 "0" */
    public static final String VALUE_ATTEND = "0";
    /** 区分：欠席 "1" */
    public static final String VALUE_ABSENCE = "1";
    /** 区分：参加 or 申請  "1" */
    public static final String VALUE_ENTRY = "1";
    /** 区分：不参加 "0" */
    public static final String VALUE_NOTENTRY = "0";
    /** 区分：未登録 or 未申請 "3" */
    public static final String VALUE_MITOUROKU = "3";

    /** 対象条件配列：参加 */
    public static final String[][] CODE_TABLE_SANKA = new String [][]{
        {VALUE_ENTRY, "参加"},
        {VALUE_NOTENTRY, "不参加"},
        {VALUE_MITOUROKU, "未登録"}};
    
    /** 対象条件配列：申請 */
    public static final String[][] CODE_TABLE_SINSEI = new String [][]{
        {VALUE_ENTRY, "申請"},
        {VALUE_MITOUROKU, "未申請"}};

    /** 対象条件配列：パターン１ */
    public static final String[][] CODE_TABLE_SHUSSEKI = new String [][]{
        {VALUE_ALL, "全て"},
        {VALUE_ATTEND, "出席"},
        {VALUE_ABSENCE, "欠席"},
        {VALUE_MITOUROKU, "未登録"}};

    /**
     * 外部からインスタンス化できない
     */
    private ConditionKbn() {
    	super();
    }
    /**
     * 参加区分リストを取得する<br>
     * 値　1：参加、0：不参加、3：未登録
     * @return  List  区分リスト
     */
    public static List getPullDownListSanka() {
        return CodeUtil.getPullDownList(CODE_TABLE_SANKA); 
    }
    /**
     * 申請区分リストを取得する<br>
     * 値　0：申請、3：未申請
     * @return  List   区分リスト
     */
    public static List getPullDownListSinsei() {
        return CodeUtil.getPullDownList(CODE_TABLE_SINSEI); 
    }
    /**
     * パターン１区分リストを取得する<br>
     * 
     * 値　2：全て、0：出席、1：欠席
     * 
     * @return  List   区分リスト
     */
    public static List getPullDownListShusseki() {
        return CodeUtil.getPullDownList(CODE_TABLE_SHUSSEKI); 
    }
    /**
     * コードの名称を取得する<br>
     * @param   code        コード
     * @param   userType    ユーザタイプ
     * @return  String      コード名称
     */
    public static String getName(String code, String[][] codeTable) {
         return CodeUtil.getName(code, codeTable); 
    }
}
