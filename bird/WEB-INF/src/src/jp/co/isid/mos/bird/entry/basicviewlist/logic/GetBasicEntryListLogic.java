/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.basicviewlist.logic;

import java.util.List;

/**
 * ベーシック研修エントリー者一覧取得ロジックインターフェース
 * @author Nakajima
 */
public interface GetBasicEntryListLogic {

    public List execute(String sysdate, String entryCd, String entryYear, String entryKai, String kbn);

}
