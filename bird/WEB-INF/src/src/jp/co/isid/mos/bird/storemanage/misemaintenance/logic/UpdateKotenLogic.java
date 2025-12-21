/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dto.MiseMaintenanceDto;

/**
 * 個店情報の更新ロジック インターフェイス
 * @author xnkusama
 */
public interface UpdateKotenLogic {
    /**
     * 個店情報の更新を行う
     * @param MiseMaintenanceDto 登録内容
     * @exception ApplicationException
     */
    public void execute(MiseMaintenanceDto dto) throws ApplicationException;
}