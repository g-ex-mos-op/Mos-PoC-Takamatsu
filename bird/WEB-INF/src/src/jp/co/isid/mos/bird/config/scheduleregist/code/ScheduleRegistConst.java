package jp.co.isid.mos.bird.config.scheduleregist.code;

/**
 * スケジュール登録 定数クラス
 * @author xnkusama
 *
 */
public class ScheduleRegistConst {

    /*画面ID*/
    public static final String GAMEN_ID = "BCF010";
    
    /*VIEW_ID*/
    public static final String VIEW_ID_CONDITION    = GAMEN_ID + "V01";
    public static final String VIEW_ID_EDIT         = GAMEN_ID + "V02";
    public static final String VIEW_ID_CONFIRM      = GAMEN_ID + "V03";
    // 操作エラー
    public static final String operationErr_VIEW_ID = "operation.Err";

    /*対象週 定数*/
    //今週
    public static final String TARGET_WEEK_THIS_WEEK = "0";
    public static final String TARGET_WEEK_THIS_WEEK_LABEL = "今週";
    //来週
    public static final String TARGET_WEEK_NEXT_WEEK = "1";
    public static final String TARGET_WEEK_NEXT_WEEK_LABEL = "来週";
    //再来週
    public static final String TARGET_WEEK_NEXT_2WEEK = "2";
    public static final String TARGET_WEEK_NEXT_2WEEK_LABEL = "再来週";
    //再々来週
    public static final String TARGET_WEEK_NEXT_3WEEK = "3";
    public static final String TARGET_WEEK_NEXT_3WEEK_LABEL = "再々来週";
    
    /*ステータス 定数*/
    //変更無し
    public static final String STATUS_NO_EDIT = "1";
    public static final String STATUS_NO_EDIT_LABEL = "変更なし";
    //更新
    public static final String STATUS_UPDATE = "2";
    public static final String STATUS_UPDATE_LABEL = "更新";
    //削除
    public static final String STATUS_DELETE = "3";
    public static final String STATUS_DELETE_LABEL = "削除";
    //新規
    public static final String STATUS_INSERT = "9";
    public static final String STATUS_INSERT_LABEL = "新規";
    
    /*対象コード 定数*/
    //全社共通
    public static final String TAISHO_CD_ALL = "01";
    //各会社個別設定
    public static final String TAISHO_CD_NOT_ALL = "02";
    /*会社コード 定数*/
    //全社共通時の会社コード
    public static final String TAISHO_CD_ALL_COMPANY = "00";
    
    /*1日の最大登録数*/
    public static final int SCHEDULE_MAX_COUNT = 10;
    
    /*DB登録最大件数（1日単位）*/
    public static final int DB_ENABLE_MAX_COUNT = 999;
    
    /*削除フラグ*/
    public static final String SAKUJO_FLG_ON = "1";
}
