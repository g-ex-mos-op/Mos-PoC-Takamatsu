package jp.co.isid.mos.bird.analysis.common.menubetu.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;
import jp.co.isid.mos.bird.common.code.UserType;

/**
 * 前年データ種別コード定数クラス
 * 
 * @author xjung
 */
public class ZennenDataShubetu extends CodeUtil {

	/** 前年データ種別：前年同月営業日補正 */
    public static final String CODE_HOSEI = "DOGETUHOSEI";

    /** 前年データ種別：前年同月 */
    public static final String CODE_DOGETU = "DOGETU";

    /** 前年データ種別：前年同曜 */
    public static final String CODE_DOYO = "DOYO";

    /** 前年データ種別：前年同日 */
    public static final String CODE_DOJITU = "DOJITU";
  
    /** 前年データ種別:前年対象店 */
    public static final String[][] CODE_TABLE_ZEN = new String [][]{
    	{CODE_HOSEI, "前年同月営業日補正"},
    	{CODE_DOGETU, "前年同月"},
    	{CODE_DOYO, "前年同曜日"}};
 
    /** 前年データ種別:前年対象店以外 */
    public static final String[][] CODE_TABLE_OTH = new String [][]{
    	{CODE_DOGETU, "前年同月"}};
  
    /** 前年データ種別:オーナー用 */
    public static final String[][] CODE_TABLE_ONR = new String [][]{
    	{CODE_DOGETU, "前年同月"},
    	{CODE_DOJITU, "前年同日"},
    	{CODE_DOYO, "前年同曜"}};
 
    /**
     * 外部からインスタンス化できない
     */
    private ZennenDataShubetu() {
    	super();
    }

    /**
     * コードの名称を取得する
     * @param   code        コード
     * @param   userType    ユーザタイプ
     * @return  String      コード名称
     */
    public static String getName(String code, String userType) {
        // ユーザタイプがオーナーの場合
        if (UserType.ONER.equals(userType)) {
            return getName(code, CODE_TABLE_ONR);           
        }
        return getName(code, CODE_TABLE_ZEN);  
    }

    /**
     * 前年データ種別リストを取得する
     * @param   userType        ユーザタイプ
     * @param 	tenpoShubetu	店舗種別
     * @return	List 			前年データ種別リスト
     */
    public static List getPullDownList(String userType, String tenpoShubetu) {
    	// ユーザタイプがオーナーの場合
    	if (UserType.ONER.equals(userType)) {
    		return getPullDownList(CODE_TABLE_ONR);    		
    	}
    	// ユーザータイプが本部の場合：前年対象店
    	else if (UserType.HONBU.equals(userType)
    			&& TenpoShubetu.CODE_ZENNEN.equals(tenpoShubetu)) {
    		return getPullDownList(CODE_TABLE_ZEN); 
    		
    	}
    	// ユーザータイプが本部の場合：前年対象店以外
    	else if (UserType.HONBU.equals(userType)){
    		return getPullDownList(CODE_TABLE_OTH); 
    	}
    	return null;    	
    }

}
