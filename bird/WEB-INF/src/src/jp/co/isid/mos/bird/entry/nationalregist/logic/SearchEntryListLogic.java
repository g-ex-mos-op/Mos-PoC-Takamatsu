package jp.co.isid.mos.bird.entry.nationalregist.logic;

import java.util.List;

/**
 * 全国大会マスタ情報リスト取得ロジック インターフェース
 *
 * @author xjung
 */
public interface SearchEntryListLogic {

    /**
     * 全国大会マスタ情報リストを取得する。
     * @param  sysDate  システム日付
     * @return 全国大会マスタ情報リスト
     */
    public List execute(String sysDate);

}