/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic;

import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;

/**
 * エントリーマスタ管理の検索ロジックインターフェース
 * @author kusama
 */
public interface SearchEntryLogic {

    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto
     */
    public void execute(HanyoApplicationDto hanyoApplicationDto);
}
