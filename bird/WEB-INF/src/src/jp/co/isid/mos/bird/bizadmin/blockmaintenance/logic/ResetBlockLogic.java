package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic;

import java.util.Map;

/**
 * ブロックメンテナンス
 * 新ブロックリセットロジックインターフェース
 * 
 * 店舗ブロック情報の新ブロックのデータを全て空にします。
 * 
 * @author xkinu
 *
 */
public interface ResetBlockLogic {
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = MoveBlockLogic.PK_USERINFO;
    /* パラメーターキー：更新対象データ保持List */
    public static final String PK_LIST_REG = MoveBlockLogic.PK_LIST_REG;

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public void execute(Map params);
}
