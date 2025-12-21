package jp.co.isid.mos.bird.bizreport.zendougeturegist.common;

public class ZenDougetuRegistConstants {
    /*メニューＩＤ*/
    public static final String SUB_MENU_ID = "BBR010";
    /*ビューID*/
    public static final String COND_VIEWID    = SUB_MENU_ID+"V01";
    public static final String EDIT_VIEWID    = SUB_MENU_ID+"V02";
    public static final String CONFIRM_VIEWID = SUB_MENU_ID+"V03";
    /*ユーザータイプ 本部：01 オーナー：02 店舗：03**/
    public static final String HONBU_USER = "01";
    public static final String TENPO_USER = "02";
    public static final String ONER_USER = "03";
    /*結果が空かどうか 空：0 結果あり:1*/    
    public static final String EMPTY_DATA = "0";
    public static final String EMPTY_NOT = "1";
    /*過去日付どうか 過去：0 現在・未来:1*/    
    public static final String DATE_PAST = "0";
    public static final String DATE_FUTURE = "1";
    /*４月を設定*/
    public static final String APRIL = "04";
    /*閏年の設定*/
    public static final String LEAP_DAY = "0229";
    /*月初の日にちを設定*/
    public static final String FIRST_DAY = "01";
    /*複数ウィンドウエラーの遷移先*/
    public static final String operationErr_VIEW_ID = "operation.Err";
    /*予算 上期置換えTo処理区分*/
    public static final String YOSAN_CONTROL_SHORI_KBN_KAMIKI_OKIKAE_TO = "02";
}
