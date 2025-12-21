package jp.co.isid.mos.bird.entry.mlentry.logic;

import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;

/**
 * 申込可能チェック ロジックインターフェース
 * @author Aspac
 */
public interface CheckEntryNumberLimitLogic {

    /**
     *申込可能チェックを行う。
     * @param hanyoRegistDto
     * @return boolean 
     */
    public void execute(MlEntryDto dto, int mode);
}
