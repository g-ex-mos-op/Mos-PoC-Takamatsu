package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic;

import java.util.Map;

/**
 * 条件項目情報の取得
 * ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface ConditionLogic {
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    /* リターンKey：会社リスト */
    public static final String RK_LIST_COMPANY = "listCompany";
    /* リターンKey：会社別支部情報 */
    public static final String RK_LIST_SIBU = "listSibu";
    /* リターンKey：ブロックリスト */
    public static final String RK_LIST_BLOCK = "listBlock";
    /* リターンKey：対象ブロックリスト */
    public static final String RK_LIST_DISPBLOCK = "listDispBlock";
    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public Map execute(Map params);
}
