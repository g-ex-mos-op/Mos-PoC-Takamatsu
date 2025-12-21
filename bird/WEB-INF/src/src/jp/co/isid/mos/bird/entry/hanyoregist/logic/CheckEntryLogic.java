/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.hanyoregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoregist.dto.HanyoRegistDto;

/**
 * 登録内容チェックロジックインターフェース
 * @author itamoto
 */
public interface CheckEntryLogic {

    /**
     * 登録内容のチェックを行う。
     * @param hanyoRegistDto
     */
    public List execute(HanyoRegistDto hanyoRegistDto);
}
