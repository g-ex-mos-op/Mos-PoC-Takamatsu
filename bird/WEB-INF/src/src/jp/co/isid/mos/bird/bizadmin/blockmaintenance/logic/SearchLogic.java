package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic;

import java.util.List;
import java.util.Map;

/**
 * ブロックメンテナンス
 * 検索結果の取得ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface SearchLogic {
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    /* パラメーターキー：会社コード */
    public static final String PK_COMPANY_CD = "compnayCd";
    /* パラメーターキー：支部コード */
    public static final String PK_SIBU_CD = "sibuCd";
    /* パラメーターキー：システム日付 */
    public static final String PK_SYSDATE = "sysDate";

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public List execute(Map params);
}
