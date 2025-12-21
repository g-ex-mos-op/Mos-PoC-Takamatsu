/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.logic;

import java.util.List;

/**
 * 研修(出張/更新)エントリー者一覧取得ロジックインターフェース
 * @author Nakajima
 */
public interface GetHanyoEntryListLogic {

    public List execute(String sysdate, String entryCd, String entryYear, String entryKai, String kbn);

}
