package jp.co.isid.mos.bird.bizreport.common.code;

import java.util.List;

/**
 * 対象店舗コード定数クラス
 * 
 * @author xjung
 */
public class TaishoTenpo {

	/** 対象店舗：全店 */
    public static final String ALL = "ALL";
 
    /** 対象店舗：FCのみ */
    public static final String FC = "FC";

    /** 対象店舗：RCのみ */
    public static final String RC = "RC";

    /**
     * 外部からインスタンス化できない
     */
    private TaishoTenpo() {
    	super();
    }

    /** 対象店舗配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{ALL, "全店"} ,
    	{FC, "FCのみ"},
    	{RC, "RCのみ"}};

    /**
     * コードの名称を取得する<br>
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return CodeUtil.getName(code, CODE_TABLE);    
    }

    /**
     * 対象店舗リストを取得する
     * <br>
     * @return	List　対象店舗リスト
     */
    public static List getPullDownList() {
    	return CodeUtil.getPullDownList(CODE_TABLE);    
    }
}
