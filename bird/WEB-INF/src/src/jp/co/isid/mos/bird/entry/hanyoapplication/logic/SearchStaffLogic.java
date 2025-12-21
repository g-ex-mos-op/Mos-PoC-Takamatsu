/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic;

import java.util.Map;

import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;

/**
 * スタッフ一覧の検索ロジックインターフェース
 * @author kusama
 */
public interface SearchStaffLogic {

    /**
     * スタッフ一覧の検索を行う
     * @param hanyoRegistDto
     */
    public Map execute(HanyoApplicationDto hanyoApplicationDto);
}
