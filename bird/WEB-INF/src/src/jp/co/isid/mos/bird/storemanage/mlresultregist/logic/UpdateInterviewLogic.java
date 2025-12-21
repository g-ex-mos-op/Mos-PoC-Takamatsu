/*
 * 作成日: 2006/07/27
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;

/**
 * 面接データ更新ロジック インターフェイス
 * @author xnkusama
 */
public interface UpdateInterviewLogic {
    /**
     * 面接データを更新
     * @exception ApplicationException
     */
    public void execute(MlResultRegistDto dto) throws ApplicationException;
}