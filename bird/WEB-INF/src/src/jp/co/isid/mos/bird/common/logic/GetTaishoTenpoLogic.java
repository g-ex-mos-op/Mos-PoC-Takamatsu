package jp.co.isid.mos.bird.common.logic;

import java.util.List;
import java.util.Map;
/**
 * 対象店舗取得ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface GetTaishoTenpoLogic {
    /** 
     * パラメーターKey：システム日付 
     */
    public static final String PK_SYSDATE = "sysDate";
    /** 
     * パラメーターKey：会社コード
     */
    public static final String PK_COMPANY_CD = "companyCd";
    /** 
     * パラメーターKey：オーナーコード
     */
    public static final String PK_ONER_CD = "onerCd";
    /** 
     * パラメーターKey：対象店舗検索タイプ
     */
    public static final String PK_SELECT_TYPE = "selectType";
    /** 
     * パラメーターKey：ソートタイプ
     */
    public static final String PK_SORT_TYPE = "sortType";
    /**
     * リターンKey：対象条件情報
     */
    public static final String RK_LIST_TAISHOTENPO= "listTaishoTenpo";
    /** 
     * 対象店舗検索タイプ：クローズ店を含む（デフォルト)
     */
    public static final String SELECT_TYPE_CLOSE_IN = "1";
    /** 
     * 対象店舗検索タイプ：クローズ店を含まない
     */
    public static final String SELECT_TYPE_CLOSE_NOT_IN = "2";
    /** 
     * ソートタイプ：オープン中＆クローズ中で分けた店舗コード順（デフォルト)
     */
    public static final String SORT_TYPE_OPENCLOSEMISE = "1";
    /** 
     * ソートタイプ：店舗コード順
     */
    public static final String SORT_TYPE_MISE = "2";

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public List execute(Map params);

}
