package jp.co.isid.mos.bird.entry.longserviceoffer.common;

/**
 * 永年勤続申込 定数クラス
 * @author xnkusama
 *
 */
public class LongserviceOfferConstants {
	
//	 画面・VIEW ID定数
    /** 画面ID:永年勤続申込画面 */
	public static final String SCREEN_ID = "BEN018";
    /** VIEW_ID:永年勤続申込登録 編集画面 */
    public static final String VIEW_ID_EDIT = "BEN018V01";
    /** VIEW_ID:永年勤続申込登録 確認画面 */
    public static final String VIEW_ID_CONFIRM = "BEN018V02";
    /** VIEW_ID：操作エラー画面 */
    public static final String VIEW_ID_ERR = "operation.Err";
    /** VIEW_ID:イベント選択画面 */
    public static final String VIEW_ID_EVENTLIST = "BEN091V01";
    /** VIEWID定義:スタッフ選択画面ID */
    public static final String VIEWID_STAFFSELECT = "BEN093V01";

//  メッセージの定数
    /** メッセージ：システム日付 */
    public static final String MSG_SYS_DATE = "システム日付";
    /** メッセージ：排他 */
    public static final String MSG_EXCLUSION_CHK = "このデータは既に更新されている";   
    /** メッセージ：削除*/
    public static final String MSG_UPDATE = "更新";
    
//　initフラグの定数
    /** メッセージ：削除*/
    public static final int INIT_FLG_SELECT = 1;
    /** メッセージ：削除*/
    public static final int INIT_FLG_NATIONAL = 2;
    
//  編集モードの定数 
    /** 編集モード:初期 */
    public static final int EDIT_MODE_INIT = 0;
    /** 編集モード:入力欄追加 */
    public static final int EDIT_MODE_INSERT = 1;
    /** 編集モード:登録・終了 */
    public static final int EDIT_MODE_UPDATE = 2;
    /** 編集モード:確認 */
    public static final int EDIT_MODE_CONFIRM = 3;
    /** 編集モード:戻る */
    public static final int EDIT_MODE_RETURN = 4;
    /** 編集モード:スタッフフォームから戻る */
    public static final int EDIT_MODE_STAFF = 5;
    /** 編集モード:削除 */
    public static final int EDIT_MODE_DELETE = 6;
    /** 編集モード:処理中 */
    public static final int EDIT_MODE_PROCESS = 7;

//  データ状態の定数 
    /** 状態:未登録 */
    public static final int TBL_MODE_OFF = 0;
    /** 状態:登録済み */
    public static final int TBL_MODE_ON = 1;
        
// 初期値
    /** エントリーコード:20永年勤続 */
    public static final String ENTRY_CD = "20";
    /** 宛先区分:00申込責任者 */
    public static final String ATESAKI_KBN = "00";
        
//　フォーカスするエリア
    /** 申込責任者 */ 			
    public static  final String FOCUS_NAME  = "name";
    /** 申込担当者 */ 	   
    public static  final String FOCUS_SOUFU_NAME = "soufuName";
    /** 電話番号 */ 
    public static  final String FOCUS_TEL   = "tel";
    /** 役職 */ 
    public static  final String FOCUS_JOB_TYPE   = "jobType";
    /** 店舗 */ 
    public static  final String FOCUS_MISE   = "miseCd";
    /** 氏-漢字 */ 
    public static  final String FOCUS_L_NAME_KJ  = "lNameKj";
    /** 名-漢字 */ 
    public static  final String FOCUS_F_NAME_KJ  = "fNameKj";
    /** 氏-ローマ字 */ 	
    public static  final String FOCUS_L_NAME_RM  = "lNameRm";
    /** 名-ローマ字  */ 	
    public static  final String FOCUS_F_NAME_RM  = "fNameRm";
    /** 年齢 */ 	
    public static  final String FOCUS_AGE = "age";
    /** 性別 */ 	
    public static  final String FOCUS_SEX = "sex";
    /** 生年月日-年号 */ 	
    public static  final String FOCUS_BIRTHDAY_NENGO = "birthdayNengo";
    /** 生年月日-年 */ 	
    public static  final String FOCUS_BIRTHDAY_YEAR = "birthdayYear";
    /** 生年月日-月 */ 	
    public static  final String FOCUS_BIRTHDAY_MONTH = "birthdayMonth";
    /** 生年月日-日 */ 	
    public static  final String FOCUS_BIRTHDAY_DAY  = "birthdayDay";
    /** 入社年月日-年号 */ 	
    public static  final String FOCUS_ENTRY_NENGO   = "entryDateNengo";
    /** 入社年月日-年 */ 	
    public static  final String FOCUS_ENTRY_YEAR   = "entryDateYear";
    /** 入社年月日-月 */ 	
    public static  final String FOCUS_ENTRY_MONTH  = "entryDateMonth";
    /** 入社年月日-日 */ 	
    public static  final String FOCUS_ENTRY_DAY   = "entryDateDay";
    /** 店舗経験 */ 	
    public static  final String FOCUS_EXP_KBN = "expKbn";
    
//  入力チェック時の初期設定
    /** 開始ソート番号 */
    public static final String SEQ_START = "01";
    /** 最大ソート数 */
    public static final int SEQ_MAX = 30;
    /** ソート番号フォーマット */
    public static final String SEQ_FORMAT = "00";
    /** 最少年齢 */
    public static final int AGE = 15;
    /** 年号：昭和 */
    public static final String NENGO_SHOWA = "3";
    /** 年号：平成 */
    public static final String NENGO_HESEI = "4";
    /** 平成年号の開始年 */
    public static final int HEISEI_START_YEAR = 64;
    /** 平成年号の開始月 */
    public static final int HEISEI_START_MONTH = 1;
    /** 平成年号の開始日 */
    public static final int HEISEI_START_DAY = 8;
    /** 店舗経験:社員暦 */
    public static final String JOB_TYPE_EMPLOYEE = "E";
    /** 店舗経験:P/A暦 */
    public static final String JOB_TYPE_PARTTIMER = "P";
        
    
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
