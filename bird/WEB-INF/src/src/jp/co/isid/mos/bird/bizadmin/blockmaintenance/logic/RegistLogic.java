package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic;

import java.util.Map;

/**
 * ブロックメンテナンス
 * データ登録ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface RegistLogic {
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    /* パラメーターキー：日付情報 */
    public static final String PK_DATEINFO = "dateInfo";
    /* パラメーターキー：更新対象データ保持List */
    public static final String PK_LIST_REG = "regList";

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public void execute(Map params);
}
