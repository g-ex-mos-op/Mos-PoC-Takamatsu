package jp.co.isid.mos.bird.entry.mlentry.logic;

import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryState;

/**
 * エントリー状況取得ロジック
 * @author Aspac
 */
public interface GetEntryInfoLogic {

    public UIEntryState execute(String entryYear, String entryKai, String staffId);
    
}
