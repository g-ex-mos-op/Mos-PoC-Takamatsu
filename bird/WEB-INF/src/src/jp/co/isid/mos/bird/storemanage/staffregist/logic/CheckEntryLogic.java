/*
 * 作成日:2007/01/16
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic;


/**
 * エントリー状況チェックロジック
 * @author inazawa
 *
 */
public interface CheckEntryLogic {

    /**
     * エントリー状況チェックロジック
     * @param システム日付
     * @param スタッフ情報
     */
    public void execute(String sysDate ,String staffId);
}