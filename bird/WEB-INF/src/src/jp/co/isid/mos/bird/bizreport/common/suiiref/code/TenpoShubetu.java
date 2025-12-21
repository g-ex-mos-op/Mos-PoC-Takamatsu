package jp.co.isid.mos.bird.bizreport.common.suiiref.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.AbstractCodeUtil;
import jp.co.isid.mos.bird.common.code.UserType;

/**
 * 店舗種別コード定数クラス
 * 
 * @author xjung
 */
public class TenpoShubetu extends AbstractCodeUtil {

    /**
     * 店舗種別：全店
     */
    public static final String CODE_ALL = "ALL";
    /**
     * 店舗種別：前年対象店
     */
    public static final String CODE_ZENNEN = "1";
    /**
     * 店舗種別：予算対象店
     */
    public static final String CODE_YOSAN = "2";
    /**
     * 店舗種別：新店
     */
    public static final String CODE_SIN = "3";

    /** 対象店舗配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_ALL, "全店"},
    	{CODE_ZENNEN, "前年対象店"},
    	{CODE_YOSAN, "予算対象店"},
    	{CODE_SIN, "新店"}};

    /** 対象店舗配列：本部ユーザ用 */
    public static final String[][] CODE_TABLE_HONBU = CODE_TABLE;

    /** 対象店舗配列：オーナー用 */
    public static final String[][] CODE_TABLE_ONER = new String [][]{
        {CODE_ALL, "全店"}};

    /** 対象店舗配列：店舗ユーザ用 */
    public static final String[][] CODE_TABLE_TENPO = new String [][]{
        {CODE_ALL, "全店"}};
 
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
    	return getName(code, CODE_TABLE);    
    }
    /**
     * 店舗種別リスト取得
     * 
     * ユーザタイプに応じた店舗種別リストを取得する<br>
     * @param   userTypeCd　ユーザタイプコード
     * @return  List　  店舗種別リスト
     */
    public static List getUIListPullDownList(String userTypeCd) {
        // ユーザータイプが本部の場合
        if(UserType.isHonbu(userTypeCd)){
            return getUIListPullDownList(CODE_TABLE_HONBU);
        }
        // ユーザータイプがオーナーの場合
        else if (UserType.isOner(userTypeCd)) {
            return getUIListPullDownList(CODE_TABLE_ONER); 
        }
        // ユーザータイプが店舗の場合
        else if (UserType.isTenpo(userTypeCd)){
            return getUIListPullDownList(CODE_TABLE_TENPO); 
        }
        return null;
    }
}
