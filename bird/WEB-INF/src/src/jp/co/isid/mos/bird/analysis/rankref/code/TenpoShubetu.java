package jp.co.isid.mos.bird.analysis.rankref.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * 店舗種別コード定数クラス
 * 
 * @author xjung
 */
public class TenpoShubetu extends CodeUtil {

    /**
     * 店舗種別：全店
     */
    public static final String CODE_ALL = "ALL";
    /**
     * 店舗種別：前年対象店
     */
    public static final String CODE_ZENNEN = "1";

    /** 対象店舗配列 */
    public static final String[][] LIST = new String [][]{
    	{CODE_ALL, "全店"},
    	{CODE_ZENNEN, "前年対象店"}}; 
    /**
     * 外部からインスタンス化できない
     */
    private TenpoShubetu() {
    	super();
    }  

    /**
     * コードの名称を取得する<br>
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return getName(code, LIST);    
    }

    /**
     * 店舗種別リストを取得する<br>
     * @return	List　店舗種別リスト
     */
    public static List getPullDownList() {
    	return getPullDownList(LIST);
    }
    /**
     * 店舗種別リストを取得する<br>
	 * 渡されたコード分のリストを作成する。
	 * @param 	arrCode		コード配列
	 * @return	List		店舗種別リスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getPullDownList(String[] arrCode) {
    	return getPullDownList(LIST, arrCode);
    }
}
