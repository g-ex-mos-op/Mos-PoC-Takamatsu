package jp.co.isid.mos.bird.entry.mlentry.logic;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;

/**
 * 登録内容チェックロジックインターフェース
 * @author Aspac
 */
public interface CheckEntryLogic {

    /**
     * 登録内容のチェックを行う。
     * @param hanyoRegistDto
     */
    public List execute(MlEntryDto dto);
}
