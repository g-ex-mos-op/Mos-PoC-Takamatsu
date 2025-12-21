/*
 * 作成日: 2006/07/27
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;

/**
 * 面接入力チェックロジック インターフェイス
 * @author xnkusama
 */
public interface CheckInterviewLogic {
    /**
     * 面接入力チェック
     * @return void
     * @exception ApplicationException
     */
    public void execute(MlResultRegistDto dto) throws ApplicationException;
}