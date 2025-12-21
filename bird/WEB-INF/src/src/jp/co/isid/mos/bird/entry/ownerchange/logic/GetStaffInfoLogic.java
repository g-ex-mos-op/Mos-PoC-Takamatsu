/*
 * 作成日:2007/01/16
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic;

import java.util.List;

/**
 * スタッフ情報取得ロジック
 * @author xkonishi
 *
 */
public interface GetStaffInfoLogic {

    /**
     * スタッフ情報取得ロジック
     * @param 会社コード
     * @param 条件区分
     * @param 店コード
     * @param オーナーコード
     * @param システム日付
     * @return スタッフ情報
     */
    public List execute(String companyCd, String kbn, String miseCd, String onerCd, String sysDate);
}