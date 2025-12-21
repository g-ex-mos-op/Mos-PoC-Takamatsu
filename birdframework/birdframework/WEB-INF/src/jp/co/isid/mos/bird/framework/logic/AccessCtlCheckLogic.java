/*
 * 作成日: 2006/02/06
 *
 */
package jp.co.isid.mos.bird.framework.logic;

import java.util.List;

/**
 * メニュー権限チェック
 * @author xytamura
 */
public interface AccessCtlCheckLogic {
    /**
     * メニュー権限チェック
     * @param userId ユーザＩＤ
     * @param pgmId 機能ＩＤ
     */
    public abstract List execute(final String userId, final String pgmId);

    /**
     * アクセス可能なメニューの有無を判定する。
     * @param userId ユーザＩＤ
     * @param pgmId  機能ＩＤ
     * @return アクセス可能メニュー有無(true:有、false:無)
     */
    public abstract boolean isAccessMenu(final String userId, final String pgmId);
}