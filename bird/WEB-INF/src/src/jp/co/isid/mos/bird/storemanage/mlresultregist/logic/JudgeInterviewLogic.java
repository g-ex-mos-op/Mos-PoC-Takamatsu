/*
 * 作成日: 2006/07/27
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;

/**
 * 面接判定ロジック インターフェイス
 * @author xnkusama
 */
public interface JudgeInterviewLogic {
    /**
     * 面接結果の判定
     * @return List
     * @exception ApplicationException
     */
    public void execute(MlResultRegistDto dto) throws ApplicationException;
}