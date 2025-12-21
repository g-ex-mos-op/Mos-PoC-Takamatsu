/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceregist.logic;

import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;

/**
 * 登録内容チェックロジックインターフェース
 * @author itamoto
 */
public interface CheckEntryLogic {

    /**
     * 登録内容のチェックを行う。
     * @param hanyoRegistDto
     */
    public void execute(LongserviceRegistDto longserviceRegistDto);
}
