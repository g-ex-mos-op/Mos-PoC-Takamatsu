/*
 * 作成日:2007/01/16
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic;

import jp.co.isid.mos.bird.entry.ownerchange.entity.MstOwnerInfo;

/**
 * オーナー情報取得ロジック
 * @author xkonishi
 *
 */
public interface GetOwnerInfoLogic {
    
    /**
     * オーナー情報取得ロジック
     * @param 会社コード
     * @param 条件区分
     * @param 店コード
     * @param オーナーコード
     * @return オーナー情報
     */
    public MstOwnerInfo execute(String companyCd, String kbn, String miseCd, String onerCd);
}