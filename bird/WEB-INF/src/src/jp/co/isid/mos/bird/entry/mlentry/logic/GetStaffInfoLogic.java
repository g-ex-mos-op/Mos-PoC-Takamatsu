package jp.co.isid.mos.bird.entry.mlentry.logic;

import jp.co.isid.mos.bird.entry.mlentry.entity.UIStaff;

/**
 * スタッフ情報取得ロジック
 * @author Aspac
 */
public interface GetStaffInfoLogic {

    public UIStaff execute(String staffId);
    
}
