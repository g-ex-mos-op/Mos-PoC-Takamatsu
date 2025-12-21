/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic;

import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;

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
    public void execute(HanyoApplicationDto dto, int mode);
}
