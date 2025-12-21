package jp.co.isid.mos.bird.bizreport.camprank.common;

/**
 * キャンペーンベスト１００定数クラス
 * @author xnkusama
 *
 */
public class CampRankConst {

    /*****************
    * 検索条件：順位 *
    *****************/
    // 金額構成比
    public static final String COND_RANK_KINGAKUKOSEI = "1";
    // 個数
    public static final String COND_RANK_KOSU = "2";
    // 前年比
    public static final String COND_RANK_ZENNENHI = "3";
    // 金額構成比名称
    public static final String COND_RANK_KINGAKUKOSEI_NAME = "金額構成比";
    // 個数名称
    public static final String COND_RANK_KOSU_NAME = "個数";
    // 前年比名称
    public static final String COND_RANK_ZENNENHI_NAME = "前年比";
    
    /*************
    * 検索モード *
    *************/
    // 対象日
    public static final String SEARCH_MODE_TAISHOBI = "1";
    // 期間合計
    public static final String SEARCH_MODE_KIKAN = "2";
    
    /**************************
    * 検索ロジック 戻り値定数 *
    **************************/
    // 対象店舗数
    public static final String SEARCH_LOGIC_RETURN_TENPO_COUNT = "TENPO_COUNT";
    // ベスト１００情報
    public static final String SEARCH_LOGIC_RETURN_RANK_DATA = "RANK_DATA";
    
    /*****************************
    * キャンペーン一覧取得用定数 *
    *****************************/
    // ソート順
    public static final int CAMP_LIST_SORT = 2;
    public static final int CAMP_LIST_SORT_CAMP_FROM = 1;
}
