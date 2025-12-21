/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.logic;

import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;

/**
 * エントリーマスタ管理の更新ロジックインターフェース
 * @author itamoto
 */
public interface UpdateOfferLogic {

    /**
     * エントリーマスタ管理・エントリー日付管理の更新を行う
     * @param hanyoRegistDto
     */
    public int execute(LongserviceOfferDto longserviceOfferDto);
}
