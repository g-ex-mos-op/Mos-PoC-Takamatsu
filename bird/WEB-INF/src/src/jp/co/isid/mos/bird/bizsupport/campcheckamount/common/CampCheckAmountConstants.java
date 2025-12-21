package jp.co.isid.mos.bird.bizsupport.campcheckamount.common;

/**
 * キャンペーン設定数量確認 共通定数クラス
 *
 * @author xlee
 */
public interface CampCheckAmountConstants {

    /** 有償無償区分：有償 */
	public static final String KBN_YUSHO = "1";

	/** 有償無償区分：無償 */
	public static final String KBN_MUSHO = "0";

    /** 会社コード：モスフードサービス*/
    public static final String COMPANY_CD = "00";

    /** VIEWID定義:キャンペーン設定数量確認画面 */
    public static final String VIEWID_CONDITION = "BBS027V01";

    /** VIEWID定義:店選択画面 */
    public static final String VIEWID_MISESEARCH = "BCO008V01";

    /** VIEWID定義:店選択画面 */
    public static final String VIEWID_ONERSEARCH = "BCO006V01";

    /** 対象条件：店舗 */
    public static final String TAISHO_COND_TENPO = "TENPO";

    /** 対象条件：支部 */
    public static final String TAISHO_COND_SIBU = "SIBU";

    /** 対象条件：オーナー */
    public static final String TAISHO_COND_OWNER = "OWNER";

    /** 対象条件：個店 */
    public static final String TAISHO_COND_TENPO_NM = "個店";

    /** 対象条件：支部 */
    public static final String TAISHO_COND_SIBU_NM = "支部";

    /** 対象条件：オーナー */
    public static final String TAISHO_COND_OWNER_NM = "オーナー";

    /** ボタン名：実行 */
    public static final String BUTTON_NM_INIT = "実行";

    /** ボタン名：再検索 */
    public static final String BUTTON_NM_REEXEC = "再検索";

    /** ボタン名：実行前 */
    public static final String EXEC_INIT_FLG = "0";

    /** ボタン名：実行前 */
    public static final String EXEC_RESERCH_STR_FLG = "1";

    /** ボタン名：実行後 */
    public static final String EXEC_RESERCH_END_FLG = "2";

    /** ユーザタイプ：本部 */
    public static final String USER_TYPE_HONBU = "01";

    /** ユーザタイプ：オーナー */
    public static final String USER_TYPE_ONER = "02";

    /** 対象キャンペーン情報がない場合 */
    public static final String CMP_NM_NODATA = "対象なし";

    /**　タブの区分：スポット数量 */
    public static final String TAB_KBN_SPOT = "1";

    /** タブの区分：設定数量 */
    public static final String TAB_KBN_SET = "2";

    /** タブの区分：両方 */
    public static final String TAB_VIEW_BOTH = "0";

    /** タブの区分：スポット数量のみ */
    public static final String TAB_VIEW_SPOT = "1";

    /** タブの区分：設定数量のみ */
    public static final String TAB_VIEW_SET = "2";

    /** 実行区分：検索（再検索） */
    public static final String PROC_KBN_EXEC = "execute";

    /** 実行区分：タブ切替 */
    public static final String PROC_KBN_CHGTAB = "changeTab";

    /** 実行処理区分 */
    public static final String CALL_EXEC_KBN = "TENPOSEARCH";

    //CSVの定数
	/** ヘッダ部：対象キャンペーン */
	public static final String CSV_HD_CMP = "対象キャンペーン：";
	/** ヘッダ部：対象条件 */
	public static final String CSV_HD_TAISHO_COND= "対象条件：";
	/** ヘッダ部：店コード */
	public static final String CSV_HD_MISE_CD = "店コード";
	/** ヘッダ部：店舗名 */
	public static final String CSV_HD_MISE_NM = "店舗名";
	/** ヘッダ部：商品コード */
	public static final String CSV_HD_SHOHIN_CD = "商品コード";
	/** ヘッダ部：商品名 */
	public static final String CSV_HD_SHOHIN_NM = "商品名";
	/** ヘッダ部：荷姿 */
	public static final String CSV_HD_NISUGATA = "荷姿";
	/** ヘッダ部：入り目 */
	public static final String CSV_HD_IRIME = "入り目";
	/** ヘッダ部：単価 */
	public static final String CSV_HD_TANKA = "単価";
	/** ヘッダ部：無償数量 */
	public static final String CSV_HD_MUSYO = "無償数量";
	/** ヘッダ部：有償設定数量 */
	public static final String CSV_HD_YUSHO = "有償設定数量";
	/** ヘッダ部：スポット有償数量 */
	public static final String CSV_HD_SPOT= "スポット有償数量";
	/** ヘッダ部：合計*/
	public static final String CSV_HD_SUM = "合計";
	/** ヘッダ部：有償金額 */
	public static final String CSV_HD_AMOUNT = "有償金額";
	/** ヘッダ部(設定数量)：支部コード */
	public static final String CSV_HD_SIBU_CD = "支部コード";
	/** ヘッダ部(設定数量)：支部名称 */
	public static final String CSV_HD_SIBU_NM = "支部名称";
	/** ヘッダ部(設定数量)：数量*/
	public static final String CSV_HD_KOTEIAMT = "数量";
	/** ヘッダ部(設定数量)：アイテム名称*/
	public static final String CSV_HD_KOTEI = "アイテム名称";
	/** ファイル名：スポット数量 */
	public static final String CSV_FILE_NAME = "CMPAMT.csv";
	/** ファイル名：設定数量*/
	public static final String CMPSETAMTCSV_FILE_NAME = "CMPAMT_SET.csv";
}