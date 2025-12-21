package jp.co.isid.mos.bird.entry.basicentry.logic;

import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;

/**
 * エントリーマスタ管理、エントリー日付管理の更新ロジックインターフェース
 * @author kusama
 */
public interface UpdateEntryInfoLogic {
    /**
     * エントリーマスタ管理、エントリー日付管理の更新を行う
     * @param hanyoRegistDto
     */
    public void execute(BasicEntryDto basicEntryDto);
}
