package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic;

import java.util.List;

/**
 * スタッフ一覧取得ロジック インターフェイス
 * ※モード１ (ML受験申込)対応
 * @author Aspac
 *
 */
public interface SearchOnerStaffByMlEntryLogic {

    public List execute(String companyCd, String onerCd, String entryYear, String entryKai);
}
