package jp.co.isid.mos.bird.bizreport.posreportregist.common;

/**
 * POS速報設定登録Constants
 * 
 */
public interface PosReportRegistConstants {

    
    public static final String VIEW_ID = "BBR007";
    
    public static final String SCREEN_ID= "screenId";
    
    
    
    
    /**
     * 画面ID
     */
    
    //登録画面
    public static final String VIEWID_REGIST      = "BBR007V01";
    
    //確認画面
    public static final String VIEWID_CONFIRM     = "BBR007V02";
    
    //店舗検索画面
    public static final String VIEWID_MISESEARCH  = "BCO008V01";
    
    //操作エラー画面
    public static final String VIEW_ID_OPERATION_ERR = "operation.Err";
    
    
    
    /**
     * システム内部制御
     */
    
    //最大設定件数
    public static final int MAX_POS_LINE = 40; 
    
    //処理ステータス　初期値　
    public static final String PRO_STATE_DEF = "0";
    
    //処理ステータス　新規　
    public static final String PRO_STATE_INS = "1";

    //処理ステータス　変更　
    public static final String PRO_STATE_UPD = "2";
    
    //処理ステータス　削除
    public static final String PRO_STATE_DEL = "3";
    
    
    //処理ステータス　新規　
    public static final String PRO_STATE_STR_INS = "新規";

    /** 処理ステータス　変更*/
    public static final String PRO_STATE_STR_UPD = "変更";
    
    //処理ステータス　削除
    public static final String PRO_STATE_STR_DEL = "削除";
    
    
   
    /**
     * リアルタイムステータステーブル　BR56RTSH
     */    
    
    //固定設定 配信指示名称
    public static final String FIX_HAIS_SIJI_NAME = "売上速報店舗追加";
    
    //固定設定 集信時刻１
    public static final String FIX_SHU_TIME1 = "1110";
    
    //固定設定 集信時刻２
    public static final String FIX_SHU_TIME2 = "1310";
    
    //固定設定 集信時刻３
    public static final String FIX_SHU_TIME3 = "1510";
    
    //固定設定 集信時刻４
    public static final String FIX_SHU_TIME4 = "1710";
    
    //固定設定 集信時刻５
    public static final String FIX_SHU_TIME5 = "1910";
    
    //配信指示SEQ初期値
    public static final String FIX_SEQ_NO = "90000";


    
    
    /**
     * リアルタイムステータステーブル　BR57RTST
     */
    
    //配信結果ステータス 正常
    public static final String HAIS_RSLT_ST_SEIJO       = "0";

    //配信結果ステータス ファイルなし
    public static final String HAIS_RSLT_ST_NOFILE      = "1";

    //配信結果ステータス 通信不良
    public static final String HAIS_RSLT_ST_TUSHINFURYO = "2";
    
    //配信結果ステータス 未実装
    public static final String HAIS_RSLT_ST_MIJISO      = "9";

    
    //配信結果ステータス 正常
    public static final String HAIS_RSLT_ST_STR_SEIJO       = "正常";

    //配信結果ステータス ファイルなし
    public static final String HAIS_RSLT_ST_STR_NOFILE      = "ファイルなし";

    //配信結果ステータス 通信不良
    public static final String HAIS_RSLT_ST_STR_TUSHINFURYO = "通信不良";
    
    //配信結果ステータス 未実施
    public static final String HAIS_RSLT_ST_STR_MIJISSI      = "未実施";
    
    /** 全店用店コード: 00000  */
    public static final String ALLMISE_CD = "00000";
    
    
    
}