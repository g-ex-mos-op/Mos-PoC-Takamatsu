package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic;

import java.util.List;

/**
 * スタッフ一覧取得ロジック インターフェイス
 * @author xnkusama
 *
 */
public interface SearchOnerStaffLogic {

    public List execute(String companyCd, String onerCd);
}
