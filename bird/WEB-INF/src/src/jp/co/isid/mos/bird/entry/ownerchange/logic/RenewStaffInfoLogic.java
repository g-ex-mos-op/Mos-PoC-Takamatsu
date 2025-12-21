/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic;

import jp.co.isid.mos.bird.entry.ownerchange.entity.MstStaffInfo;
/**
 * 加盟店スタッフマスタ更新ロジック
 * @author xkonishi
 *
 */
public interface RenewStaffInfoLogic {

    /**
     * 加盟店スタッフマスタ更新ロジック
     * @param 更新後オーナーコード
     * @param 更新後店コード
     * @param 移動日
     * @param ユーザーID
     * @param スタッフ情報
     */
    public void execute(String newOnerCd, String newMiseCd, String moveDt, 
                         String userId, MstStaffInfo mstStaffInfo);
}