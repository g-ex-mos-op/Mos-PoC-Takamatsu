package jp.co.isid.mos.bird.storemanage.mlresultregist.common;

/**
 * マスターライセンス結果登録用共通クラス
 */
public class MlResultRegistCommon {

    /* VIEW_ID */
    public static final String VIEW_ID                   = "BSM008";
    public static final String VIEW_ID_CONDITION         = "BSM008V01";
    public static final String VIEW_ID_ABILITY_LIST      = "BSM008V02";
    public static final String VIEW_ID_ABILITY_EDIT      = "BSM008V03";
    public static final String VIEW_ID_ABILITY_CONFIRM   = "BSM008V04";
    public static final String VIEW_ID_INTERVIEW_EDIT    = "BSM008V05";
    public static final String VIEW_ID_INTERVIEW_CONFIRM = "BSM008V06";
    public static final String VIEW_ID_CSVIMPORT         = "BSM008V07";
    public static final String VIEW_ID_CSVIMPORT_RESULT  = "BSM008V08";

    // 店検索
    public static final String VIEW_ID_MISESEARCH  = "BCO008V01";
    // オーナー検索
    public static final String VIEW_ID_ONERSEARCH  = "BCO006V01";
    
    /* ユーザータイプコード */
    public static final String USER_TYPE_CD_HONBU = "01";
    public static final String USER_TYPE_CD_ONER  = "02";
    
    // 結果
    public static final String RESULT_FAILURE   = "0";  //不合格
    public static final String RESULT_PASS      = "1";  //合格
    public static final String RESULT_EXEMPT    = "2";  //免除
    public static final String RESULT_NOENTRY   = "3";  //受験不可
    public static final String RESULT_NOTENTRY     = "9";  // 未受験

    // 総合結果
    public static final String TOTAL_RESULT_INVALID     = "0";  // 無効
    public static final String TOTAL_RESULT_RESERVATION = "1";  // 保留
    public static final String TOTAL_RESULT_FAILURE     = "2";  // 不合格
    public static final String TOTAL_RESULT_WAIT        = "3";  // 発行待ち
    public static final String TOTAL_RESULT_LICENSED    = "4";  // 発行済
    public static final String TOTAL_RESULT_NOENTRY     = "9";  // 未受験

    // 失効フラグ
    public static final String EXPIRE_FLG_VALID     = "0";  // 有効
    public static final String EXPIRE_FLG_INVALID   = "1";  // 失効
    public static final String EXPIRE_FLG_WAIT      = "2";  // 発行待ち
    
    //汎用画面別ロール制御の一括取込ロール：分類
    public static final String BR11_BUNRUI_IKKATU_TORIKOMI = "01";
    
    // 検索タイプ
    public static final String COND_TYPE_SIBU     = "1";    //支部
    public static final String COND_TYPE_ONER     = "2";    //オーナー
    public static final String COND_TYPE_MISE     = "3";    //店舗
}
