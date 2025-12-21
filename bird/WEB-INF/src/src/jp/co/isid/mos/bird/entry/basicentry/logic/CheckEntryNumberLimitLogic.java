/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.basicentry.logic;

import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;

/**
 * 申込可能チェック ロジックインターフェース
 * @author kusama
 */
public interface CheckEntryNumberLimitLogic {

    /**
     *申込可能チェックを行う。
     * @param hanyoRegistDto
     * @return boolean 
     */
    public void execute(BasicEntryDto dto, int mode);
}
