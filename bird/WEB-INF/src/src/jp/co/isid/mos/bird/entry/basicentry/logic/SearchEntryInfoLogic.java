/*
 * 作成日: 2006/06/19
 */
package jp.co.isid.mos.bird.entry.basicentry.logic;

import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;

/**
 * ベーシック研修情報の検索ロジックインターフェース
 * @author kusama
 */
public interface SearchEntryInfoLogic {

    /**
     * 研修エントリー情報の検索を行う
     * @param hanyoRegistDto
     */
    public void execute(BasicEntryDto basicEntryDto);
}