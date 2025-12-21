package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic;

import java.util.Map;

/**
 * ブロックメンテナンス
 * 選択店舗新ブロック設定ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface MoveBlockLogic {
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    /* パラメーターキー：登録対象データ保持List */
    public static final String PK_LIST_REG = "regList";
    /* パラメーターキー：移動対象ブロックコード */
    public static final String PK_MOVE_BLOCK_CD = "blockCd";
    /* パラメーターキー：移動対象ブロック名称 */
    public static final String PK_MOVE_BLOCK_NAME = "blockName";
    /* パラメーターキー：移動対象ブロック画面変更フラグ */
    public static final String PK_MOVE_BLOCK_CHANGE_FLG = "changeFlg";
    /* リターン値キー：移動対象店舗先頭インデックス */
    public static final String RK_FORCUS_INDEX = "focusIndex";

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public Map execute(Map params);
}
