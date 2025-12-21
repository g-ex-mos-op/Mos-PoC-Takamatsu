/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.codbalancelist.common;

/**
 * COD残高一覧static共通メソッド保持クラス
 * 
 * @author xkinu
 *
 */
public class CodBalanceListUtil {
    /* 画面ID */
    public static final String SCREEN_ID = "BBS031";

    /* VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";

    /* ACTIONID：初期(条件)画面 */
    public static final String ACTION_ID_CONFIRM = SCREEN_ID+"A1";
    
    /* LOGICID：初期(条件)画面項目情報の取得 */
    public static final String LOGIC_ID_CONDITION = SCREEN_ID+"L01";
    /* LOGICID：検索対象情報の取得 */
    public static final String LOGIC_ID_GETCODDATA = SCREEN_ID+"L02";
    /* LOGICID：CSVダウンロード */
    public static final String LOGIC_ID_CSVOUTPUT= SCREEN_ID+"L03";
    /* LOGICID：CSVダウンロード事後処理 */
    public static final String LOGIC_ID_CSVOUTPUT_FINAL= SCREEN_ID+"L04";
    /**
     * コンストラクター
     *
     */
    private CodBalanceListUtil(){
        super();
    }
    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
