package jp.co.isid.mos.bird.bizreport.netorderniporef.common;

/**
 * ネット注文 共通定数クラス
 */
public interface NetorderNipoConstants {
	//画面・VIEW ID定数
    /** 画面ID:注文日報画面 */
	public static final String SCREEN_ID = "BBR017";

	//Mapオブジェクトキーの定数
    /** MAPオブジェクトキー:管理会社企業コード */
    public static final String MAP_COMPANY_CD = "companyCd";
    /** MAPオブジェクトキー:ユーザーID */
    public static final String MAP_USER_ID = "userId";
    /** MAPオブジェクトキー:店舗種別 */
    public static final String MAP_TENSHU = "tenpoShu";
    /** MAPオブジェクトキー:前年データ種別 */
    public static final String MAP_DATASHU = "dataShu";
    /** MAPオブジェクトキー:対象期間 */
    public static final String MAP_TAISHO_KIKAN = "taishoKikan";
    /** MAPオブジェクトキー:対象期間FROM */
    public static final String MAP_KIKAN_FROM  = "kikanFrom";
    /** MAPオブジェクトキー:対象期間TO */
    public static final String MAP_KIKAN_TO = "kikanTo";
    /** MAPオブジェクトキー:制限区分 */
    public static final String MAP_LIMIT_FLG = "limitFlg";
    /** MAPオブジェクトキー:エリア大 */
    public static final String MAP_AREA_DAI_FLG = "areaDaiFlg";
    /** MAPオブジェクトキー:対象店舗 */
    public static final String MAP_TAISHO_TENPO = "taishoTenpo";
    /** MAPオブジェクトキー:オーナーコード */
    public static final String MAP_ONER_CD = "onerCd";
    /** MAPオブジェクトキー:ユーザタイプコード */
    public static final String MAP_USER_TYPE_CD = "userTypeCd";
    /** MAPオブジェクトキー:支部コード */
    public static final String MAP_SIBU_CD = "sibuCd";
    /** MAPオブジェクトキー:区分 */
    public static final String MAP_KBN_CD = "kbnCd";    
    /** MAPオブジェクトキー:前年同月ネット注文日補正売上リスト */   
    public static final String MAP_HOSE_URI_LIST = "hoseiyou";
    /** MAPオブジェクトキー:支部情報リスト */   
    public static final String MAP_SIBU_LIST = "sibu";
    /** MAPオブジェクトキー:予算リスト */   
    public static final String MAP_YOSAN_LIST = "yosan";
    /** MAPオブジェクトキー:集計区分 */   
    public static final String MAP_SHUKEI_KBN = "areadai";
    /** MAPオブジェクトキー:パラメータ */   
    public static final String MAP_PARAMS = "param";
    /** MAPオブジェクトキー:結果リスト */   
    public static final String MAP_RESULT = "result";
    /** MAPオブジェクトキー:売上リスト */   
    public static final String MAP_URIAGE_LIST = "uriage";
    /** MAPオブジェクトキー:結果売上平均リスト */   
    public static final String MAP_RST_URI_AVE = "uriHei";
    /** MAPオブジェクトキー:結果売上リスト(前年同月ネット注文日補正) */    
    public static final String MAP_RST_URI_HOSE = "uriHosei";
    /** MAPオブジェクトキー:結果売上リスト(前年同月ネット注文日補正) */    
    public static final String MAP_RST_URIAGE_HOSE = "uriageHosei";
    /** MAPオブジェクトキー:結果売上平均リスト(前年同月ネット注文日補正) */   
    public static final String MAP_RST_URI_AVE_HOSE = "uriHeiHosei";
    /** MAPオブジェクトキー:結果客数リスト */   
    public static final String MAP_KYAKUSU_LIST = "kyakusu";
    /** MAPオブジェクトキー:結果客数平均リスト */   
    public static final String MAP_RST_KYAKUSU_AVE = "kyakuHei";
    /** MAPオブジェクトキー:結果客数リスト(前年同月ネット注文日補正) */   
    public static final String MAP_RST_KYAKU_HOSE = "kyakuHosei";
    /** MAPオブジェクトキー:結果客数リスト(前年同月ネット注文日補正) */   
    public static final String MAP_RST_KYAKUSU_HOSE = "kyakusuHosei";
    /** MAPオブジェクトキー:結果客数平均リスト(前年同月ネット注文日補正) */   
    public static final String MAP_RST_KYAKUSU_AVE_HOSE = "kyakuHeiHosei";
    /** MAPオブジェクトキー:対象店舗数 */   
    public static final String MAP_TENPO_COUNT = "tenpoCount";
    /** MAPオブジェクトキー:予算対象店舗数 */   
    public static final String MAP_YOSAN_TENPO_COUNT = "yosanTenpoCount";
    /** MAPオブジェクトキー:対象店舗数(オーナー) */   
    public static final String MAP_ONER_TENPO_COUNT = "miseCnt"; 
    /** MAPオブジェクトキー:小数桁数 */   
    public static final String MAP_DIGIT_CNT = "digitCnt"; 
    //SVコード 
    public String SV_CD = "svCd";
    //SV名称
    public String SV_NAME = "svName";

    //メッセージの定数
    /** メッセージ：検索条件 */
    public static final String MSG_CONDITION = "検索条件";
    /** メッセージ：ユーザータイプ*/
    public static final String MSG_USER_TYPE = "ユーザータイプ";
    /** メッセージ：ユーザーID */
    public static final String MSG_USER_ID = "ユーザーID";
    /** メッセージ：オーナーコード */
    public static final String MSG_ONER_CD = "オーナーコード";
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
    /** メッセージ：支部コード */
    public static final String MSG_SIBU_CD = "支部コード";
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
    /** ヘッダ部：業態別 */
    public static final String CSV_HD_GYOTAI = "業態別：";
    /** ヘッダ部：総合計 */
    public static final String CSV_HD_ALL = "総合計";
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
    /** データ部：店種 */
    public static final String CSV_DT_TENSHU = "店種";
    /** データ部：クローズ */
    public static final String CSV_DT_KYUGYO = "クローズ";
    /** データ部：天候 */
    public static final String CSV_DT_TENKOU = "天候";
    /** データ部：前年天候 */
    public static final String CSV_DT_ZEN_TENKOU = "前年天候";
    /** データ部：前年クローズ */
    public static final String CSV_DT_ZEN_KYUGYO = "前年クローズ";
    /** データ部：営業日数 */
    public static final String CSV_DT_EIGYO_DAYS = "営業日数";
    /** データ部：前年営業日数 */
    public static final String CSV_DT_ZEN_EIGYO_DAYS = "前年営業日数";
    /** データ部：店舗数 */
    public static final String CSV_DT_TENPO_COUNT = "店舗数";
    /** データ部：当年店数 */
    public static final String CSV_DT_TOUNEN_TENPO_COUNT = "当年店数";
    /** データ部：前年店数 */
    public static final String CSV_DT_ZENNEN_TENPO_COUNT = "前年店数";
    /** データ部：前年比対象店数 */
    public static final String CSV_DT_HOSEI_TENPO_COUNT = "前年比対象店数";
    /** データ部：前年比対象前年店数 */
    public static final String CSV_DT_HOSEI_ZEN_TENPO_COUNT = "前年比対象前年店数";
    /** データ部：売上 */
    public static final String CSV_DT_URIAGE = "売上";
    /** データ部：予算 */
    public static final String CSV_DT_YOSAN = "予算";
    /** データ部：達成率 */
    public static final String CSV_DT_TASSEI = "達成率";
    /** データ部：前年実績 */
    public static final String CSV_DT_URIAGE_ZEN = "前年実績";
    /** データ部：前年比対象売上 */
    public static final String CSV_DT_HOSEI_URIAGE = "前年比対象売上";
    /** データ部：前年比対象前年売上 */
    public static final String CSV_DT_HOSEI_URIAGE_ZEN = "前年比対象前年売上";
    /** データ部：前年比(売上) */
    public static final String CSV_DT_ZEN_HI_URIAGE = "前年比(売上)";
    /** データ部：前年差(売上) */
    public static final String CSV_DT_ZEN_DIFF_URIAGE = "前年差(売上)";
    /** データ部：売上平均 */
    public static final String CSV_DT_URIAGE_AVE = "売上平均";
    /** データ部：前年実績平均 */
    public static final String CSV_DT_ZEN_URIAGE_AVE = "前年実績平均";
    /** データ部：前年比対象売上平均 */
    public static final String CSV_DT_HOSEI_URIAGE_AVE = "前年比対象売上平均";
    /** データ部：前年比対象前年売上平均 */
    public static final String CSV_DT_HOSEI_ZEN_URIAGE_AVE = "前年比対象前年売上平均";
    /** データ部：予算平均 */
    public static final String CSV_DT_YOSAN_AVE = "予算平均";
    /** データ部：予算差 */
    public static final String CSV_DT_YOSAN_DIFF = "予算差";
    /** データ部：客数 */
    public static final String CSV_DT_KYAKUSU = "客数";
    /** データ部：前年客数 */
    public static final String CSV_DT_KYAKUSU_ZEN = "前年客数";
    /** データ部：前年比対象客数 */
    public static final String CSV_DT_HOSEI_KYAKUSU = "前年比対象客数";
    /** データ部：前年比対象前年客数 */
    public static final String CSV_DT_HOSEI_KYAKUSU_ZEN = "前年比対象前年客数";
    /** データ部：前年比(客数) */
    public static final String CSV_DT_ZEN_HI_KYAKUSU = "前年比(客数)";
    /** データ部：客数平均 */
    public static final String CSV_DT_KYAKUSU_AVE = "客数平均";
    /** データ部：前年客数平均 */
    public static final String CSV_DT_ZEN_KYAKUSU_AVE = "前年客数平均";
    /** データ部：前年比対象客数平均 */
    public static final String CSV_DT_HOSEI_KYAKUSU_AVE = "前年比対象客数平均";
    /** データ部：前年比対象前年客数平均 */
    public static final String CSV_DT_HOSE_ZEN_KYAKUSU_AVE = "前年比対象前年客数平均";
    /** データ部：前年差(客数) */
    public static final String CSV_DT_ZEN_DIFF_KYAKUSU = "前年差(客数)";
    /** データ部：客単価 */
    public static final String CSV_DT_KYAKU_TANKA = "客単価";
    /** データ部：前年客単価 */
    public static final String CSV_DT_KYAKU_TANKA_ZEN = "前年客単価";
    /** データ部：前年比対象客単価 */
    public static final String CSV_DT_HOSEI_KYAKU_TANKA = "前年比対象客単価";
    /** データ部：前年比対象前年客単価 */
    public static final String CSV_DT_HOSEI_KYAKU_TANKA_ZEN = "前年比対象前年客単価";
    /** データ部：前年比(客単価) */
    public static final String CSV_DT_ZEN_HI_KYAKU_TANKA = "前年比(客単価)";
    
    /** データ部：ネット注文日数 */
    public static final String CSV_DT_EIGYO_DAYS_NSUM = "ネット注文日数";
    /** データ部：ネット注文実績店舗数 */
    public static final String CSV_DT_MISE_KBN_NSUM = "ネット注文実績店舗数";
    /** データ部：ネット注文売上 */
    public static final String CSV_DT_URIAGE_NSUM = "ネット注文売上";
    /** データ部：前年ネット注文実績 */
    public static final String CSV_DT_ZEN_URIAGE_NSUM = "前年ネット注文実績";
    /** データ部：前年比対象ネット注文売上 */
    public static final String CSV_DT_HOSEI_URIAGE_NSUM = "前年比対象ネット注文売上";
    /** データ部：前年比対象前年ネット注文実績 */
    public static final String CSV_DT_ZEN_HOSEI_URIAGE_NSUM = "前年比対象前年ネット注文実績";
    /** データ部：前年比(ネット注文売上) */
    public static final String CSV_DT_ZEN_HI_URIAGE_NSUM = "前年比(ネット注文売上)";
    /** データ部：構成比(売上) */
    public static final String CSV_DT_KOSEI_HI_URIAGE_NSUM = "構成比(売上)";
    /** データ部：ネット注文売上平均 */
    public static final String CSV_DT_URIAGE_NSUM_AVE = "ネット注文売上平均";
    /** データ部：ネット注文件数 */
    public static final String CSV_DT_KYAKUSU_NSUM = "ネット注文件数";
    /** データ部：前年ネット注文件数 */
    public static final String CSV_DT_ZEN_KYAKUSU_NSUM = "前年ネット注文件数";
    /** データ部：前年比対象ネット注文件数 */
    public static final String CSV_DT_HOSEI_KYAKUSU_NSUM = "前年比対象ネット注文件数";
    /** データ部：前年比対象前年ネット注文件数 */
    public static final String CSV_DT_ZEN_HOSEI_KYAKUSU_NSUM = "前年比対象前年ネット注文件数";
    /** データ部：前年比(ネット注文件数) */
    public static final String CSV_DT_ZEN_HI_KYAKUSU_NSUM = "前年比(ネット注文件数)";
    /** データ部：構成比(件数) */
    public static final String CSV_DT_KOSEI_HI_KYAKUSU_NSUM = "構成比(件数)";
    /** データ部：ネット注文件数平均 */
    public static final String CSV_DT_KYAKUSU_NSUM_AVE = "ネット注文件数平均";
//    /** データ部：客単価（ネット注文） */
//    public static final String CSV_DT_KYAKU_TANKA_NSUM = "客単価（ネット注文）";
//    /** データ部：前年客単価（ネット注文） */
//    public static final String CSV_DT_KYAKU_TANKA_NSUM_ZEN = "前年客単価（ネット注文）";
//    /** データ部：前年比対象客単価（ネット注文） */
//    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NSUM = "前年比対象客単価（ネット注文）";
//    /** データ部：前年比対象前年客単価（ネット注文） */
//    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NSUM_ZEN = "前年比対象前年客単価（ネット注文）";
    /** データ部：客単価(ネット注文) */
    public static final String CSV_DT_KYAKU_TANKA_NSUM = "客単価(ネット注文)";
    /** データ部：前年客単価(ネット注文)*/
    public static final String CSV_DT_KYAKU_TANKA_NSUM_ZEN = "前年客単価(ネット注文)";
    /** データ部：前年比対象客単価(ネット注文)*/
    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NSUM = "前年比対象客単価(ネット注文)";
    /** データ部：前年比対象前年客単価(ネット注文)*/
    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NSUM_ZEN = "前年比対象前年客単価(ネット注文)";
    /** データ部：前年比(客単価) */
    public static final String CSV_DT_ZEN_HI_KYAKU_TANKA_NSUM = "前年比(客単価)";
    
    /** データ部：ネットテイク日数 */
    public static final String CSV_DT_EIGYO_DAYS_NTAKE = "ネットテイク日数";
    /** データ部：ネットテイク実績店舗数 */
    public static final String CSV_DT_MISE_KBN_NTAKE = "ネットテイク実績店舗数";
    /** データ部：ネットテイク売上 */
    public static final String CSV_DT_URIAGE_NTAKE = "ネットテイク売上";
    /** データ部：前年ネットテイク実績 */
    public static final String CSV_DT_ZEN_URIAGE_NTAKE = "前年ネットテイク実績";
    /** データ部：前年比対象ネットテイク売上 */
    public static final String CSV_DT_HOSEI_URIAGE_NTAKE = "前年比対象ネットテイク売上";
    /** データ部：前年比対象前年ネットテイク実績 */
    public static final String CSV_DT_ZEN_HOSEI_URIAGE_NTAKE = "前年比対象前年ネットテイク実績";
    /** データ部：前年比(ネットテイク売上) */
    public static final String CSV_DT_ZEN_HI_URIAGE_NTAKE = "前年比(ネットテイク売上)";
    /** データ部：構成比(売上) */
    public static final String CSV_DT_KOSEI_HI_URIAGE_NTAKE = "構成比(売上)";
    /** データ部：ネットテイク売上平均 */
    public static final String CSV_DT_URIAGE_NTAKE_AVE = "ネットテイク売上平均";
    /** データ部：ネットテイク件数 */
    public static final String CSV_DT_KYAKUSU_NTAKE = "ネットテイク件数";
    /** データ部：前年ネットテイク件数 */
    public static final String CSV_DT_ZEN_KYAKUSU_NTAKE = "前年ネットテイク件数";
    /** データ部：前年比対象ネットテイク件数 */
    public static final String CSV_DT_HOSEI_KYAKUSU_NTAKE = "前年比対象ネットテイク件数";
    /** データ部：前年比対象前年ネットテイク件数 */
    public static final String CSV_DT_ZEN_HOSEI_KYAKUSU_NTAKE = "前年比対象前年ネットテイク件数";
    /** データ部：前年比(ネットテイク件数) */
    public static final String CSV_DT_ZEN_HI_KYAKUSU_NTAKE = "前年比(ネットテイク件数)";
    /** データ部：構成比(件数) */
    public static final String CSV_DT_KOSEI_HI_KYAKUSU_NTAKE = "構成比(件数)";
    /** データ部：ネットテイク件数平均 */
    public static final String CSV_DT_KYAKUSU_NTAKE_AVE = "ネットテイク件数平均";
//    /** データ部：客単価（ネットテイク） */
//    public static final String CSV_DT_KYAKU_TANKA_NTAKE = "客単価（ネットテイク）";
//    /** データ部：前年客単価（ネットテイク） */
//    public static final String CSV_DT_KYAKU_TANKA_NTAKE_ZEN = "前年客単価（ネットテイク）";
//    /** データ部：前年比対象客単価（ネットテイク） */
//    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NTAKE = "前年比対象客単価（ネットテイク）";
//    /** データ部：前年比対象前年客単価（ネットテイク） */
//    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NTAKE_ZEN = "前年比対象前年客単価（ネットテイク）";
    /** データ部：客単価(ネットテイク) */
    public static final String CSV_DT_KYAKU_TANKA_NTAKE = "客単価(ネットテイク)";
    /** データ部：前年客単価(ネットテイク) */
    public static final String CSV_DT_KYAKU_TANKA_NTAKE_ZEN = "前年客単価(ネットテイク)";
    /** データ部：前年比対象客単価(ネットテイク) */
    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NTAKE = "前年比対象客単価(ネットテイク)";
    /** データ部：前年比対象前年客単価(ネットテイク) */
    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NTAKE_ZEN = "前年比対象前年客単価(ネットテイク)";
    /** データ部：前年比(客単価) */
    public static final String CSV_DT_ZEN_HI_KYAKU_TANKA_NTAKE = "前年比(客単価)";
    
    /** データ部：ネット宅配日数 */
    public static final String CSV_DT_EIGYO_DAYS_NTAKU = "ネット宅配日数";
    /** データ部：ネット宅配実績店舗数 */
    public static final String CSV_DT_MISE_KBN_NTAKU = "ネット宅配実績店舗数";
    /** データ部：ネット宅配売上 */
    public static final String CSV_DT_URIAGE_NTAKU = "ネット宅配売上";
    /** データ部：前年ネット宅配実績 */
    public static final String CSV_DT_ZEN_URIAGE_NTAKU = "前年ネット宅配実績";
    /** データ部：前年比対象ネット宅配売上 */
    public static final String CSV_DT_HOSEI_URIAGE_NTAKU = "前年比対象ネット宅配売上";
    /** データ部：前年比対象前年ネット宅配実績 */
    public static final String CSV_DT_ZEN_HOSEI_URIAGE_NTAKU = "前年比対象前年ネット宅配実績";
    /** データ部：前年比(ネット宅配売上) */
    public static final String CSV_DT_ZEN_HI_URIAGE_NTAKU = "前年比(ネット宅配売上)";
    /** データ部：構成比(売上) */
    public static final String CSV_DT_KOSEI_HI_URIAGE_NTAKU = "構成比(売上)";
    /** データ部：ネット宅配売上平均 */
    public static final String CSV_DT_URIAGE_NTAKU_AVE = "ネット宅配売上平均";
    /** データ部：ネット宅配件数 */
    public static final String CSV_DT_KYAKUSU_NTAKU = "ネット宅配件数";
    /** データ部：前年ネット宅配件数 */
    public static final String CSV_DT_ZEN_KYAKUSU_NTAKU = "前年ネット宅配件数";
    /** データ部：前年比対象ネット宅配件数 */
    public static final String CSV_DT_HOSEI_KYAKUSU_NTAKU = "前年比対象ネット宅配件数";
    /** データ部：前年比対象前年ネット宅配件数 */
    public static final String CSV_DT_ZEN_HOSEI_KYAKUSU_NTAKU = "前年比対象前年ネット宅配件数";
    /** データ部：前年比(ネット宅配件数) */
    public static final String CSV_DT_ZEN_HI_KYAKUSU_NTAKU = "前年比(ネット宅配件数)";
    /** データ部：構成比(件数) */
    public static final String CSV_DT_KOSEI_HI_KYAKUSU_NTAKU = "構成比(件数)";
    /** データ部：ネット宅配件数平均 */
    public static final String CSV_DT_KYAKUSU_NTAKU_AVE = "ネット宅配件数平均";
//    /** データ部：客単価（ネット宅配） */
//    public static final String CSV_DT_KYAKU_TANKA_NTAKU = "客単価（ネット宅配）";
//    /** データ部：前年客単価（ネット宅配） */
//    public static final String CSV_DT_KYAKU_TANKA_NTAKU_ZEN = "前年客単価（ネット宅配）";
//    /** データ部：前年比対象客単価（ネット宅配） */
//    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NTAKU = "前年比対象客単価（ネット宅配）";
//    /** データ部：前年比対象前年客単価（ネット宅配） */
//    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NTAKU_ZEN = "前年比対象前年客単価（ネット宅配）";
    /** データ部：客単価(ネット宅配) */
    public static final String CSV_DT_KYAKU_TANKA_NTAKU = "客単価(ネット宅配)";
    /** データ部：前年客単価(ネット宅配) */
    public static final String CSV_DT_KYAKU_TANKA_NTAKU_ZEN = "前年客単価(ネット宅配)";
    /** データ部：前年比対象客単価(ネット宅配) */
    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NTAKU = "前年比対象客単価(ネット宅配)";
    /** データ部：前年比対象前年客単価(ネット宅配) */
    public static final String CSV_DT_HOSEI_KYAKU_TANKA_NTAKU_ZEN = "前年比対象前年客単価(ネット宅配)";
    /** データ部：前年比(客単価) */
    public static final String CSV_DT_ZEN_HI_KYAKU_TANKA_NTAKU = "前年比(客単価)";

	/** ファイル名：ネット注文 */
	public static final String CSV_FILE_NAME = "NETORDERNIPO.csv";
	/** ファイル名：支部別・ネット注文 */
	public static final String CSV_FILE_NAME_SIBU = "NETORDERNIPOSIBU.csv";


// その他
    /** 空白文字列 */
    public static final String EMPTY = "";
 
    /** 表示 */
    public static final String DISP = "DISP";
    
    /** CSV */
    public static final String CSV = "CSV";

}