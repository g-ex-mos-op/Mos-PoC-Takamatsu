/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.hanyoregist.logic;

import jp.co.isid.mos.bird.entry.hanyoregist.dto.HanyoRegistDto;

/**
 * エントリーマスタ管理の検索ロジックインターフェース
 * @author itamoto
 */
public interface SearchEntryLogic {

    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto
     */
    public void execute(HanyoRegistDto hanyoRegistDto);
}
