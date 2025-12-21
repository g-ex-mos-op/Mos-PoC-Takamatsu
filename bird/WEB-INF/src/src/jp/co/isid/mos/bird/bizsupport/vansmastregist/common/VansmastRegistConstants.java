/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.common;

/**
 * 汎用研修マスタ登録 定数クラス
 * @author narita
 *
 */
public class VansmastRegistConstants {
	
//	 画面・VIEW ID定数
    /** 画面ID:バンズ倉庫別登録画面 */
	public static final String SCREEN_ID = "BBS029";
	/** VIEW_ID:バンズ倉庫別登録 初期画面 */
    public static final String VIEW_ID_SELECT = "BBS029V01";
    /** VIEW_ID:バンズ倉庫別登録 編集画面 */
    public static final String VIEW_ID_EDIT = "BBS029V02";
    /** VIEW_ID:バンズ倉庫別登録 確認画面 */
    public static final String VIEW_ID_CONFIRM = "BBS029V03";
    /** VIEW_ID：操作エラー画面 */
    public static final String VIEW_ID_ERR = "operation.Err";
    
//  メッセージの定数
    /** メッセージ：排他 */
    public static final String MSG_EXCLUSION_CHK = "このデータは既に更新されている";   
    /** メッセージ：削除*/
    public static final String MSG_UPDATE = "更新";
    
//  対象処理の定数
    /** 対象倉庫：全倉庫*/
    public static final String ALL_SOKO_KJ = "全倉庫";
    /** 削除項目：新規*/
    public static final String PROCESS_INSERT = "新規";
    /** 商品コードフォーマット */
    public static final String SHO_CD_FORMAT = "00000";
    /** 荷姿コード未選択 */
    public static final String NISUGATA_CD_NOTING = "000";
    /** 登録モード：更新 */
    public static final String DATA_MODE_UPDATE = "0";
    /** 登録モード：削除 */
    public static final String DATA_MODE_DELETE = "1";
    /** 登録モード：新規 */
    public static final String DATA_MODE_INSERT = "2";
    /** 登録モード：未設定 */
    public static final String DATA_MODE_NOTING = "3";
    /** 入力欄追加区分：初期表示 */
    public static final int ADD_MODE_INIT = 1;
    /** 入力欄追加区分：リンク */
    public static final int ADD_MODE_LINK = 2;
    
//　フォーカスするエリア
    /** 代表商品コード */ 
    public static  final String FOCUS_SHO_CD_DAI		= "shohin";
    /** 商品荷姿コード */ 
    public static  final String FOCUS_SHO_NISUGATA	= "nisugata";   
    
//  その他
    /** 空白文字列 */
    public static final String EMPTY = "";
    /** スペース */
    public static final String SPACE = " ";
    /** ゼロ */
    public static final String ZERO = "0";
}
