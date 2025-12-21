package jp.co.isid.mos.bird.analysis.posdata.common;

/**
 * 総合営業日報 定数クラス
 *
 * @author xjung
 */
public interface PosDataConstants {
	    
//  画面ＩＤの定数
	/** ＰＯＳ集信データ検索画面 */
    public final static String VIEW_INIT_ID = "BDT001V01";
    /** （共通）オーナー検索画面 */
    public final static String VIEW_ONER_ID = "BCO006V01";
    
//  メッセージの定数
	/** メッセージ：条件画面初期処理 */
    public final static String MSG_CONDITION_ERR = "条件画面初期処理";
	/** メッセージ：ユーザータイプ */
    public final static String MSG_USER_TYPE = "ユーザータイプ";
    /** メッセージ：オーナーコード */
    public final static String MSG_ONER_ID = "オーナーコード";
    /** メッセージ：アプリ日付 */
    public final static String MSG_DATA_DT = "アプリ日付";
	/** メッセージ：集信データダウンロード */
	public static final String MSG_DATA_DOWNLOAD = "集信データダウンロード";
	/** メッセージ：PDFダウンロード */
	public static final String MSG_PDF_DOWNLOAD = "PDFダウンロード";
	
//  Mapオブジェクトキーの定数
    /** ユーザID */
    public final static String MAP_USER_ID = "userId";
    /** オーナーＩＤ */
    public final static String MAP_ONER_ID = "onerId";
    /** ユーザタイプ */
    public final static String MAP_USER_TYPE = "userType";
    /** 集信データ日 */
    public final static String MAP_DATA_DT = "dataDt";
    /** 集信データ日(yyyy/MM/dd)プルダウンリスト */
    public final static String MAP_DATA_DT_LIST = "dataDtList";
    /** 会社コード */
    public final static String MAP_COMPANY_CD = "companyCd";
    
 //  期間指定の定数コード
    /** 表示月(表示日用) */
    public final static int DISPLAY_DAY_MONTH = 2;

// その他の定数
    /** デフォルト会社コード */
	public final static String DEFORT_COMPANY_CD = "00";
    /** 空白文字 */
	public final static String EMPTY = "";
    /** 空白文字 */
	public final static String ONE_BYTE = " ";
    /** 最大バイト数文字 */
	public final static int MAX_BYTE = 512;
	
	public final static String KAIGYOU = "\r\n";

}