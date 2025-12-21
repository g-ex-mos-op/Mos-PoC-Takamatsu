/*
 * 作成日:2007/02/06
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.common;

/**
 * 売上修正確認　定数クラス
 * @author xwatanabe
 *
 */
public class UriMainteViewConstants {

    /** 画面ID */
    public static final String SCREEN_ID= "BBR009";

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
    // 表示タブインデックス(メイン)
    //------------------------------
    /** タブインデックス：売上金 */
    public static final int TAB_INDEX_URIAGE   = 0;
    /** タブインデックス：前受・売掛 */
    public static final int TAB_INDEX_MAEUKE   = 1;
    /** タブインデックス：販売 */
    public static final int TAB_INDEX_HANBAI   = 2;
    /** タブインデックス：値引き */
    public static final int TAB_INDEX_NEBIKI   = 3;
    
    //---------------------------------
    // 表示タブインデックス(サブタブ用)
    //---------------------------------
    /** サブタブインデックス：(前受・売掛タブ用) */
    public static final int TAB_INDEX_SUB_MAEUKE  = 0;
    /** サブタブインデックス：(販売タブ用) */
    public static final int TAB_INDEX_SUB_HANBAI  = 2;
    /** サブタブインデックス：(値引きタブ用) */
    public static final int TAB_INDEX_SUB_NEBIKI  = 5;

    //---------------------
    // メッセージの定数
    //---------------------
    /** メッセージ：システム日付 */
    public static final String MSG_USER_SYSDATE   = "システム日付";
    /** メッセージ：会社コード */
    public static final String MSG_USER_COMPANYCD   = "会社コード";
    /** メッセージ：会社情報 */
    public static final String MSG_USER_COMPANY   = "会社情報";
    /** メッセージ：修正日 */
    public static final String MSG_USER_SYUSEIBI   = "修正日";
    /** メッセージ：集計区分 */
    public static final String MSG_USER_SYUKEIKBN   = "集計区分";
    
    //---------------------
    // BIRD表示フラグ
    //---------------------
    /** BIRD表示フラグ：表示 */
    public static final String BIRD_DISP_FLG_ON = "1";

}
