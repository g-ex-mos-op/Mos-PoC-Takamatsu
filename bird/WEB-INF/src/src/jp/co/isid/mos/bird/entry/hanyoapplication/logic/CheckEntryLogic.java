/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;

/**
 * 登録内容チェックロジックインターフェース
 * @author kusama
 */
public interface CheckEntryLogic {

    /**
     * 登録内容のチェックを行う。
     * @param hanyoRegistDto
     */
    public List execute(HanyoApplicationDto dto);
}
