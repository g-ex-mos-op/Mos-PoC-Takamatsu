package jp.co.isid.mos.bird.entry.longserviceviewlist.util;

/**
 * 永年勤続申込状況確認
 * static 処理保持クラス
 * 
 * @author xamaruyama
 */
public class LongserviceViewListUtil {

    /** VIEWID：SV選択 */
    public static final String VIEW_ID_SVSEARCH   = "BCO011V01";
    /** VIEWID：事業方針共通条件画面 */
    public static final String VIEW_ID_COMMONCONDITION   = "BEN092V01";
    /** 画面ID */
    public static final String SCREEN_ID = "BEN019";
    /** VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /** VIEWID：結果画面 */
    public static final String VIEW_ID_RESULT = SCREEN_ID+"V02";
    /** エントリーコード：永年勤続コード 「20」*/
    public static final String ENTRY_CD = "20";
    /** 対象条件値：全オーナー "0" */
    public static final String TAISHO_JOKEN_ALLONSER = "0";
    /** 対象条件：支部 "1" */
    public static final String TAISHO_JOKEN_SIBU = "1";
    /** 対象条件：SVコード "2" */
    public static final String TAISHO_JOKEN_SV = "2";
    
    /**
     * コンストラクター
     */
    private LongserviceViewListUtil() {
        super();
    }
    
    /**
     * String用Null判断処理
     * 
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    
}