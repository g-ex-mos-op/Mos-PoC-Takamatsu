/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.logic;

import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;

/**
 * 登録内容チェックロジックインターフェース
 * @author itamoto
 */
public interface CheckOfferLogic {

    /**
     * 登録内容のチェックを行う。
     * @param longserviceOfferDto
     * @param sysDate
     */
    public void execute(LongserviceOfferDto longserviceOfferDto,String sysDate,int mode);
}
