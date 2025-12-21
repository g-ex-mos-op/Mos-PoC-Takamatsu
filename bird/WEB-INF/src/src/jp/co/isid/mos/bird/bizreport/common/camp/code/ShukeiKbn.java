package jp.co.isid.mos.bird.bizreport.common.camp.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.AbstShukeiKbn;

/**
 * 集計区分コード定数クラス
 * 
 * @author xjung
 */
public class ShukeiKbn extends AbstShukeiKbn {

	/** 集計区分：直営・○○を含まない */
    public static final String OUT_RC = TaishoJoken.CODE_SIBU;

    /** 集計区分：直営・○○を含む */
    public static final String IN_RC = TaishoJoken.CODE_AREADAI;

    /** 対象店舗配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{OUT_RC, LABEL_OUT_RC},
        {IN_RC, LABEL_IN_RC}};
 
    /**
     * 外部からインスタンス化できない
     */
    private ShukeiKbn() {
    	super();
    }  

    /**
     * コードの名称を取得する
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return getName(code, CODE_TABLE);    
    }

    /**
     * 集計区分リストを取得する
     * @return	List　集計区分リスト
     */
    public static List getPullDownList() {
    	return getPullDownList(CODE_TABLE);
    }

    /**
     * 集計区分リストを取得する
	 * 渡されたコード分のリストを作成する
	 * @param 	arrCode		コード配列
	 * @return	List		集計区分リスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getPullDownList(String[] arrCode) {
    	return getPullDownList(CODE_TABLE, arrCode);
    }
}
