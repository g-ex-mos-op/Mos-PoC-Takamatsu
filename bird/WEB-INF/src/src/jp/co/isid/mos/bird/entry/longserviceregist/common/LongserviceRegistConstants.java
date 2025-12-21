package jp.co.isid.mos.bird.entry.longserviceregist.common;

/**
 * 汎用研修マスタ登録 定数クラス
 * @author xnkusama
 *
 */
public class LongserviceRegistConstants {
	
//	 画面・VIEW ID定数
    /** 画面ID:全国大会マスタ登録画面 */
	public static final String SCREEN_ID = "BEN017";
	/** VIEW_ID:全国大会マスタ登録 条件画面 */
    public static final String VIEW_ID_SELECT = "BEN017V01";
    /** VIEW_ID:全国大会マスタ登録 編集画面 */
    public static final String VIEW_ID_EDIT = "BEN017V02";
    /** VIEW_ID:全国大会マスタ登録 確認画面 */
    public static final String VIEW_ID_CONFIRM = "BEN017V03";
    /** VIEW_ID：操作エラー画面 */
    public static final String VIEW_ID_ERR = "operation.Err";

//　Map定数
    /** エントリーコード */
    public static final String ENTRY_Cd = "EntryCd";
    /** エントリー年 */
    public static final String ENTRY_YEAR = "EntryYear";
    /** エントリー回 */
    public static final String ENTRY_KAI = "EntryKai";
    /** タイトル */
    public static final String ENTRY_TITLE = "EntryTitle";
    /** 開催場所 */
    public static final String ENTRY_PLACE = "EntryPlace";
    /** 申込定員 */
    public static final String NUMBER_LIMIT = "NumberLimit";
    /** 会場定員 */
    public static final String PLACE_LIMIT = "PlaceLimit";
    /** 備考 */
    public static final String NOTE = "Note";
    /** 予備フラグ１ */
    public static final String SPARE_FLG1 = "SpareFlg1";
    /** 予備フラグ２ */
    public static final String SPARE_FLG2 = "SpareFlg2";
    /** 削除フラグ */
    public static final String SAKUJO_FLG = "SakujoFlg";
    /** ユーザタイプコード */
    public static final String USERTYPE_CD = "UsertypeCd";
    /** 日付区分 */
    public static final String DAY_KBN = "DayKbn";
    /** 開始日 */
    public static final String FROM_DT = "FromDt";
    /** 終了日 */
    public static final String TO_DT = "ToDt";
    /** 登録ユーザー */
    public static final String FIRST_USER = "FirstUser";
    /** 登録プログラム */
    public static final String FIRST_PGM = "FirstPgm";
    /** 登録時タイムスタンプ */
    public static final String FIRST_TMSP = "FirstTmsp";
    /** 修正ユーザー */
    public static final String LAST_USER = "LastUser";
    /** 修正プログラム */
    public static final String LAST_PGM = "LastPgm";
    /** 修正時タイムスタンプ */
    public static final String LAST_TMSP = "LastTmsp";
    
//  ユーザータイプコードの定数 
    /** ユーザータイプコード:本部(01) */
    public static final String HONBU = "01";
    /** ユーザータイプコード:オーナー(02) */
    public static final String ONER = "02";

//  メッセージの定数
    /** メッセージ：削除不可 */
    public static final String MSG_DELETE = "申込が開始されているため、削除";
    /** メッセージ：システム日付 */
    public static final String MSG_SYS_DATE = "システム日付";
    /** メッセージ：排他 */
    public static final String MSG_EXCLUSION_CHK = "このデータは既に更新されている";   
    /** メッセージ：削除*/
    public static final String MSG_UPDATE = "更新";
    
//  編集モードの定数 
    /** 編集モード:初期 */
    public static final int EDIT_MODE_INIT = 0;
    /** 編集モード:新規登録 */
    public static final int EDIT_MODE_INSERT = 1;
    /** 編集モード:編集 */
    public static final int EDIT_MODE_UPDATE = 2;
    /** 編集モード:削除 */
    public static final int EDIT_MODE_DELETE = 3;
    /** 編集モード:戻る */
    public static final int EDIT_MODE_RETURN = 4;

//  対象処理の定数
    /** 対象処理：新規登録*/
    public static final String PROCESS_INSERT = "新規登録";
    /** 対象処理：編集*/
    public static final String PROCESS_UPDATE = "編集";
    /** 対象処理：削除*/
    public static final String PROCESS_DELETE = "削除";
    /** エントリーコード:永年勤続 */
    public static final String ENTRY_CD_LONGSERVICE = "20";
    /** 削除フラグ:ON */
    public static final String SAKUJO_FLG_ON = "1";
    /** 削除フラグ:OFF */
    public static final String SAKUJO_FLG_OFF = "0";
    
// 日付区分
    /** 01：開催 */
    public static  final String DAY_KBN_KAISAI      = "01";
    /** 02：その他 */
    public static  final String DAY_KBN_SONOTA      = "02";
    /** 03：表示 */
    public static  final String DAY_KBN_HYOJI       = "03";
    /** 04：登録 */
    public static  final String DAY_KBN_TOROKU      = "04";
    /** 05：結果表示 */
    public static  final String DAY_KBN_KEKKA_HYOJI = "05";
    /** 06：結果登録 */
    public static  final String DAY_KBN_KEKKA_TOROKU = "06";
    
//　フォーカスするエリア
    /** タイトル */ 			
    public static  final String FOCUS_TITLE 			 = "entryTitle";
    /** オーナー：表示開始日 */ 
    public static  final String FOCUS_ONER_DISP_FROM   = "onerDispFrom";
    /** オーナー：表示終了日 */ 
    public static  final String FOCUS_ONER_DISP_TO     = "onerDispTo";
    /** オーナー：申込開始日 */ 
    public static  final String FOCUS_ONER_ENTRY_FROM  = "onerEntryFrom";
    /** オーナー：申込終了日 */ 
    public static  final String FOCUS_ONER_ENTRY_TO    = "onerEntryTo";
    /** 本部：表示開始日 */ 	
    public static  final String FOCUS_HONBU_DISP_FROM  = "honbuDispFrom";
    /** 本部：表示終了日 */ 	
    public static  final String FOCUS_HONBU_DISP_TO    = "honbuDispTo";
    /** 本部：申込開始日 */ 	
    public static  final String FOCUS_HONBU_ENTRY_FROM = "honbuEntryFrom";
    /** 本部：申込終了日 */ 	
    public static  final String FOCUS_HONBU_ENTRY_TO   = "honbuEntryTo";
    
//  その他
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
}
