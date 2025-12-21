package jp.co.isid.mos.bird.entry.manageregist.common;

/**
 * 全国店長勉強会マスタ登録 共通定数クラス
 * 
 * @author xjung
 */
public interface ManageRegistConstants {
//　画面・VIEW ID定数
    /** 画面ID:全国店長勉強会マスタ登録画面 */
	public static final String SCREEN_ID = "BEN020";
	/** VIEW_ID:全国店長勉強会マスタ登録 条件画面 */
    public static final String VIEW_ID_SELECT = "BEN020V01";
    /** VIEW_ID:全国店長勉強会マスタ登録 編集画面 */
    public static final String VIEW_ID_EDIT = "BEN020V02";
    /** VIEW_ID:全国店長勉強会マスタ登録 確認画面 */
    public static final String VIEW_ID_CONFIRM = "BEN020V03";
    /** VIEW_ID：操作エラー画面 */
    public static final String VIEW_ID_ERR = "operation.Err";

//  ユーザータイプコードの定数 
    /** ユーザータイプコード：その他「99」*/
    public static final String USERTYPE_CD_ETC = "99";
    /** ユーザータイプコード：本部「01」*/
    public static final String USERTYPE_CD_HONBU = "01";
    /** ユーザータイプコード：オーナー「02」*/
    public static final String USERTYPE_CD_ONER = "02";

//  日付区分の定数    
    /** 日付区分：開催日 「01」*/
    public static final String DAY_KBN_OPEN  = "01";
    /** 日付区分：申込 「04」*/
    public static final String DAY_KBN_ENTRY = "04";
    /** 日付区分：表示 「03」*/
    public static final String DAY_KBN_DISP  = "03";

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

//  エントリーコードの定数   
    /** エントリーコード:全国店長勉強会 */
    public static final String ENTRY_CD_MANAGE = "35";

//  削除フラグの定数  
    /** 削除フラグ:ON */
    public static final String SAKUJO_FLG_ON = "1";
    /** 削除フラグ:OFF */
    public static final String SAKUJO_FLG_OFF = "0";

//  セレクション区分の定数
    /** セレクション区分:申込区分 */
    public static final String SEL_KBN_ENTRY = "1";
    /** セレクション区分:オプショナル */
    public static final String SEL_KBN_OPTIONAL = "2";

// メッセージの定数
    /** メッセージ：ユーザーID */
    public static final String MSG_USER_ID = "ユーザーID";
    /** メッセージ：システム日付 */
    public static final String MSG_SYS_DATE = "システム日付";
    /** メッセージ：全国店長勉強会マスタ登録情報 */
    public static final String MSG_MANAGE_REGIST_INFO = "全国全国店長勉強会タ登録情報";
    /** メッセージ：全国店長勉強会マスタ情報 */
    public static final String MSG_MANAGE_MST_INFO = "全国店長勉強会マスタ情報";
    /** メッセージ：排他エラー１ */
    public static final String MSG_EXCLUSION_CHK = "このデータは既に更新されている";
    /** メッセージ：更新 */
    public static final String MSG_UPDATE = "更新";
    /** メッセージ：申込が開始されている */
    public static final String MSG_ENTRY_CHK = "申込が開始されている";
    /** メッセージ：削除 */
    public static final String MSG_DELETE = "削除";
    /** メッセージ：申込区分は二つ以上入力してください */
    public static final String MSG_ENTRY_KBN_REQ = "申込区分は二つ以上入力してください。";
    /** メッセージ：エントリーコード */
    public static final String MSG_ENTRY_CD = "エントリーコード";
    /** メッセージ：エントリー年 */
    public static final String MSG_ENTRY_YEAR = "エントリー年";
    /** メッセージ：エントリー回 */
    public static final String MSG_ENTRY_KAI = "エントリー回";
    /** メッセージ：開催開始日 */
    public static final String MSG_OPEN_FROM = "開催開始日";
    /** HTMLパラメータ：開催開始日 */
    public static final String ID_OPEN_FROM = "openFrom";
    /** メッセージ：開催終了日 */
    public static final String MSG_OPEN_TO = "開催終了日";
    /** HTMLパラメータ：開催終了日 */
    public static final String ID_OPEN_TO = "openTo";
    /** メッセージ：イベント名称 */
    public static final String MSG_ENTRY_TITLE = "イベント名称";
    /** メッセージ：イベント名称桁数 */
    public static final String MSG_ENTRY_TITLE_LMT = "全角３０文字以内";
    /** HTMLパラメータ：イベント名称 */
    public static final String ID_ENTRY_TITLE = "entryTitle";
    /** メッセージ：オーナー：表示開始 */
    public static final String MSG_ONER_DISP_FROM = "オーナー：表示開始";
    /** HTMLパラメータ：オーナー：表示開始 */
    public static final String ID_ONER_DISP_FROM = "onerDispFrom";
    /** メッセージ：オーナー：表示終了 */
    public static final String MSG_ONER_DISP_TO = "オーナー：表示終了";
    /** HTMLパラメータ：オーナー：表示終了 */
    public static final String ID_ONER_DISP_TO = "onerDispTo";
    /** メッセージ：オーナー：申込開始 */
    public static final String MSG_ONER_ENTRY_FROM = "オーナー：申込開始";
    /** HTMLパラメータ：オーナー：申込開始 */
    public static final String ID_ONER_ENTRY_FROM = "onerEntryFrom";
    /** メッセージ：オーナー：申込終了 */
    public static final String MSG_ONER_ENTRY_TO = "オーナー：申込終了";
    /** HTMLパラメータ：オーナー：申込終了 */
    public static final String ID_ONER_ENTRY_TO = "onerEntryTo";
    /** メッセージ：本部：表示開始 */
    public static final String MSG_HONBU_DISP_FROM = "本部：表示開始";
    /** HTMLパラメータ：本部：表示開始 */
    public static final String ID_HONBU_DISP_FROM = "honbuDispFrom";
    /** メッセージ：本部：表示終了 */
    public static final String MSG_HONBU_DISP_TO = "本部：表示終了";
    /** HTMLパラメータ：本部：表示終了 */
    public static final String ID_HONBU_DISP_TO = "honbuDispTo";
    /** メッセージ：本部：申込開始 */
    public static final String MSG_HONBU_ENTRY_FROM = "本部：申込開始";
    /** HTMLパラメータ：本部：申込開始 */
    public static final String ID_HONBU_ENTRY_FROM = "honbuEntryFrom";
    /** メッセージ：本部：申込終了 */
    public static final String MSG_HONBU_ENTRY_TO = "本部：申込終了";
    /** HTMLパラメータ：本部：申込終了 */
    public static final String ID_HONBU_ENTRY_TO = "honbuEntryTo";
    /** メッセージ：申込区分 */
    public static final String MSG_ENTRY_KBN = "申込区分";
    /** HTMLパラメータ：申込区分 */
    public static final String ID_ENTRY_KBN = "entryKbn";
    /** メッセージ：オプショナル */
    public static final String MSG_OPTIONAL = "オプショナル";
    /** HTMLパラメータ：オプショナル */
    public static final String ID_OPTIONAL = "optional";
    /** メッセージ：セレクション名称桁数 */
    public static final String MSG_SELECTION_LMT = "全角１５文字以内";
    /** メッセージ：オーナー：申込開始以前の日付 */
    public static final String MSG_ONER_ERTRY_FROM_PREV = "オーナー：申込開始以前の日付";
    /** メッセージ：オーナー：申込終了以前の日付 */
    public static final String MSG_ONER_ERTRY_TO_PREV = "オーナー：申込終了以前の日付";
    /** メッセージ：オーナー：表示開始以前の日付 */
    public static final String MSG_ONER_DISP_FROM_PREV = "オーナー：表示開始以前の日付";
    /** メッセージ：オーナー：表示終了以前の日付 */
    public static final String MSG_ONER_DISP_TO_PREV = "オーナー：表示終了以前の日付";
    /** メッセージ：本部：申込開始以前の日付 */
    public static final String MSG_HONBU_ERTRY_FROM_PREV = "本部：申込開始以前の日付";
    /** メッセージ：本部：申込終了以前の日付 */
    public static final String MSG_HONBU_ERTRY_TO_PREV = "本部：申込終了以前の日付";
    /** メッセージ：本部：表示終了以前の日付 */
    public static final String MSG_HONBU_DISP_TO_PREV = "本部：表示終了以前の日付";
    /** メッセージ：オーナー：開催開始日以前の日付 */
    public static final String MSG_OPEN_FROM_PREV = "開催開始日以前の日付";
    /** メッセージ：オーナー：開催終了日以前の日付 */
    public static final String MSG_OPEN_TO_PREV = "開催終了日以前の日付";
    /** メッセージ：年度算出ロエラー(全国全国店長勉強会タ情報リスト取得ロジック) */
    public static final String MSG_ERR_PREV_YEAR =
        "全国店長勉強会マスタ情報リスト取得時、年度算出ロジックでエラーが発生しました。";
    /** メッセージ：年度算出ロエラー(全国店長勉強会マスタ情規登録ロジック) */
    public static final String MSG_ERR_ENTRY_YEAR =
        "全国店長勉強会マスタ情規登録時、年度算出ロジックでエラーが発生しました。";
    /** メッセージ：エントリー日付情報取得失敗(全国店長勉強会マスタ情報取得ロジック) */
    public static final String MSG_ERR_DATE_INFO =
        "全国店長勉強会マスタ情報取得の時、エントリー日付情報取得に失敗しました。";
    /** メッセージ：エントリーセレクション情報取得失敗(全国店長勉強会マスタ情報取得ロジック) */
    public static final String MSG_ERR_SELECTION_INFO =
        "全国店長勉強会マスタ情報取得の時、エントリーセレクション情報(申込区分、オプショナル)取得に失敗しました。";

// Mapオブジェクトキーの定数
    /** 全国店長勉強会日付情報リスト*/
    public static final String MAP_DATE_LIST = "EntryDateList";
    /** 全国店長勉強会セレクション情報リスト */
    public static final String MAP_SELECTION_LIST = "EntrySelectionList";

// その他
    /** 空白文字列 */
    public static final String EMPTY = "";
    /** コードフォーマッタ：エントリー回 */
    public static final String CF_ENTRY_KAI = "000";
    /** コードフォーマッタ：セレクションインデックス */
    public static final String CF_SEL_INDEX = "00";
}