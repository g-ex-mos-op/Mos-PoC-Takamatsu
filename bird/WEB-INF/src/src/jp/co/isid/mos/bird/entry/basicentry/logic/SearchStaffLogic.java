/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.basicentry.logic;

import java.util.Map;

import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;

/**
 * スタッフ一覧の検索ロジックインターフェース
 * @author kusama
 */
public interface SearchStaffLogic {

    /**
     * スタッフ一覧の検索を行う
     * @param hanyoRegistDto
     */
    public Map execute(BasicEntryDto basicEntryDto);
}
