package jp.co.isid.mos.bird.bizadmin.accountlist.code;

/**
 * お申込内容一覧 定数クラス
 * @author xnkusama
 *
 */
public class AccountListConst {
    /*画面ID*/
    public static final String VIEW_ID = "BBA005";
    //初期画面
    public static final String VIEW_ID_INIT = "BBA005V01";

    /*オプション機能*/
    //利用不可値
    public static final String OPTION_FLG_NOT_AVAILABLE = "0";
    //利用可能値
    public static final String OPTION_FLG_AVAILABLE = "1";
    //対象外
    public static final String OPTION_FLG_NO_TARGET = "9";
    //利用可能 表示値
    public static final String OPTION_FLG_AVAILABLE_DISP = "○";
    //利用不可 表示値
    public static final String OPTION_FLG_NOT_AVAILABLE_DISP = "×";
    //利用対象外 表示値
    public static final String OPTION_FLG_NO_TARGET_DISP = "－";
    
    /*状態*/
    //使用停止値
    public static final String STOP_FLG_ON = "1";
    //使用可能 表示値
    public static final String STOP_FLG_ON_DISP = "未使用";
    //使用不可 表示値
    public static final String STOP_FLG_OFF_DISP = "ご利用中";
    
    /*アカウント変更 リンク情報*/
    //汎用ロール制御情報 分類コード
    public static final String LINK_DISPLAY_BUNRUI = "01";
}
