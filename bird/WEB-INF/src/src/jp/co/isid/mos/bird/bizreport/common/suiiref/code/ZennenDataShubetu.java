package jp.co.isid.mos.bird.bizreport.common.suiiref.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.AbstractCodeUtil;
import jp.co.isid.mos.bird.common.code.UserType;

/**
 * 前年データ種別コード定数クラス
 * 
 * @author xjung
 */
public class ZennenDataShubetu extends AbstractCodeUtil{

	/** 前年データ種別：前年同月営業日補正 */
    public static final String CODE_HOSEI = "HOSEI";

    /** 前年データ種別：前年同月 */
    public static final String CODE_DOGETU = "DOGETU";

    /** 前年データ種別：前年同曜 */
    public static final String CODE_DOYO = "DOYO";

    /** 前年データ種別：前年同日 */
    public static final String CODE_DOJITU = "DOJITU";
  

    
    /** 前年データ種別：本部用：前年対象店用 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_HOSEI, "前年同月営業日補正"},
        {CODE_DOGETU, "前年同月"},
        {CODE_DOJITU, "前年同日"},
    	{CODE_DOYO, "前年同曜日"}};
  
    /** 前年データ種別：本部用：その他 */
    public static final String[][] CODE_TABLE_DOGETU = new String [][]{
        {CODE_DOGETU, "前年同月"}};

    /** 前年データ種別：オーナー用 */
    public static final String[][] CODE_TABLE_ONR = new String [][]{
    	{CODE_DOGETU, "前年同月"},
    	{CODE_DOJITU, "前年同日"},
    	{CODE_DOYO, "前年同曜日"}};
 
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
    public static String getName(String code) {
        return getName(code, CODE_TABLE);
    }

    /**
     * 前年データ種別リストを取得する
     * @param   userType        ユーザタイプ
     * @param 	tenpoShubetu	店舗種別
     * @return	List 			前年データ種別リスト
     */
    public static List getPullDownList(String userType, String tenpoShubetu) {
    	// ユーザータイプが本部の場合：前年対象店
    	if (UserType.isHonbu(userType)) {
    		if(TenpoShubetu.CODE_ZENNEN.equals(tenpoShubetu)) {
    			return getPullDownList(CODE_TABLE);
    		}
    		// ユーザータイプが本部の場合：前年対象店以外
    		return getPullDownList(CODE_TABLE_DOGETU);
    	}
    	// ユーザタイプがオーナーの場合
   		return getPullDownList(CODE_TABLE_ONR);
    }
}
