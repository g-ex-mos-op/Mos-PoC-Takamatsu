/*
 * 作成日: 2006/5/31
 */
package jp.co.isid.mos.bird.entry.basicregist.logic;

import jp.co.isid.mos.bird.entry.basicregist.dto.BasicRegistDto;

/**
 * エントリーマスタ管理の検索ロジックインターフェース
 * @author xyuchida
 */
public interface SearchEntryListLogic {

    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param basicRegistDto ベーシック研修マスタ登録DTO
     */
    public void execute(BasicRegistDto basicRegistDto);
}
