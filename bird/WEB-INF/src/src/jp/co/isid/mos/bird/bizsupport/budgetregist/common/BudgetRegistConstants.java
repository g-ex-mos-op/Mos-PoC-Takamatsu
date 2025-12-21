package jp.co.isid.mos.bird.bizsupport.budgetregist.common;



/**
 * 予算登録定数
 * 
 * @author Aspac
 */
public class BudgetRegistConstants {

    
    /** プログラムID **/
    public static final String BUDGET_PRG_ID = "BBS022";
    
    
    
    /** 下期予算クリア可能月(９月) */
    public static final String BUDGET_CLEAR_MONTH = "9";
    
    /** 下期始め月(９月) */
    public static final String BUDGET_GLEAR_MONTH_START = "09";
    
    /** 下期終り月(３月) */
    public static final String BUDGET_GLEAR_MONTH_END   = "03";
    
    /** 年度始め月(４月) */
    public static final String BUDGET_NENDO_FROM_MONTH   = "04";
    
    
    
    /** 登録CSVファイルエラー記号連結文字 */
    public static final String CSV_ERR_SEPARATOR = " ";

    /** 登録CSVファイルヘッダー行数 */
    public static final int CSV_HEADER = 3;
    
    /** 登録CSVファイル予算開始列*/
    public static final int CSV_BUDGET_START_COL = 10;

    /** 登録CSVファイル項目列行数 XX行目 */
    //CSV読込で空行がスキップされているため -1 → ヘッダー空白行削除に伴い4行目が項目名に変更。
    public static final int CSV_CLM_LINE = 4; 
    
    /** 登録CSVファイル　データ行数 XX行目 */
    //CSV読込で空行がスキップされているため -1 → ヘッダー空白行削除に伴い5行目からデータ部に変更。
    public static final int CSV_DATA_LINE = 5;
    
    /** 登録CSVファイル最大列数 */
    public static final int CSV_MAX_ROW = 21;
    
    
    
    
    /** 登録CSVファイル ヘッダー 会社 */
    public static final String CSV_HEADER_COMP_STR = "会社：";
    
    /** 登録CSVファイル ヘッダー 年度 */
    public static final String CSV_HEADER_NEND_STR = "年度：";
    
    /** 登録CSVファイル ヘッダー 対象店舗 */
    public static final String CSV_HEADER_MISE_STR = "対象店舗：";
    
    /** 登録CSVファイル カラム */
    public static final String[] CSV_CLM = new String[]{"店舗コード","店舗名称","FC/RC区分","FC/RC","支部コード",
                                                            "支部名称","支部取込コード","支部取込名称","予算合計",
                                                            "４月予算","５月予算","６月予算","７月予算","８月予算",
                                                            "９月予算","１０月予算","１１月予算","１２月予算",
                                                            "１月予算","２月予算","３月予算"};
    
    
    
    /** 登録CSVファイル 店舗コード列 */
    public static final int CSV_ROW_MISE = 1;

    /** 登録CSVファイル 店舗名称列 */
    public static final int CSV_ROW_MISENAME = 2;
    
    /** 登録CSVファイル FCRC区分列 */
    public static final int CSV_ROW_FCRC_KBN = 3;
    
    /** 登録CSVファイル FCRC列 */
    public static final int CSV_ROW_FCRC = 4;
    
    /** 登録CSVファイル 支部コード列 */
    public static final int CSV_ROW_SIBU = 5;
    
    /** 登録CSVファイル 支部名称列 */
    public static final int CSV_ROW_SIBUNAME = 6;

    /** 登録CSVファイル 支部取込コード列 */
    public static final int CSV_ROW_AREADAI = 7;
    
    /** 登録CSVファイル 支部取込名称列 */
    public static final int CSV_ROW_AREADAINAME = 8;
    
    /** 登録CSVファイル 登録予算合計 */
    public static final int CSV_ROW_YOSANTOTAL = 9;
    
    /** 登録CSVファイル 4月予算 */
    public static final int CSV_ROW_YOSAN04 = 10;
    
    /** 登録CSVファイル 5月予算 */
    public static final int CSV_ROW_YOSAN05 = 11;

    /** 登録CSVファイル 6月予算 */
    public static final int CSV_ROW_YOSAN06 = 12;

    /** 登録CSVファイル 7月予算 */
    public static final int CSV_ROW_YOSAN07 = 13;
    
    /** 登録CSVファイル 8月予算 */
    public static final int CSV_ROW_YOSAN08 = 14;

    /** 登録CSVファイル 9月予算 */
    public static final int CSV_ROW_YOSAN09 = 15;

    /** 登録CSVファイル 10月予算 */
    public static final int CSV_ROW_YOSAN10 = 16;

    /** 登録CSVファイル 11月予算 */
    public static final int CSV_ROW_YOSAN11 = 17;

    /** 登録CSVファイル 12月予算 */
    public static final int CSV_ROW_YOSAN12 = 18;

    /** 登録CSVファイル 1月予算 */
    public static final int CSV_ROW_YOSAN01 = 19;

    /** 登録CSVファイル 2月予算 */
    public static final int CSV_ROW_YOSAN02 = 20;

    /** 登録CSVファイル 3月予算 */
    public static final int CSV_ROW_YOSAN03 = 21;
    
    /** 登録CSVファイル 予算開始列 */
    public static final int CSV_ROW_YOSAN_START = 10;

    /** 登録CSVファイル 予算終了列 */
    public static final int CSV_ROW_YOSAN_END = 21;

    
    
    
    /** 登録CSVファイル エラーコード フォーマット */
    public static final String CSV_ERR_FORMAT = "E01";
    
    /** 登録CSVファイル エラーコード マスタ存在 */
    public static final String CSV_ERR_ISMST  = "E02";
    
    /** 登録CSVファイル エラーコード 重複 */
    public static final String CSV_ERR_DUPL   = "E03";
    
    /** 登録CSVファイル エラーコード 整合性 */
    public static final String CSV_ERR_COMPL  = "E04";
    
    /** 登録CSVファイル エラーコード 必須 */
    public static final String CSV_ERR_MUST   = "E05";

    /** 登録CSVファイル エラーコード 妥当性 */
    public static final String CSV_ERR_VALID  = "E06";
    
    
    
    /** コード TABLE.BT42MSYD  FCRC区分 FC */
    public static final String CODE_FC = "1";
    
    /** コード TABLE.BT42MSYD  FCRC区分 RC */
    public static final String CODE_RC = "2";
    
    /** コード TABLE.BT42MSYD  店舗コード 仮店舗先頭文字 */
    public static final String CODE_MISECD_KARI_STR = "X";
    
    /** コード TABLE.BM01TENM  店舗コード  桁数  */
    public static final int CODE_MISECD_LENGTH = 5;
    
    /** コード TABLE.BM01TENM  支部コード  桁数  */
    public static final int CODE_SIBUCD_LENGHT = 3;
    
    /** コード TABLE.BM01TENM  支部取込コード  桁数  */
    public static final int CODE_AREADAICD_LENGHT = 3;
    
    /** コード TABLE.BT42MSYD  削除区分  初期値or更新 */
    public static final String CODE_DELFLG_OFF = "0";
    
    /** コード TABLE.BT42MSYD  削除区分  削除 */
    public static final String CODE_DELFLG_ON = "1";

    /** コード TABLE.BT42MSYD  店舗種別 前年対象店 */
    public static final String CODE_TENPO_SHU_BEFORE = "1";

    /** コード TABLE.BT42MSYD  店舗種別 予算店 */
    public static final String CODE_TENPO_SHU_BUDGET = "2";

    /** コード TABLE.BT42MSYD  店舗種別 新店 */
    public static final String CODE_TENPO_SHU_NEW = "3";
    
    /** TABLE.BT42MSYD  店舗カウント */
    public static final int TENPO_CNT_COUNTABLE = 1;

    /** 月 */
    public static final String[] DATE_MONTH = new String[]{"04","05","06","07","08","09","10","11","12","01","02","03"};
}