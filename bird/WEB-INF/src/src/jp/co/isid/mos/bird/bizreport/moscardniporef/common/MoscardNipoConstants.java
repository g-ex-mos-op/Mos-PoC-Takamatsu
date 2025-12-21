package jp.co.isid.mos.bird.bizreport.moscardniporef.common;

/**
 * 営業日報 共通定数クラス
 * 
 * @author xyamauchi
 */
public interface MoscardNipoConstants {
//  画面・VIEW ID定数
    /** 画面ID:営業日報画面 */
	public static final String SCREEN_ID = "BBR015";

//  Mapオブジェクトキーの定数
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
    /** MAPオブジェクトキー:前年同月営業日補正売上リスト */   
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
    /** MAPオブジェクトキー:本部発行売上リスト */   
    public static final String MAP_HONBUURIAGE_LIST = "honbuuriage";    
    /** MAPオブジェクトキー:本部発行売上リスト(前年同月) */   
    public static final String MAP_HONBU_HOSEI_URIAGE_LIST = "honbuHoseiUriage";    
    /** MAPオブジェクトキー:結果売上平均リスト */   
    public static final String MAP_RST_URI_AVE = "uriHei";
    /** MAPオブジェクトキー:結果売上リスト(前年同月営業日補正) */    
    public static final String MAP_RST_URI_HOSE = "uriHosei";
    /** MAPオブジェクトキー:結果売上リスト(前年同月営業日補正) */    
    public static final String MAP_RST_URIAGE_HOSE = "uriageHosei";
    /** MAPオブジェクトキー:結果売上平均リスト(前年同月営業日補正) */   
    public static final String MAP_RST_URI_AVE_HOSE = "uriHeiHosei";
    /** MAPオブジェクトキー:結果客数リスト */   
    public static final String MAP_KYAKUSU_LIST = "kyakusu";
    /** MAPオブジェクトキー:結果客数平均リスト */   
    public static final String MAP_RST_KYAKUSU_AVE = "kyakuHei";
    /** MAPオブジェクトキー:結果客数リスト(前年同月営業日補正) */   
    public static final String MAP_RST_KYAKU_HOSE = "kyakuHosei";
    /** MAPオブジェクトキー:結果客数リスト(前年同月営業日補正) */   
    public static final String MAP_RST_KYAKUSU_HOSE = "kyakusuHosei";
    /** MAPオブジェクトキー:結果客数平均リスト(前年同月営業日補正) */   
    public static final String MAP_RST_KYAKUSU_AVE_HOSE = "kyakuHeiHosei";
    /** MAPオブジェクトキー:対象店舗数 */   
    public static final String MAP_TENPO_COUNT = "tenpoCount";
    /** MAPオブジェクトキー:予算対象店舗数 */   
    public static final String MAP_YOSAN_TENPO_COUNT = "yosanTenpoCount";
    /** MAPオブジェクトキー:対象店舗数(オーナー) */   
    public static final String MAP_ONER_TENPO_COUNT = "miseCnt"; 
    // SVコード 20081209追加
    public String SV_CD = "svCd";
    // SV名称 20081209追加
    public String SV_NAME = "svName";

//  メッセージの定数
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
    /** データ部：休業 */
    public static final String CSV_DT_KYUGYO = "クローズ";
    /** データ部：天候 */
    public static final String CSV_DT_TENKOU = "天候";
    /** データ部：前年天候 */
    public static final String CSV_DT_ZEN_TENKOU = "前年天候";
    /** データ部：前年休業 */
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
    /** データ部：発行枚数 */
    public static final String CSV_DT_ISSUE_CNT= "発行枚数";
    /** データ部：前年発行枚数 */
    public static final String CSV_DT_ZEN_ISSUE_CNT= "前年発行枚数";
    /** データ部：前年比対象発行枚数 */ 
    public static final String CSV_DT_ZEN_HIKAKU_ISSUE_CNT= "前年比対象発行枚数";
    /** データ部：前年比対象前年発行枚数 */ 
    public static final String CSV_DT_ZEN_HIKAKU_ZENISSUE_CNT= "前年比対象前年発行枚数";
    /** データ部：前年比(売上) */
    public static final String CSV_DT_ZEN_URIHI_ISSUE_CNT= "前年比(発行枚数)";
    /** データ部：発行枚数平均 */
    public static final String CSV_DT_ISSUE_CNT_AVE= "発行枚数平均";
    /** データ部：前年実績平均 */
    public static final String CSV_DT_ZEN_ISSUE_CNT_AVE= "前年発行枚数平均";
    /** データ部：前年比較対象発行枚数平均 */
    public static final String CSV_DT_ZEN_HIKAKU_ISSUE_CNT_AVE= "前年比較対象発行枚数平均";
    /** データ部：前年比較対象前年発行枚数平均 */
    public static final String CSV_DT_ZEN_HIKAKU_ZENISSUE_CNT_AVE= "前年比較対象前年発行枚数平均";
    /** データ部：前年差(発行枚数) */
    public static final String CSV_DT_ISSUE_CNT_DIFF= "前年差(発行枚数)";
    /** データ部：チャージ金額 */
    public static final String CSV_DT_CHARGEKIN= "チャージ金額";
    /** データ部：前年チャージ金額 */
    public static final String CSV_DT_ZEN_CHARGEKIN= "前年チャージ金額";
    /** データ部：前年比対象チャージ金額 */
    public static final String CSV_DT_ZEN_HIKAKU_CHARGEKIN= "前年比対象チャージ金額";
    /** データ部：前年比対象前年チャージ金額*/
    public static final String CSV_DT_ZEN_HIKAKU_ZENCHARGEKIN= "前年比対象前年チャージ金額";
    /** データ部：売上比（チャージ金額）*/
    public static final String CSV_DT_URIHI_CHARGEKIN= "売上比（チャージ金額）";
    /** データ部：前年比(チャージ金額)*/
    public static final String CSV_DT_ZENHI_CHARGEKIN= "前年比(チャージ金額)";
    /** データ部：チャージ金額平均*/
    public static final String CSV_DT_CHARGEKIN_AVE= "チャージ金額平均";
    /** データ部：前年チャージ金額平均*/
    public static final String CSV_DT_ZEN_CHARGEKIN_AVE= "前年チャージ金額平均";
    /** データ部：前年比較対象チャージ金額平均*/
    public static final String CSV_DT_ZEN_HIKASKU_CHARGEKIN_AVE = "前年比較対象チャージ金額平均";
    /** データ部：前年比較対象前年チャージ金額平均*/
    public static final String CSV_DT_ZEN_HIKASKU_ZENCHARGEKIN_AVE = "前年比較対象前年チャージ金額平均";
    /** データ部：前年差(チャージ金額)*/
    public static final String CSV_DT_CHARGEKIN_DIFF= "前年差(チャージ金額)";
    /** データ部：チャージ件数 */
    public static final String CSV_DT_CHARGEKEN= "チャージ件数";
    /** データ部：前年チャージ件数 */
    public static final String CSV_DT_ZEN_CHARGEKEN= "前年チャージ件数";
    /** データ部：前年比対象チャージ件数 */
    public static final String CSV_DT_ZEN_HIKAKU_CHARGEKEN= "前年比対象チャージ件数";
    /** データ部：前年比対象前年チャージ件数 */
    public static final String CSV_DT_ZEN_HIKAKU_ZENCHARGEKEN= "前年比対象前年チャージ件数";
    /** データ部：売上比(チャージ件数) */
    public static final String CSV_DT_URIHI_CHARGEKEN= "客数比(チャージ件数)";
    /** データ部：前年比(チャージ件数) */
    public static final String CSV_DT_ZENHI_CHARGEKEN= "前年比(チャージ件数)";
    /** データ部：チャージ単価*/
    public static final String CSV_DT_CHARGE_TANKA= "チャージ単価";
    /** データ部：前年チャージ単価*/
    public static final String CSV_DT_ZEN_CHARGE_TANKA= "前年チャージ単価";
    /** データ部：前年比対象チャージ単価*/
    public static final String CSV_DT_ZEN_HIKAKU_CHARGE_TANKA= "前年比対象チャージ単価";
    /** データ部：前年比対象前年チャージ単価*/
    public static final String CSV_DT_ZEN_HIKAKU_ZENCHARGE_TANKA= "前年比対象前年チャージ単価";
    /** データ部：前年比(チャージ単価)*/
    public static final String CSV_DT_ZEN_TANKAHI= "前年比(チャージ単価)";
    /** データ部：チャージ件数平均*/
    public static final String CSV_DT_CHARGEKEN_AVE= "チャージ件数平均";
    /** データ部：前年チャージ件数平均*/
    public static final String CSV_DT_ZEN_CHARGEKEN_AVE= "前年チャージ件数平均";
    /** データ部：前年比較対象チャージ件数平均*/
    public static final String CSV_DT_ZEN_HIKAKU_CHARGEKEN_AVE= "前年比較対象チャージ件数平均";
    /** データ部：前年比較対象前年チャージ件数平均*/
    public static final String CSV_DT_ZEN_HIKAKU_ZENCHARGEKEN_AVE= "前年比較対象前年チャージ件数平均";
    /** データ部：前年差(チャージ件数)*/
    public static final String CSV_DT_CHARGEKEN_DIFF= "前年差(チャージ件数)";
    /** データ部：決済金額 */
    public static final String CSV_DT_KESSAI_KIN= "決済金額";
    /** データ部：前年決済金額 */
    public static final String CSV_DT_ZEN_KESSAI_KIN= "前年決済金額";
    /** データ部：前年比対象決済金額 */
    public static final String CSV_DT_ZEN_HIKAKU_KESSAI_KIN= "前年比対象決済金額";
    /** データ部：前年比対象前年決済金額 */
    public static final String CSV_DT_ZEN_HIKAKU_ZENKESSAI_KIN= "前年比対象前年決済金額";
    /** データ部：売上比(決済金額) */
    public static final String CSV_DT_URIHI_KESSAI_KIN= "売上比(決済金額)";
    /** データ部：前年比(決済金額) */
    public static final String CSV_DT_ZENHI_KESSAI_KIN= "前年比(決済金額)";
    /** データ部：決済金額平均*/
    public static final String CSV_DT_KESSAI_KIN_AVG= "決済金額平均";
    /** データ部：前年決済金額平均*/
    public static final String CSV_DT_ZEN_KESSAI_KIN_AVG= "前年決済金額平均";
    /** データ部：前年比較対象決済金額平均*/
    public static final String CSV_DT_ZEN_HIKAKU_KESSAI_KIN_AVG= "前年比較対象決済金額平均";
    /** データ部：前年比較対象前年決済金額平均*/
    public static final String CSV_DT_ZEN_HIKAKU_ZENKESSAI_KIN_AVG= "前年比較対象前年決済金額平均";
    /** データ部：前年差(決済金額)*/
    public static final String CSV_DT_KESSAI_KIN_DIFF= "前年差(決済金額)";
    /** データ部：決済件数 */
    public static final String CSV_DT_KESSAI_KEN= "決済件数";
    /** データ部：前年決済件数 */
    public static final String CSV_DT_ZEN_KESSAI_KEN= "前年決済件数";
    /** データ部：前年比対象決済件数 */
    public static final String CSV_DT_ZEN_HIKAKU_KESSAI_KEN= "前年比対象決済件数";
    /** データ部：前年比対象前年決済件数 */
    public static final String CSV_DT_ZEN_HIKAKU_ZENKESSAI_KEN= "前年比対象前年決済件数";
    /** データ部：売上比(決済件数) */
    public static final String CSV_DT_URIHI_KESSAI_KEN= "客数比(決済件数)";
    /** データ部：前年比(決済件数) */
    public static final String CSV_DT_ZENHI_KESSAI_KEN= "前年比(決済件数)";
    /** データ部：決済単価*/
    public static final String CSV_DT_KESSAI_TANKA= "決済単価";
    /** データ部：前年決済単価*/
    public static final String CSV_DT_ZEN_KESSAI_TANKA= "前年決済単価";
    /** データ部：前年比対象決済単価*/
    public static final String CSV_DT_ZEN_HIKAKU_KESSAI_TANKA= "前年比対象決済単価";
    /** データ部：前年比対象前年決済単価*/
    public static final String CSV_DT_ZEN_HIKAKU_ZENKESSAI_TANKA= "前年比対象前年決済単価";
    /** データ部：前年差(決済件数)*/
    public static final String CSV_DT_TANKAHI_KESSAI_TANKA= "単価比(決済単価)";
    /** データ部：前年差(決済単価)*/
    public static final String CSV_DT_ZENHI_KESSAI_TANKA= "前年比(決済単価)";
    /** データ部：決済件数平均*/
    public static final String CSV_DT_KESSAI_KEN_AVG= "決済件数平均";
    /** データ部：前年決済件数平均*/
    public static final String CSV_DT_ZEN_KESSAI_KEN_AVG= "前年決済件数平均";
    /** データ部：前年比対象決済件数平均*/
    public static final String CSV_DT_ZEN_HIKAKU_KESSAI_KEN_AVG= "前年比対象決済件数平均";
    /** データ部：前年比対象前年決済件数平均*/
    public static final String CSV_DT_ZEN_HIKAKU_ZENKESSAI_KEN_AVG= "前年比対象前年決済件数平均";
    /** データ部：前年差(決済件数)*/
    public static final String CSV_DT_KESSAI_KEN_DIFF= "前年差(決済件数)";
    /** データ部：SVコード*/
    public static final String CSV_DT_SV_CD = "SVコード";
    /** データ部：SVコード*/
    public static final String CSV_DT_SV_NAME = "SV名称";
    /** データ部：入金取消金額*/
    public static final String CSV_DT_CHARGE_KIN_CANCEL = "入金取消金額";
    /** データ部：入金取消件数*/
    public static final String CSV_DT_CHARGE_KEN_CANCEL = "入金取消件数";
    /** データ部：利用取消金額*/
    public static final String CSV_DT_USE_KIN_CANCEL = "利用取消金額";
    /** データ部：利用取消件数*/
    public static final String CSV_DT_USE_KEN_CANCEL = "利用取消件数";
    /** データ部：発行ボーナスバリュー*/
    public static final String CSV_DT_BONUS_V_ISSUE = "発行ボーナスバリュー";
    /** データ部：利用ボーナスバリュー*/
    public static final String CSV_DT_BONUS_V_USE = "利用ボーナスバリュー";
    /** データ部：発行クーポンバリュー*/
    public static final String CSV_DT_COUPON_V_ISSUE = "発行クーポンバリュー";
    /** データ部：利用クーポンバリュー*/
    public static final String CSV_DT_COUPON_V_USE = "利用クーポンバリュー";
    /** データ部：前受残高*/
    public static final String CSV_DT_ZANDAKA = "前受残高";  
	/** ファイル名：MOSCARD */
	public static final String CSV_FILE_NAME = "MOSCARD.csv";
	/** ファイル名：支部別・MOSCARD */
	public static final String CSV_FILE_NAME_SIBU = "MOSCARDSIBU.csv";
    /** ファイル名：月報・MOSCARD */
    public static final String CSV_FILE_NAME_GEP = "MOSCARDGEP.csv";
    /** ファイル名：SV担当店舗・MOSCARD */
    public static final String CSV_FILE_NAME_SV = "MOSCARDSV.csv";
    

    // スタイルシート定数
    /** 指定無し */
    public static final String NO_CLASS = "";
    /** 行CSS：エリア計 */
    public static final String TR_AREA_SUM = "body_sum1";
    /** 行CSS：事業本部計 */
    public static final String TR_JIGYOU_SUM = "body_sum2";
    /** 行CSS：本部計 */
    public static final String TR_HONBU_SUM = "body_sum3";
    /** 行CSS：業態計 */
    public static final String TR_GYOTAI_SUM = "body_sum4";
    /** 行CSS：総合計 */  
    public static final String TR_TOTAL_SUM = "body_sum5";

    // 合計区分定数
    /** 合計区分：支部 */
    public static final String LINK_SIBU = "SIBU";
    /** 合計区分：エリア計 */
    public static final String LINK_SLAREA = "SLAREA";
    /** 合計区分：事業本部計 */
    public static final String LINK_JIGYOU = "JIGYOU";
    /** 合計区分：本部計 */
    public static final String LINK_HONBU = "HONBU";
    /** 合計区分：業態計 */
    public static final String LINK_GYOTAI = "GYOTAI";
    /** 合計区分：総合計 */
    public static final String LINK_ALL = "ALL";

// その他
    /** 空白文字列 */
    public static final String EMPTY = "";
 
    /** 表示 */
    public static final String DISP = "DISP";

}