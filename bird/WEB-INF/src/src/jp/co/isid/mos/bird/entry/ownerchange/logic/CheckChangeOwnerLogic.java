/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic;

import java.util.List;

/**
 * 変更後オーナーチェック
 * @author xkonishi
 *
 */
public interface CheckChangeOwnerLogic {

    /**
     * 変更後オーナーチェック
     * @param 店舗情報
     */
    public boolean execute(List miseInfo);
}
