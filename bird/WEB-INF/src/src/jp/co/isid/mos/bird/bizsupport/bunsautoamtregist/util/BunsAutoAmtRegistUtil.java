package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util;

/**
 * バンズ自動設定数量変更
 * 
 * static 処理保持クラス
 * @author xkinu
 *
 */
public class BunsAutoAmtRegistUtil {
    /** 画面ID */
    public static final String SCREEN_ID = "BBS026";
    /** 画面名称 */
    public static final String SCREEN_NAME = "バンズ自動設定数量変更／照会";
    /** VIEW_ID：操作エラー(複数Window対応用）*/
    public static final String operationErr_VIEW_ID = "operation.Err";
    /** VIEWID：オーナー選択 */
    public static final String VIEW_ID_ONERSEARCH   = "BCO006V01";
    /** VIEWID：店舗選択 */
    public static final String VIEW_ID_MISESEARCH   = "BCO008V01";
    /** VIEWID：SV選択 */
    public static final String VIEW_ID_SVSEARCH   = "BCO011V01";
    /** VIEWID：初期(条件)画面 */
    public static final String VIEW_ID = SCREEN_ID+"V01";
    /** VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /** VIEWID：編集画面 */
    public static final String VIEW_ID_EDIT = SCREEN_ID+"V02";
    /** VIEWID：確認or照会画面 */
    public static final String VIEW_ID_CONFIRM = SCREEN_ID+"V03";
    
    /**
     * コンストラクター
     *
     */
    private BunsAutoAmtRegistUtil() {
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
