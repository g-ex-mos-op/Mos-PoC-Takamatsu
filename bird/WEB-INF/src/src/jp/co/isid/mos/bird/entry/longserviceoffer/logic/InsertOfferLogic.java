/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.logic;

import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferEntry;

/**
 * 登録内容チェックロジックインターフェース
 * @author itamoto
 */
public interface InsertOfferLogic {

    /**
     * 入力欄の追加を行う。
     * @param longserviceOfferDto
     */
    public UIOfferEntry getNewEntryData(LongserviceOfferDto longserviceOfferDto);
     
    /**
     * 入力欄の追加を行う。
     * @param longserviceOfferDto
     */
    public LongserviceOfferDto execute(LongserviceOfferDto longserviceOfferDto);
}
