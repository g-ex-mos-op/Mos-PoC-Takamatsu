package jp.co.isid.mos.bird.bizreport.takuhainiporef.common;

/**
 * 宅配売上日報 共通定数クラス
 * 
 * @author xjung
 */
public interface TakuhaiNipoConstants {
// 画面・VIEW ID定数
    /** 画面ID:宅配売上日報画面 */
	public static final String SCREEN_ID = "BBR003";

	/** VIEW_ID:宅配売上日報・条件部画面 */
    public static final String VIEW_ID_CONDITION = "BBR003V01";
    /** VIEW_ID:宅配売上日報・支部別結果画面 */
    public static final String VIEW_ID_SIBU = "BBR003V02";
    /** VIEW_ID:宅配売上日報・店舗別結果画面(本部用) */
    public static final String VIEW_ID_MISE = "BBR003V03"; 
    /** VIEW_ID:宅配売上日報・店舗別結果画面(オーナー用) */
    public static final String VIEW_ID_ONER_MISE = "BBR003V04";
    
    /** VIEW_ID:営業日報・支部別結果画面 */
    public static final String VIEW_ID_E_SIBU = "BBR001V02";
    /** VIEW_ID:営業日報・店舗別結果画面(本部用) */
    public static final String VIEW_ID_E_MISE = "BBR001V03"; 
    /** VIEW_ID:営業日報・店舗別結果画面(オーナー用) */
    public static final String VIEW_ID_E_ONER_MISE = "BBR001V04";

    /** VIEW_ID:屋号別売上・支部別結果画面 */
    public static final String VIEW_ID_Y_SIBU = "BBR005V02";
    /** VIEW_ID:屋号別売上・店舗別結果画面(本部用) */
    public static final String VIEW_ID_Y_MISE = "BBR005V03"; 
    /** VIEW_ID:屋号別売上・店舗別結果画面(オーナー用) */
    public static final String VIEW_ID_Y_ONER_MISE = "BBR005V04";

	/** VIEW_ID:個店ポータル画面 */
	public static final String VIEW_ID_KOTEN_POTAL = "BSI001V02";
    /** VIEWID：SV選択 */
    public static final String VIEW_ID_SVSEARCH   = "BCO011V01";

//  会社コード定数 
    /** 会社コード：モスフードサービス*/
    public static final String MOS_COMPANY_CD = "00";    

// メッセージの定数
    /** メッセージ：検索条件 */
    public static final String MSG_CONDITION = "検索条件";
    /** メッセージ：ユーザータイプ*/
    public static final String MSG_USER_TYPE = "ユーザータイプ";
    /** メッセージ：ユーザーID */
    public static final String MSG_USER_ID = "ユーザーID";
    /** メッセージ：オーナーコード */
    public static final String MSG_ONER_CD = "オーナーコード";
    /** メッセージ：アプリ日付 */
    public static final String MSG_APP_DATE = "アプリ日付";
    /** メッセージ：会社コード*/
    public static final String MSG_COMPANY_CD = "会社コード";
    /** メッセージ：集計区分プルダウンリスト */
    public static final String MSG_TENPO_SHU = "店舗種別";
    /** メッセージ：対象店舗 */
    public static final String MSG_TAISHO_TENPO = "対象店舗";
    /** メッセージ：集計区分 */
    public static final String MSG_SHUKEI_KBN = "集計区分";
    /** メッセージ：対象期間 */
    public static final String MSG_TAISHO_KIKAN = "対象期間";
    /** メッセージ：対象期間FROM */
    public static final String MSG_KIKAN_FROM = "対象期間FROM";
    /** メッセージ：対象期間TO */
    public static final String MSG_KIKAN_TO = "対象期間TO";
    /** メッセージ：期間指定はFROM < TOで */
    public static final String MSG_FROMTO = "期間指定はFROM < TOで";
    /** メッセージ：期間 */
    public static final String MSG_KIKAN = "期間";
    /** メッセージ：９２日以内 */
    public static final String MSG_LIMIT_DAY = "９２日以内";
    /** メッセージ：前年データ種別 */
    public static final String MSG_ZEN_DATA = "前年データ種別";
    /** メッセージ：FCのみ */
    public static final String MSG_FC = "FCのみ";
    /** メッセージ：選択 */
    public static final String MSG_SENTAKU = "選択";
	/** メッセージ：CSVダウンロード */
	public static final String MSG_CSV_DOWNLOAD = "CSVダウンロード";
	/** メッセージ：支部コード */
	public static final String MSG_SIBU_CD = "支部コード";
	/** メッセージ：行CSSクラス名 */
	public static final String MSG_CLASS_NAME = "行CSSクラス名";
    /** メッセージ：SVコード 20081209追加 */
    public static final String MSG_SV_CD = "SVコード";

//  CSVの定数
    /** ヘッダ部：会社 */
    public static final String CSV_HD_COMPANY = "会社：";
    /** ヘッダ部：店舗種別 */
    public static final String CSV_HD_TENPO_SHU = "店舗種別：";
    /** ヘッダ部：対象期間 */
    public static final String CSV_HD_TAISHO_KIKAN = "対象期間：";
    /** ヘッダ部：集計区分 */
    public static final String CSV_HD_SHUKEI_KBN = "集計区分：";
    /** ヘッダ部：前年データ種別 */
    public static final String CSV_HD_ZEN_DATA_SHU = "前年データ種別：";
	/** ヘッダ部：対象店舗 */
	public static final String CSV_HD_TAISHO_TENPO = "対象店舗：";
    /** ヘッダ部：分 */
    public static final String CSV_HD_PORTION = "分";
	/** ヘッダ部：年 */
	public static final String CSV_HD_YEAR = "年";
	/** ヘッダ部：から */
	public static final String CSV_HD_FROM = " から ";
	/** ヘッダ部：まで */
	public static final String CSV_HD_TO = "まで";
    /** ヘッダ部：支部別 */
    public static final String CSV_HD_SIBU = "支部別：";
    /** ヘッダ部：ｴﾘｱ別計 */
    public static final String CSV_HD_SLAREA = "エリア別：";
    /** ヘッダ部：事業部別 */
    public static final String CSV_HD_JIGYOU = "事業部別：";
    /** ヘッダ部：統括本部別 */
    public static final String CSV_HD_HONBU = "統括本部別：";
    /** ヘッダ部：総合計 */
    public static final String CSV_HD_ALL = "総合計";
    /** ヘッダ部：有無別 */
    public static final String CSV_HD_UMU = "有無別";
    /** ヘッダ部：タイプ別 */
    public static final String CSV_HD_TYPE = "タイプ別";
    /** ヘッダ部：SV指定 */
    public static final String CSV_HD_SV = "SV指定";
    /** ヘッダ部：担当SV */
    public static final String CSV_HD_SV_TANTO = "担当SV：";
 
    /** データ部：集計区分 */
    public static final String CSV_DT_SHUKEI_KBN = "集計区分";
    /** データ部：事業本部ｺｰﾄﾞ */
    public static final String CSV_DT_JIGYOU_CD = "事業本部ｺｰﾄﾞ";		
    /** データ部：事業本部名称 */
    public static final String CSV_DT_JIGYOU_NAME = "事業本部名称";		
    /** データ部：ｴﾘｱｺｰﾄﾞ */
    public static final String CSV_DT_SLAREA_CD = "ｴﾘｱｺｰﾄﾞ";
    /** データ部：ｴﾘｱ名称 */
    public static final String CSV_DT_SLAREA_NAME = "ｴﾘｱ名称";
    /** データ部：支部ｺｰﾄﾞ */
    public static final String CSV_DT_SIBU_CD = "支部ｺｰﾄﾞ";
    /** データ部：支部名称 */
    public static final String CSV_DT_SIBU_NAME = "支部名称";
    /** データ部：ﾌﾞﾛｯｸｺｰﾄﾞ */
    public static final String CSV_DT_BLOCK_CD = "ﾌﾞﾛｯｸｺｰﾄﾞ";
    /** データ部：ﾌﾞﾛｯｸ名称 */
    public static final String CSV_DT_BLOCK_NAME = "ﾌﾞﾛｯｸ名称";
    /** データ部：店ｺｰﾄﾞ */
    public static final String CSV_DT_MISE_CD = "店ｺｰﾄﾞ";
    /** データ部：店名称 */
    public static final String CSV_DT_MISE_NAME = "店名称";
    /** データ部：種別 */
    public static final String CSV_DT_TAKUHAI_KBN = "種別";
    /** データ部：宅配実績店舗数 */
    public static final String CSV_DT_TENPO_COUNT = "宅配実績店舗数";
    /** データ部：宅配日数 */
    public static final String CSV_DT_EIGYO_DAYS = "宅配日数";
    /** データ部：売上 */
    public static final String CSV_DT_URIAGE = "売上";
    /** データ部：前年実績 */
    public static final String CSV_DT_URIAGE_ZEN = "前年実績";
    /** データ部：前年比(売上) */
    public static final String CSV_DT_ZEN_HI_URIAGE = "前年比(売上)";
    /** データ部：宅配売上 */
    public static final String CSV_DT_URIAGE_TAKU = "宅配売上";
    /** データ部：前年宅配実績 */
    public static final String CSV_DT_URIAGE_TAKU_ZEN = "前年宅配実績";
    /** データ部：客数 */
    public static final String CSV_DT_KYAKUSU = "客数";
    /** データ部：前年客数 */
    public static final String CSV_DT_KYAKUSU_ZEN = "前年客数";
    /** データ部：前年比(客数) */
    public static final String CSV_DT_ZEN_HI_KYAKUSU = "前年比(客数)";
    /** データ部：宅配件数 */
    public static final String CSV_DT_KYAKUSU_TAKU = "宅配件数";
    /** データ部：前年宅配件数 */
    public static final String CSV_DT_KYAKUSU_TAKU_ZEN = "前年宅配件数";
    /** データ部：前年比(宅配売上) */
    public static final String CSV_DT_ZEN_HI_TAKU_URI = "前年比(宅配売上)";
    /** データ部：前年比(宅配件数) */
    public static final String CSV_DT_ZEN_HI_TAKU_KYA = "前年比(宅配件数)";
    /** データ部：客単価 */
    public static final String CSV_DT_KYAKU_TANKA = "客単価";
    /** データ部：前年客単価 */
    public static final String CSV_DT_KYAKU_TANKA_ZEN = "前年客単価";
    /** データ部：前年比(客単価) */
    public static final String CSV_DT_ZEN_HI_KYAKU_TANKA = "前年比(客単価)";
    /** データ部：構成比(売上) */
    public static final String CSV_DT_KOSEIHI_URI = "構成比(売上)";
    /** データ部：構成比(件数) */
    public static final String CSV_DT_KOSEIHI_KYA = "構成比(件数)";
    /** データ部：宅配平均売上 */
    public static final String CSV_DT_TAKU_AVE_URI = "宅配平均売上";
    /** データ部：宅配平均客数 */
    public static final String CSV_DT_TAKU_AVE_KYAKUSU = "宅配平均客数";
    /** データ部(補正)：当年宅配実績店数 */
    public static final String CSV_DT_DOGETU_TENPO_COUNT = "当年宅配実績店数";
    /** データ部(補正)：前年比対象宅配実績店数 */
    public static final String CSV_DT_HOSE_TENPO_COUNT = "前年比対象宅配実績店数";
    /** データ部(補正)：当年宅配日数 */
    public static final String CSV_DT_DOGETU_EIGYO_DAYS = "当年宅配日数";
    /** データ部(補正)：前年比対象宅配日数 */
    public static final String CSV_DT_HOSE_EIGYO_DAYS = "前年比対象宅配日数";
    /** データ部(補正)：前年比対象売上 */
    public static final String CSV_DT_HOSE_URIAGE = "前年比対象売上";
    /** データ部(補正)：前年比対象前年売上 */
    public static final String CSV_DT_HOSE_URIAGE_ZEN = "前年比対象前年売上";
    /** データ部(補正)：前年比対象宅配売上 */
    public static final String CSV_DT_HOSE_URIAGE_TAKU = "前年比対象宅配売上";
    /** データ部(補正)：前年比対象前年宅配売上 */
    public static final String CSV_DT_HOSE_URIAGE_TAKU_ZEN = "前年比対象前年宅配売上";
    /** データ部(補正)：前年比対象宅配平均売上 */
    public static final String CSV_DT_HOSE_TAKU_AVE_URI = "前年比対象宅配平均売上";
    /** データ部(補正)：前年比対象客数 */
    public static final String CSV_DT_HOSE_KYAKUSU = "前年比対象客数";
    /** データ部(補正)：前年比対象前年客数 */
    public static final String CSV_DT_HOSE_KYAKUSU_ZEN = "前年比対象前年客数";
    /** データ部(補正)：前年比対象宅配件数 */
    public static final String CSV_DT_HOSE_KYAKUSU_TAKU = "前年比対象宅配件数";
    /** データ部(補正)：前年比対象前年宅配件数 */
    public static final String CSV_DT_HOSE_KYAKUSU_TAKU_ZEN = "前年比対象前年宅配件数";
    /** データ部(補正)：前年比対象宅配平均客数 */
    public static final String CSV_DT_HOSE_TAKU_AVE_KYAKUSU = "前年比対象宅配平均客数";
    /** データ部(補正)：前年比対象客単価 */
    public static final String CSV_DT_HOSE_KYAKU_TANKA = "前年比対象客単価";
    /** データ部(補正)：前年比対象前年客単価 */
    public static final String CSV_DT_HOSE_KYAKU_TANKA_ZEN = "前年比対象前年客単価";

	/** ファイル名：宅配売上日報 */
	public static final String CSV_FILE_NAME = "TAKUHAINIPO.csv";
	/** ファイル名：支部別宅配売上日報 */
	public static final String CSV_FILE_NAME_SIBU = "TAKUHAINIPOSIBU.csv";

 // スタイルシート定数
    /** 行CSS：支部合計（SV担当を選択された場合）:body_sum3 */  

    /** 数字右寄せ、文字色：黒 */
    public static final String TD_NUM = "body_num";
    /** 数字右寄せ、文字色：赤  */
    public static final String TD_NUM_RED = "body_hiritu";
    /** 数字右寄せ、文字色：黒、背景色：緑 */
    public static final String TD_NUM_GREEN = "body_num_n";
    /** 数字右寄せ、文字色：赤、背景色：緑  */
    public static final String TD_NUM_RED_GREEN = "body_hiritu_m";

// 画面表示用ラベル定数
	/** 総合計コード */
   	public static final String TOTAL = "TOTAL";
    /** 総合計コード(SV担当用) */
    public static final String TOTAL_SV = "TOTAL_SV";
   	/** 総合計 */
   	public static final String LABEL_TOTAL_SUM = "総合計";
   	/** 支部合計 */
   	public static final String LABEL_SIBU_SUM = "支部合計";

// Mapオブジェクトキーの定数
   	/** 対象店舗数 */
   	public static final String MAP_TENPO_COUNT = "totalTenpoCount";
   	/** 結果リスト */
   	public static final String MAP_RST_LIST = "resultList";
   	/** 店舗別結果リスト */
   	public static final String MAP_MISE_RST_LIST = "miseResultList";
    
    /** SVコード 20081209追加 */
    public String MAP_SV_CD = "svCd";
    /** SV名称 20081209追加   */
    public String MAP_SV_NAME = "svName";

// その他
    /** 空白文字列 */
    public static final String EMPTY = "";
    /** スペース */
    public static final String SPACE = " ";
    /** ゼロ */
    public static final String ZERO = "0";
    /** 計 */
    public static final String STR_SUM = "計";

}