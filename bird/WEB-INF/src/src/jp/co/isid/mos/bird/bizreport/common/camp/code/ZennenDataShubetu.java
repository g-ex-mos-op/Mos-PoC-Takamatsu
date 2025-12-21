/**
 * 更新：2008/08/21 店舗種別に関わらず「前年同曜日」のみに変更
 */
package jp.co.isid.mos.bird.bizreport.common.camp.code;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.common.code.CodeUtil;

/**
 * キャンペーン用前年データ種別定数クラス
 * 
 * @author xkinu
 *
 */
public class ZennenDataShubetu extends CodeUtil {
	/** 前年データ種別：前年同月営業日補正 */
    public static final String CODE_HOSEI  = "DOGETUHOSEI";

    /** 前年データ種別：前年同月 */
    public static final String CODE_DOGETU = "DOGETU";

    /** 前年データ種別：前年同曜 */
    public static final String CODE_DOYO   = "DOYO";

    /** 前年データ種別：前年同日 */
    public static final String CODE_DOJITU = "DOJITU";
   
//---2008/08/21 update start 前年データ種別を「前年同曜日」のみに変更    
//    /** 前年データ種別：本部用：前年対象店用 */
//    public static final String[][] CODE_TABLE = new String [][]{
//    	{CODE_HOSEI, "前年同月営業日補正"},
//        {CODE_DOGETU, "前年同月"},
//        {CODE_DOJITU, "前年同日"},
//    	{CODE_DOYO, "前年同曜日"}};
//    /** 前年データ種別:本部 */
//    public static final String[][] CODE_TABLE_HONBU = new String [][]{
//    	{CODE_HOSEI, "前年同月営業日補正"},
//    	{CODE_DOGETU, "前年同月"},
//    	{CODE_DOYO, "前年同曜日"}};
//    /** 前年データ種別:オーナー用 */
//    public static final String[][] CODE_TABLE_ONER = new String [][]{
//    	{CODE_DOGETU, "前年同月"},
//    	{CODE_DOJITU, "前年同日"},
//    	{CODE_DOYO, "前年同曜日"}};
//    /** 前年データ種別:店舗用 */
//    public static final String[][] CODE_TABLE_TENPO = CODE_TABLE_ONER;
  
    /** 前年データ種別：本部用：前年対象店用 */
    private static final String[][] CODE_TABLE = new String [][]{
        {CODE_DOYO, "前年同曜日"}};
    /** 前年データ種別:本部 */
    private static final String[][] CODE_TABLE_HONBU = new String [][]{
        {CODE_DOYO, "前年同曜日"}};
    /** 前年データ種別:オーナー用 */
    private static final String[][] CODE_TABLE_ONER = new String [][]{
        {CODE_DOYO, "前年同曜日"}};
    /** 前年データ種別:店舗用 */
    private static final String[][] CODE_TABLE_TENPO = CODE_TABLE_ONER;

    /** 前年データ種別：前年同曜日のみ */
    private static final String[][] CODE_TABLE_DOYO = new String [][]{
        {CODE_DOYO, "前年同曜日"}};

//---2008/08/21 update end    
    /** 前年データ種別：前年同月のみ */
    public static final String[][] CODE_TABLE_DOGETU = new String [][]{
        {CODE_DOGETU, "前年同月"}};
    /**
     * 外部からインスタンス化できない
     */
    private ZennenDataShubetu() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
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
     * ユーザタイプに応じた店舗種別リストを取得する<br>
     * @param   userTypeCd　ユーザタイプコード
     * @param   type 照会画面タイプ
     * @return  List　  店舗種別リスト
     */
    public static List getPullDownList(String userTypeCd, int type) {
        // ユーザータイプが本部の場合
        if(UserType.HONBU.equals(userTypeCd)){
        	if(CommonUtil.TYPE_NIPO == type) {
        		return getPullDownList(CODE_TABLE_HONBU);
        	}
	    	if(CommonUtil.TYPE_SUII == type) {
	    		return getPullDownList(CODE_TABLE);
	    	}
        }
        // ユーザータイプがオーナーの場合
        else if (UserType.ONER.equals(userTypeCd)) {
            return getPullDownList(CODE_TABLE_ONER); 
        }
        // ユーザータイプが店舗の場合
        else if (UserType.TENPO.equals(userTypeCd)){
            return getPullDownList(CODE_TABLE_TENPO); 
        }
        return null;
    }
    /**
     * 前年データ種別リストを取得する
     * 
     * @param   userType        ユーザタイプ
     * @param   type 照会画面タイプ
     * @param 	 tenpoShubetu	店舗種別
     * @return	List 			前年データ種別リスト
     */
    public static List getPullDownList(String userType, int type, String tenpoShubetu) {

    	// ユーザータイプが本部の場合：前年対象店
    	if (UserType.HONBU.equals(userType)
    			&& !TenpoShubetu.CODE_ZENNEN.equals(tenpoShubetu)) {
//---2008/08/21 前年同曜日のみに変更            
//    		return CodeUtil.getPullDownList(CODE_TABLE_DOGETU); 
            return CodeUtil.getPullDownList(CODE_TABLE_DOYO); 
    		
    	}
    	return getPullDownList(userType, type);
    }

}
