package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic;

import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity.MstStaff;

/**
 * 指定スタッフ情報取得ロジック インターフェイス
 * @author xnkusama
 *
 */
public interface SearchStaffInfoLogic {

    public MstStaff execute(String staffId);
}
