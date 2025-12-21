/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic;

import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;

/**
 * 研修エントリー情報の検索ロジックインターフェース
 * @author kusama
 */
public interface SearchEntryInfoLogic {

    /**
     * 研修エントリー情報の検索を行う
     * @param hanyoRegistDto
     */
    public void execute(HanyoApplicationDto hanyoApplicationDto);
}