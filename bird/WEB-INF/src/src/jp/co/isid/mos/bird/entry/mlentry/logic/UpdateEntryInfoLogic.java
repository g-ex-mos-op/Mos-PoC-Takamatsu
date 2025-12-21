package jp.co.isid.mos.bird.entry.mlentry.logic;

import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;

/**
 * エントリーマスタ管理、エントリー日付管理の更新ロジックインターフェース
 * @author Aspac
 */
public interface UpdateEntryInfoLogic {
    /**
     * エントリーマスタ管理、エントリー日付管理の更新を行う
     * @param hanyoRegistDto
     */
    public void execute(MlEntryDto mlEntryDto);
}
