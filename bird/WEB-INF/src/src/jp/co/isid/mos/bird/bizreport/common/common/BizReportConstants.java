package jp.co.isid.mos.bird.bizreport.common.common;

/**
 * 総合営業日報 定数クラス
 *
 * @author xjung
 */
public interface BizReportConstants {
//  画面ID
    /** ポータル */
    public static final String SCREEN_ID_PORTAL = "BSI001";

//  メッセージの定数
	/** メッセージ：ユーザータイプ */
    public static final String MSG_USER_TYPE = "ユーザータイプ";
    /** メッセージ：ユーザーID */
    public static final String MSG_USER_ID = "ユーザーID";
    /** メッセージ：アプリ日付 */
    public static final String MSG_APP_DATE = "アプリ日付";
    /** メッセージ：会社コード */
    public static final String MSG_COMPANY_CD = "会社コード";
    /** メッセージ：会社対象支部 */
    public static final String MSG_TAISHO_SIBU = "対象支部";    
    /** メッセージ：集計区分 */
    public static final String MSG_SHUKEI_KBN = "集計区分";    
	/** メッセージ：条件画面初期処理 */
    public final static String MSG_CONDITION_ERR = "条件画面初期処理";

//  Mapオブジェクトキーの定数
    /** 会社情報プルダウンリスト*/
    public static final String COMPANY_INFO_LIST = "companyInfoList";
    /** 店舗種別プルダウン */
    public static final String TENPO_SHUBETU_LIST = "tenpoShubetuList";
    /** 集計区分プルダウンリスト */
    public static final String SHUKEI_KBN_LIST = "shukeiKbnList";
    /** 対象店舗プルダウンリスト */
    public static final String TAISHO_TENPO_LIST = "taishoTenpoList";
    /** 対象期間プルダウンリスト */
    public static final String TAISHO_KIKAN_LIST = "taishoKikanList";
    /** 期間指定年月日(yyyy/MM/dd)プルダウンリスト */
    public static final String KIKAN_YMD_LIST = "kikanYMDList";
    /** 期間指定年(yyyy)プルダウンリスト */
    public static final String KIKAN_Y_LIST = "kikanYearList";
    /** 期間指定期別プルダウンリスト */
    public static final String KIKAN_KIBETU_LIST = "kikanKibetuList";
    /** 期間指定年月(yyyy/MM)プルダウンリスト */
    public static final String KIKAN_YM_LIST = "kikanYMList";
    /** 期間指定当月 */
    public static final String KIKAN_CURR_MONTH = "kikanCurrMonth";
    /** 前年データ種別(前年対象店)プルダウンリスト */
    public static final String ZEN_DATA_LIST = "zenDataList";
    /** 前年データ種別(前年対象店以外)プルダウンリスト */
    public static final String ZEN_DATA_OTH_LIST = "zenDataOthList";
    /** 前年データ種別(オーナー用)プルダウンリスト */
    public static final String ZEN_DATA_ONER_LIST = "zenDataOnerList";
    /** ユーザID */
    public static final String USER_ID = "userId";
    /** 個店ポータルアクセスフラグ */
    public static final String PORTAL_ACCESS_FLG = "portalAccessFlg";

    /** 2008/10/16 追加                                          */
    /** 期間指定年月(yyyy/MM)プルダウンリスト 営業月報抽出CSV用　*/
    public static final String KIKAN_GEPO_YM_LIST = "kikanGepoYMList";
    /** 対象期間：営業月報抽出 */
    public static final String GEPOCSV = "GEPOCSV";
    /** 対象期間:オーナー用 */
    public static final String[][] CODE_GEPOCSV = new String [][]{
        {GEPOCSV , "営業月報抽出"}};
    
 //  期間指定の定数コード
    /** 期別期報の年(yyyy)リストサイズ(3年間分) */
    public static final String Y_SIZE = "3";
    /** 期別期報の年(yyyy)リスト*/
    public static final String KIBETU_Y_LIST = "list1";
    /** 期別期報の期別リスト */
    public static final String KIBETU_LIST = "list2";
    /** 期別期報の期別：期間タイプ */
    public static final String PM_KIKANTYPE = "kikanType";
    /** 期別期報の期別：リスト個数（アプリ日付含めて過去何個分か）*/
    public static final String PM_SIZE = "listSize";
    /** 表示年(期報) */
    public final static int DISPLAY_YEAR = 2;
    /** 表示月 + 1(表示日用) */
    public final static int DISPLAY_DAY_MONTH = 14;
    /** 表示月(年) */
    public final static int DISPLAY_MONTH_YEAR = 3; 
    /** 期間指定の表示日 */
    public final static int LIMIT_DAY = 92;
    /** 01月 */
    public final static String MONTH_01 = "01";
    /** 03月 */
    public final static String MONTH_03 = "03";
    /** 04月 */
    public final static String MONTH_04 = "04";
    /** 06月 */
    public final static String MONTH_06 = "06";
    /** 07月 */
    public final static String MONTH_07 = "07";
    /** 09月 */
    public final static String MONTH_09 = "09";
    /** 10月 */
	public final static String MONTH_10 = "10";
    /** 12月 */
	public final static String MONTH_12 = "12";

// サブタグ区分
	/** サブタグ区分：売上*/
    public final static String SUB_TAG_0 = "0";
    /** サブタグ区分：件数*/
    public final static String SUB_TAG_1 = "1";
    /** サブタグ区分：詳細売上*/
    public final static String SUB_TAG_2 = "2";
    /** サブタグ区分：詳細件数*/
    public final static String SUB_TAG_3 = "3";

// その他の定数
    /** 空白文字 */
	public final static String EMPTY = "";
    
    public static final String VIEW_ID_SVSEARCH = "BCO011V01";
    /** 海外フラグ：海外の会社 */
    public static final String FOREING_ON = "1";
    /** 換算フラグ：換算済み */
    public static final String KANSAN_ON = "1";
    public static final String FORMAT_JPY = "#,##0";

}