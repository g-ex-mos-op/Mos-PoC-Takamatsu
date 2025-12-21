package jp.co.isid.mos.bird.config.campaignmasterregist.common;

/**
 * キャンペーンマスタ登録 定数クラス
 * @author xnkusama
 *
 */
public class CampaignMasterRegistConst {

    /*********
    * 画面ID *
    *********/
    public static final String VIEW_ID = "BCF006";
    
    /*************************
    * キャンペーン一覧モード *
    *************************/
    // 編集可能キャンペーン
    public static final int CAMP_LIST_MODE_EDITABLE = 1;
    // 過去キャンペーン
    public static final int CAMP_LIST_MODE_KAKO = 2;
    
    /**************
     * 登録モード *
     *************/
     // 新規
     public static final int REGIST_MODE_INSERT = 1;
     // 編集
     public static final int REGIST_MODE_UPDATE = 2;
     // 照会
     public static final int REGIST_MODE_READONLY = 3;
     
    /**********
    * VIEW ID *
    **********/
    // 条件画面
    public static final String VIEW_ID_CONDITION = "BCF006V01";
    // 編集画面
    public static final String VIEW_ID_EDIT = "BCF006V02";
    // 確認画面
    public static final String VIEW_ID_CONFIRM = "BCF006V03";

    /*****************************
    * 個店アップロード表示モード *
    *****************************/
     // １：通常選択モード
     public static final int KOTEN_UPLOAD_STATUS_NORMAL = 1;
     // ２：確認後エラーなし
     public static final int KOTEN_UPLOAD_STATUS_NOERROR = 2;
     // ３：確認後エラーあり
     public static final int KOTEN_UPLOAD_STATUS_ERROR = 3;
     // ４：登録済み
     public static final int KOTEN_UPLOAD_STATUS_REGISTED = 4;

     /*******************
     * 親メニューフラグ *
     *******************/
     public static final String OYA_MENU_FLG_ON = "1";
     
     /*******************
     * 親メニュー最大数 *
     *******************/
     public static final int OYA_MENU_MAX_SIZE = 18;
     
     /***********
     * 対象区分 *
     ***********/
     public static final String TARGET_KBN_ZENTEN = "1";
     public static final String TARGET_KBN_SIBU = "2";
     public static final String TARGET_KBN_MISE = "3";
     
     /*****************
     * エリア大フラグ *
     *****************/
     public static final String AREA_DAI_FLG_OFF = "0";
     public static final String AREA_DAI_FLG_ON = "1";

     /*****************
      * ワーニングMSG *
      *****************/
     public static final String WARNING_MSG1 = "対象期間開始日が変更されています。\n翌日未明まで、照会画面の期間合計の結果が一致しなくなります。";
     public static final String WARNING_MSG2 = "対象期間終了日に本日以前の日付が入力されています。\n翌日未明まで、照会画面の期間合計の結果が一致しなくなります。";
}