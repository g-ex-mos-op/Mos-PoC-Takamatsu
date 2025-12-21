/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.hanyoregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoregist.dto.HanyoRegistDto;

/**
 * エントリーマスタ管理の更新ロジックインターフェース
 * @author itamoto
 */
public interface UpdateUserLogic {

    /**
     * エントリーマスタ管理・エントリー日付管理の更新を行う
     * @param hanyoRegistDto
     */
    public List execute(HanyoRegistDto hanyoRegistDto);
}
