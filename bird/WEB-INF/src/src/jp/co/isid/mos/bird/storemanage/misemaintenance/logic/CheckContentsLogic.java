/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dto.MiseMaintenanceDto;

/**
 * 登録内容チェックロジック インターフェイス
 * @author xnkusama
 */
public interface CheckContentsLogic {
    /**
     * 登録内容のチェックを行う
     * @param MiseMaintenanceDto 登録内容
     * @exception ApplicationException
     */
    public void execute(MiseMaintenanceDto dto) throws ApplicationException;
}