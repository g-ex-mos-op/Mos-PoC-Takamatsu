package jp.co.isid.mos.bird.entry.manageregist.logic;

import java.util.Map;

/**
 * 全国店長勉強会マスタ情報取得ロジック インターフェース
 *
 * @author xjung
 */
public interface SearchEntryLogic {

    /**
     * 全国店長勉強会マスタ情報を取得する。
     * @param  entryCd      エントリーコード
     * @param  entryYear    エントリー年
     * @param  entryKai     エントリー回
     * @param dateSize      日付サイズ
     * @param selectionSize セレクションサイズ
     * @return エントリー日付情報リスト、エントリーセレクション情報リスト
     */
    public Map execute(
        String entryCd,
        String entryYear,
        String entryKai,
        int dateSize,
        int selectionSize);
}
