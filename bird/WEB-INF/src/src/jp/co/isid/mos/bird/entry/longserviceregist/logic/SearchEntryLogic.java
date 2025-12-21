/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;

/**
 * エントリーマスタ管理の検索ロジックインターフェース
 * @author itamoto
 */
public interface SearchEntryLogic {

    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto
     */
    public List execute(LongserviceRegistDto longserviceRegistDto);
    
    /**
     * デフォルトのラジオボタン番号の取得
     * @param i チェックを付けるラジオボタン番号
     */
    public int getDefSelectIndex(List mstInfoList,String sysDate);
}
