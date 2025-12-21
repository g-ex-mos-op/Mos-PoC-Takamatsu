package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common;

import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.common.code.TaishoJoken;

/**
 * 屋号別売上日報 共通定数クラス
 * 
 */
public interface GyotaibetuNipoConstants {

    public static final String VIEW_ID = "BBR005";
    
    public static final String SCREEN_ID= "screenId";
    
    public static final String yago_sibu_view_id ="BBR005V02";
    
    public static final String yago_oner_view_id = "BBR005V04";
    
    public static final String yago_tenpo_ID = "BBR005V03";
    
    public static final String eigyonipo_sibu_ID   = "BBR001V02";
    
    public static final String takuhai_sibu_ID   = "BBR003V02";

    public static final String eigyonipo_tenpo_ID   = "BBR001V03";
    
    public static final String takuhai_tenpo_ID   = "BBR003V03";
    
    public static final String eigyonipo_oner_ID   = "BBR001V04";
    
    public static final String takuhai_oner_ID   = "BBR003V04";
    
    public static final String portal_ACTION_ID = "BSI001V02";
    
    /** VIEWID：初期(条件)画面 */
    public static final String yago_cond_view_id = "BBR005V01";
    
    /** VIEWID：SV選択 */
    public static final String VIEW_ID_SVSEARCH   = "BCO011V01";
    
    
    // オーナーコード
    public String ONER_CD = "onerCd";
        
    
    // 管理会社企業コード
    public String COMPANY_CD = "companyCd";
    // ユーザーID
    public String USER_ID = "userId";
    // 店舗種別
    public String TENSHU = "tenpoShu";
    // 前年データ種別
    public String DATASHU = "dataShu";
    // 対象期間
    public String TAISHO_KIKAN = "taishoKikan";
    // 期間FROM
    public String KIKAN_FROM  = "kikanFrom";
    // 期間TO
    public String KIKAN_TO = "kikanTo";
    // 支部制限
    public String LIMIT_FLG = "limitFlg";
    // エリア大
    public String AREA_DAI_FLG = "areaDaiFlg";
    // 対象店舗
    public String TAISHO_TENPO = "taishoTenpo";
    // 支部コード
    public String SIBU_CD = "sibuCd";
    // 区分
    public String KBN_CD = "kbnCd";
    // タイプコード
    public String USER_TYPE_CD = "userTypeCd";
    // 集計区分
    public String SHUKEI_KBN = "ShukeiKbn";
    // システム日付
    public String SYS_DATE = "sysDate";
    // SVコード 20081209追加
    public String SV_CD = "svCd";
    // SV名称 20081209追加
    public String SV_NAME = "svName";
    
        
    //屋号コード
    public static final String YAGO_CD = "yagoCd";
    //屋号タイプ
    public static final String YAGO_TYPE = "yagoType";
    //エリア区分
    public static final String KUBUN = "kubun";
    
    
    // 店区分
    public static final String mise = TaishoJoken.CODE_MISE;
    // ブロック区分
    public static final String block = "BLOCK";

    
    // 店区分
    public static final String action_link = "link";
    // ブロック区分
    public static final String action_button = "button";
    
    
    // 屋号タイプ　親
    public static final String OYA = "OYA";
    // 屋号タイプ　詳細
    public static final String DTL = "DTL";
    // 全対象区分
    public static final String ALL = "ALL";
    
    // 合計１
    public static final String rClassSum1 = "body_field";
    // 合計２
    public static final String rClassSum2 = NipoRefConstants.CSS_TR_CLASS_SLAREA;
    // 合計３
    public static final String rClassSum3 = NipoRefConstants.CSS_TR_CLASS_JIGYOU;
    // 合計４
    public static final String rClassSum4 = NipoRefConstants.CSS_TR_CLASS_HONBU;
    // 合計５
    public static final String rClassSum5 = NipoRefConstants.CSS_TR_CLASS_TOTAL;
    
    
    // 支部区分
    public static final String sibu = TaishoJoken.CODE_SIBU;
    // エリア区分
    public static final String area = TaishoJoken.CODE_SLAREA;
    // 事業部区分
    public static final String jigyo = TaishoJoken.CODE_JIGYOU;
    // 統括本部区分
    public static final String toukatu = TaishoJoken.CODE_HONBU;
    // 総合計区分
    public static final String total = TaishoJoken.CODE_ALL;
    // 総合計区分(SV指定で検索時用)
    public static final String sv_total = "SV_TOTAL";
    /** ヘッダ部：支部別 */
    public static final String HD_SIBU = "支部別：";
    /** ヘッダ部：ｴﾘｱ別計 */
    public static final String HD_SLAREA = "エリア別：";
    /** ヘッダ部：事業部別 */
    public static final String HD_JIGYOU = "事業部別：";
    /** ヘッダ部：統括本部別 */
    public static final String HD_HONBU = "統括本部別：";
    /** ヘッダ部：総合計 */
    public static final String HD_ALL = "総合計";
    /** ヘッダ部：担当SV */
    public static final String HD_SV = "担当SV：";
    
    
    // 数字右寄せ(合計行専用)
    public static final String bodyNumClass = "body_num";
    // 比率100以下またはマイナス(合計行専用) 
    public static final String bodyHirituClass = "body_hiritu";
    // 比率100以下またはマイナス(通常行専用) 
    public static final String bodyHirituClassM = "body_hiritu_m";
    // 比率100以上またはプラス(通常行専用)
    public static final String bodyNumClassN = "body_num_n";
    
    
//  メッセージの定数
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
	/** ヘッダ部：まで */
	public static final String CSV_HD_TAB = "タブ名：";
    /** ヘッダ部：SV指定 */
    public static final String CSV_HD_SV = "SV指定";
	
//　　タブ
	/** タブNo：0 */
	public static final String TAB_NO_URIAGE = "0";
	/** タブNo：1 */
	public static final String TAB_NO_KYAKUSU = "1";
	/** タブNo：2 */
	public static final String TAB_NO_DTL_URIAGE = "2";
	/** タブNo：3 */
	public static final String TAB_NO_DTL_KYAKUSU = "3";
	/** タブ名：屋号別(売上) */
	public static final String TAB_TITLE = "屋号別";
	/** タブ名：詳細別(売上) */
	public static final String TAB_TITLE_DTL = "詳細別";
	
//	 その他
    /** 空白文字列 */
    public static final String EMPTY = "";
    /** スラッシュ文字列 */
    public static final String SPACE = " ";
    /** 合計 */
    public static final String MSG_TOTAL = "計";
    /** モス */
    public static final String MOS = "00";
}