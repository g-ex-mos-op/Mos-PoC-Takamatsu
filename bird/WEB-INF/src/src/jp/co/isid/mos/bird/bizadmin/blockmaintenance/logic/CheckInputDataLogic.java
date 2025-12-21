package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic;

import java.util.Map;

/**
 * ブロックメンテナンス
 * 入力値のチェック ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface CheckInputDataLogic {
    /* パラメーターキー：更新対象データ保持List */
    public static final String PK_LIST_REG = "regList";

    /**
     * 実行処理
     * 
     * @param params
     */
    public void execute(Map params);
}
