/*
 * 作成日: 2006/05/31
 */
package jp.co.isid.mos.bird.entry.hanyoregist.logic;

import java.util.Map;


/**
 * エントリーマスタ管理の検索ロジックインターフェース
 * 指定したコード、年、回の情報を取得
 * @author kusama
 */
public interface SearchEntryInfoLogic {

    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto
     */
    public Map execute(String code, String year, String kai);
}
