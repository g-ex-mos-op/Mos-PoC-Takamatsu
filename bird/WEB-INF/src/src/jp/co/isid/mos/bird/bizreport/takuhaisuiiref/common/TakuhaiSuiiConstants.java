package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common;

/**
 * 宅配売上推移表 定数クラス
 * 
 * @author xwatanabe
 */
public interface TakuhaiSuiiConstants {

    /** 会社コード */
    public static final String COMPANY_CD_MOS = "00";
    /** 画面ID:宅配売上推移表画面 */
    public static final String SCREEN_ID = "BBR004";

    //---------------------
    // メッセージ
    //---------------------
    /** メッセージ：CSVダウンロード */
    public static final String MSG_CSV_DOWNLOAD = "CSVダウンロード";

    //---------------------
    // ビューID
    //---------------------
    public static final String VIEWID_TAKUHAI_SUII       = "BBR004V01";   //宅配売上推移表結果画面
    public static final String VIEWID_TAKUHAI_SUII_MISE  = "BBR004V02";   //宅配売上推移表結果(店舗)画面
    public static final String VIEWID_MISESEARCH         = "BCO008V01";   //店選択

    //---------------------
    // ユーザータイプ
    //---------------------
    /** ユーザータイプコード：01:本部 */
    public static final String USERTYPE_HONBU  = "01";
    /** ユーザータイプコード：02:オーナー */
    public static final String USERTYPE_ONER   = "02";
    /** ユーザータイプコード：03:店舗 */
    public static final String USERTYPE_MISE   = "03";

    //---------------------
    // 引数パラメータキー
    //---------------------
    /** パラメータキー：会社コード */
    public static final String PARAM_KEY_COMPANY_CD      = "PARAM_KEY_COMPANY_CD";
    /** パラメータキー：ユーザID */
    public static final String PARAM_KEY_USER_ID         = "PARAM_KEY_USER_ID";
    /** パラメータキー：ユーザタイプ */
    public static final String PARAM_KEY_USER_TYPE       = "PARAM_KEY_USER_TYPE";
    /** パラメータキー：オーナーコード */
    public static final String PARAM_KEY_ONER_CD         = "PARAM_KEY_ONER_CD";
    /** パラメータキー：制限区分 */
    public static final String PARAM_KEY_LIMIT_FLG       = "PARAM_KEY_LIMIT_FLG";
    /** パラメータキー：店舗種別 */
    public static final String PARAM_KEY_TENPO_SHUBETU   = "PARAM_KEY_TENPO_SHUBETU";
    /** パラメータキー：対象条件 */
    public static final String PARAM_KEY_TAISHO_JOKEN    = "PARAM_KEY_TAISHO_JOKEN";
    /** パラメータキー：表示対象A */
    public static final String PARAM_KEY_HYOJITAISHO_A   = "PARAM_KEY_HYOJITAISHO_A";
    /** パラメータキー：表示対象B */
    public static final String PARAM_KEY_HYOJITAISHO_B   = "PARAM_KEY_HYOJITAISHO_B";
    /** パラメータキー：前年データ種別 */
    public static final String PARAM_KEY_ZENDATA_SHUBETU = "PARAM_KEY_ZENDATA_SHUBETU";
    /** パラメータキー：期間指定 */
    public static final String PARAM_KEY_KIKAN_SITEI     = "PARAM_KEY_KIKAN_SITEI";
    /** パラメータキー：対象期間(任意月指定or年度) */
    public static final String PARAM_KEY_TAISHO_KIKAN    = "PARAM_KEY_TAISHO_KIKAN";
    /** パラメータキー：ブロックコード */
    public static final String PARAM_KEY_BLOCK_CD        = "PARAM_KEY_BLOCK_CD";
    /** パラメータキー：アプリ日付 */
    public static final String PARAM_KEY_APPDATE         = "PARAM_KEY_APPDATE";

    //---------------------
    // メッセージの定数
    //---------------------
    /** ユーザータイプ*/
    public static final String MSG_USER_TYPE        = "ユーザータイプ";
    /** 会社コード */
    public static final String MSG_USER_COMPANYCD   = "会社コード";
    /** ユーザーID */
    public static final String MSG_USER_ID          = "ユーザーID";
    /** 対象条件 */
    public static final String MSG_TAISHO_JOKEN     = "対象条件";
    /** 制限区分 */
    public static final String MSG_LIMIT_FLG        = "制限区分";
    /** オーナーコード */
    public static final String MSG_ONER_CD          = "オーナーコード";
    /** 表示対象 */
    public static final String MSG_HYOJI_TAISHO     = "表示対象";
    /** 前年データ種別 */
    public static final String MSG_ZENDATA_SHUBETU  = "前年データ種別";
    /** 店舗種別 */
    public static final String MSG_TENPO_SHUBETU    = "店舗種別";
    /** 対象期間 */
    public static final String MSG_TAISHO_KIKAN     = "対象期間";
    /** ブロックコード */
    public static final String MSG_BLOCK_CD         = "ブロックコード";

    //---------------------
    // 行タイプの定数
    //---------------------
    /** 行タイプ：通常 */
    public static final int ROW_TYPE_NOMAL      = 0;
    /** 行タイプ：合計(通期) */
    public static final int ROW_TYPE_SUM        = 1;
    /** 行タイプ：上半期 */
    public static final int ROW_TYPE_HANKI_1    = 2;
    /** 行タイプ：下半期 */
    public static final int ROW_TYPE_HANKI_2    = 3;
    /** 行タイプ：第一四半期 */
    public static final int ROW_TYPE_SIHANKI_1  = 4;
    /** 行タイプ：第一四半期 */
    public static final int ROW_TYPE_SIHANKI_2  = 5;
    /** 行タイプ：第一四半期 */
    public static final int ROW_TYPE_SIHANKI_3  = 6;
    /** 行タイプ：第一四半期 */
    public static final int ROW_TYPE_SIHANKI_4  = 7;
    /** 行タイプ：平均 */
    public static final int ROW_TYPE_AVG        = 8;

    //---------------------
    // 表示タグの定数
    //---------------------
    /** タグ：個店:日次 or 複数店：日次売上 */
    public static final String TAB_AREA_0      = "0";
    /** タグ：複数店：日次件数 */
    public static final String TAB_AREA_1      = "1";
    /** タグ：個店:月次 or 複数店：月次売上 */
    public static final String TAB_AREA_2      = "2";
    /** タグ：複数店：月次件数 */
    public static final String TAB_AREA_3      = "3";

    //---------------------
    // マップ用キー定数
    //---------------------
    /** マップ返却用キー：日報画面表示用リスト */
    public static final String MAPKEY_NIPO_GAMEN_LIST    = "NIPO_GAMEN_LIST";
    /** マップ返却用キー：日報リスト */
    public static final String MAPKEY_NIPO_LIST          = "NIPO_LIST";
    /** マップ返却用キー：日報リスト(再取得) */
    public static final String MAPKEY_RE_NIPO_LIST       = "RE_NIPO_LIST";
    /** マップ返却用キー：月報画面表示用リスト */
    public static final String MAPKEY_GEPO_GAMEN_LIST    = "GEPO_GAMEN_LIST";
    /** マップ返却用キー：月報報リスト */
    public static final String MAPKEY_GEPO_LIST          = "GEPO_LIST";
    /** マップ返却用キー：月報リスト(再取得) */
    public static final String MAPKEY_RE_GEPO_LIST       = "RE_GEPO_LIST";
    /** マップ返却用キー：月末宅配対象店舗数 */
    public static final String MAPKEY_TENPO_CNT_MAP      = "MAPKEY_TENPO_CNT_MAP";

    
    //---------------------
    // スタイルシート定数
    //---------------------
    /** 
     * 指定無し 
     */
    public static final String NO_CLASS      = "";

    /** 
     * 比率項目
     * 　背景：緑
     * 　文字色：黒
     * 　右寄せ
     */
    public static final String TD_HIRITU     = "hiritu";

    /** 
     * 比率項目 
     * 　背景：緑
     * 　文字色：赤
     * 　右寄せ
     */
    public static final String TD_HIRITU_RED     = "hiritu_red";
    /** 
     * 合計欄用：数値項目 
     * 　背景：オレンジ
     * 　右寄せ
     */
    public static final String TD_SUM_NUM     = "sum_num";
    /** 
     * 合計欄用：数値項目 
     * 　背景：オレンジ
     * 　右寄せ
     * 　文字色：赤
     */
    public static final String TD_SUM_NUM_RED = "sum_num_red";

    /** 
     * 四半期欄用：数値項目 
     * 　背景：ピンク
     * 　右寄せ
     */
    public static final String TD_SIHANKI_NUM     = "sihanki_num";
    /** 
     * 四半期欄用：数値項目 
     * 　背景：ピンク
     * 　右寄せ
     * 　文字色：赤
     */
    public static final String TD_SIHANKI_NUM_RED = "sihanki_num_red";

    /** 
     * 半期欄用：数値項目 
     * 　背景：紫
     * 　右寄せ
     */
    public static final String TD_HANKI_NUM     = "hanki_num";
    /** 
     * 半期欄用：数値項目 
     * 　背景：紫
     * 　右寄せ
     * 　文字色：赤
     */
    public static final String TD_HANKI_NUM_RED = "hanki_num_red";

}
