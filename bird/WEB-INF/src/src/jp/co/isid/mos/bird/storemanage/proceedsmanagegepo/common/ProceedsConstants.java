package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common;

/**
 * 売上金管理月報 共通定数クラス
 *
 * @author xjung
 */
public interface ProceedsConstants {
// 画面・VIEW ID定数
    /** 画面ID:売上金管理月報画面 */
    public static final String SCREEN_ID = "BSM013";
    /** VIEW_ID:売上金管理月報画面 */
    public static final String VIEW_ID = "BSM013V01";
    /** VIEW_ID:売上金管理月報明細照会画面 */
    public static final String VIEW_ID_MAISAI_SCH = "BSM013V02";
    /** VIEW_ID:店選択画面 */
    public static final String VIEW_ID_MISE_SCH = "BCO008V01";
//add 2019/07/15 USI張 #34 begin
    /** VIEW_ID:売上と消費税明細照会画面 */
    public static final String VIEW_ID_UTMAISAI_SCH = "BSM013V03";
//add 2019/07/15 USI張 #34 end

//  ユーザータイプコード定数
    /** ユーザータイプコード:本部(01) */
    public static final String HONBU = "01";
    /** ユーザータイプコード:オーナー(02) */
    public static final String ONER = "02";
    /** ユーザータイプコード:店舗(03) */
    public static final String MISE = "03";

// メッセージの定数
    /** メッセージ：ユーザータイプ*/
    public static final String MSG_USER_TYPE = "ユーザータイプ";
    /** メッセージ：ユーザーID */
    public static final String MSG_USER_ID = "ユーザーID";
    /** メッセージ：アプリ日付 */
    public static final String MSG_APP_DATE = "アプリ日付";
    /** メッセージ：オーナーコード */
    public static final String MSG_ONER_CD = "オーナーコード";
    /** メッセージ：条件画面初期処理 */
    public final static String MSG_CONDITION_ERR = "条件画面初期処理";
    /** メッセージ：会社コード */
    public static final String MSG_COMPANY_CD = "会社コード";
    /** メッセージ：対象条件 */
    public static final String MSG_TAISHO_JOKEN = "対象条件";
    /** メッセージ：対象店舗 */
    public static final String MSG_TAISHO_TENPO = "対象店舗";
    /** メッセージ：対象年月 */
    public static final String MSG_TAISHO_YM = "対象年月";
    /** メッセージ：会社情報 */
    public static final String MSG_COMPANY_INFO = "会社情報";
    /** メッセージ：対象店舗情報 */
    public static final String MSG_TAISHO_TENPO_INFO = "対象店舗情報";

//  CSVの定数
    /** ヘッダ部：会社 */
    public static final String CSV_HD_COMPANY = "会社：";
    /** ヘッダ部：店舗種別 */
    public static final String CSV_HD_TAISHO_TENPO = "対象店舗：";
    /** ヘッダ部：対象期間 */
    public static final String CSV_HD_TAISHO_YM = "対象年月：";

    /** データ部：営業日 */
    public static final String CSV_DT_EIGYO_DT = "営業日";
    /** データ部：売上 */
    public static final String CSV_DT_URIAGE = "売上";
    //begin add 2019/09/17 xou.zoubun 軽減税率項目の追加
    /** データ部：通常税率対象売上 */
    public static final String CSV_DT_URIAGE1 = "通常税率対象売上";
    /** データ部：軽減税率対象売上 */
    public static final String CSV_DT_URIAGE2 = "軽減税率対象売上";
    //end add 2019/09/17 xou.zoubun 軽減税率項目の追加
    /** データ部：値引合計 */
    public static final String CSV_DT_NEBIKI = "値引合計";
    /** データ部：消費税 */
    public static final String CSV_DT_TAX = "消費税";
    //begin add 2019/09/17 xou.zoubun 軽減税率項目の追加
    /** データ部：通常税率対象消費税 */
    public static final String CSV_DT_TAX1 = "通常税率対象税額";
    /** データ部：軽減税率対象消費税 */
    public static final String CSV_DT_TAX2 = "軽減税率対象税額";
    //end add 2019/09/17 xou.zoubun 軽減税率項目の追加
    /** データ部：金券販売等 */
    public static final String CSV_DT_GC_HAN_KIN = "金券販売等";
    /** データ部：前受・売掛件数 */
    public static final String CSV_DT_URIKAKE_KEN = "前受・売掛件数";
    /** データ部：前受・売掛金額 */
    public static final String CSV_DT_URIKAKE_KIN = "前受・売掛金額";
    /** データ部：現金合計 */
    public static final String CSV_DT_GENKIN = "現金合計";
    /** データ部：現金在高 */
    public static final String CSV_DT_ARIDAKA_JITU = "現金在高";
    /** データ部：過剰 */
    public static final String CSV_DT_KAJOU = "過剰";
    /** データ部：不足 */
    public static final String CSV_DT_FUSOKU = "不足";
    /** データ部：客数 */
    public static final String CSV_DT_KYAKUSU = "客数";
    /** データ部：売上取消件数 */
    public static final String CSV_DT_CAN_KEN = "売上取消件数";
    /** データ部：売上取消金額 */
    public static final String CSV_DT_CAN_KIN = "売上取消金額";
    /** データ部：ｵｰﾀﾞｰ票ｷｬﾝｾﾙ件数 */
    public static final String CSV_DT_ALLCAN_KEN = "ｵｰﾀﾞｰ票ｷｬﾝｾﾙ件数";
    /** データ部：ｵｰﾀﾞｰ票ｷｬﾝｾﾙ金額 */
    public static final String CSV_DT_ALLCAN_KIN = "ｵｰﾀﾞｰ票ｷｬﾝｾﾙ金額";
    /** データ部：件数の名称 */
    public static final String CSV_DT_KEN_NAME = "件数";
    /** データ部：金額の名称 */
    public static final String CSV_DT_KIN_NAME = "金額";
    /** データ部：税額の名称 */
    public static final String CSV_DT_ZEI_NAME = "税額";

    // 明細一括
    /** データ部：集計区分名称 */
    public static final String CSV_MEISAI_DT_SYUKEIKBN_NAME = "集計区分名称：";
    /** データ部：合計件数 */
    public static final String CSV_MEISAI_DT_GOUKEIKEN = "合計件数";
    /** データ部：合計金額 */
    public static final String CSV_MEISAI_DT_GOUKEIKIN  = "合計金額";

// add 2020/05/08 USI張  begin
    public static final String CSV_DT_POINT = "dポイント";
    public static final String CSV_DT_POINT_PRT = "進呈（単位:ポイント）";
// add 2020/05/08 USI張  end

// add 2021/09/02 USI戚 begin
    public static final String CSV_DT_POINT_NET = "dポイントネット注文利用";
    public static final String CSV_DT_POINT_NET_PRT = "進呈（単位:ポイント）";
// add 2021/09/02 USI戚 end

// add 2020/06/09 USI張  begin
    public static final String CSV_DT_KABUPOINT = "株主優待券";
    public static final String CSV_DT_KABUPOINT_PRT = "株主優待券チャージ金額";
// add 2020/06/09 USI張  end

    /** CSVファイル名 */
    public static final String CSV_FILE_NAME = "URIKANRI";
    /** 明細CSVファイル名 */
    public static final String CSV_MEISAI_FILE_NAME = "KAIKEIMEISAI";
    /** CSV拡張子 */
    public static final String CSV_EXTENSION = ".csv";

// 対象年月の定数
    /** 対象年月の表示月サイズ */
    public final static int DISPLAY_DAY_MONTH = 13;
    /** フォーマットパターン：DD日(？曜) */
    public static final String PATTERN_DAY_WEEK_KJ = "dd'日('E')'";

// スタイルシート定数
    /** スタイルシート：合計行 */
    public static final String TR_TOTAL_SUM = "body_sum5";

// 画面表示用ラベル定数
   	/** 合計 */
   	public static final String LABEL_SUM = "合計";
    /** 集計区分 */
    public static final String LABEL_SYUKEI = "集計区分";
    /** 会計区分 */
    public static final String LABEL_KAIKEI = "会計区分";
    /** チケット販売 */
    public static final String LABEL_TICKET = "チケット販売";
    /** 値引*/
    public static final String LABEL_NEBIKI = "値引";
    /** 検索ボタン：実行 */
    public static final String BT_EXECUTE = "実行";
    /** 検索ボタン：再検索 */
    public static final String BT_RESEARCH = "再検索";
// Mapオブジェクトキーの定数
    /** 会社情報プルダウンリスト*/
    public static final String MAP_COMPANY_LIST = "companyList";
    /** 対象年月(yyyy/MM)プルダウンリスト */
    public static final String MAP_TAISHO_YM_LIST = "taishoYMList";
   	/** 売上金管理月報・結果リスト */
   	public static final String MAP_RST_LIST = "ResultList";
    /** 集計区分名リスト */
    public static final String MAP_SK_LIST = "syukeiKbnList";
    /** 会計区分名リスト */
    public static final String MAP_KK_LIST = "kaikeiKbnList";
   	/** チケット名称リスト */
   	public static final String MAP_TCK_NAME_LIST = "TicketNameList";
    /** 対象店舗情報 */
    public static final String MAP_TAISHO_TENPO_INFO = "taishoTenpoInfo";
    /** 値引情報・結果リスト */
   	public static final String MAP_NEBIKI_RST_LIST = "nebikiResultList";
   	/** 値引区分名称リスト */
   	public static final String MAP_NK_NAME_LIST = "nebikiKbnNameList";
    /** 明細リンク表示フラグ */
    public static final String MAP_MEISAI_DISP_FLG = "meisaiDispFlg";
//add 2019/07/15 USI張 #34 begin
    /** 売上と消費税列明細リンク表示フラグ */
    public static final String MAP_URIAGE_MEISAI_DISP_FLG = "uriageMeisaiDispFlg";
//add 2019/07/15 USI張 #34 end

// タブ定数
    /** タブ：売上金タブ*/
    public static final String TAB_0 = "0";
    /** タブ：前受・売掛タブ*/
    public static final String TAB_1 = "1";
    /** タブ：販売タブ*/
    public static final String TAB_2 = "2";
    /** タブ：値引 */
    public static final String TAB_3 = "3";
// add 2020/05/08 USI張  begin
    /** タブ：ポイント関連 */
    public static final String TAB_4 = "4";
// add 2020/05/08 USI張  end
    /** サブタブ：売上金・その１サブタブ*/
    public static final String SUB_TAB_0 = "0";
    /** サブタブ：売上金・その２サブタブ*/
    public static final String SUB_TAB_1 = "1";
    /** サブタブ：前受・売掛・その１サブタブ*/
    public static final String SUB_TAB_2 = "2";
    /** サブタブ：前受・売掛・その２サブタブ*/
    public static final String SUB_TAB_3 = "3";
    /** サブタブ：販売・その１サブタブ*/
    public static final String SUB_TAB_4 = "4";
    /** サブタブ：販売・その２サブタブ*/
    public static final String SUB_TAB_5 = "5";
    /** サブタブ：販売・その３サブタブ*/
    public static final String SUB_TAB_6 = "6";
    /** サブタブ：値引・その１サブタブ*/
    public static final String SUB_TAB_7 = "7";
    /** サブタブ：値引・その２サブタブ*/
    public static final String SUB_TAB_8 = "8";
    /** サブタブ：値引・その３サブタブ*/
    public static final String SUB_TAB_9 = "9";
// add 2020/05/08 USI張  begin
    /** サブタブ：ポイント関連・その１サブタブ*/
    public static final String SUB_TAB_10 = "10";
// add 2020/05/08 USI張  end

    /** 明細照会：初期タブ */
    public static final String MEISAI_TAB_1 = "01";

// その他
    /** 空白文字列 */
    public static final String EMPTY = "";
    /** スペース */
    public static final String SPACE = " ";
    /** ゼロ */
    public static final String ZERO = "0";
    /** 最初日 */
    public static final String FIRST_DAY = "01";
    /** 最終日 */
    public static final String LAST_DAY = "31";
    /** 対象条件：全店：店舗コード */
    public static final String ZENTEN_CD = "99999";
    /** 対象条件：全店：店舗コード */
    public static final String ZENTEN_NAME = "全店";
    /** 明細検索の最古月 **/
    public static final String MEISAI_MIN_MONTH = "201208";
//add 2019/07/15 USI張  #34 begin
    /** 売上明細検索の最古月 */
    public static final String URIAGE_MEISAI_MIN_MONTH = "201910";
//add 2019/07/15 USI張  #34 end
    /** BIRD 表示フラグ - ON */
    public static final String BIRD_DISP_FLG_ON = "1";

}