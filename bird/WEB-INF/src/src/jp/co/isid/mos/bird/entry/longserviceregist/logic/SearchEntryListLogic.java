/*
 * 作成日: 2006/05/31
 */
package jp.co.isid.mos.bird.entry.longserviceregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;


/**
 * エントリーマスタ管理の検索ロジックインターフェース
 * 指定したコード、年、回の情報を取得
 * @author kusama
 */
public interface SearchEntryListLogic {

    /**
     * 永年勤続マスタ情報リストを取得する。
     * @param  sysDate  システム日付
     * @return 永年勤続マスタ情報リスト
     */
    public List execute(String sysDate);
    
    /**
     * 永年勤続マスタ情報リストを取得する。
     * @param  dto  永年勤続マスタＤＴＯ
     * @return boolean  true:有り false:無し
     */
    public boolean getButtonFlg(LongserviceRegistDto dto);
}
