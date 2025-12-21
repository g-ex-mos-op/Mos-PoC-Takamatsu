package jp.co.isid.mos.bird.entry.mlentry.logic;

import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;

/**
 * マスターライセンス情報の検索ロジックインターフェース
 * @author Aspac
 */
public interface SearchEntryLogic {

    /**
     * マスターライセンスエントリー情報の検索を行う
     * @param hanyoRegistDto
     */
    public void execute(MlEntryDto mlEntryDto);
}