/*
 * 作成日:2007/02/06
 */
package jp.co.isid.mos.bird.storemanage.posniporef.common;

/**
 * POS日報　定数クラス
 * @author xwatanabe
 *
 */
public class PosNipoRefConstants {
    
    /** 画面ID */
    public static final String SCREEN_ID= "BSM014";

    /** 期間プルダウン表示件数 */
    public static final int NENGETU_DISP_MONTH = 3;

    //---------------------
    // VIEWID
    //---------------------
    /** POS日報画面 */
    public static final String VIEW_ID_POSNIPO     = "BSM014V01";
    /** 店舗選択画面 */
    public static final String VIEW_ID_MISESEARCH  = "BCO008V01";
    /** オーナー選択画面 */
    public static final String VIEW_ID_OWNERSEARCH = "BCO006V01";

    //---------------------
    // ユーザータイプ
    //---------------------
    /** ユーザータイプコード：01:本部 */
    public static final String USERTYPE_HONBU  = "01";
    /** ユーザータイプコード：02:オーナー */
    public static final String USERTYPE_ONER   = "02";
    /** ユーザータイプコード：03:店舗 */
    public static final String USERTYPE_MISE   = "03";

    //------------------------------
    // 検索区分(0:店舗  1:オーナー)
    //------------------------------
    /** 検索区分：店舗 */
    public static final String KBN_MISE  = "1";
    /** 検索区分：オーナー */
    public static final String KBN_OWNER = "2";
    /** 検索区分：全店 */
    public static final String KBN_ZENTEN ="3";

    //------------------------------
    // 表示タブインデックス
    //------------------------------
    /** タブインデックス：日報 */
    public static final int TAB_INDEX_NIPO     = 1;
    /** タブインデックス：仕入 */
    public static final int TAB_INDEX_SIIRE    = 2;
    /** タブインデックス：原価 */
    public static final int TAB_INDEX_GENKA    = 3;
    /** タブインデックス：人件費 */
    public static final int TAB_INDEX_JINKENHI = 4;
    
    //---------------------------------
    // 表示タブインデックス(サブタブ用)
    //---------------------------------
    /** サブタブインデックス：実際原価 */
    public static final int SUB_TAB_INDEX_JITU = 0;
    /** サブタブインデックス：標準原価 */
    public static final int SUB_TAB_INDEX_STD  = 1;

}
