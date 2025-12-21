/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic;

/**
 * 登録前オーナーチェック
 * @author xkonishi
 *
 */
public interface CheckRegistOwnerLogic {

    /**
     * 登録前オーナーチェック
     * @param オーナーコード
     * @param 更新後オーナーコード
     */
    public void execute(String oldOnerCd, String newOnerCd, String moveDt);
}