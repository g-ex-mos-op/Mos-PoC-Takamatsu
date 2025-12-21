/*
 * 作成日:2007/01/16
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic;

import jp.co.isid.mos.bird.entry.ownerchange.entity.MstStaffInfo;

/**
 * エントリー状況チェックロジック
 * @author xkonishi
 *
 */
public interface CheckEntryLogic {

    /**
     * エントリー状況チェックロジック
     * @param システム日付
     * @param スタッフ情報
     */
    public void execute(String sysDate, MstStaffInfo mstStaffInfo);
}