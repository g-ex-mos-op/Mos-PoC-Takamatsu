package jp.co.isid.mos.bird.bizreport.urimaintenance.common;

/**
 * 売上修正Constants
 * 
 */
public interface UriMaintenanceConstants {

    
    public static final String VIEW_ID = "BBR008";
    
    public static final String SCREEN_ID= "screenId";
    
    
    
    
    /**
     * 画面ID
     */
    
    //検索画面
    public static final String VIEWID_CONDITION   = "BBR008V01";
    
    //編集画面
    public static final String VIEWID_EDIT        = "BBR008V02";
    
    //確認画面
    public static final String VIEWID_CONFIRM     = "BBR008V03";
    
    //店舗検索画面
    public static final String VIEWID_MISESEARCH  = "BCO008V01";
    
    //操作エラー画面
    public static final String VIEW_ID_OPERATION_ERR = "operation.Err";
    
    
    /**
     * ステータス取得
     */
    
    //バッチプログラムID
    public static final String BATCH_PGM_ID = "MODHTORI";
    
    //プログラム区分
    public static final String BATCH_PGM_KBN = "01";
    
    //バッチステータス更新モード
    public static final String BATCH_STATE_KBN_UPD = "0";

    //バッチステータス参照モード
    public static final String BATCH_STATE_KBN_REF = "1";
    
    //バッチ更新フラグ(BT97ADUP.UP_FLG) 初期状態
    public static final String UP_FLG_UPD = "0";
    
    //バッチ更新フラグ(BT97ADUP.UP_FLG) バッチ抽出済
    public static final String UP_FLG_REF = "1";
    
    
    /**
     * 検索画面
     */
       
    //対象年月プルダウン期間(XXヶ月)
    public static final int NENGETU_DISPLAY_MONTH = 2;
    
    
    /**
     * 編集画面
     */
    
    //タブインデックス(売上金)
    public static final int TAB_INDEX_URIAGEKIN  = 0;
    
    //タブインデックス(前受・売掛)
    public static final int TAB_INDEX_URIKAKEKIN = 1;
    
    //タブインデックス(販売)
    public static final int TAB_INDEX_HANBAI     = 2;

    //タブインデックス(値引き)
    public static final int TAB_INDEX_NEBIKI     = 3;
    
    //計算結果チェック (確認アクション)
    public static final int TAB_INDEX_CHK_CALCULATE  = 9;
    
    
    //サブタブインデックス(その１)
    public static final int TAB_SUB_INDEX_NO1 = 0;
    
    //サブタブインデックス(その２)
    public static final int TAB_SUB_INDEX_NO2 = 1;
    
    //サブタブインデックス(その３)
    public static final int TAB_SUB_INDEX_NO3 = 2;
    
    
    /**
     * 売上情報リスト
     */
    
    //合計
    public static final String LIST_TOTAL_REC = "合計";
    
    //金額
    public static final String LIST_KINGAKUK_CLM = "金額";
    
    //件数
    public static final String LIST_KENSU_CLM = "件数";

    //消費税
    public static final String LIST_TAX_CLM = "消費税";
    
//    //リスト項目キー
//    public static final String[] LIST_URI_DECIMAL_CLM = {
//        "u01Uriage","u02MenuUri","u03BuppanUri","u04Nebiki","u05Nebikigo","u06Tax","u07MinusKen","u08MinusKin","u09NebikiKen",
//        "u10NebikiKin","u11PNebikiKen","u12PNebikiKin","u13GenkinKen","u14GenkinKin","u15KaikeiKen2","u16KaikeiKin2","u17KaikeiKen3","u18KaikeiKin3","u19KaikeiKen4",
//        "u20KaikeiKin4","u21KaikeiKen5","u22KaikeiKin5","u23KaikeiKen6","u24KaikeiKin6","u25KaikeiKen7","u26KaikeiKin7","u27KaikeiKen8","u28KaikeiKin8","u29KaikeiKen9",
//        "u30KaikeiKin9","u31KaikeiKen10","u32KaikeiKin10","u33KaikeiKen11","u34KaikeiKin11","u35TieketKen1","u36TieketKin1","u37TieketKen2","u38TieketKin2","u39TieketKen3",
//        "u40TieketKin3","u41TieketKen4","u42TieketKin4","u43TieketKen5","u44TieketKin5","u45TieketKen6","u46TieketKin6","u47TieketKen7","u48TieketKin7","u49TieketKen8",
//        "u50TieketKin8","u51TieketKen9","u52TieketKin9","u53TieketKen10","u54TieketKin10","u55TieketKen11","u56TieketKin11","u57TieketKen12","u58TieketKin12","u59TieketKen13",
//        "u60TieketKin13","u61TieketKen14","u62TieketKin14","u63TieketKen15","u64TieketKin15","u65Nyukin","u66Shukin","u67AridakaCal","u68AridakaJitu","u69Kajou",
//        "u70Fusoku","u71CanKen","u72CanKin","u73ChengeCnt","u74Kyakusu","u75AllcanKen","u76AllcanKin",
//        "u01NebikiKen1","u02NebikiKin1","u03NebikiTax1","u04NebikiKen2","u05NebikiKin2","u06NebikiTax2","u07NebikiKen3","u08NebikiKin3","u09NebikiTax3",
//        "u10NebikiKen4","u11NebikiKin4","u12NebikiTax4","u13NebikiKen5","u14NebikiKin5","u15NebikiTax5","u16NebikiKen6","u17NebikiKin6","u18NebikiTax6","u19NebikiKen7",
//        "u20NebikiKin7","u21NebikiTax7","u22NebikiKen8","u23NebikiKin8","u24NebikiTax8","u25NebikiKen9","u26NebikiKin9","u27NebikiTax9"
//    };
//add 2019/07/19 USI欒  #34 begin
    /** 売上明細検索の最古月 */
    public static final String URIAGE_MEISAI_MIN_MONTH = "201910";
//add 2019/07/19 USI欒  #34 end

}