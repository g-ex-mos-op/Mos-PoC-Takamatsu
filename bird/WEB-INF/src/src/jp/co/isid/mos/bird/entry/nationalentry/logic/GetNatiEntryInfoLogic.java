/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.logic;

import java.util.Map;

import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;

/**
 * 申込情報取得ロジック
 * 
 * @author xlee
 */
public interface GetNatiEntryInfoLogic {

	/**
	 * 申込情報取得
	 * @param nationalEntryDto　全国大会申込ＤＴＯ情報
	 * @return map 申込情報
	 */
    public Map execute(NationalEntryDto nationalEntryDto);
}
