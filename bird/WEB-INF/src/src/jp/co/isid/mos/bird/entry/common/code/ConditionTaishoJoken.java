package jp.co.isid.mos.bird.entry.common.code;

import java.util.List;

import jp.co.isid.mos.bird.entry.common.code.CodeUtil;

/**
 * 条件項目『対象条件』コード定数クラス
 * 
 * @author xkinu
 */
public class ConditionTaishoJoken {

    /** 対象条件値：全オーナー "0" */
    public static final String VALUE_ALLONSER = "0";
    /** 対象条件：支部 "1" */
    public static final String VALUE_SIBU = "1";
    /** 対象条件：SVコード "2" */
    public static final String VALUE_SV = "2";

    /** 対象条件配列：デフォルト */
    public static final String[][] CODE_TABLE = new String [][]{
        {VALUE_ALLONSER, "全オーナー"},
        {VALUE_SIBU, "支部"},
        {VALUE_SV, "ＳＶコード"}};

    /**
     * 外部からインスタンス化できない
     */
    private ConditionTaishoJoken() {
    	super();
    }
    /**
     * 対象期間リストを取得する<br>
     * @param   userType    ユーザタイプ
     * @return  List        対象期間リスト
     */
    public static List getPullDownList() {
        return CodeUtil.getPullDownList(CODE_TABLE); 
    }
    /**
     * コードの名称を取得する<br>
     * @param   code        コード
     * @param   userType    ユーザタイプ
     * @return  String      コード名称
     */
    public static String getName(String code) {
         return CodeUtil.getName(code, CODE_TABLE); 
    }
}
