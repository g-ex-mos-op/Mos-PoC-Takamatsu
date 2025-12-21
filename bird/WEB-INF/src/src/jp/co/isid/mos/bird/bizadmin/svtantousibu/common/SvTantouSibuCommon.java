package jp.co.isid.mos.bird.bizadmin.svtantousibu.common;

/**
 * リモート閲覧支部SV担当店登録 共通クラス
 * @author xnkusama
 *
 */
public class SvTantouSibuCommon {

    /* 定数 */
    // 画面ID
    public static final String VIEW_ID = "BBA004";
    // 処理選択 １：リモート閲覧支部登録
    public static final String PROCESS_MODE_REGIST_REMOTE_SIBU = "1";
    // 処理選択 ２：SV担当店ダウンロード
    public static final String PROCESS_MODE_REGIST_SV_CSV = "2";
    // 処理選択 ３：SV担当店アップロード登録
    public static final String PROCESS_MODE_REGIST_UPLOAD = "3";
    // SV未設定店のみ　０：チェックOff
    public static final boolean MISETTEITEN_ONLY_OFF = false; 
    // SV未設定店のみ　１：チェックOn
    public static final boolean MISETTEITEN_ONLY_ON  = true;
    // 対象支部：すべて
    public static final String COND_SIBU_ALL = "ALL";
    
    //--- 画面遷移情報 ---//
    // 初期画面
    public static final String VIEW_ID_INIT = "BBA004V01";
    // リモート閲覧支部登録編集画面
    public static final String VIEW_ID_REMOTESIBU_EDIT = "BBA004V02";
    // リモート閲覧支部登録確認画面
    public static final String VIEW_ID_REMOTESIBU_CONFIRM = "BBA004V03";
    // SV担当店登録確認画面
    public static final String VIEW_ID_TANTOUSIBU_UPLOAD = "BBA004V04";
    // ユーザー検索
    public static final String VIEW_ID_USER_SEARCH = "BCO003V01";
    
    //--- リモート閲覧支部登録 ---//
    // 閲覧可：チェックOn
    public static final boolean ETURAN_ON  = true;
    // 閲覧可：チェックOff
    public static final boolean ETURAN_OFF = false;
    
    //--- CSVアップロード ---//
    // CSV項目列数
    public static final int CSV_UPLOAD_COL_NUM = 8;
    // 位置：店コード列
    public static final int CSV_UPLOAD_COL_MISE_CD = 4;
    // 位置：SVコード列
    public static final int CSV_UPLOAD_COL_SV_CD = 6;
    
    
    //--- 共通 ---//
    // モス会社コード
    public static final String COMPANY_CD_MOS = "00";
}
