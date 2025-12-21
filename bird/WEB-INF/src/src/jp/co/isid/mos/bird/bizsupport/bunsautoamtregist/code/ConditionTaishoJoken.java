package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code;

import java.util.List;


/**
 * 条件項目『対象条件』コード定数クラス
 * 
 * @author xkinu
 */
public class ConditionTaishoJoken {

    /** 対象条件値：全て "0" */
    public static final String VALUE_ALL  = "0";
    /** 対象条件値：オーナー "1" */
    public static final String VALUE_ONER = "1";
    /** 対象条件：店舗 "2" */
    public static final String VALUE_MISE= "2";
    /** 対象条件：SVコード "3" */
    public static final String VALUE_SV = "3";

    /** 対象条件配列：デフォルト */
    public static final String[][] CODE_TABLE = new String [][]{
        {VALUE_ONER, "オーナー"},
        {VALUE_MISE, "店舗"},
        {VALUE_SV, "ＳＶ"}};

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
